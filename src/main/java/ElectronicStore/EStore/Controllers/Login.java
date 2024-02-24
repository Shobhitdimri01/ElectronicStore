package ElectronicStore.EStore.Controllers;


import ElectronicStore.EStore.DataTransferObject.JWTResponse;
import ElectronicStore.EStore.DataTransferObject.LoginDetails;
import ElectronicStore.EStore.DataTransferObject.UserDTO;
import ElectronicStore.EStore.Exceptions.BadAPIRequest;
import ElectronicStore.EStore.Services.UserService;
import ElectronicStore.EStore.SpringSecurity.JwtHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
        @RequestMapping("/auth")
public class Login {

    @Autowired
            private UserDetailsService service;
    @Autowired
            private AuthenticationManager authenticationManager;
    @Autowired
            private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ModelMapper mapper;
//    Map<String,String>userDetails = new HashMap<String,String>(){{
//        put("shobhit", "abc");
//    }
//    };
//
//    Map<String, String>JWTToken = new LinkedHashMap<>();


    @PostMapping("/login")
    public ResponseEntity<JWTResponse>LoginAPI(@RequestBody LoginDetails details){
        System.out.println(details);
        this.doAuthenticate(details.getEmail(),details.getPassword());
        UserDetails userDetails = service.loadUserByUsername(details.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);
        UserDTO userDTO = mapper.map(userDetails,UserDTO.class);
        JWTResponse resp = JWTResponse.builder().jwtToken(token).user(userDTO).build();

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    private void doAuthenticate(String email,String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);

        try{
            authenticationManager.authenticate(authenticationToken);
        }
        catch (BadCredentialsException e){
            throw new BadAPIRequest("Invalid username or Password !");
        }
    }

}
