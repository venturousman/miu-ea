package cs544;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class Payment {
    private LocalDate paydate;
    private double amount;

    public Payment() {
    }

    public Payment(LocalDate paydate, double amount) {
        this.paydate = paydate;
        this.amount = amount;
    }

    public LocalDate getPaydate() {
        return paydate;
    }

    public void setPaydate(LocalDate paydate) {
        this.paydate = paydate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
