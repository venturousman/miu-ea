package cs544;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate appdate;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @Embedded
    private Payment payment;

    public Appointment() {
        payment = new Payment();
    }

    public Appointment(LocalDate appdate, LocalDate paydate, double amount) {
        this.appdate = appdate;
        this.payment = new Payment(paydate, amount);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getAppdate() {
        return appdate;
    }

    public void setAppdate(LocalDate appdate) {
        this.appdate = appdate;
    }
}
