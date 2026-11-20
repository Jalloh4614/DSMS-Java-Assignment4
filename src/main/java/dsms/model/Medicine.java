package dsms.model;

public class Medicine {
    private final String id;
    private final String name;
    private final double price;
    private final int reorderLevel;

    public Medicine(String id, String name, double price, int reorderLevel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reorderLevel = reorderLevel;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getReorderLevel() { return reorderLevel; }
}
