package ElectronicStore.EStore.Services.Implementations;

import ElectronicStore.EStore.DataTransferObject.UserDTO;
import ElectronicStore.EStore.Documents.User;
import ElectronicStore.EStore.Repositories.UserRepo;
import ElectronicStore.EStore.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo u_repo;
    @Autowired(required = true)
    private ModelMapper mapper;
    @Override
    public UserDTO createUser(UserDTO u) {
        u.setUser_Id(UUID.randomUUID().toString());
        User user = UserDTO_TO_User(u);
        System.out.println("user:"+user.getEmail()+" "+user.getName()+" "+user.getPassword()+" "+user.getGender());
        User save = u_repo.save(user);
        UserDTO user_dto = User_TO_UserDTO(save);
        return user_dto;
    }

    @Override
    public UserDTO updateUser(UserDTO u,String id) {
        User user = u_repo.findById(id).orElseThrow(()->new RuntimeException("User with id: "+id+" not found!!"));
        user.setName(u.getName());
        user.setGender(u.getGender());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        User update_user = u_repo.save(user);
        UserDTO user_dto=User_TO_UserDTO(update_user);
        return user_dto;
    }

    @Override
    public String deleteUser(String id) {
        //Get user
        User user = u_repo.findById(id).orElseThrow(()->new RuntimeException("Id not found in database !!"));
        u_repo.delete(user);
        return "user record with id : "+id+" is deleted";
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> All_users = u_repo.findAll();
        List<UserDTO> collect = All_users.stream().map(u -> User_TO_UserDTO(u)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = u_repo.findById(id).orElseThrow(() -> new RuntimeException("User with id : " + id + " doesn't exists!"));
        UserDTO u = User_TO_UserDTO(user);
        return u;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = u_repo.findByEmail(email);
        UserDTO u = User_TO_UserDTO(user);
        return u;
    }

    @Override
    public List<UserDTO> searchUserByName(String name) {
        List<User> user = u_repo.findByNameContaining(name);
        List<UserDTO> u = user.stream().map(users->User_TO_UserDTO(users)).collect(Collectors.toList());
        return u;
    }

    public User UserDTO_TO_User(UserDTO u){
        //manual method to map object
//        User users = User.builder()
//                .user_Id(u.getUser_Id())
//                .user_email(u.getUser_email())
//                .user_gender(u.getUser_email())
//                .user_password(u.getUser_password())
//                .user_name(u.getUser_name())
//                .build();
        //automate method

        return mapper.map(u,User.class);
    }
    public UserDTO User_TO_UserDTO(User u){
//        UserDTO user_dto = UserDTO.builder()
//                .user_Id(u.getUser_Id())
//                .user_email(u.getUser_email())
//                .user_gender(u.getUser_email())
//                .user_password(u.getUser_password())
//                .user_name(u.getUser_name())
//                .build();
        return mapper.map(u, UserDTO.class);
    }
}
