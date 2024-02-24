package ElectronicStore.EStore.Repositories;

import ElectronicStore.EStore.Documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product,String> {
    List<Product> findByTitleContaining(String subtitle);
    List<Product> findByLiveTrue();
}
