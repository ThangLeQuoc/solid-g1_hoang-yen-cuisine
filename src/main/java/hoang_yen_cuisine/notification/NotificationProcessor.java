package hoang_yen_cuisine.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationProcessor {
    private static final NotificationProcessor INSTANCE = new NotificationProcessor();

    private List<NotificationChannel> notificationChannels;

    public static final NotificationProcessor getInstance() {
        return INSTANCE;
    }

    private NotificationProcessor() {
        notificationChannels = new ArrayList<>();
    }

    public void sendNotification(Set<String> users) {
        notificationChannels.stream().forEach(notificationChannel -> notificationChannel.sendNotification(users));
    }

    public void sendNotification(String... users) {
        Set<String> setOfUsers = new HashSet<>();
        Collections.addAll(setOfUsers, users);
        sendNotification(setOfUsers);
    }

    public List<NotificationChannel> getNotificationChannels() {
        return notificationChannels;
    }

    public void registerNotificationChannel(NotificationChannel notificationChannel) {
        boolean channelExists = false;
        for (NotificationChannel notiChannel : notificationChannels) {
            if (notiChannel.getClass().getSimpleName().equals(notificationChannel.getClass().getSimpleName())) {
                channelExists = true;
            }
        }
        
        if (!channelExists) {
            notificationChannels.add(notificationChannel);            
        }
    }

    public void removeNotificationChannel(NotificationChannel notificationChannel) {
        notificationChannels.remove(notificationChannel);
    }

}
