package net.javaguides.springboot_restful_webservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "User DTO Model Information"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotEmpty(message = "User first name should not be null or empty.")
    @Schema(
            description = "User First Name"
    )
    private String firstName;
    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "User last name should not be null or empty.")
    private String lastName;
    @Schema(
            description = "User Email"
    )
    @NotEmpty(message = "User email should not be null or empty.")
    @Email(message = "Email address should be valid.")
    private String email;
}
