package com.genill.usersprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersProfileApplication.class, args);
	}

	/*@Bean
	ApplicationRunner init(UsersService usersService) {
		return args -> {
			Stream.of(
					"javadevjournal:Java Dev Journal",
					"octocat:The Octocat",
					"guest:From Another Universe"
			).forEach( data -> {
				Users u = new Users();
				String[] fields = data.split(":");

			});
			usersService.getAllUsers().forEach(System.out::println);
		};
	}*/

}
