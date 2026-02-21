package com.cja.inventory.security;

import java.util.Collection;
import java.util.List;
import com.cja.inventory.models.User;
import com.cja.inventory.models.UserBusiness;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private final User user;
    private final Long businessId;
    private final UserBusiness.Role role;

    public MyUserDetails(User user, Long businessId, UserBusiness.Role role) {
        this.user = user;
        this.businessId = businessId;
        this.role = role;
    }

    public Long getUserId() {
        return user.getId();
    }

    public Long getBusinessId() {
        return businessId;
    }

    public UserBusiness.Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return user.getActive(); }
}