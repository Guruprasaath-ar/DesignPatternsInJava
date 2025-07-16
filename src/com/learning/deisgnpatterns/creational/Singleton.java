package com.learning.deisgnpatterns.creational;

public class Singleton {
	
	private Singleton()
	{
		
	}
	
	public static class Holder {
		private static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance()
	{
		return Holder.instance;
	}
}
