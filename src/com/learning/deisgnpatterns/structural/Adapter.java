package com.learning.deisgnpatterns.structural;

/**
 * ADAPTER DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * The Adapter Pattern allows incompatible interfaces to work together
 * by converting the interface of one class into another that a client expects.
 *
 * Real-World Analogy:
 * Using a power plug adapter to connect a U.S. charger (110V) to an Indian socket (220V).
 * In this example, Razorpay's API doesn't match the expected payment interface.
 * So, we use an adapter to make it compatible.
 */

// Target Interface (the client expects this format)
interface PaymentGateway {
    void pay(String orderId, double amount);
}

// Concrete implementation of the PaymentGateway (already compatible)
class PayUGateway implements PaymentGateway {
    public void pay(String orderId, double amount) {
        System.out.println("PayU Gateway: ₹" + amount + " has been deducted for order " + orderId);
    }
}

// Adaptee - incompatible third-party API (e.g., Razorpay)
class RazorpayAPI {
    public void makePayment(String orderId, double amount) {
        System.out.println("Razorpay API: ₹" + amount + " has been deducted for order " + orderId);
    }
}

// Adapter - makes RazorpayAPI compatible with PaymentGateway
class RazorPayAdapter implements PaymentGateway {

    private RazorpayAPI razorpay;

    public RazorPayAdapter() {
        this.razorpay = new RazorpayAPI();
    }

    /**
     * Adapts Razorpay's makePayment() to match the pay() method expected by the client.
     */
    public void pay(String orderId, double amount) {
        razorpay.makePayment(orderId, amount);
    }
}

// Client class that depends only on the PaymentGateway interface
class CheckoutService {

    private PaymentGateway gateway;

    /**
     * Constructor accepts any implementation of PaymentGateway.
     * Enables loose coupling and flexibility.
     */
    public CheckoutService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Triggers the payment through the provided gateway.
     */
    void checkOut(String orderId, double amount) {
        gateway.pay(orderId, amount);
    }
}

// Main class demonstrating the Adapter Pattern
public class Adapter {

    public static void main(String[] args) {
        // Use PayU Gateway (already compatible)
        CheckoutService payUCheckout = new CheckoutService(new PayUGateway());
        payUCheckout.checkOut("ORD123", 100);

        System.out.println();

        // Use Razorpay Gateway (adapted via adapter)
        CheckoutService razorpayCheckout = new CheckoutService(new RazorPayAdapter());
        razorpayCheckout.checkOut("ORD124", 150);
    }
}



