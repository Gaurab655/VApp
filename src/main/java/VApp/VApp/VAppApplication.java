package VApp.VApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class VAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VAppApplication.class, args);
	}

}
