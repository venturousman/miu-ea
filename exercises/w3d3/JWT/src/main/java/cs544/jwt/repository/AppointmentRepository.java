package cs544.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.jwt.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    
}
