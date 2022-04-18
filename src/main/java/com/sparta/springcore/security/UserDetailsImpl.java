package com.sparta.springcore.security;

import com.sparta.springcore.model.User;
import com.sparta.springcore.model.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return Collections.emptyList();

        // 로그인된 user 의 권한을 가져와서
        // 걔를 스프링 시큐리티의 규칙에 맞춰서 보내주기
        UserRoleEnum userRole = user.getRole();
        String authority = userRole.getAuthority();  //"ROLE_USER" or "ROLE_ADMIN"

        // Collection 거쳐서 보내주기
        // Collection 을 만들고 add
        SimpleGrantedAuthority simpleAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>(); //여러개가 될 수 있어서 복수형태
        authorities.add(simpleAuthority);

        return authorities;
    }
}