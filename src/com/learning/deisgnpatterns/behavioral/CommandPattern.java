package com.learning.deisgnpatterns.behavioral;

import java.util.List;
import java.util.Stack;

//========== RECEIVERS ==========
//Receiver Interface
interface Device {
 void turnOn();
 void turnOff();
}

//Concrete Receiver: TV
class Tv implements Device {
 private String name;

 public Tv(String name) {
     this.name = name;
 }

 public void turnOn() {
     System.out.println("Turning on " + name + " TV");
 }

 public void turnOff() {
     System.out.println("Turning off " + name + " TV");
 }
}

//Concrete Receiver: AC
class Ac implements Device {
 private String name;

 public Ac(String name) {
     this.name = name;
 }

 public void turnOn() {
     System.out.println("Turning on " + name + " AC");
 }

 public void turnOff() {
     System.out.println("Turning off " + name + " AC");
 }
}

//========== COMMAND INTERFACE ==========
interface Command {
 void execute();  // Perform the operation
 void undo();     // Revert the operation
}

//Concrete Command: Turn TV On
class TvOnCommand implements Command {
 private Tv tv;

 public TvOnCommand(Tv tv) {
     this.tv = tv;
 }

 public void execute() {
     tv.turnOn();
 }

 public void undo() {
     tv.turnOff();
 }
}

//Concrete Command: Turn TV Off
class TvOffCommand implements Command {
 private Tv tv;

 public TvOffCommand(Tv tv) {
     this.tv = tv;
 }

 public void execute() {
     tv.turnOff();
 }

 public void undo() {
     tv.turnOn();
 }
}

//Concrete Command: Turn AC On
class AcOnCommand implements Command {
 private Ac ac;

 public AcOnCommand(Ac ac) {
     this.ac = ac;
 }

 public void execute() {
     ac.turnOn();
 }

 public void undo() {
     ac.turnOff();
 }
}

//Concrete Command: Turn AC Off
class AcOffCommand implements Command {
 private Ac ac;

 public AcOffCommand(Ac ac) {
     this.ac = ac;
 }

 public void execute() {
     ac.turnOff();
 }

 public void undo() {
     ac.turnOn();
 }
}

//========== INVOKER ==========
class Remote {
 private List<Command> commands;           // Stores available buttons (slots)
 private Stack<Command> commandsHistory;   // Stores executed commands for undo

 // Remote constructor gets its commands from outside (dependency injection)
 public Remote(List<Command> commands) {
     this.commands = commands;
     this.commandsHistory = new Stack<>();
 }

 // Simulates pressing a button on the remote
 public void pressButton(int slot) {
     if (slot >= 0 && slot < commands.size()) {
         commands.get(slot).execute();
         commandsHistory.push(commands.get(slot));
     } else {
         System.out.println("Invalid slot number.");
     }
 }

 // Undo the last executed command
 public void undo() {
     if (!commandsHistory.isEmpty()) {
         commandsHistory.pop().undo();
     } else {
         System.out.println("Nothing to undo.");
     }
 }
}

//========== CLIENT ==========
public class CommandPattern {
 public static void main(String[] args) {
     // Step 1: Create Receivers
     Tv samsungTv = new Tv("Samsung");
     Ac onidaAc = new Ac("Onida");

     // Step 2: Create Commands
     Command tvOn = new TvOnCommand(samsungTv);
     Command tvOff = new TvOffCommand(samsungTv);
     Command acOn = new AcOnCommand(onidaAc);
     Command acOff = new AcOffCommand(onidaAc);

     // Step 3: Provide the commands to the Remote (Invoker)
     List<Command> commands = List.of(tvOn, tvOff, acOn, acOff);
     Remote remote = new Remote(commands);

     // Step 4: Use Remote to invoke commands
     remote.pressButton(0);  // TV ON
     remote.pressButton(1);  // TV OFF
     remote.undo();          // Undo (TV ON)
     remote.pressButton(2);  // AC ON
     remote.pressButton(3);  // AC OFF
     remote.undo();          // Undo (AC ON)
 }
}
