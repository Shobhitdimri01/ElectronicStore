package ElectronicStore.EStore.Services.Implementations;

import ElectronicStore.EStore.DataTransferObject.CategoryDTO;
import ElectronicStore.EStore.DataTransferObject.ProductDTO;
import ElectronicStore.EStore.Documents.Category;
import ElectronicStore.EStore.Documents.Product;
import ElectronicStore.EStore.Exceptions.CustomResourceNotFoundExceptions;
import ElectronicStore.EStore.Repositories.ProductRepo;
import ElectronicStore.EStore.Services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepo product;
    @Autowired
    private ModelMapper m;


    @Override
    public ProductDTO create(ProductDTO p) {
        p.setProductId(UUID.randomUUID().toString());
        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now.toString());
//        Date date = new Date(now.toString());
//        p.setAddedDate(date);
        Product prod = ProductDTOtoProduct(p);
        Product save = product.save(prod);
        return ProducttoProductDTO(save);

    }

    @Override
    public void updateUserImage(String prod_id,String imageName) {
        Product prod = product.findById(prod_id).orElseThrow(()->new CustomResourceNotFoundExceptions("Product with id: "+prod_id+" not found!!"));
        prod.setProductImageName(imageName);
        product.save(prod);
    }

    @Override
    public String delete(String id) {
        String prodid = id;
        product.deleteById(id);
        return "Product with id - "+prodid+" has been deleted successfully.";

    }

    @Override
    public ProductDTO GetOneProduct(String id) {
       Product prod = product.findById(id).orElseThrow(()->new RuntimeException("Id not found!"));
       ProductDTO prodDto = ProducttoProductDTO(prod);
       return prodDto;
    }

    @Override
    public ArrayList<String>GetAllProductID(){
        List<Product> all = product.findAll();
        ArrayList<String>IDs=new ArrayList<>();
        for (Product i:all){
            String productId = i.getProductId();
            IDs.add(productId);
        }
        return IDs;
    }

    @Override
    public List<ProductDTO> GetAllLiveProduct() {
        List<Product>Prod = product.findByLiveTrue();
        List<ProductDTO>LiveProducts = new ArrayList<>();
        for (Product i:Prod){
            ProductDTO productDTO = ProducttoProductDTO(i);
            LiveProducts.add(productDTO);
        }
        return LiveProducts;

    }

    @Override
    public List<ProductDTO> Search(String subtitle) {
        return null;
    }

    public Product ProductDTOtoProduct(ProductDTO p){
        return m.map(p,Product.class);
    }

    public ProductDTO ProducttoProductDTO(Product p){
        return m.map(p,ProductDTO.class);
    }
}
