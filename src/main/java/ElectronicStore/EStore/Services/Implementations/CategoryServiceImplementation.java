package ElectronicStore.EStore.Services.Implementations;

import ElectronicStore.EStore.DataTransferObject.CategoryDTO;
import ElectronicStore.EStore.DataTransferObject.PageableResponse;
import ElectronicStore.EStore.Documents.Category;
import ElectronicStore.EStore.Exceptions.CustomResourceNotFoundExceptions;
import ElectronicStore.EStore.Helper.Helper;
import ElectronicStore.EStore.Repositories.CategoryRepo;
import ElectronicStore.EStore.Repositories.UserRepo;
import ElectronicStore.EStore.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService {


    @Autowired
    private CategoryRepo u;
    @Autowired
    private ModelMapper m;
    @Override
    public CategoryDTO createCategory(CategoryDTO c){
        c.setCategoryId(UUID.randomUUID().toString());
        Category ct = CategoryDTOtoCategory(c);
        Category save = u.save(ct);
        CategoryDTO cat = CategorytoCategoryDTO(save);
        return cat;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO c,String Id) {
        Category user = u.findById(Id).orElseThrow(() -> new CustomResourceNotFoundExceptions("User with Id : " + Id + " not found!"));
        user.setTitle(c.getTitle());
        user.setDescription(c.getDescription());
        user.setCoverImage(c.getCoverImage());
        u.save(user);
        CategoryDTO updated_user = CategorytoCategoryDTO(user);
        return updated_user;
    }

    @Override
    public String deleteCategoryById(String Id) {
        u.deleteById(Id);
        return "Record with Id : "+Id+" has been deleted";
    }

    @Override
    public PageableResponse<CategoryDTO> getAllCategory(int pageNumber, int pageSize, String sortby, String sortdir) {
        Sort sort = (sortdir.equalsIgnoreCase("desc")) ? (Sort.by(sortby).descending() ): (Sort.by(sortby).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Category> all = u.findAll(pageable);
        PageableResponse<CategoryDTO> save = Helper.getPageableResponse(all, CategoryDTO.class);
        return save;
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        Category user = u.findById(id).orElseThrow(()->new CustomResourceNotFoundExceptions("User with ID:"+id+"not found"));
        return CategorytoCategoryDTO(user);

    }

    public Category CategoryDTOtoCategory(CategoryDTO c){
        return m.map(c,Category.class);
    }

    public CategoryDTO CategorytoCategoryDTO(Category ct){
        return m.map(ct,CategoryDTO.class);
    }
}
