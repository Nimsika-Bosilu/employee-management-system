package edu.nimsika.ecom.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    private Long id;
    @NotBlank(message="username not provide")
    private String username;
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;
    @NotBlank(message = "not provide user role")
    private String user_role;
    @NotBlank(message = "Provide Activation Status")
    private boolean isActive;
}
