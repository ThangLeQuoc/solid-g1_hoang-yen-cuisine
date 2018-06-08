
package hoang_yen_cuisine.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MotherOfRepositories {
	public static final String YEN_USER = "yen";

	public static final List<Dish> MENU = new ArrayList<>();

	public static final Set<String> OTHER_USERS = new HashSet<>();
	
	static {
		Collections.addAll(MENU, 
				new Dish("Pork", 30),
				new Dish("Beef", 40), 
				new Dish("Chicken", 25),
				new Dish("Prawns", 40),
				new Dish("Salmon", 60),
				new Dish("Rice", 5),
				new Dish("Pizza", 110));
	}
	
	public static String CURRENT_USER = null;
	public static boolean GOD_MODE = false;
}
