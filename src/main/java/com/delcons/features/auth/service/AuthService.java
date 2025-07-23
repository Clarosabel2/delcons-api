package com.delcons.features.auth.service;

import com.delcons.features.auth.dto.request.LoginRequest;
import com.delcons.features.auth.dto.request.UserCreateDTO;
import com.delcons.features.auth.dto.request.UserCreateWithRoleDTO;
import com.delcons.features.auth.dto.response.TokenDTO;
import com.delcons.features.auth.dto.response.UserResponseDTO;
import com.delcons.features.user.model.User;
import com.delcons.features.user.model.UserRole;
import com.delcons.features.user.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            @Lazy AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public TokenDTO authenticationUser(LoginRequest loginRequest) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var userAuth = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((User) userAuth.getPrincipal());
        return new TokenDTO(token);
    }

    private void validateUserRegistration(UserCreateDTO request) {
        if (userRepository.existsUserByUsernameOrEmail(request.email(), request.username())) {
            throw new UsernameNotFoundException("Username or Email already in use");
        }
        if (!request.password().equals(request.password())) {
            throw new RuntimeException("Passwords don't match");
        }
    }

    public TokenDTO registerUser(UserCreateDTO request) {
        validateUserRegistration(request);
        User newUser = createUser(request, UserRole.USER);
        return new TokenDTO(tokenService.generateToken(newUser));
    }

    public UserResponseDTO registerUserByAdmin(UserCreateWithRoleDTO request) {
        validateUserRegistration(new UserCreateDTO(
                request.username(),
                request.email(),
                request.password(),
                request.confirmPassword()
        ));
        User newUser = createUser(request.toUserCreateDTO(), request.role());
        return new UserResponseDTO(newUser.getEmail(), newUser.getUsername(), newUser.getRol().name());
    }

    private User createUser(UserCreateDTO request, UserRole role) {
        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRol(role);
        return userRepository.save(newUser);
    }
}
