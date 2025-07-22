package com.learning.deisgnpatterns.behavioral;

/**
 * STRATEGY DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Define a family of algorithms (strategies), encapsulate each one,
 * and make them interchangeable at runtime.
 *
 * Real-World Analogy:
 * A user can choose to pay via UPI, PayPal, or Credit Card.
 * The algorithm to process payment changes, but the interface remains the same.
 */

// Strategy interface: declares the pay method
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategy: UPI payment implementation
class UpiPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("UPI payment made: " + amount);
    }
}

// Concrete strategy: PayPal payment implementation
class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("PayPal payment made: " + amount);
    }
}

// Concrete strategy: Credit card payment implementation
class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Credit card payment made: " + amount);
    }
}

// Context class: uses a PaymentStrategy to perform checkout
class PaymentService {

    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * Executes the selected strategy to process payment
     */
    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }

    /**
     * Allows changing the strategy dynamically
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

// Client code
public class Strategy {

    public static void main(String[] args) {

        // Create service using UPI strategy
        PaymentService paymentService = new PaymentService(new UpiPayment());
        paymentService.checkout(100);  // UPI payment

        // Change strategy to PayPal dynamically
        paymentService.setPaymentStrategy(new PayPalPayment());
        paymentService.checkout(200);  // PayPal payment

        // Change strategy to Credit Card dynamically
        paymentService.setPaymentStrategy(new CreditCardPayment());
        paymentService.checkout(300);  // Credit card payment
    }
}
