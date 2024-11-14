package cs544.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.jwt.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByOrderByAddressCityAsc();
    List<Patient> findByFirstNameContainingOrLastNameContainingOrAddressStreetContainingOrAddressCityContaining(String firstName, String lastName, String street, String city);   
}
