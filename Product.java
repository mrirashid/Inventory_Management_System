import java.io.Serializable;

// The Product class represents a product with an ID, name, quantity, and price
public class Product implements Serializable {
    private String id;
    private String name;
    private int quantity;
    private double price;

    // Constructor to initialize product details
    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters for product attributes
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', quantity=" + quantity + ", price=" + price + "}";
    }
}
