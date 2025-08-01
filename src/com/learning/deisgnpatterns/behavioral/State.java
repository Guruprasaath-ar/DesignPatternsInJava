package com.learning.deisgnpatterns.behavioral;

/**
 * STATE DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Allow an object to alter its behavior when its internal state changes.
 * The object will appear to change its class.
 *
 * Real-World Analogy:
 * A traffic signal system cycles through RED → YELLOW → GREEN.
 * Each state has its own behavior and knows what the next state is.
 * The context (TrafficSignalContext) delegates the state transition to the current state.
 */

// State interface that defines a common behavior for all states
interface TrafficSignalState {
    void next(TrafficSignalContext context);
}

// Concrete state representing the RED signal
class Red implements TrafficSignalState {
    @Override
    public void next(TrafficSignalContext context) {
        System.out.println("Red");
        context.setState(new Yellow());  // Transition to Yellow
    }
}

// Concrete state representing the YELLOW signal
class Yellow implements TrafficSignalState {
    @Override
    public void next(TrafficSignalContext context) {
        System.out.println("Yellow");
        context.setState(new Green());  // Transition to Green
    }
}

// Concrete state representing the GREEN signal
class Green implements TrafficSignalState {
    @Override
    public void next(TrafficSignalContext context) {
        System.out.println("Green");
        context.setState(new Red());  // Transition back to Red
    }
}

// Context class that maintains the current state
class TrafficSignalContext {

    private TrafficSignalState currentState;

    // Constructor to initialize context with a starting state
    public TrafficSignalContext(TrafficSignalState state) {
        this.currentState = state;
    }

    // Allows state transition by setting a new state
    public void setState(TrafficSignalState state) {
        this.currentState = state;
    }

    // Delegates behavior to the current state
    public void next() {
        currentState.next(this);
    }
}

// Main class demonstrating the state transitions in a traffic signal
public class State {

    public static void main(String[] args) {

        // Start with RED signal
        TrafficSignalContext context = new TrafficSignalContext(new Red());

        // Cycle through states by repeatedly calling next()
        context.next();  // Output: Red → sets Yellow
        context.next();  // Output: Yellow → sets Green
        context.next();  // Output: Green → sets Red again
    }
}