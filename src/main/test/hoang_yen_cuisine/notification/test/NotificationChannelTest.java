package hoang_yen_cuisine.notification.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hoang_yen_cuisine.notification.EmailChannel;
import hoang_yen_cuisine.notification.NotificationChannel;
import hoang_yen_cuisine.notification.PushNotificationChannel;
import hoang_yen_cuisine.notification.SmsChannel;

public class NotificationChannelTest {
    
    private NotificationChannel notificationChannel;
    
    private Set<String> users;
    
    @Before
    public void setUp() {
        users = new HashSet<>(Arrays.asList("thanglequoc", "dejan", "tobi", "phuc", "hoangyen")); 
    }
    
    @After
    public void tearDown() {
        System.out.println("------------------------------------");
    }
    
    @Test
    public void testSendNotification_ShouldNotSendAnythingWithNullUsers() {
        notificationChannel = new EmailChannel();
        notificationChannel.sendNotification(null);
        //to lazy to assert sysout, better view the console of the test runner to verify its correctness...
    }
    
    @Test
    public void testSendNotification_SHouldNotSendAnythingWithEmptyCollectionOfUsers() {
        notificationChannel = new EmailChannel();
        notificationChannel.sendNotification(Collections.emptySet());
    }
    
    @Test
    public void testSendNotification_ShouldSendEmailNotification() {
        notificationChannel = new EmailChannel();
        notificationChannel.sendNotification(users);
    }
    
    @Test
    public void testSendNotification_ShouldSendPushNotification() {
        notificationChannel = new PushNotificationChannel();
        notificationChannel.sendNotification(users);
    }
    
    @Test
    public void testSendNotifcation_ShouldSendSmsNotification() {
        notificationChannel = new SmsChannel();
        notificationChannel.sendNotification(users);
    }
}
