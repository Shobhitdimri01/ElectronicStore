package ElectronicStore.EStore.DataTransferObject;

import ElectronicStore.EStore.CustomValidator.NameValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String user_Id;
    @NameValidator
    private String name;
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    private String gender;
}
