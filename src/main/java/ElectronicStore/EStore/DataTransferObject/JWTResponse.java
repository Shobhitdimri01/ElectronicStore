package ElectronicStore.EStore.DataTransferObject;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JWTResponse {
    private String jwtToken;
    private UserDTO user;
}
