package cs544.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.jwt.model.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Long>{
    
}
