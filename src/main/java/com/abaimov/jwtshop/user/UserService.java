package com.abaimov.jwtshop.user;

import com.abaimov.jwtshop.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserService(PasswordEncoder encoder, UserRepository userRepository, TokenService tokenService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public User getUserFromToken(String token) {
        String userEmail = tokenService.getClaimFromToken(token);
        return userRepository.findByEmail(userEmail);
    }
}