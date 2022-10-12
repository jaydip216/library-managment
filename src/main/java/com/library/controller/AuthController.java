package com.library.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.entity.User;
import com.library.exceptions.type.ApplicationException;
import com.library.model.AuthRequest;
import com.library.model.AuthResponse;
import com.library.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            final User user = (User) authentication.getPrincipal();
            final String jwtToken = jwtUtil.generateAccessToken(user);
            final AuthResponse response = new AuthResponse(user.getUserName(), jwtToken);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new ApplicationException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
