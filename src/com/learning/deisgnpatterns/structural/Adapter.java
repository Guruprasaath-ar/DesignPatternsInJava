package com.learning.deisgnpatterns.structural;

interface paymentGateway {
	void pay(String orderId,double amount);
}

class PayUGateway implements paymentGateway {
	public void pay(String orderId,double amount)
	{
		System.out.println("payUGateway : " + amount + " is deducted");
	}
}

class RazorpayAPI {
	public void makePayment(String orderId,double amount)
	{
		System.out.println("Razorpay API : " + amount + " is deducted");
	}
}

class RazorPayAdapter implements paymentGateway {
	
	private RazorpayAPI razorpay;
	
	public RazorPayAdapter()
	{
		this.razorpay = new RazorpayAPI();
	}
	
	public void pay(String orderID,double amount)
	{
		razorpay.makePayment(orderID, amount);
	}
	
}

class CheckoutService {
	
	private paymentGateway gateway;
	
	public CheckoutService(paymentGateway gateway)
	{
		this.gateway = gateway;
	}
	
	void checkOut(String Id,double amount)
	{
		gateway.pay(Id, amount);
	}
	
}


public class Adapter {
	
	public static void main(String args[])
	{
		CheckoutService cs = new CheckoutService(new PayUGateway());
		cs.checkOut("1", 100);
		
	}

}
