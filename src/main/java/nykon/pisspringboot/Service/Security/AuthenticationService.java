package nykon.pisspringboot.Service.Security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nykon.pisspringboot.Controller.Request.AuthenticationRequest;
import nykon.pisspringboot.Controller.Request.RegisterRequest;
import nykon.pisspringboot.Controller.Response.AuthenticationResponse;
import nykon.pisspringboot.DAO.Interface.Jpa.RoleRepository;
import nykon.pisspringboot.DAO.Interface.Jpa.UserRepository;
import nykon.pisspringboot.Model.Role;
import nykon.pisspringboot.Model.User;
import nykon.pisspringboot.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        log.info("Request for register");
        Role role = roleRepository.findByName("Guest").orElseThrow();
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Request for auth ");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
