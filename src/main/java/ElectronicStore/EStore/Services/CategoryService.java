package ElectronicStore.EStore.Services;

import ElectronicStore.EStore.DataTransferObject.CategoryDTO;
import ElectronicStore.EStore.DataTransferObject.PageableResponse;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO c);
    CategoryDTO updateCategory(CategoryDTO c,String Id);
    String deleteCategoryById(String Id);
    PageableResponse<CategoryDTO> getAllCategory(int pageNumber, int pageSize, String sortby, String sortdir);
    CategoryDTO getCategoryById(String id);
}
