package cs544.jwt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Surgery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Address address;
    @OneToMany(mappedBy = "surgery")
    @JsonBackReference
    private List<Appointment> appointments;

    public String toString() {
        return """
                Surgery {
                    id: %d,
                    name: %s,
                    address: %s
                }
                """.formatted(id, name, address);
    }
}
