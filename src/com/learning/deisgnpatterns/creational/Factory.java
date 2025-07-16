package com.learning.deisgnpatterns.creational;

interface notification
{
	void setText();
}

class Email implements notification
{
	public void setText()
	{
		System.out.println("Email Notification");
	}
}

class Message implements notification
{
	public void setText()
	{
		System.out.println("Text Message Notification");
	}
}

class Telegram implements notification
{
	public void setText()
	{
		System.out.println("Telegram Notification");
	}
}


enum NotificationType{
	Email, Message, Telegram
}

class NotificationFactory {
	private NotificationType notificationType;
	
	public NotificationFactory(NotificationType notificationType)
	{
		this.notificationType = notificationType;
	}
	
	public notification getNotificationType()
	{
		if(notificationType == NotificationType.Email)
			return new Email();
		
		else if(notificationType == NotificationType.Telegram)
			return new Telegram();
		
		return new Message();
	}
}

class NotificationService {
	
	private NotificationType notificationType;
		
	public NotificationService(NotificationType notificationType)
	{
		this.notificationType = notificationType;
	}
	
	public void sendNotification()
	{
		NotificationFactory Notification = new NotificationFactory(notificationType);
		Notification.getNotificationType().setText();;
	}
}

public class Factory {
	public static void main(String[] args) {
		
		NotificationService notificationService = new NotificationService(NotificationType.Telegram);
		notificationService.sendNotification();
	
	}
}
