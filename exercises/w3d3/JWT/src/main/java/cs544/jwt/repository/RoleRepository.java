package cs544.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.jwt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
