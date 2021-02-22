package nl.akker.springboot.backend.application.controller;

import nl.akker.springboot.backend.application.payload.request.LoginRequest;
import nl.akker.springboot.backend.application.payload.request.SignupRequest;
import nl.akker.springboot.backend.application.payload.response.JwtResponse;
import nl.akker.springboot.backend.application.payload.response.MessageResponse;
import nl.akker.springboot.backend.application.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateEmployee(@RequestBody LoginRequest loginRequest) {
        return authorizationService.authenticateEmployee(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerEmployee(@RequestBody SignupRequest signUpRequest) {
        return authorizationService.registerEmployee(signUpRequest);
    }

}
