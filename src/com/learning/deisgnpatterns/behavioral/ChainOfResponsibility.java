package com.learning.deisgnpatterns.behavioral;

/**
 * CHAIN OF RESPONSIBILITY DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Avoid coupling the sender of a request to its receiver by giving multiple objects a chance to handle the request.
 * Chain the receiving objects and pass the request along the chain until one of them handles it.
 *
 * Real-World Analogy:
 * In a customer support system, simple issues are resolved by Level 1 support.
 * If they can't handle it, the request is passed to Level 2, and so on until it reaches a specialist.
 * Each handler decides whether to process the request or pass it along.
 */

class SupportRequest {

    private String description;
    private int complexityLevel;

    public SupportRequest(String description, int complexityLevel) {
        this.description = description;
        this.complexityLevel = complexityLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getComplexityLevel() {
        return complexityLevel;
    }

    public void setComplexityLevel(int complexityLevel) {
        this.complexityLevel = complexityLevel;
    }
}

// Abstract handler class defines the interface for handling requests
abstract class SupportHandler {

    protected SupportHandler nextHandler;

    // Defines how a request is handled or forwarded
    public abstract void handleRequest(SupportRequest request);

    // Sets the next handler in the chain
    public abstract void setNextHandler(SupportHandler nextHandler);
}

// Concrete handler for level 1 support
class Level1SupportHandler extends SupportHandler {

    public void handleRequest(SupportRequest request) {
        if (request.getComplexityLevel() <= 1)
            System.out.println("Level 1 supporter handling Request: " + request.getDescription());
        else if (nextHandler != null)
            nextHandler.handleRequest(request);
    }

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

// Concrete handler for level 2 support
class Level2SupportHandler extends SupportHandler {

    public void handleRequest(SupportRequest request) {
        if (request.getComplexityLevel() <= 2)
            System.out.println("Level 2 supporter handling Request: " + request.getDescription());
        else if (nextHandler != null)
            nextHandler.handleRequest(request);
    }

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

// Concrete handler for level 3 or specialist support
class Level3SupportHandler extends SupportHandler {

    public void handleRequest(SupportRequest request) {
        System.out.println("Specialist handling Request: " + request.getDescription());
    }

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

// Client class that sets up the chain and triggers requests
public class ChainOfResponsibility {

    public static void main(String[] args) {

        // Create handler instances
        SupportHandler supportHandler1 = new Level1SupportHandler();
        SupportHandler supportHandler2 = new Level2SupportHandler();
        SupportHandler supportHandler3 = new Level3SupportHandler();

        // Set up the chain: Level 1 → Level 2 → Level 3
        supportHandler1.setNextHandler(supportHandler2);
        supportHandler2.setNextHandler(supportHandler3);

        // Create requests with varying complexity
        supportHandler1.handleRequest(new SupportRequest("Forgot password", 1));      // Handled by Level 1
        supportHandler1.handleRequest(new SupportRequest("Data breach incident", 3)); // Handled by Specialist
    }
}

