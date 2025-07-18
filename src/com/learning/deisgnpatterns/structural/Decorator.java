package com.learning.deisgnpatterns.structural;

/**
 * DECORATOR DESIGN PATTERN - JAVA IMPLEMENTATION
 * 
 * Purpose:
 * Allows behavior to be added to individual objects dynamically
 * without modifying their class. It promotes composition over inheritance.
 *
 * Real-World Analogy:
 * Ordering a coffee and optionally adding toppings like milk or sugar.
 * Each add-on "wraps" the original drink and adds its own cost/description.
 */

// Base Component Interface
interface Coffee {
    /**
     * Returns the description of the coffee.
     */
    String getDescription();

    /**
     * Returns the total cost of the coffee.
     */
    double getCost();
}

// Concrete Component
class Espresso implements Coffee {

    /**
     * Description of the base coffee (Espresso).
     */
    public String getDescription() {
        return "Espresso";
    }

    /**
     * Base cost of the Espresso.
     */
    public double getCost() {
        return 30;
    }
}

// Abstract Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    /**
     * Wraps the original coffee object.
     * @param coffee - the coffee instance being decorated
     */
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
}

// Concrete Decorator - Adds Milk
class Milk extends CoffeeDecorator {

    /**
     * Constructor passes the coffee to be decorated.
     */
    public Milk(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds "Milk" to the coffee description.
     */
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Milk";
    }

    /**
     * Adds Rs.5 to the base coffee cost for milk.
     */
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 5;
    }
}

// Concrete Decorator - Adds Sugar
class Sugar extends CoffeeDecorator {

    /**
     * Constructor wraps the coffee object.
     */
    public Sugar(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds "Sugar" to the coffee description.
     */
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Sugar";
    }

    /**
     * Adds Rs.2 to the base coffee cost for sugar.
     */
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 2;
    }
}

// Client Code
public class Decorator {

    public static void main(String[] args) {
        // Order: Espresso with Milk and Sugar
        Coffee coffee = new Sugar(new Milk(new Espresso()));

        System.out.println(coffee.getDescription() + "   " + coffee.getCost());
    }
}