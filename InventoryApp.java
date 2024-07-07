import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//  InventoryApp class creates the GUI for the inventory system
public class InventoryApp {
    private InventoryManager manager;

    // Constructor initializes the inventory manager and sets up the GUI
    public InventoryApp() {
        manager = new InventoryManager();
        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    // Method to set up the components in the GUI
    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem addItem = new JMenuItem("Add Product");
        JMenuItem removeItem = new JMenuItem("Remove Product");
        JMenuItem updateItem = new JMenuItem("Update Product");
        JMenuItem searchItem = new JMenuItem("Search Product");
        JMenuItem displayItem = new JMenuItem("Display All Products");
        JMenuItem saveItem = new JMenuItem("Save to File");
        JMenuItem loadItem = new JMenuItem("Load from File");

        // Add menu items to the menu
        menu.add(addItem);
        menu.add(removeItem);
        menu.add(updateItem);
        menu.add(searchItem);
        menu.add(displayItem);
        menu.add(saveItem);
        menu.add(loadItem);
        menuBar.add(menu);

        // Add the menu bar to the panel
        panel.add(menuBar, BorderLayout.NORTH);

        // Text Area for Output
        JTextArea textArea = new JTextArea();
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Action Listeners for menu items
        addItem.addActionListener(e -> {
            // Panel for input fields
            JPanel inputPanel = new JPanel(new GridLayout(4, 2));
            JTextField idText = new JTextField();
            JTextField nameText = new JTextField();
            JTextField quantityText = new JTextField();
            JTextField priceText = new JTextField();

            // Add labels and text fields to input panel
            inputPanel.add(new JLabel("ID:"));
            inputPanel.add(idText);
            inputPanel.add(new JLabel("Name:"));
            inputPanel.add(nameText);
            inputPanel.add(new JLabel("Quantity:"));
            inputPanel.add(quantityText);
            inputPanel.add(new JLabel("Price:"));
            inputPanel.add(priceText);

            // Show input dialog
            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Add Product", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // Add product to inventory
                String id = idText.getText();
                String name = nameText.getText();
                int quantity = Integer.parseInt(quantityText.getText());
                double price = Double.parseDouble(priceText.getText());
                manager.addProduct(new Product(id, name, quantity, price));
                textArea.append("Product Added: " + id + "\n");
            }
        });

        removeItem.addActionListener(e -> {
            // Remove product from inventory by ID
            String id = JOptionPane.showInputDialog("Enter Product ID to Remove:");
            if (id != null) {
                manager.removeProduct(id);
                textArea.append("Product Removed: " + id + "\n");
            }
        });

        updateItem.addActionListener(e -> {
            // Update product details
            String id = JOptionPane.showInputDialog("Enter Product ID to Update:");
            if (id != null) {
                Product product = manager.searchProduct(id);
                if (product != null) {
                    // Panel for input fields
                    JPanel inputPanel = new JPanel(new GridLayout(4, 2));
                    JTextField idText = new JTextField(product.getId());
                    JTextField nameText = new JTextField(product.getName());
                    JTextField quantityText = new JTextField(String.valueOf(product.getQuantity()));
                    JTextField priceText = new JTextField(String.valueOf(product.getPrice()));

                    // Add labels and text fields to input panel
                    inputPanel.add(new JLabel("ID:"));
                    inputPanel.add(idText);
                    inputPanel.add(new JLabel("Name:"));
                    inputPanel.add(nameText);
                    inputPanel.add(new JLabel("Quantity:"));
                    inputPanel.add(quantityText);
                    inputPanel.add(new JLabel("Price:"));
                    inputPanel.add(priceText);

                    // Show input dialog
                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Update Product", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        // Update product in inventory
                        String newId = idText.getText();
                        String newName = nameText.getText();
                        int newQuantity = Integer.parseInt(quantityText.getText());
                        double newPrice = Double.parseDouble(priceText.getText());
                        manager.updateProduct(new Product(newId, newName, newQuantity, newPrice));
                        textArea.append("Product Updated: " + newId + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found!");
                }
            }
        });

        searchItem.addActionListener(e -> {
            // Search for a product by ID
            String id = JOptionPane.showInputDialog("Enter Product ID to Search:");
            if (id != null) {
                Product product = manager.searchProduct(id);
                if (product != null) {
                    textArea.append("Product Found: " + product + "\n");
                } else {
                    textArea.append("Product Not Found: " + id + "\n");
                }
            }
        });

        displayItem.addActionListener(e -> {
            // Display all products in inventory
            StringBuilder sb = new StringBuilder();
            for (Product product : manager.getAllProducts()) {
                sb.append(product.toString()).append("\n");
            }
            textArea.append("All Products:\n" + sb.toString());
        });

        saveItem.addActionListener(e -> {
            // Save products to file
            try {
                manager.saveToFile("products.dat");
                textArea.append("Products Saved to File\n");
            } catch (IOException ex) {
                textArea.append("Error Saving Products to File\n");
            }
        });

        loadItem.addActionListener(e -> {
            // Load products from file
            try {
                manager.loadFromFile("products.dat");
                textArea.append("Products Loaded from File\n");
            } catch (IOException | ClassNotFoundException ex) {
                textArea.append("Error Loading Products from File\n");
            }
        });
    }

    public static void main(String[] args) {
        new InventoryApp();
    }
}
