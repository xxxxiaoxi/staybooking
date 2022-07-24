package com.laioffer.staybooking.service;

import com.laioffer.staybooking.exception.UserNotExistException;
import com.laioffer.staybooking.model.Token;
import com.laioffer.staybooking.model.User;
import com.laioffer.staybooking.model.UserRole;
import com.laioffer.staybooking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public Token authenticate(User user, UserRole role) throws UserNotExistException {
        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (AuthenticationException exception) {
            throw new UserNotExistException("User Doesn't Exist");
        }
        //auth is null || auth不成功
        if (auth == null || !auth.isAuthenticated() ) {
            throw new UserNotExistException("User Doesn't Exist");
        }
        //权限不够
        if (!auth.getAuthorities().contains(new SimpleGrantedAuthority(role.name()))) {
            throw new UserNotExistException("role Doesn't match");
        }
        return new Token(jwtUtil.generateToken(user.getUsername()));
    }
}
