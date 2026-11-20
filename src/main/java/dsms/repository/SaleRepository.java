package dsms.repository;

import dsms.model.Sale;
import java.util.*;

public class SaleRepository {
    private final List<Sale> sales = new ArrayList<>();
    public void save(Sale sale) { sales.add(sale); }
    public List<Sale> findAll() { return new ArrayList<>(sales); }
}
