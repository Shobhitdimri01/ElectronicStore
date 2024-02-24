package ElectronicStore.EStore.Repositories;

import ElectronicStore.EStore.Documents.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category,String> {
}
