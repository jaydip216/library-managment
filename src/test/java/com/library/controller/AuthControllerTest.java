package com.library.controller;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertThrows;
import java.util.Collections;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.library.entity.User;
import com.library.exceptions.type.ApplicationException;
import com.library.model.AuthRequest;
import com.library.util.JwtUtil;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void login() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(getUser(), new Object(),
                Collections.emptyList());
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(jwtUtil.generateAccessToken(Mockito.any())).thenReturn("jwtToken");
        assertNotNull(authController.login(getAuthRequest()));
    }

    @Test
    public void loginException() {

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

        assertThrows(ApplicationException.class, () -> authController.login(getAuthRequest()));
    }

    private User getUser() {
        User user = new User();
        user.setUserName("jaydip21");
        user.setPassword("jaydip#23");
        user.setEmail("jaydip@email.com");
        user.setFirstName("Jaydip");
        user.setLastName("Bhanderi");
        user.setPhoneNo("1562325981");
        return user;
    }

    private AuthRequest getAuthRequest() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUserName("jaydip21");
        authRequest.setPassword("jaydip#23");
        return authRequest;
    }
}
