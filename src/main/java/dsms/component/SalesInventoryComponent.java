package dsms.component;

import dsms.model.*;
import dsms.repository.*;
import dsms.service.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SalesInventoryComponent {
    private final MedicineRepository medicineRepository;
    private final StockRepository stockRepository;
    private final SaleRepository saleRepository;
    private final InventoryService inventoryService;
    private final SalesCalculator calculator;

    public SalesInventoryComponent(MedicineRepository medicineRepository,
                                   StockRepository stockRepository,
                                   SaleRepository saleRepository,
                                   InventoryService inventoryService,
                                   SalesCalculator calculator) {
        this.medicineRepository = medicineRepository;
        this.stockRepository = stockRepository;
        this.saleRepository = saleRepository;
        this.inventoryService = inventoryService;
        this.calculator = calculator;
    }

    public Sale makeSale(String medicineId, int quantity, LocalDate today) {
        Medicine medicine = medicineRepository.findById(medicineId);
        if (medicine == null || quantity <= 0) return null;
        List<StockBatch> batches = stockRepository.findByMedicine(medicineId);
        if (inventoryService.getAvailableQuantity(batches, medicineId, today) < quantity) return null;

        double total = calculator.lineTotal(medicine.getPrice(), quantity);
        if (!inventoryService.reduceStock(batches, medicineId, quantity, today)) return null;

        Sale sale = new Sale("S-" + (saleRepository.findAll().size() + 1),
                total, LocalDateTime.now());
        saleRepository.save(sale);
        return sale;
    }
    public int getAvailableStock(String medicineId, LocalDate today) {
        return inventoryService.getAvailableQuantity(stockRepository.findAll(), medicineId, today);
    }
    public int getSaleCount() { return saleRepository.findAll().size(); }
}
