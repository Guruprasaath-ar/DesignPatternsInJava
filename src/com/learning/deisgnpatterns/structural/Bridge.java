package com.learning.deisgnpatterns.structural;

/**
 * BRIDGE DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Decouple an abstraction from its implementation so that both can vary independently.
 *
 * Real-World Analogy:
 * A remote control (abstraction) can operate different devices (TV, Radio, etc.).
 * The remote doesn't care what device it's connected to — it just calls enable/disable.
 */

// Implementor Interface
interface Device {
    void enable();
    void disable();
}

// Concrete Implementor - TV
class TV implements Device {
    public void enable() {
        System.out.println("TV is now ON");
    }

    public void disable() {
        System.out.println("TV is now OFF");
    }
}

// Concrete Implementor - Radio
class Radio implements Device {
    public void enable() {
        System.out.println("Radio is now ON");
    }

    public void disable() {
        System.out.println("Radio is now OFF");
    }
}

// Abstraction - Remote Control
abstract class RemoteControl {

    protected Device device;

    /**
     * Constructor accepts any device implementation.
     */
    public RemoteControl(Device device) {
        this.device = device;
    }

    /**
     * Turns on the connected device.
     */
    abstract void turnOn();

    /**
     * Turns off the connected device.
     */
    abstract void turnOff();
}

// Refined Abstraction - UltimateRemote
class UltimateRemote extends RemoteControl {

    public UltimateRemote(Device device) {
        super(device);
    }

    /**
     * Enables the device using its own implementation.
     */
    public void turnOn() {
        device.enable();
    }

    /**
     * Disables the device using its own implementation.
     */
    public void turnOff() {
        device.disable();
    }
}

// Client - Demonstrates Bridge usage
public class Bridge {
    public static void main(String[] args) {

        // Remote working with a TV
        RemoteControl tvRemote = new UltimateRemote(new TV());
        tvRemote.turnOn();
        tvRemote.turnOff();

        System.out.println();

        // Remote working with a Radio
        RemoteControl radioRemote = new UltimateRemote(new Radio());
        radioRemote.turnOn();
        radioRemote.turnOff();
    }
}
