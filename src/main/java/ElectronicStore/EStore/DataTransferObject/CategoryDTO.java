package ElectronicStore.EStore.DataTransferObject;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

        @Id
        private String categoryId;

        @Field(name = "category_title")
        @NotBlank@Min(value = 3,message = "Title must be of minimum 4 character")
        private String title;

        @NotBlank(message = "description is required")
        @Field(name = "category_desc")
        private String description;

        @NotBlank(message = "cover image required")
        private String coverImage;
    }
