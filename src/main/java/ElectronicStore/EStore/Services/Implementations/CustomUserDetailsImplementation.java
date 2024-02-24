package ElectronicStore.EStore.Services.Implementations;

import ElectronicStore.EStore.Documents.User;
import ElectronicStore.EStore.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsImplementation implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> byEmail = userRepository.findByEmail(username);
        if (byEmail.isEmpty()) new UsernameNotFoundException("user with given email:"+username+" not found!");
        return byEmail.get(0);
    }
}
