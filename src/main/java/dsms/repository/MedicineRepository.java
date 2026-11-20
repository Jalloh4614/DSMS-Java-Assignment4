package dsms.repository;

import dsms.model.Medicine;
import java.util.*;

public class MedicineRepository {
    private final Map<String, Medicine> medicines = new LinkedHashMap<>();

    public boolean save(Medicine medicine) {
        if (medicine == null || medicines.containsKey(medicine.getId())) return false;
        medicines.put(medicine.getId(), medicine);
        return true;
    }
    public Medicine findById(String id) { return medicines.get(id); }
    public List<Medicine> findAll() { return new ArrayList<>(medicines.values()); }
}
