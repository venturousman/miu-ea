package cs544.jwt.dto;

import java.time.LocalDate;

import cs544.jwt.model.Address;
import cs544.jwt.model.Patient;

public record PatientOutDto(
    Long id, 
    String firstName, 
    String lastName, 
    LocalDate dob, 
    Address address
    ) {

    public PatientOutDto(Patient patient) {
        this(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getDob(), patient.getAddress());
    }


    public Patient toPatient() {
        return new Patient(id, firstName, lastName, dob, address, null); 
    }
    
}
