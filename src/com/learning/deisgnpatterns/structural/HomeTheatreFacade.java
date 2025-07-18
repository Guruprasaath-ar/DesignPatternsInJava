package com.learning.deisgnpatterns.structural;

class DvdPlayer {
	
	public void on() {
		System.out.println("DVD player turning on");
	}
	
	public void off() {
		System.out.println("DVD player turning off");
	}
	
	public void playMovie(String movie) {
		System.out.println("now playing " + movie);
	}

}

class TheatreLights {
	
	public void on() {
		System.out.println("Lights turning on");
	}
	
	public void off() {
		System.out.println("Lights turning off");
	}	
}

class Projector {
	
	public void on( ) {
		System.out.println("Projector turning on");
	}
	
	public void off() {
		System.out.println("Projector turning off");
	}
	
	public void wideScreenMode() {
		System.out.println("enabled : wide screen mode");
	}
	
}

class MovieFacade {
	
	private DvdPlayer dvdPlayer;
	private TheatreLights theatreLights;
	private Projector projector;
	
	public MovieFacade(DvdPlayer dvdPlayer,TheatreLights theatreLights,Projector projector)
	{
		this.dvdPlayer = dvdPlayer;
		this.theatreLights = theatreLights;
		this.projector = projector;
	}
	
	public void watchMovie(String movie) {
		System.out.println("About to start movie");
		theatreLights.off();
		projector.on();
		dvdPlayer.on();
		dvdPlayer.playMovie(movie);
	}
	
	public void endMovie() {
		System.out.println("Movie over");
		theatreLights.on();
		projector.off();
		dvdPlayer.off();
	}
}

public class HomeTheatreFacade {
	
	public static void main(String args[]) {
		
		MovieFacade movieFacade = new MovieFacade(new DvdPlayer(),new TheatreLights(),new Projector());
		movieFacade.watchMovie("Batman");
		movieFacade.endMovie();
	}
	
}
