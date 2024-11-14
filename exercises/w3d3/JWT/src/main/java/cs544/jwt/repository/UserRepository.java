package cs544.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.jwt.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByUsername(String username);
}
