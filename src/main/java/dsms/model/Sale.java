package dsms.model;

import java.time.LocalDateTime;

public class Sale {
    private final String saleId;
    private final double total;
    private final LocalDateTime dateTime;

    public Sale(String saleId, double total, LocalDateTime dateTime) {
        this.saleId = saleId;
        this.total = total;
        this.dateTime = dateTime;
    }
    public String getSaleId() { return saleId; }
    public double getTotal() { return total; }
    public LocalDateTime getDateTime() { return dateTime; }
}
