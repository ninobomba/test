package io.github.ninobomba.t4m.test.db;

import lombok.Data;

@Data
public class UserDto {

	private Long id;

	private String username;

	private String email;

	private String password;

	private String firstName;

	private String lastName;

}
