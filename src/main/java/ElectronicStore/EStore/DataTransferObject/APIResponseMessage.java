package ElectronicStore.EStore.DataTransferObject;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponseMessage {
    private List<?> message;
    private boolean success;
    private HttpStatus status;

}
