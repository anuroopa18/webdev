package webdev.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import webdev.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	Optional<User> findUserByUsername(String username);
}
