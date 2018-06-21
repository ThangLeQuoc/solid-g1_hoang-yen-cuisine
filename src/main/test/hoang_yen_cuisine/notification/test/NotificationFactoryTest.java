package hoang_yen_cuisine.notification.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import hoang_yen_cuisine.notification.EmailChannel;
import hoang_yen_cuisine.notification.NotificationChannel;
import hoang_yen_cuisine.notification.NotificationFactory;
import hoang_yen_cuisine.notification.NotificationType;
import hoang_yen_cuisine.notification.PushNotificationChannel;
import hoang_yen_cuisine.notification.SmsChannel;

public class NotificationFactoryTest {

    private NotificationFactory notificationFactory;

    private NotificationChannel notificationChannel;

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNotificationChannel_ShouldThrowExceptionWithInvalidNotificationType() {
        try {
            notificationChannel = notificationFactory.buildNotificationChannel(null);
            fail("Exception expected earlier");
        } catch (IllegalArgumentException e) {
            assertEquals("Notification type unsupported", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCreateNotificationChannel_ShouldCreateEmailNotificationChannel() {
        notificationChannel = notificationFactory.buildNotificationChannel(NotificationType.EMAIL);
        assertEquals(EmailChannel.class.getSimpleName(), notificationChannel.getClass().getSimpleName());
    }

    @Test
    public void testCreateNotificationChannel_ShouldCreateSMSNotificationChannel() {
        notificationChannel = notificationFactory.buildNotificationChannel(NotificationType.SMS);
        assertEquals(SmsChannel.class.getSimpleName(), notificationChannel.getClass().getSimpleName());
    }

    @Test
    public void testCreateNotificationChannel_ShouldCreatePushNotificationChannel() {
        notificationChannel = notificationFactory.buildNotificationChannel(NotificationType.PUSH_NOTIFICATION);
        assertEquals(PushNotificationChannel.class.getSimpleName(), notificationChannel.getClass().getSimpleName());
    }

}
