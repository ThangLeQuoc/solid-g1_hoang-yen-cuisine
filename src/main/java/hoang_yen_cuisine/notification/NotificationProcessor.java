package hoang_yen_cuisine.notification;

import java.util.Set;

public class NotificationProcessor {
	private EmailChannel emailChanel = new EmailChannel();
	private SmsChannel smsChannel = new SmsChannel();
	private PushNotificationChannel pushNotification = new PushNotificationChannel();
	
	//this violate the DIP principle, the NotificationProcessor is the High Level one, and it depends on the concrete low-level  
	//EmailChannel, SmsChannel ..... What happened if we add more channel? Even when the detail of implementation of each sending channel is different,
	//it does the same thing, so it should has the same method name.
	public void sendNotification (Set<String> users) {
		emailChanel.sendEmail(users);
		smsChannel.sendSMS(users);
		pushNotification.sendPushNotification(users);
	}
}
