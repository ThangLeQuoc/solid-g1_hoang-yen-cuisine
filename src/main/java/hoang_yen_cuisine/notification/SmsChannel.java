
package hoang_yen_cuisine.notification;

public class SmsChannel extends NotificationChannel {
    @Override
    protected void printNotificationTypes() {
        System.out.println("Sending SMS to users:");
    }
}
