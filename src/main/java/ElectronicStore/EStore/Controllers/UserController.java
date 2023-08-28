package ElectronicStore.EStore.Controllers;

import ElectronicStore.EStore.DataTransferObject.APIResponseMessage;
import ElectronicStore.EStore.DataTransferObject.UserDTO;
import ElectronicStore.EStore.Services.UserService;
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
    public ResponseEntity<APIResponseMessage>Create(@RequestBody UserDTO u){
        List<UserDTO> message = new ArrayList<>();
        message.add(user_service.createUser(u));
        return new ResponseEntity<>(APIResponseMessage.builder().message(message.toString()).status(HttpStatus.CREATED).success(true).build(), HttpStatus.CREATED);
    }

    @GetMapping("/get_all")
    public ResponseEntity<APIResponseMessage>GetAllUsers(){
        return new ResponseEntity<>( APIResponseMessage.builder().message(user_service.getAllUsers().toString()).status(HttpStatus.CREATED).success(true).build(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponseMessage>UpdateUser(@RequestBody UserDTO u,@PathVariable String id){
        List<UserDTO> message = new ArrayList<>();
        message.add(user_service.updateUser(u,id));
        return new ResponseEntity<>(APIResponseMessage.builder().message(message.toString()).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponseMessage> DeleteUserByID(@PathVariable String id){
        return new ResponseEntity<>(APIResponseMessage.builder().message(user_service.deleteUser(id)).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponseMessage> getUserById(@PathVariable String id){
       return new ResponseEntity<>( APIResponseMessage.builder().message(String.valueOf(user_service.getUserById(id))).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);

    }
    //Get single user by email
    @GetMapping("/email")
    public ResponseEntity<APIResponseMessage> getUserByEmail(@RequestParam String email){
        return new ResponseEntity<>(APIResponseMessage.builder().message(String.valueOf(user_service.getUserByEmail(email))).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
    //search user

    @GetMapping("/name")
    public ResponseEntity<APIResponseMessage> searchUserByName(@RequestParam String name){
        return new ResponseEntity<>(APIResponseMessage.builder().message(String.valueOf(user_service.searchUserByName(name))).success(true).status(HttpStatus.ACCEPTED).success(true).build(),HttpStatus.ACCEPTED);
    }
}
