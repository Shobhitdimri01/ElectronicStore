package ElectronicStore.EStore.Controllers;

import ElectronicStore.EStore.DataTransferObject.APIResponseMessage;
import ElectronicStore.EStore.DataTransferObject.PageableResponse;
import ElectronicStore.EStore.DataTransferObject.UserDTO;
import ElectronicStore.EStore.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
    private UserService user_service;





//Create user
    @PostMapping("/create")
    public ResponseEntity<APIResponseMessage>Create(@Valid @RequestBody UserDTO u){
        List<UserDTO> message = new ArrayList<>();
        UserDTO user = user_service.createUser(u);
        message.add(user);
        return new ResponseEntity<>(APIResponseMessage.builder().message(message).status(HttpStatus.CREATED).success(true).build(), HttpStatus.CREATED);
    }

    @GetMapping("/get_all")
    public ResponseEntity<PageableResponse<UserDTO>>GetAllUsers(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortby",defaultValue = "name",required = false)String sortby,
            @RequestParam(value = "sortdir",defaultValue = "asc",required = false)String sortdir

    ){
        return new ResponseEntity<>(user_service.getAllUsers(pageNumber,pageSize,sortby,sortdir),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponseMessage>UpdateUser(@RequestBody UserDTO u,@PathVariable String id){
        List<UserDTO> message = new ArrayList<>();
        message.add(user_service.updateUser(u,id));
        return new ResponseEntity<>(APIResponseMessage.builder().message(message).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponseMessage> DeleteUserByID(@PathVariable String id){
        System.out.println("Controller id"+id);
        String s = user_service.deleteUser(id);
        UserDTO delete = new UserDTO();
        delete.setUser_Id(s);
        List<UserDTO>del = new ArrayList<>();
        del.add(delete);
        return new ResponseEntity<>(APIResponseMessage.builder().message(del).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponseMessage> getUserById(@PathVariable String id){
        System.out.println("ID:::"+id);
        UserDTO userById = user_service.getUserById(id);
        List<UserDTO>user = new ArrayList<>();
        user.add(userById);
        return new ResponseEntity<>( APIResponseMessage.builder().message(user).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);

    }
    //Get single user by email
    @GetMapping("/email")
    public ResponseEntity<APIResponseMessage> getUserByEmail(@RequestParam String email){
      List<UserDTO>message = user_service.getUserByEmail(email);
        return new ResponseEntity<>(APIResponseMessage.builder().message(message).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
    //search user

    @GetMapping("/name")
    public ResponseEntity<APIResponseMessage> searchUserByName(@RequestParam String name){
        List<UserDTO> message = user_service.searchUserByName(name);
        ArrayList mess  = new ArrayList<>();
        mess.add(message);
        return new ResponseEntity<>(APIResponseMessage.builder().message(mess).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
}
