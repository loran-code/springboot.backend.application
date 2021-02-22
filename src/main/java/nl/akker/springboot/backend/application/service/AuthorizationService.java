package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ERole;
import nl.akker.springboot.backend.application.model.Employee;
import nl.akker.springboot.backend.application.model.Role;
import nl.akker.springboot.backend.application.payload.request.LoginRequest;
import nl.akker.springboot.backend.application.payload.request.SignupRequest;
import nl.akker.springboot.backend.application.payload.response.JwtResponse;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;


import nl.akker.springboot.backend.application.repository.EmployeeRepository;
import nl.akker.springboot.backend.application.repository.RoleRepository;
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

    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";

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

    /**
     *
     * Deze methode verwerkt de gebruiker die wil registreren. De username en e-mail worden gecheckt. Eventuele rollen
     * worden toegevoegd en de gebruiker wordt opgeslagen in de database.
     *
     * @param signUpRequest de payload signup-request met gebruikersnaam en wachtwoord.
     * @return een HTTP response met daarin een succesbericht.
     */
    public ResponseEntity<MessageResponse> registerEmployee(@Valid SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(employeeRepository.existsByUsername(signUpRequest.getUserName()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(employeeRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new employee account
        Employee employee = new Employee(signUpRequest.getUserName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleDescription(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
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

        return ResponseEntity.ok(new MessageResponse("New employee registered successfully!"));
    }

    /**
     * Deze methode controleert de ontvangen username en wachtwoord. Het gebruikt hiervoor de
     * AuthenticationManager. I.a.w. Spring security doet die allemaal voor ons.
     *
     * Wanneer de gebruikersnaam/wachtwoord combinatie niet klopt, wordt er een Runtime exception gegooid:
     * 401 Unauthorized. Deze wordt gegooid door
     * {@link nl.novi.stuivenberg.springboot.example.security.service.security.jwt.AuthEntryPointJwt}
     *
     *
     * @param loginRequest De payload met username en password.
     * @return een HTTP-response met daarin de JWT-token.
     */
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
                userDetails.getUserName(),
                userDetails.getEmail(),
                roles));
    }

}