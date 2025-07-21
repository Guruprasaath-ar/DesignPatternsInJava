package com.learning.deisgnpatterns.behavioral;

import java.util.*;

/**
 * OBSERVER DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Define a one-to-many dependency between objects so that when one object (Subject)
 * changes state, all its dependents (Observers) are notified automatically.
 *
 * Real-World Analogy:
 * YouTube: Users subscribe to channels. When a channel uploads a video,
 * it notifies all subscribers.
 */

// Observer Interface
interface Subscriber {
    void update(String message);  // Called when the subject publishes an update
}

// Concrete Observer - Email
class EmailSubscriber implements Subscriber {

    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    public void update(String title) {
        System.out.println("Email sent to " + email + ": " + title);
    }
}

// Concrete Observer - Mobile App
class MobileAppSubscriber implements Subscriber {

    private String name;

    public MobileAppSubscriber(String name) {
        this.name = name;
    }

    public void update(String title) {
        System.out.println("Mobile notification sent to " + name + ": " + title);
    }
}

// Subject Interface
interface Channel {
    void subscribe(Subscriber sub);
    void unSubscribe(Subscriber sub);
    void uploadVideo(String title);
}

// Concrete Subject - YouTube Channel
class YoutubeChannel implements Channel {

    private List<Subscriber> subscribers;  // List of observers
    private String name;

    public YoutubeChannel(String name) {
        this.name = name;
        subscribers = new ArrayList<>();
    }

    // Add an observer
    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    // Remove an observer
    public void unSubscribe(Subscriber sub) {
        subscribers.remove(sub);
    }

    // Uploading new content - triggers notifications
    public void uploadVideo(String title) {
        System.out.println("New video uploaded: " + title);
        notifySubscribers(title);
    }

    // Notify all observers
    private void notifySubscribers(String title) {
        for (Subscriber s : subscribers) {
            s.update(title);
        }
    }
}

// Client - Demonstrates Observer Pattern
public class ObserverPattern {

    public static void main(String[] args) {

        // Create a YouTube channel (Subject)
        YoutubeChannel lld = new YoutubeChannel("Low Level Design");

        // Create observers (Subscribers)
        Subscriber emailSub = new EmailSubscriber("123@gmail.com");
        Subscriber mobileSub = new MobileAppSubscriber("Eclipse");

        // Subscribe both observers to the channel
        lld.subscribe(emailSub);
        lld.subscribe(mobileSub);

        // Uploading a video will notify all subscribers
        lld.uploadVideo("Observer Pattern");
    }
}