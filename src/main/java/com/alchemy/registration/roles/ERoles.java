package com.alchemy.registration.roles;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ERoles {
    USER(Sets.newHashSet(ApplicationUserPermission.USER_ADD)),
    MODERATOR(Sets.newHashSet(ApplicationUserPermission.USER_MODIFY)),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.USER_DELETE,ApplicationUserPermission.USER_MODIFY,ApplicationUserPermission.USER_ADD));

   private final Set<ApplicationUserPermission> permissions;

    ERoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }



}
