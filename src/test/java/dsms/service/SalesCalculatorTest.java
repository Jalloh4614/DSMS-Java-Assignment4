package dsms.service;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class SalesCalculatorTest {
    private final SalesCalculator calculator = new SalesCalculator();

    @Test
    void calculatesLineTotal() {
        assertEquals(7.50, calculator.lineTotal(2.50, 3), 0.0001);
    }

    @Test
    void returnsZeroForEmptySale() {
        assertEquals(0.0, calculator.saleTotal(Collections.emptyList()), 0.0001);
    }

    @Test
    void addsLinesAndRoundsToCents() {
        assertEquals(10.01,
                calculator.saleTotal(Arrays.asList(2.505, 7.505)), 0.0001);
    }
}
