package ElectronicStore.EStore.Controllers;


import ElectronicStore.EStore.DataTransferObject.APIResponseMessage;
import ElectronicStore.EStore.DataTransferObject.ProductDTO;
import ElectronicStore.EStore.Services.FileService.FileService;
import ElectronicStore.EStore.Services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private FileService fileService;

    @Value("${product.image.path}")
    private String product_path;

    @PostMapping("/create")
    public ResponseEntity<APIResponseMessage>CreateProduct(@RequestBody ProductDTO p){
        ProductDTO prod = service.create(p);
        List prods = new ArrayList();
        prods.add(prod);
        return new ResponseEntity<>(APIResponseMessage.builder().message(prods).status(HttpStatus.CREATED).success(true).build(),HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponseMessage>GetProductByID(@PathVariable String id){
        ProductDTO prod = service.GetOneProduct(id);
        List prods =  new ArrayList();
        prods.add(prod);
        return new ResponseEntity<>(APIResponseMessage.builder().message(prods).success(true).status(HttpStatus.FOUND).build(),HttpStatus.FOUND);
    }

    @GetMapping("/get_all_ids")
    public ResponseEntity<APIResponseMessage>GetALLProductID(){
        ArrayList<String> productID = service.GetAllProductID();
        List Ids = new ArrayList<>();
        Ids.add(productID);
        return new ResponseEntity<>(APIResponseMessage.builder().message(Ids).success(true).status(HttpStatus.ACCEPTED).build(),HttpStatus.OK);
    }

    @GetMapping("/live_products")
    public ResponseEntity<APIResponseMessage>GetLiveProduct(){
        List<ProductDTO> productID = service.GetAllLiveProduct();
        List LiveProd = new ArrayList<>();
        LiveProd.add(productID);
        return new ResponseEntity<>(APIResponseMessage.builder().message(LiveProd).success(true).status(HttpStatus.ACCEPTED).build(),HttpStatus.OK);
    }

    @DeleteMapping("/delete_prod/{id}")
    public ResponseEntity<APIResponseMessage>DeleteProductbyID(@PathVariable String id){
        String msg = service.delete(id);
        List<String>Stats = new ArrayList<>();
        Stats.add(msg);
        return new ResponseEntity<>(APIResponseMessage.builder().message(Stats).success(true).status(HttpStatus.ACCEPTED).build(),HttpStatus.OK);
    }

    @PostMapping("/image/{product_id}")
    public ResponseEntity<APIResponseMessage> UploadImage(
            @PathVariable String product_id,
            @RequestParam("productImage")MultipartFile image
            ) throws IOException
    {
        String image_Name = fileService.UploadImage(image, product_path);
        List<String>images = new ArrayList<>();
        images.add(image_Name);
        service.updateUserImage(product_id,image_Name);
        return new ResponseEntity<>(APIResponseMessage.builder().message(images).status(HttpStatus.CREATED).success(true).build(),HttpStatus.CREATED);
    }

    @GetMapping("/image")
    public void GetImage(@RequestParam String imageid, HttpServletResponse response) throws IOException {
        InputStream image =  fileService.getResource(product_path,imageid);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(image,response.getOutputStream());
    }

}
