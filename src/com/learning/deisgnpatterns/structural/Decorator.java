package com.learning.deisgnpatterns.structural;

interface coffee
{
	String getDescription();
	double getCost();
}

class Espresso implements coffee {
	
	public String getDescription()
	{
		return "Espresso";
	}
	
	public double getCost()
	{
		return 30;
	}
}

abstract class CoffeeDecorator implements coffee {
	
	protected coffee decoratedCoffee;

    public CoffeeDecorator(coffee coffee) 
    {
        this.decoratedCoffee = coffee;
    }
}

class Milk extends CoffeeDecorator {
	
	public Milk(coffee coffee)
	{
		super(coffee);
	}
	
	@Override
	public String getDescription()
	{
		return decoratedCoffee.getDescription() + " Milk ";
	}

	@Override
	public double getCost() {
		return decoratedCoffee.getCost() + 5;
	}
}

class Sugar extends CoffeeDecorator {
	
	public Sugar(coffee coffee)
	{
		super(coffee);
	}

	@Override
	public String getDescription() {
		return decoratedCoffee.getDescription() + " Sugar ";
	}

	@Override
	public double getCost() {
		return decoratedCoffee.getCost() + 2;
	}
	
}

public class Decorator {
	
	public static void main(String args[])
	{
		coffee coffee = new Sugar(new Milk(new Espresso()));
		System.out.println(coffee.getDescription() + "   " + coffee.getCost());
		
	}
	
}
