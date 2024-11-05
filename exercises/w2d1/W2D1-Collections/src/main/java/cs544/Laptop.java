package cs544;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String type;

    @ManyToOne
    private Employee employee;

    public Laptop() {
    }

    public Laptop(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    public Laptop(String brand, String type, Employee employee) {
        this.brand = brand;
        this.type = type;
        this.employee = employee;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
