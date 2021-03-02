package nl.akker.springboot.backend.application.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "username must not be empty")
    private String username;
    @NotBlank(message = "password must not be empty")
    private String password;
}
