package cs544.jwt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private LocalDate dob;
    @OneToOne(cascade = jakarta.persistence.CascadeType.ALL)
    private Address address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Appointment> appointments;

    public String toString() {
        return """
                Patient {
                    id: %d,
                    firstName: %s,
                    lastName: %s,
                    dob: %s,
                    address: %s
                }
                """.formatted(id, getFirstName(), getLastName(), dob, address);
    }
}
