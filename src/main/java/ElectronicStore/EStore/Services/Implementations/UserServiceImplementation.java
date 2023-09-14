package ElectronicStore.EStore.Services.Implementations;

import ElectronicStore.EStore.DataTransferObject.PageableResponse;
import ElectronicStore.EStore.DataTransferObject.UserDTO;
import ElectronicStore.EStore.Documents.User;
import ElectronicStore.EStore.Exceptions.CustomResourceNotFoundExceptions;
import ElectronicStore.EStore.Helper.Helper;
import ElectronicStore.EStore.Repositories.UserRepo;
import ElectronicStore.EStore.Services.UserService;
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
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo u_repo;
    @Autowired
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
        User user = u_repo.findById(id).orElseThrow(()->new CustomResourceNotFoundExceptions("User with id: "+id+" not found!!"));
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
        System.out.println("ID ---->"+id);
        User user = u_repo.findById(id).orElseThrow(()->new RuntimeException("Id not found in database !!"));
        u_repo.delete(user);
        return "user record with id : "+id+" is deleted";
    }

    @Override
    public PageableResponse<UserDTO> getAllUsers(int pageNumber, int pageSize, String sortby, String sortdir) {
        Sort sort = (sortdir.equalsIgnoreCase("desc")) ? (Sort.by(sortby).descending() ): (Sort.by(sortby).ascending());

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> All_users = u_repo.findAll(pageable);
        PageableResponse<UserDTO> pageableResponse = Helper.getPageableResponse(All_users, UserDTO.class);
        return pageableResponse;
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = u_repo.findById(id).orElseThrow(() -> new CustomResourceNotFoundExceptions("User with id : " + id + " doesn't exists!"));
        UserDTO u = User_TO_UserDTO(user);
        return u;
    }

    @Override
    public List<UserDTO> getUserByEmail(String email) {
        List<User> user = u_repo.findByEmail(email);
        List<UserDTO> u = user.stream().map(users->User_TO_UserDTO(users)).collect(Collectors.toList());
        return u;
    }

    @Override
    public List<UserDTO> searchUserByName(String name) {
        System.out.println("NAME:"+name);
            List<User> user = u_repo.findByNameContaining(name);
        List<UserDTO> u = user.stream().map(users->User_TO_UserDTO(users)).collect(Collectors.toList());
        return u;
    }

    public User UserDTO_TO_User(UserDTO u){
//        manual method to map object
//        User users = User.builder()
//                .user_Id(u.getUser_Id())
//                .email(u.getEmail())
//                .gender(u.getGender())
//                .password(u.getPassword())
//                .name(u.getName())
//                .build();
//        return users;
        //automate method

        return mapper.map(u,User.class);
    }
    public UserDTO User_TO_UserDTO(User u){
//        UserDTO user_dto = UserDTO.builder()
//                .user_Id(u.getUser_Id())
//                .email(u.getEmail())
//                .gender(u.getGender())
//                .password(u.getPassword())
//                .name(u.getName())
//                .build();
//        return user_dto;
        return mapper.map(u, UserDTO.class);
    }
}
