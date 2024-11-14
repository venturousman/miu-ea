package cs544.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.jwt.model.Surgery;

public interface SurgeryRepository extends JpaRepository<Surgery, Long>{
    
}
