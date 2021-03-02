package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.enums.ERole;
import nl.akker.springboot.backend.application.model.dbmodels.Employee;
import nl.akker.springboot.backend.application.model.dbmodels.Role;
import nl.akker.springboot.backend.application.payload.request.LoginRequest;
import nl.akker.springboot.backend.application.payload.request.SignupRequest;
import nl.akker.springboot.backend.application.payload.response.JwtResponse;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;


import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import nl.akker.springboot.backend.application.repository.RoleRepository;
import nl.akker.springboot.backend.application.service.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthorizationService {

    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role has not been found.";

    private EmployeeRepository employeeRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    public ResponseEntity<MessageResponse> registerEmployee(@Valid SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(employeeRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username has already been taken"));
        }

        if (Boolean.TRUE.equals(employeeRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use"));
        }

        // Create new employee account
        Employee employee = new Employee(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleDescription(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else { // Check for roles and assign the matching roles to the user account.
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByRoleDescription(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(adminRole);
                    }
                    case "mechanic" -> {
                        Role mechanicRole = roleRepository.findByRoleDescription(ERole.ROLE_MECHANIC)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(mechanicRole);
                    }
                    case "frontoffice" -> {
                        Role frontOfficeRole = roleRepository.findByRoleDescription(ERole.ROLE_FRONTOFFICE)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(frontOfficeRole);
                    }
                    case "backoffice" -> {
                        Role backOfficeRole = roleRepository.findByRoleDescription(ERole.ROLE_BACKOFFICE)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(backOfficeRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByRoleDescription(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(userRole);
                    }
                }
            });
        }

        employee.setRoles(roles);
        employee.setCreated(LocalDateTime.now());
        employee.setModified(LocalDateTime.now());
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponse("New employee has registered successfully"));
    }

    public ResponseEntity<JwtResponse> authenticateEmployee(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}