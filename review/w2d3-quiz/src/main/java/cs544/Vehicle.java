package cs544;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

@Entity
// @Table(name = "Vehicle") // no affect, still be 'vehicle'
@SecondaryTable(name = "Owners")
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20)
    private Long id;

    @Column(table = "Owners")
    private String owner;

    @Column(name = "manufacturer")
    private String make;

    private String model;

    @Column(length = 11, nullable = false)
    private int year;

    private String color;

    @OneToMany
    @JoinColumn(name = "wheels_id") // name is optional
    @OrderColumn(name = "wheels_ORDER") // name is optional
    private List<Wheel> wheels;

    public Vehicle() {
    }

    public Vehicle(String owner, String make, String model, int year, String color) {
        this.owner = owner;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

}
