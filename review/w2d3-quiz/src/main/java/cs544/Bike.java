package cs544;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Bike extends Vehicle {

    @Column(length = 11)
    private int numgears;

    public Bike() {
        super();
    }

    public Bike(String owner, String make, String model, int year, String color, int numgears) {
        super(owner, make, model, year, color);
        this.numgears = numgears;
    }

    public int getNumgears() {
        return numgears;
    }

    public void setNumgears(int numgears) {
        this.numgears = numgears;
    }

}