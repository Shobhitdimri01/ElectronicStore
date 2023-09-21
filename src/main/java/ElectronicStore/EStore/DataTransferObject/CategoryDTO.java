package ElectronicStore.EStore.DataTransferObject;

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
        private String title;

        @Field(name = "category_desc")
        private String description;
        private String coverImage;
    }
