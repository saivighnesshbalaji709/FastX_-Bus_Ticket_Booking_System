package com.hexaware.restapi.config;

import com.hexaware.fastx.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserInfoUserDetails implements UserDetails {

	private final User user;

    public UserInfoUserDetails(User user) {
        this.user = user;
    }

    public int getUserId() {
        return user.getUserId();  
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override 
    public boolean isAccountNonExpired() { 
        return true; 
    }
    
    @Override 
    public boolean isAccountNonLocked() { 
    	return true; 
    }
    
    @Override 
    public boolean isCredentialsNonExpired() { 
        return true; 
    }
    
    @Override 
    public boolean isEnabled() { 
        return true; 
    }
}