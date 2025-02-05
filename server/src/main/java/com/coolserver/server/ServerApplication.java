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
		u1.setEmail("yuzhiliu8@gmail.com");
		u1.setPassword("password");

		userRepo.save(u1);

		};
	}
}
