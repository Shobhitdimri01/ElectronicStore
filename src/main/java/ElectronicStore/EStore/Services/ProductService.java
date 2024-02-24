package ElectronicStore.EStore.Services;

import ElectronicStore.EStore.DataTransferObject.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    //create
    ProductDTO create(ProductDTO p);

    //Get all productID
    ArrayList<String> GetAllProductID();

    //update
   void updateUserImage(String prod_id,String imageName);

    //delete
    String delete(String id);

    //get single
    ProductDTO GetOneProduct(String id);

    //get all:Live Product
    List<ProductDTO>GetAllLiveProduct();

    //search product
    List<ProductDTO>Search(String subtitle);

}
