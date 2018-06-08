
package hoang_yen_cuisine.notification;

import java.util.Set;

public class EmailChannel {
	public void sendEmail(Set<String> users) {
		if (users == null || users.isEmpty()) {
			return;
		}

		System.out.println("Sending email to users:");

		users.forEach(u -> {
			System.out.println("Hey " + u + "!" + " there are dishes for today, you can make an order now.");
		});
	}
}
