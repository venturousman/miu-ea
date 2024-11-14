package cs544.jwt;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cs544.jwt.model.Address;
import cs544.jwt.model.Appointment;
import cs544.jwt.model.Dentist;
import cs544.jwt.model.Patient;
import cs544.jwt.model.Role;
import cs544.jwt.model.Surgery;
import cs544.jwt.model.User;
import cs544.jwt.repository.AppointmentRepository;
import cs544.jwt.repository.DentistRepository;
import cs544.jwt.repository.PatientRepository;
import cs544.jwt.repository.RoleRepository;
import cs544.jwt.repository.SurgeryRepository;
import cs544.jwt.repository.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner{
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private DentistRepository dentistRepository;
	@Autowired
	private SurgeryRepository surgeryRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder pEncoder;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(null, "user1", pEncoder.encode("password"), null);
		User user2 = new User(null, "user2", pEncoder.encode("password"), null);
		User user3 = new User(null, "user3", pEncoder.encode("password"), null);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		Role role1 = new Role(null, "DENTIST", user1, null, null);
		Role role2 = new Role(null, "PATIENT", user2, null, null);
		Role role3 = new Role(null, "OFFICE", user3, null, null);
		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role3);

		Dentist tony = new Dentist(null, "Tony", "Smith", "Orthodontist", null);
		Dentist helen = new Dentist(null, "Helen", "Pearson", "Oral Higene", null);
		Dentist robin = new Dentist(null, "Robin", "Plevin", "Endodontist", null);
		
		dentistRepository.save(tony);
		dentistRepository.save(helen);
		dentistRepository.save(robin);

		role1.setDentist(tony);
		roleRepository.save(role1);
		
		Address address1 = new Address(null, "1000 N 4th St", "Fairfield", "Iowa", "52557");
		Address address2 = new Address(null, "2000 N 4th St", "Chicago", "Iowa", "52557");
		Address address3 = new Address(null, "3000 N 4th St", "Springfield", "Iowa", "52557");
		Address address4 = new Address(null, "4000 N 4th St", "Ottumwa", "Iowa", "52557");

		Patient gillian = new Patient(null, "Gillian", "White", LocalDate.of(1990, 1, 1), address1, null);
		Patient jill = new Patient(null, "Jill", "Bell", LocalDate.of(1995, 2, 2), address2, null);
		Patient ian = new Patient(null, "Ian", "MacKay", LocalDate.of(2000, 3, 3), address3, null);
		Patient john = new Patient(null, "John", "Walker", LocalDate.of(2005, 4, 4), address4, null);
		patientRepository.save(gillian);
		patientRepository.save(jill);
		patientRepository.save(ian);
		patientRepository.save(john);

		role2.setPatient(gillian);
		roleRepository.save(role2);

		Surgery surgery15 = new Surgery(null, "S15", null, null);
		Surgery surgery10 = new Surgery(null, "S10", null, null);
		Surgery surgery13 = new Surgery(null, "S13", null, null);
		surgeryRepository.save(surgery15);
		surgeryRepository.save(surgery10);
		surgeryRepository.save(surgery13);

		Appointment appointment1 = new Appointment(null, LocalDateTime.of(2013, 9, 12, 10, 0), tony, gillian, surgery15);
		Appointment appointment2 = new Appointment(null, LocalDateTime.of(2013, 9, 12, 12, 0), tony, jill, surgery15);
		Appointment appointment3 = new Appointment(null, LocalDateTime.of(2013, 9, 12, 10, 0), helen, ian, surgery10);
		Appointment appointment4 = new Appointment(null, LocalDateTime.of(2013, 9, 14, 14, 0), helen, ian, surgery10);
		Appointment appointment5 = new Appointment(null, LocalDateTime.of(2013, 9, 14, 16, 30), robin, jill, surgery15);
		Appointment appointment6 = new Appointment(null, LocalDateTime.of(2013, 9, 14, 18, 0), robin, john, surgery13);
		appointmentRepository.save(appointment1);
		appointmentRepository.save(appointment2);
		appointmentRepository.save(appointment3);
		appointmentRepository.save(appointment4);
		appointmentRepository.save(appointment5);
		appointmentRepository.save(appointment6);
	}

}
