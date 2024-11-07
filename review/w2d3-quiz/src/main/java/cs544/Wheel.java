package cs544;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
// @Table(name = "Wheel") // no affect, still be 'wheel'
public class Wheel {

    @Id
    @Column(length = 20)
    private Long id;

    @Column(length = 11, nullable = false)
    private int size;

    @Column(nullable = false)
    private double pressure;

    public Wheel() {
    }

    public Wheel(Long id, int size, double pressure) {
        this.id = id;
        this.size = size;
        this.pressure = pressure;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

}