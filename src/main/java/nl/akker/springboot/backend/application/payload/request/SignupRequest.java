package nl.akker.springboot.backend.application.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

    @NotBlank(message = "username must not be empty")
    @Size(min = 3, max = 20)
    private String userName;

    @NotBlank(message = "email must not be empty")
    @Size(min = 6, max = 50)
    @Email
    private String email;

    @NotBlank(message = "password must not be empty")
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
