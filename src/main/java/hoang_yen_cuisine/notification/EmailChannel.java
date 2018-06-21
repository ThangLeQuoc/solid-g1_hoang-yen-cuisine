
package hoang_yen_cuisine.notification;

public class EmailChannel extends NotificationChannel {
    @Override
    protected void printNotificationTypes() {
        System.out.println("Sending email to users:");
    }
}
