package io.github.ninobomba.t4m.test.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface IUserRepository extends JpaRepository < UserEntity, Serializable > {
}
