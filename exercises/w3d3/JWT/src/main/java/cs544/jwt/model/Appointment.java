package cs544.jwt.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
    @ManyToOne
    @JsonManagedReference
    private Dentist dentist;
    @ManyToOne
    @JsonManagedReference
    private Patient patient;
    @ManyToOne
    @JsonManagedReference
    private Surgery surgery;

    public String toString() {
        return """
                Appointment {
                    id: %d,
                    date: %s,
                    dentist: %s,
                    patient: %s,
                    surgery: %s
                }
                """.formatted(id, date, dentist, patient, surgery);
    }
}
