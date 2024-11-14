package cs544.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.jwt.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
