package playStation;
import java.time.LocalDate;

public class Gain {
    private LocalDate date;
    private double amountPaid;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Gain(LocalDate date, double amountPaid){
        this.setAmountPaid(amountPaid);
        this.setDate(date);
    }
}
