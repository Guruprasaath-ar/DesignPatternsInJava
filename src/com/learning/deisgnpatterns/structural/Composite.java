package com.learning.deisgnpatterns.structural;
import java.util.*;

/**
 * COMPOSITE DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Treat individual objects and compositions (groups) of objects uniformly using a common interface.
 *
 * Real-World Analogy:
 * Think of a shopping cart where you can add both individual products (like a book)
 * and product bundles (like a phone + case + earphones combo) — all of them have a cost and description.
 */

// Component Interface
interface CartItem {
    /**
     * Prints the description of the item (product or bundle).
     */
    void Description();

    /**
     * Returns the cost of the item.
     */
    double getCost();
}

// Leaf node - represents an individual product
class Product implements CartItem {

    private String name;
    private double cost;

    /**
     * Creates a new product with name and cost.
     */
    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Displays product name and cost.
     */
    public void Description() {
        System.out.println(name + " - ₹" + cost);
    }

    /**
     * Returns product cost.
     */
    public double getCost() {
        return cost;
    }
}

// Composite node - represents a bundle of products
class ProductBundle implements CartItem {

    private String bundleName;
    private List<Product> products;

    /**
     * Creates a new product bundle with a name.
     */
    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
        this.products = new ArrayList<>();
    }

    /**
     * Adds a product to the bundle.
     */
    public void addItem(Product product) {
        products.add(product);
    }

    /**
     * Displays description of each item in the bundle.
     */
    public void Description() {
        System.out.println("Bundle: " + bundleName);
        for (Product p : products) {
            p.Description();
        }
        System.out.println();
    }

    /**
     * Calculates total cost of all items in the bundle.
     */
    public double getCost() {
        double totalCost = 0;
        for (Product p : products) {
            totalCost += p.getCost();
        }
        return totalCost;
    }
}

// Client class that processes the cart
class CartCheckoutService {

    /**
     * Calculates total cost and prints descriptions of all items in the cart.
     */
    public void checkoutCart(List<CartItem> cart) {
        double totalAmount = 0;

        System.out.println("=== Cart Items ===");
        for (CartItem item : cart) {
            item.Description();            // Polymorphic call
            totalAmount += item.getCost(); // Works for both Product and Bundle
        }

        System.out.println("==================");
        System.out.println("Total amount to be paid: ₹" + totalAmount);
    }
}

// Driver class (Main)
public class Composite {

    public static void main(String[] args) {

        // Create individual products
        Product iPhone = new Product("iPhone 16", 150000);
        Product tws = new Product("Boat TWS", 1500);
        Product book = new Product("Atomic Habits", 600);

        // Create a bundle with iPhone + TWS
        ProductBundle iPhoneCombo = new ProductBundle("iPhone Combo");
        iPhoneCombo.addItem(iPhone);
        iPhoneCombo.addItem(tws);

        // Create a shopping cart with both bundle and individual item
        List<CartItem> cart = new ArrayList<>();
        cart.add(iPhoneCombo);  // Composite item
        cart.add(book);         // Leaf item

        // Perform checkout
        CartCheckoutService checkoutService = new CartCheckoutService();
        checkoutService.checkoutCart(cart);
    }
}