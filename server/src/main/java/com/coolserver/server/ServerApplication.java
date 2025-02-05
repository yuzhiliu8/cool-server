package com.coolserver.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coolserver.server.user.User;
import com.coolserver.server.user.UserRepository;
import com.coolserver.server.user.UserService;
import com.coolserver.server.role.RoleType;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepo, UserService userService){ return args -> {
			User u1 = new User();
			u1.setRoleType(RoleType.ADMIN);
			u1.setFirstName("Yuzhi");
			u1.setLastName("Liu");
			u1.setEmail("yliu08@gmail.com");
			u1.setPassword("password");
			u1.setSalt("salt");
			userRepo.save(u1);

			String salt = userService.generateSalt();
			String hp = userService.getHashedPassword("password", salt);
			System.out.println(salt);
			System.out.println(hp);
		};
	}
}
