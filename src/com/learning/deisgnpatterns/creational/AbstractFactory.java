package com.learning.deisgnpatterns.creational;

interface paymentGateway
{
	void processPayment(double amount);
}

interface invoice
{
	void generateInvoice();
}

class PayPal implements paymentGateway {
	
	public void processPayment(double amount)
	{
		System.out.println("paypal : " + amount);
	}
}

class RazorPay implements paymentGateway {
	
	public void processPayment(double amount)
	{
		System.out.println("razorPay : " + amount);
	}
	
}

class GST implements invoice {
	
	public void generateInvoice()
	{
		System.out.println("GST Generated");
	}
	
}

class UsInvoice implements invoice {
	
	public void generateInvoice()
	{
		System.out.println("US Invoice Generated");
	}
	
}

enum Country {
	india, usa
}

enum GatewayType {
	razorPay, paypal
}

class CheckoutService {
	
	private Country country;
	private paymentGateway gateway;
	
	public CheckoutService(Country country)
	{
		this.country = country;
	}
	
	public void processPayment(GatewayType gatewayType,double amount)
	{
		if(gatewayType == GatewayType.paypal)
		{
			gateway = new PayPal();
			gateway.processPayment(amount);
			invoice invoice = new GST();
			invoice.generateInvoice();
		}
		
		else if(gatewayType ==  GatewayType.razorPay)
		{
			gateway = new RazorPay();
			gateway.processPayment(amount);
			invoice invoice = new UsInvoice();
			invoice.generateInvoice();
		}
	}
}

public class AbstractFactory {

}
