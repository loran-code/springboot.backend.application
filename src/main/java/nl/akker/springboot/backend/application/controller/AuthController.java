package nl.akker.springboot.backend.application.controller;

import lombok.AllArgsConstructor;
import nl.akker.springboot.backend.application.payload.request.LoginRequest;
import nl.akker.springboot.backend.application.payload.request.SignupRequest;
import nl.akker.springboot.backend.application.payload.response.JwtResponse;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import nl.akker.springboot.backend.application.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthorizationService authorizationService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> authenticateEmployee(@Valid @RequestBody LoginRequest loginRequest) {
        return authorizationService.authenticateEmployee(loginRequest);
    }

    @PostMapping("register")
    public ResponseEntity<MessageResponse> registerEmployee(@Valid @RequestBody SignupRequest signUpRequest) {
        return authorizationService.registerEmployee(signUpRequest);
    }
}
