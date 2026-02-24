package com.mmt.resort;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mmt.resort.model.Admin;
import com.mmt.resort.repository.AdminRepository;

@SpringBootApplication
public class ResortBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResortBackendApplication.class, args);
	}
	
	 @Bean
	    CommandLineRunner initAdmin(AdminRepository repo, PasswordEncoder encoder) {
	        return args -> {
	            if (repo.findByEmail("admin@mmt.com").isEmpty()) {

	                Admin admin = new Admin();
	                admin.setEmail("admin@mmt.com");
	                admin.setPassword(encoder.encode("admin123"));
	                admin.setRole("ADMIN");

	                repo.save(admin);

	                System.out.println("Initial admin user created.");
	            }
	        };
	    }

}
