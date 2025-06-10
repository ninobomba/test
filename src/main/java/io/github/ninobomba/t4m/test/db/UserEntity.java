package io.github.ninobomba.t4m.test.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table ( name = "USERS", schema = "demo" )
public class UserEntity {

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "id", nullable = false )
	private Long id;

	@Column ( name = "username", nullable = false, length = 50 )
	private String username;

	@Column ( name = "email", nullable = false, length = 100 )
	private String email;

	@Column ( name = "password", nullable = false )
	private String password;

	@Column ( name = "first_name", length = 50 )
	private String firstName;

	@Column ( name = "last_name", length = 50 )
	private String lastName;

	@CreationTimestamp
	@Column ( name = "created_at", nullable = false, updatable = false )
	private Instant createdAt;

	@UpdateTimestamp
	@Column ( name = "updated_at", nullable = false )
	private Instant updatedAt;

	@ColumnDefault ( "1" )
	@Column ( name = "active" )
	private Boolean active;

}
