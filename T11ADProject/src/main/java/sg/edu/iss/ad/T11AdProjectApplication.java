package sg.edu.iss.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.ad.utility.DbSeeding;

@SpringBootApplication
public class T11AdProjectApplication {

	@Autowired
	DbSeeding DbSeeding;
	public static void main(String[] args) {
		SpringApplication.run(T11AdProjectApplication.class, args);
		
	}
	
	
	@Bean
	CommandLineRunner runner() {
		return args->{
			//Seed data only done once
			//DbSeeding.seedDB1();
			//DbSeeding.seedDB2();
		};
	}
}
