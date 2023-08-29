package ElectronicStore.EStore.Repositories;

import ElectronicStore.EStore.Documents.User;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
       List <User> findByEmail(String user_email);
        User findByEmailAndPassword(String user_name,String user_password);
        List<User> findByNameContaining(String user_name);

}
