package dsms.validation;

import dsms.model.Medicine;

public class MedicineValidator {
    public boolean isValid(Medicine medicine) {
        if (medicine == null) return false;
        return medicine.getId() != null && !medicine.getId().trim().isEmpty()
                && medicine.getName() != null && !medicine.getName().trim().isEmpty()
                && medicine.getPrice() > 0
                && medicine.getReorderLevel() >= 0;
    }
}
