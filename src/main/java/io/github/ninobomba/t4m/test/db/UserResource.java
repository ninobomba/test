package io.github.ninobomba.t4m.test.db;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ( "/users" )
@RequiredArgsConstructor
public class UserResource {

	private final IUserRepository IUserRepository;

	@GetMapping
	public ResponseEntity < List < UserDto > > getAll ( ) { // Changed return type to ResponseEntity<List<UserDto>>
		List < UserEntity > users = IUserRepository.findAll ( );
		List < UserDto > userDtos = users.stream ( )
				.map ( this :: convertToDto )
				.collect ( Collectors.toList ( ) );
		return ResponseEntity.ok ( userDtos );
	}

	private UserDto convertToDto ( UserEntity userEntity ) {
		UserDto dto = new UserDto ( );
		dto.setId ( userEntity.getId ( ) );
		dto.setUsername ( userEntity.getUsername ( ) );
		dto.setEmail ( userEntity.getEmail ( ) );
		dto.setFirstName ( userEntity.getFirstName ( ) );
		dto.setLastName ( userEntity.getLastName ( ) );
		return dto;
	}
}
