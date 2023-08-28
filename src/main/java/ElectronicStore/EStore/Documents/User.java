package ElectronicStore.EStore.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;


//Document or Entities are used for database related operations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("users")
public class User {

    private String user_Id;
    @Field("name")
    private String name;
    @Field("email")
    private String email;
    @Field("password")
    private String password;
    @Field("gender")
    private String gender;

}
