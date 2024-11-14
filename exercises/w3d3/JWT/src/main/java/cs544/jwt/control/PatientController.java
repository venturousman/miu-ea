package cs544.jwt.control;

import org.springframework.web.bind.annotation.RestController;

import cs544.jwt.dto.PatientInDto;
import cs544.jwt.dto.PatientOutDto;
import cs544.jwt.exception.EntityNotFoundException;
import cs544.jwt.service.PatientService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("adsweb/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    
    @GetMapping("")
    public List<PatientOutDto> getAllPatients() {
        return patientService.getAll()
            .stream().map(PatientOutDto::new).toList();
    }

    @GetMapping("/{id}")
    public PatientOutDto getPatientById(@PathVariable int id) {
        var patient = patientService.getById(id);
        if (patient == null)
            throw new EntityNotFoundException("Patient not found");
        return new PatientOutDto(patient);
    }

    @PostMapping("")
    public PatientOutDto addPatient(@RequestBody @Valid PatientInDto patientInDto) {
        var patient = patientInDto.toPatient();
        return new PatientOutDto(patientService.save(patient));
    }

    @PutMapping("/{id}")
    public PatientOutDto updatePatient(
        @PathVariable long id, 
        @RequestBody @Valid PatientInDto patientInDto
        ) {
        var patient = patientService.getById(id);
        if (patient == null)
            throw new EntityNotFoundException("Patient not found");
        patientInDto.update(patient);
        return new PatientOutDto(patientService.save(patient));
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable int id) {
        patientService.deleteById(id);
    }

    @GetMapping("/search/{param}")
    public List<PatientOutDto> search(@PathVariable String param) {
        return patientService.search(param).stream().map(PatientOutDto::new).toList();
    }
}