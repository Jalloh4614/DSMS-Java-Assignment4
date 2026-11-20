package dsms.component;

import dsms.model.Medicine;
import dsms.model.Sale;
import dsms.model.StockBatch;
import dsms.repository.MedicineRepository;
import dsms.repository.SaleRepository;
import dsms.repository.StockRepository;
import dsms.service.InventoryService;
import dsms.service.SalesCalculator;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class SalesInventoryComponentTest {
    private final LocalDate today = LocalDate.of(2026, 11, 19);

    private SalesInventoryComponent makeComponent(MedicineRepository medicines,
            StockRepository stock, SaleRepository sales) {
        return new SalesInventoryComponent(medicines, stock, sales,
                new InventoryService(), new SalesCalculator());
    }

    @Test
    void savesSaleAndReducesStock() {
        MedicineRepository medicines = new MedicineRepository();
        StockRepository stock = new StockRepository();
        SaleRepository sales = new SaleRepository();
        medicines.save(new Medicine("M20", "Pain Relief", 3.00, 2));
        stock.addBatch(new StockBatch("M20", "B20", today.plusDays(30), 8));
        SalesInventoryComponent component = makeComponent(medicines, stock, sales);

        Sale sale = component.makeSale("M20", 2, today);
        assertNotNull(sale);
        assertEquals(6.00, sale.getTotal(), 0.0001);
        assertEquals(6, component.getAvailableStock("M20", today));
        assertEquals(1, component.getSaleCount());
    }

    @Test
    void rejectsSaleWhenStockIsTooLow() {
        MedicineRepository medicines = new MedicineRepository();
        StockRepository stock = new StockRepository();
        SaleRepository sales = new SaleRepository();
        medicines.save(new Medicine("M20", "Pain Relief", 3.00, 2));
        stock.addBatch(new StockBatch("M20", "B20", today.plusDays(30), 6));
        SalesInventoryComponent component = makeComponent(medicines, stock, sales);

        assertNull(component.makeSale("M20", 20, today));
        assertEquals(6, component.getAvailableStock("M20", today));
        assertEquals(0, component.getSaleCount());
    }

    @Test
    void doesNotSellExpiredStock() {
        MedicineRepository medicines = new MedicineRepository();
        StockRepository stock = new StockRepository();
        SaleRepository sales = new SaleRepository();
        medicines.save(new Medicine("M20", "Pain Relief", 3.00, 2));
        stock.addBatch(new StockBatch("M20", "OLD", today.minusDays(1), 10));
        SalesInventoryComponent component = makeComponent(medicines, stock, sales);

        assertNull(component.makeSale("M20", 1, today));
        assertEquals(0, component.getSaleCount());
    }
}
