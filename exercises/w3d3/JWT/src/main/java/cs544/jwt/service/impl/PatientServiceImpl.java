package cs544.jwt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.jwt.model.Patient;
import cs544.jwt.repository.PatientRepository;
import cs544.jwt.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getById(long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deleteById(long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> allSortedByCity() {
        return patientRepository.findByOrderByAddressCityAsc();
    }   

    @Override
    public List<Patient> search(String param) {
        return patientRepository.findByFirstNameContainingOrLastNameContainingOrAddressStreetContainingOrAddressCityContaining(param, param, param, param);
    }
}
