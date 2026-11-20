package dsms.service;

import dsms.model.StockBatch;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {
    private final InventoryService service = new InventoryService();
    private final LocalDate today = LocalDate.of(2026, 11, 19);

    @Test
    void countsOnlyUnexpiredBatchesForMedicine() {
        List<StockBatch> batches = new ArrayList<>();
        batches.add(new StockBatch("M1", "B1", today.plusDays(10), 8));
        batches.add(new StockBatch("M1", "B2", today.minusDays(1), 5));
        batches.add(new StockBatch("M2", "B3", today.plusDays(5), 4));
        assertEquals(8, service.getAvailableQuantity(batches, "M1", today));
    }

    @Test
    void refusesToReduceMoreThanAvailableStock() {
        List<StockBatch> batches = new ArrayList<>();
        batches.add(new StockBatch("M1", "B1", today.plusDays(10), 8));
        assertFalse(service.reduceStock(batches, "M1", 9, today));
        assertEquals(8, service.getAvailableQuantity(batches, "M1", today));
    }

    @Test
    void reducesStockWhenEnoughIsAvailable() {
        List<StockBatch> batches = new ArrayList<>();
        batches.add(new StockBatch("M1", "B1", today.plusDays(10), 8));
        assertTrue(service.reduceStock(batches, "M1", 3, today));
        assertEquals(5, service.getAvailableQuantity(batches, "M1", today));
    }
}
