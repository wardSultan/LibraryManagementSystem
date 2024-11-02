package com.example.LibraryManagementSystem.secur.Controller;

import com.example.LibraryManagementSystem.secur.Model.MyAppUserService;
import com.example.LibraryManagementSystem.secur.Security.AuthRequest;
import com.example.LibraryManagementSystem.secur.Security.AuthResponse;
import com.example.LibraryManagementSystem.secur.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ContentController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyAppUserService appUserService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final UserDetails userDetails = appUserService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
