package dsms.service;

import java.util.List;

public class SalesCalculator {
    public double lineTotal(double price, int quantity) {
        if (price <= 0 || quantity <= 0) return 0.0;
        return price * quantity;
    }

    public double saleTotal(List<Double> lineTotals) {
        if (lineTotals == null || lineTotals.isEmpty()) return 0.0;
        double total = 0.0;
        for (double line : lineTotals) total += line;
        return Math.round(total * 100.0) / 100.0;
    }
}
