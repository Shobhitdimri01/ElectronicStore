package ElectronicStore.EStore.Controllers;


import ElectronicStore.EStore.DataTransferObject.APIResponseMessage;
import ElectronicStore.EStore.DataTransferObject.CategoryDTO;
import ElectronicStore.EStore.DataTransferObject.PageableResponse;
import ElectronicStore.EStore.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController{
    @Autowired
    private CategoryService categoryservice;

    @PostMapping("/create")
    public ResponseEntity<APIResponseMessage> CreateCategory(@RequestBody CategoryDTO c){
        CategoryDTO category =  categoryservice.createCategory(c);
        List<CategoryDTO>message = new ArrayList<>();
        message.add(category);
        return new ResponseEntity<>(APIResponseMessage.builder().message(message).status(HttpStatus.CREATED).success(true).build(),HttpStatus.CREATED);
    }

    @GetMapping("/get_all")
    public ResponseEntity<PageableResponse<CategoryDTO>>GetAllCategory(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortby",defaultValue = "name",required = false)String sortby,
            @RequestParam(value = "sortdir",defaultValue = "asc",required = false)String sortdir

    ){
        return new ResponseEntity<>(categoryservice.getAllCategory(pageNumber,pageSize,sortby,sortdir),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseMessage>GetCategoryById(@PathVariable String id){

        List<CategoryDTO>message = new ArrayList<>();
        message.add(categoryservice.getCategoryById(id));
        return new ResponseEntity<>(APIResponseMessage.builder().message(message).status(HttpStatus.FOUND).success(true).build(),HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponseMessage>UpdateCategory(@PathVariable String id,@RequestBody CategoryDTO c){
        List<CategoryDTO> category = new ArrayList<>();
        category.add(categoryservice.updateCategory(c,id));
        return new ResponseEntity<>(APIResponseMessage.builder().message(category).status(HttpStatus.OK).success(true).build(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponseMessage> DeleteCategoryById(@PathVariable String id){
        String delete = categoryservice.deleteCategoryById(id);
        List<String>data = new ArrayList<>();
        data.add(delete);
        return new ResponseEntity<>(APIResponseMessage.builder().message(data).status(HttpStatus.NO_CONTENT).success(true).build(),HttpStatus.NO_CONTENT);
    }

}
