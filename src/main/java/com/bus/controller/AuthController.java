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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

public class AuthController {
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
        public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.createToken(authRequest.getUsername(), authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            return ResponseEntity.ok(new AuthResponse(token));
        }
    }

}
