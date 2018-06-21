package hoang_yen_cuisine.notification;

public class NotificationFactory {
    private NotificationFactory() {

    }

    public static final NotificationChannel buildNotificationChannel(NotificationType type) {
        switch (type) {
        case EMAIL:
            return new EmailChannel();
        case SMS:
            return new SmsChannel();
        case PUSH_NOTIFICATION:
            return new PushNotificationChannel();
        default:
            throw new IllegalArgumentException("Notification type unsupported");
        }
    }

}
