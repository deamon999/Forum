package com.gmail.deamon999;

import com.gmail.deamon999.entities.CustomUser;
import com.gmail.deamon999.entities.Role;
import com.gmail.deamon999.servicies.CustomUsersService;
import com.gmail.deamon999.servicies.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(final CustomUsersService customUsersService, final RoleService roleService) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... strings) throws Exception {
//				Role admin = new Role("ADMIN");
//				Role user = new Role("USER");
//				roleService.addRole(admin);
//				roleService.addRole(user);
//				customUsersService.addUser(new CustomUser("admin", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", admin));
//				customUsersService.addUser(new CustomUser("user", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", user));
//
//			}
//		};
//	}
}
