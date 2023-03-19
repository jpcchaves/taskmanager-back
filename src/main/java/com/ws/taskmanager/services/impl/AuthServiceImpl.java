package com.ws.taskmanager.services.impl;

import com.ws.taskmanager.data.DTO.LoginDto;
import com.ws.taskmanager.data.DTO.RegisterDto;
import com.ws.taskmanager.exceptions.BadRequestException;
import com.ws.taskmanager.models.RoleModel;
import com.ws.taskmanager.models.UserModel;
import com.ws.taskmanager.repositories.RoleRepository;
import com.ws.taskmanager.repositories.UserRepository;
import com.ws.taskmanager.security.JwtTokenProvider;
import com.ws.taskmanager.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        // verify if user already registered
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BadRequestException("Já existe um usuário cadastrado com o usuário informado");
        }

        // check if email already registered
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException("Já existe um usuário cadastrado com o email informado");
        }

        UserModel user = new UserModel();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<RoleModel> roles = new HashSet<>();
        Optional<RoleModel> userRole = roleRepository.findByName("ROLE_USER");

        if (userRole.isPresent()) {
            roles.add(userRole.get());
            user.setRoles(roles);
        }

        userRepository.save(user);

        return "User registered successfully";
    }
}
