package com.learning.deisgnpatterns.structural;

/**
 * Subsystem class representing the DVD Player.
 * Handles operations related to playing the movie.
 */
class DvdPlayer {
	
	/**
	 * Turns the DVD player on.
	 */
	public void on() {
		System.out.println("DVD player turning on");
	}
	
	/**
	 * Turns the DVD player off.
	 */
	public void off() {
		System.out.println("DVD player turning off");
	}
	
	/**
	 * Plays the specified movie.
	 * @param movie - Name of the movie to be played
	 */
	public void playMovie(String movie) {
		System.out.println("Now playing: " + movie);
	}
}

/**
 * Subsystem class representing the Theatre Lights.
 * Handles lighting before and after the movie.
 */
class TheatreLights {
	
	/**
	 * Turns the lights on.
	 */
	public void on() {
		System.out.println("Lights turning on");
	}
	
	/**
	 * Turns the lights off.
	 */
	public void off() {
		System.out.println("Lights turning off");
	}	
}

/**
 * Subsystem class representing the Projector.
 * Handles screen and projection settings.
 */
class Projector {
	
	/**
	 * Turns the projector on.
	 */
	public void on() {
		System.out.println("Projector turning on");
	}
	
	/**
	 * Turns the projector off.
	 */
	public void off() {
		System.out.println("Projector turning off");
	}
	
	/**
	 * Enables wide screen mode.
	 */
	public void wideScreenMode() {
		System.out.println("Enabled: Wide screen mode");
	}
}

/**
 * Facade class that provides a unified interface to start and stop a movie.
 * It simplifies interaction with the complex subsystem components like DVD player,
 * projector, and lights by exposing two simple methods: watchMovie() and endMovie().
 *
 * This class demonstrates the Facade Design Pattern in action.
 */
class MovieFacade {
	
	private DvdPlayer dvdPlayer;
	private TheatreLights theatreLights;
	private Projector projector;
	
	/**
	 * Constructor initializes all subsystem components.
	 * @param dvdPlayer the DVD player to use
	 * @param theatreLights the lights to control
	 * @param projector the projector to operate
	 */
	public MovieFacade(DvdPlayer dvdPlayer, TheatreLights theatreLights, Projector projector) {
		this.dvdPlayer = dvdPlayer;
		this.theatreLights = theatreLights;
		this.projector = projector;
	}
	
	/**
	 * Prepares and starts the movie by coordinating all subsystems.
	 * This is the main method a client would call.
	 */
	public void watchMovie(String movie) {
		System.out.println("\n--- Starting Movie ---");
		theatreLights.off();             // Dim lights
		projector.on();                  // Turn on projector
		projector.wideScreenMode();      // Set screen mode
		dvdPlayer.on();                  // Power on DVD
		dvdPlayer.playMovie(movie);     // Play the selected movie
	}
	
	/**
	 * Shuts down the movie setup by reversing the operations.
	 */
	public void endMovie() {
		System.out.println("\n--- Movie Over ---");
		theatreLights.on();             // Turn lights back on
		projector.off();                // Turn off projector
		dvdPlayer.off();                // Stop DVD player
	}
}

/**
 * Client class that interacts with the facade.
 * The client doesn't need to know the details of each subsystem,
 * it simply uses the MovieFacade for a smooth movie experience.
 */
public class HomeTheatreFacade {
	
	public static void main(String[] args) {
		// Create individual subsystem components
		DvdPlayer dvd = new DvdPlayer();
		TheatreLights lights = new TheatreLights();
		Projector projector = new Projector();
		
		// Pass them to the facade
		MovieFacade movieFacade = new MovieFacade(dvd, lights, projector);
		
		// Start the movie (client only calls one method)
		movieFacade.watchMovie("Batman");
		
		// End the movie
		movieFacade.endMovie();
	}
}