package com.learning.deisgnpatterns.structural;

/**
 * PROXY DESIGN PATTERN - JAVA IMPLEMENTATION
 * 
 * Intent:
 * Provide a placeholder or surrogate for another object to control access to it.
 * Commonly used for lazy loading, access control, logging, caching, etc.
 * 
 * Real-World Analogy:
 * Think of a proxy as a representative. You don’t go directly to the resource
 * (e.g., a large image from disk or a heavy network object) — the proxy loads it only when needed.
 */

// Subject Interface
interface Image {
    void display();
}

// Real Subject - Resource-heavy object
class RealImage implements Image {

    private String fileName;

    /**
     * Loads the image from disk (simulated).
     */
    private void loadFromDisk() {
        System.out.println("Loading " + fileName + " from disk...");
    }

    /**
     * Constructor immediately loads image from disk (expensive).
     */
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    /**
     * Displays the image content.
     */
    public void display() {
        System.out.println("Displaying: " + fileName);
    }
}

// Proxy - Controls access to RealImage (lazy loading here)
class ProxyImage implements Image {

    private String fileName;
    private RealImage realImage;

    /**
     * Constructor stores fileName but delays RealImage loading.
     */
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Displays the image by loading it only if necessary (lazy init).
     */
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // Lazy loading
        }
        realImage.display(); // Delegates to real object
    }
}

// Client
public class Proxy {

    public static void main(String[] args) {

        // Proxy holds off on loading until display() is called
        Image image = new ProxyImage("image.jpg");

        // Image will now be loaded from disk and displayed
        image.display();

        // This time, it doesn't reload — just displays
        image.display();
    }
}

