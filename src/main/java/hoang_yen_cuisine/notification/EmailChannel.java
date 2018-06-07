
package hoang_yen_cuisine.notification;

import java.util.Arrays;
import java.util.Set;

import hoang_yen_cuisine.basic.MotherOfRepositories;

public class EmailChannel {
	public void sendEmail(Set<String> users) {
		if (users == null || users.isEmpty()) {
			return;
		}

		System.out.println("Sending email to users:");

		users.forEach(u -> {
			System.out.println(u + "!" + " there are dishes for today, you can make an order now.");
			System.out.println(Arrays.toString(MotherOfRepositories.MENU.toArray()));
		});
	}
}
