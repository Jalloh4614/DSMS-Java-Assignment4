package dsms.repository;

import dsms.model.StockBatch;
import java.util.*;

public class StockRepository {
    private final List<StockBatch> batches = new ArrayList<>();
    public void addBatch(StockBatch batch) { batches.add(batch); }
    public List<StockBatch> findAll() { return new ArrayList<>(batches); }
    public List<StockBatch> findByMedicine(String id) {
        List<StockBatch> found = new ArrayList<>();
        for (StockBatch batch : batches) {
            if (batch.getMedicineId().equals(id)) found.add(batch);
        }
        return found;
    }
}
