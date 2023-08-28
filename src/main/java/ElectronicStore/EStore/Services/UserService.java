package ElectronicStore.EStore.Services;

import ElectronicStore.EStore.DataTransferObject.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    //creating user
    UserDTO createUser(UserDTO u);

    //update user
    UserDTO updateUser(UserDTO u,String id);
    //delete user by id
    String deleteUser(String id);
    //Get all users
    List<UserDTO>getAllUsers();
    //Get single user by id
    UserDTO getUserById(String id);
    //Get single user by email
    UserDTO getUserByEmail(String email);
    //search user
    List<UserDTO> searchUserByName(String name);
}
