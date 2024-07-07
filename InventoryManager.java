import java.util.ArrayList;
import java.util.List;
import java.io.*;

//  InventoryManager class
public class InventoryManager {
    private List<Product> products;

    // Constructor initializes an empty list of products
    public InventoryManager() {
        products = new ArrayList<>();
    }

    // Method to add a product to the inventory
    public void addProduct(Product product) {
        products.add(product);
    }

    // Method to remove a product from the inventory by ID
    public void removeProduct(String id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    // Method to search for a product by ID
    public Product searchProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Method to update a product's details
    public void updateProduct(Product updatedProduct) {
        for (Product product : products) {
            if (product.getId().equals(updatedProduct.getId())) {
                product.setName(updatedProduct.getName());
                product.setQuantity(updatedProduct.getQuantity());
                product.setPrice(updatedProduct.getPrice());
                return;
            }
        }
    }

    // Method to get a list of all products
    public List<Product> getAllProducts() {
        return products;
    }

    // Method to save the list of products to a file
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(products);
        }
    }

    // Method to load the list of products from a file
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            products = (List<Product>) ois.readObject();
        }
    }
}
