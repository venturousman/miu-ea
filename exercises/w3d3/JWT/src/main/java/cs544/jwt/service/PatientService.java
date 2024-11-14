package cs544.jwt.service;

import java.util.List;

import cs544.jwt.model.Patient;

public interface PatientService {
    List<Patient> getAll();
    Patient getById(long id);
    Patient save(Patient patient);
    void deleteById(long id);
    List<Patient> allSortedByCity();
    List<Patient> search(String param);
} 
