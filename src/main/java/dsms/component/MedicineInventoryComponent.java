package dsms.component;

import dsms.model.Medicine;
import dsms.model.StockBatch;
import dsms.repository.MedicineRepository;
import dsms.repository.StockRepository;
import dsms.validation.MedicineValidator;
import java.time.LocalDate;

public class MedicineInventoryComponent {
    private final MedicineRepository medicineRepository;
    private final StockRepository stockRepository;
    private final MedicineValidator validator;

    public MedicineInventoryComponent(MedicineRepository medicineRepository,
                                      StockRepository stockRepository,
                                      MedicineValidator validator) {
        this.medicineRepository = medicineRepository;
        this.stockRepository = stockRepository;
        this.validator = validator;
    }

    public boolean addMedicine(Medicine medicine) {
        return validator.isValid(medicine) && medicineRepository.save(medicine);
    }
    public boolean receiveStock(StockBatch batch, LocalDate today) {
        if (batch == null || medicineRepository.findById(batch.getMedicineId()) == null
                || batch.getQuantity() <= 0 || !batch.getExpiryDate().isAfter(today)) {
            return false;
        }
        stockRepository.addBatch(batch);
        return true;
    }
    public int getStock(String medicineId, LocalDate today) {
        int total = 0;
        for (StockBatch batch : stockRepository.findByMedicine(medicineId)) {
            if (batch.getExpiryDate().isAfter(today)) total += batch.getQuantity();
        }
        return total;
    }
    public boolean isLowStock(String medicineId, LocalDate today) {
        Medicine medicine = medicineRepository.findById(medicineId);
        return medicine != null && getStock(medicineId, today) <= medicine.getReorderLevel();
    }
}
