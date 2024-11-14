package cs544.jwt.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.jwt.dto.PatientOutDto;
import cs544.jwt.service.PatientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("adsweb/api/v1/addresses")
public class AddressController {
    @Autowired
    private PatientService patientService;
    
    @GetMapping("")
    public List<PatientOutDto> getPatientAddresses() {
        return patientService.allSortedByCity().stream().map(PatientOutDto::new).toList();
    }
}
