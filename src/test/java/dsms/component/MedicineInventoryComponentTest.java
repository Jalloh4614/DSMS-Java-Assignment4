package dsms.component;

import dsms.model.Medicine;
import dsms.model.StockBatch;
import dsms.repository.MedicineRepository;
import dsms.repository.StockRepository;
import dsms.validation.MedicineValidator;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class MedicineInventoryComponentTest {
    private final LocalDate today = LocalDate.of(2026, 11, 19);

    @Test
    void addsMedicineAndReceivesStock() {
        MedicineRepository medicines = new MedicineRepository();
        StockRepository stock = new StockRepository();
        MedicineInventoryComponent component =
                new MedicineInventoryComponent(medicines, stock, new MedicineValidator());

        assertTrue(component.addMedicine(new Medicine("M10", "Vitamin C", 4.00, 3)));
        assertTrue(component.receiveStock(
                new StockBatch("M10", "B10", today.plusDays(30), 10), today));
        assertEquals(10, component.getStock("M10", today));
    }

    @Test
    void rejectsStockForMedicineNotInRecords() {
        MedicineInventoryComponent component =
                new MedicineInventoryComponent(new MedicineRepository(),
                        new StockRepository(), new MedicineValidator());

        assertFalse(component.receiveStock(
                new StockBatch("UNKNOWN", "B11", today.plusDays(30), 4), today));
    }

    @Test
    void identifiesLowStockAtReorderLevel() {
        MedicineRepository medicines = new MedicineRepository();
        StockRepository stock = new StockRepository();
        MedicineInventoryComponent component =
                new MedicineInventoryComponent(medicines, stock, new MedicineValidator());

        assertTrue(component.addMedicine(new Medicine("M11", "Bandage", 1.00, 2)));
        assertTrue(component.receiveStock(
                new StockBatch("M11", "B12", today.plusDays(30), 2), today));
        assertTrue(component.isLowStock("M11", today));
    }
}
