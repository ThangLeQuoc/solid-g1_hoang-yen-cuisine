package hoang_yen_cuisine.notification;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public abstract class NotificationChannel {
    
    public final void sendNotification(Set<String> users) {
        if (CollectionUtils.isEmpty(users)) {
            return;
        }
        printNotificationType();
        printReminder(users);
    }
    
    protected abstract void printNotificationType();
    
    private void printReminder(Set<String> users) {
        users.forEach(u -> {
            System.out.println("Hey " + u + "!" + " there are dishes for today, you can make an order now.");
        });
    }
    
}
