package cs544;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity(name = "cs544.Students")
@Table(name = "students")
@RequiredArgsConstructor
@AllArgsConstructor
public class Students {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "name", nullable = true)
  private String name;
  @Column(name = "email", nullable = true)
  private String email;
  @Column(name = "password", nullable = true)
  private String password;
}