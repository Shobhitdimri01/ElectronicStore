package ElectronicStore.EStore.DataTransferObject;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    @Id
    private String productId;
    private String title;
    private String descriptions;

    private int price;
    private int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private String productImageName;



}
