package com.bus;


import com.bus.entity.Role;
import com.bus.repository.AdminRepository;
import com.bus.repository.ClientRepository;
import com.bus.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;

        userDetails = driverRepository.findByUsername(username)
                .map(owner -> new org.springframework.security.core.userdetails.User(owner.getUsername(), owner.getPassword(),
                        getAuthorities(owner.getRole())))
                .orElse(null);

        if (userDetails == null) {
            userDetails = clientRepository.findByUsername(username)
                    .map(client -> new org.springframework.security.core.userdetails.User(client.getUsername(), client.getPassword(),
                            getAuthorities(client.getRole())))
                    .orElse(null);
        }

        if (userDetails == null) {
            userDetails = adminRepository.findByUsername(username)
                    .map(admin -> new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
                            getAuthorities(admin.getRole())))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        }

        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }
}

