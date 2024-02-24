package ElectronicStore.EStore;

import ElectronicStore.EStore.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EStoreApplication implements CommandLineRunner {
	public static void main(String[] args) {

		SpringApplication.run(EStoreApplication.class, args);
		//username@gmail.com
		//abc

	}
	@Override
	public void run(String... args) throws Exception {
		//to print
	}
}
