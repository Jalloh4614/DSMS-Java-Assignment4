package dsms.service;

import dsms.model.StockBatch;
import java.time.LocalDate;
import java.util.List;

public class InventoryService {
    public int getAvailableQuantity(List<StockBatch> batches, String medicineId, LocalDate today) {
        int total = 0;
        for (StockBatch batch : batches) {
            if (batch.getMedicineId().equals(medicineId)
                    && batch.getExpiryDate().isAfter(today)
                    && batch.getQuantity() > 0) {
                total += batch.getQuantity();
            }
        }
        return total;
    }

    public boolean reduceStock(List<StockBatch> batches, String medicineId,
                               int quantity, LocalDate today) {
        if (quantity <= 0) return false;
        if (getAvailableQuantity(batches, medicineId, today) < quantity) return false;

        int remaining = quantity;
        for (StockBatch batch : batches) {
            if (batch.getMedicineId().equals(medicineId)
                    && batch.getExpiryDate().isAfter(today)
                    && batch.getQuantity() > 0) {
                int used = Math.min(batch.getQuantity(), remaining);
                batch.setQuantity(batch.getQuantity() - used);
                remaining -= used;
                if (remaining == 0) break;
            }
        }
        return true;
    }
}
