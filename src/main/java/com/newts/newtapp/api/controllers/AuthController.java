package com.newts.newtapp.api.controllers;

import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.UserProfile;
import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.controllers.assemblers.UserProfileModelAssembler;
import com.newts.newtapp.api.controllers.forms.UserAuthForm;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.security.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private AuthenticationManager authManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserProfileModelAssembler profileAssembler;
    private UserManager userManager;

    public AuthController(AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil,
                          UserProfileModelAssembler profileAssembler, UserManager userManager) {
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.profileAssembler = profileAssembler;
        this.userManager = userManager;
    }

    /**
     * Attempts to authenticate a user and log them in.
     * @param form  A UserAuthForm containing user's credentials
     * @return      ResponseEntity containing whether the login was successful.
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserAuthForm form) throws UserNotFound {
        try {
            // Try to authenticate with provided credentials
            Authentication authenticate = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

            // We have authenticated the user, so we will send them a new JWT to authenticate them for a while!
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            RequestModel request = new RequestModel();
            request.fill(RequestField.USERNAME, userDetails.getUsername());
            UserProfile user = userManager.getProfileByUsername(request);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(profileAssembler.toModel(user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
