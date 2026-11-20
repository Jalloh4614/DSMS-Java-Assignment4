package dsms.validation;

import dsms.model.Medicine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicineValidatorTest {
    private final MedicineValidator validator = new MedicineValidator();

    @Test
    void acceptsCorrectMedicineDetails() {
        Medicine medicine = new Medicine("M1", "Paracetamol", 2.50, 5);
        assertTrue(validator.isValid(medicine));
    }

    @Test
    void rejectsMedicineWithBlankName() {
        Medicine medicine = new Medicine("M2", " ", 2.50, 5);
        assertFalse(validator.isValid(medicine));
    }

    @Test
    void rejectsMedicineWithZeroPrice() {
        Medicine medicine = new Medicine("M3", "Cough Syrup", 0, 5);
        assertFalse(validator.isValid(medicine));
    }
}
