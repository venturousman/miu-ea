package cs544.jwt.dto;

import java.time.LocalDate;

import cs544.jwt.model.Address;
import cs544.jwt.model.Patient;
import jakarta.validation.constraints.NotBlank;

public record PatientInDto(
    Long id, 
    @NotBlank
    String firstName, 
    String lastName, 
    Long addressId, 
    @NotBlank
    String addressStreet, 
    @NotBlank
    String addressCity, 
    String addressState, 
    String addressZip, 
    LocalDate dob
    ) {
    
    public PatientInDto(Patient patient) {
        this(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getAddress().getId(), patient.getAddress().getStreet(), patient.getAddress().getCity(), patient.getAddress().getState(), patient.getAddress().getZip(), patient.getDob());
    }

    public Patient toPatient() {
        return new Patient(id, firstName, lastName, dob, new Address(null, addressStreet, addressCity, addressState, addressZip), null);
    }

    public void update(Patient patient) {
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDob(dob);
        patient.getAddress().setStreet(addressStreet);
        patient.getAddress().setCity(addressCity);
        patient.getAddress().setState(addressState);
        patient.getAddress().setZip(addressZip);
    }
}
