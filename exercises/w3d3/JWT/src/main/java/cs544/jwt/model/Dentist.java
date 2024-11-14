package cs544.jwt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    @OneToMany(mappedBy = "dentist")
    @JsonBackReference
    private List<Appointment> appointments;

    public String toString() {
        return """
                Dentist {
                    id: %d,
                    firstName: %s,
                    lastName: %s,
                    specialty: %s
                }
                """.formatted(id, getFirstName(), getLastName(), specialty);
    }
}
