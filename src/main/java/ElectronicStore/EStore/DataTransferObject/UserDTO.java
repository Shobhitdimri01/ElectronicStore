package ElectronicStore.EStore.DataTransferObject;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String user_Id;
    private String name;
    private String email;
    private String password;
    private String gender;
}
