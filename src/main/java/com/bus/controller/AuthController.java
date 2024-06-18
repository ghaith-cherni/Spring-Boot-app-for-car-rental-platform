package com.bus.controller;

import com.bus.JwtTokenProvider;
import com.bus.entity.Client;
import com.bus.entity.Driver;
import com.bus.request.AuthRequest;
import com.bus.request.SignupAdminRequest;
import com.bus.service.AdminService;
import com.bus.service.ClientService;
import com.bus.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/sign-up/client")
    public ResponseEntity<?> registerClient(@RequestBody Client client) {
        try {
            clientService.saveClient(client);
            return ResponseEntity.ok("Client registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/sign-up/driver")
    public ResponseEntity<?> registerOwner(@RequestBody Driver driver) {
        try {
            driverService.saveDriver(driver);
            return ResponseEntity.ok("driver registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/sign-up/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody SignupAdminRequest admin) {
        try {
            adminService.saveAdmin(admin);
            return ResponseEntity.ok("Admin registered successfully");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {            //accepts login credentials (username and password)
        try {
            Authentication authentication = authenticationManager.authenticate(                 //authenticate the user based on the provided credentials using authenticationManager
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));  // creates a UsernamePasswordAuthenticationToken with the provided username and password, and passes it to the authenticate method of the AuthenticationManager

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.createToken(                                    //If authentication is successful, we generate a JWT token using the JwtTokenProvider:
                    authRequest.getUsername(), authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));   //If the authentication is successful, the AuthenticationManager returns an Authentication object. The controller then uses the JwtTokenProvider to generate a JWT token for the authenticated user. This token includes the user's username and roles.
            Map<String, String> response = new HashMap<>();
            response.put("token", token); // username + role
            System.out.println("token------- " + response);
            return ResponseEntity.ok(response);                            //The controller returns a ResponseEntity containing the JWT token to the client
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");

        }
    }

}
