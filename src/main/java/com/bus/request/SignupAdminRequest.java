package com.bus.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupAdminRequest {
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private String phoneNumber;
    private String password;
    private String username;
    private String role;
}

