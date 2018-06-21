
package hoang_yen_cuisine.notification;

public class SmsChannel extends NotificationChannel {
    @Override
    protected void printNotificationType() {
        System.out.println("Sending SMS to users:");
    }
}
