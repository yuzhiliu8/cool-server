package com.coolserver.server;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coolserver.server.user.User;
import com.coolserver.server.user.UserRepository;
import com.coolserver.server.role.RoleType;
import com.coolserver.server.storage.StorageItem;
import com.coolserver.server.storage.StorageItemRepository;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepo, StorageItemRepository storageItemRepository){ return args -> {
		User u1 = new User();
		u1.setRoleType(RoleType.ADMIN);
		u1.setFirstName("Yuzhi");
		u1.setLastName("Liu");
		u1.setEmail("yuzhiliu8@gmail.com");
		u1.setPassword("password");

		userRepo.save(u1);

		System.out.println(u1.getId());
		StorageItem s1 = new StorageItem("file1", LocalDateTime.now(), 100, ".jpg", u1.getId());
		storageItemRepository.save(s1);
		
		StorageItem s2 = new StorageItem("file2", LocalDateTime.now(), 100, ".jpg", 2L);
		StorageItem s3 = new StorageItem("file3", LocalDateTime.now(), 100, ".jpg", u1.getId());
		storageItemRepository.save(s2);
		storageItemRepository.save(s3);
		};


	}
}
