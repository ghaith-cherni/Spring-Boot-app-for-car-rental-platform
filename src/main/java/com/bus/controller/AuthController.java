package com.bus.controller;

import com.bus.JwtTokenProvider;
import com.bus.entity.Admin;
import com.bus.entity.Client;
import com.bus.entity.Owner;
import com.bus.request.AuthRequest;
import com.bus.response.AuthResponse;
import com.bus.service.AdminService;
import com.bus.service.ClientService;
import com.bus.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.stream.Collectors;


    @RestController
    @RequestMapping("/auth")
    public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private JwtTokenProvider jwtTokenProvider;
        @Autowired
        private ClientService clientService;
        @Autowired
        private OwnerService ownerService;
        @Autowired
        private AdminService adminService;

        @PostMapping("/sign-up/client")
        public ResponseEntity<?> registerClient(@RequestBody Client client) {
            clientService.saveClient(client);
            return ResponseEntity.ok("Client registered successfully");
        }

        @PostMapping("/sign-up/owner")
        public ResponseEntity<?> registerOwner(@RequestBody Owner owner) {
            ownerService.saveOwner(owner);
            return ResponseEntity.ok("Owner registered successfully");
        }

        @PostMapping("/sign-up/admin")
        public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
            adminService.saveAdmin(admin);
            return ResponseEntity.ok("Admin registered successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {            //accepts login credentials (username and password)
            Authentication authentication = authenticationManager.authenticate(    //authenticate the user based on the provided credentials using authenticationManager
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())     // creates a UsernamePasswordAuthenticationToken with the provided username and password, and passes it to the authenticate method of the AuthenticationManager
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.createToken(                                    //If authentication is successful, we generate a JWT token using the JwtTokenProvider:
                    authRequest.getUsername(), authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));   //If the authentication is successful, the AuthenticationManager returns an Authentication object. The controller then uses the JwtTokenProvider to generate a JWT token for the authenticated user. This token includes the user's username and roles.
            return ResponseEntity.ok(new AuthResponse(token));                            //The controller returns a ResponseEntity containing the JWT token to the client
        }


}
