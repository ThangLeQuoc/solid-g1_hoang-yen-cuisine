
package hoang_yen_cuisine.basic;

import java.util.*;

public abstract class MotherOfRepositories {
	public static String YEN_USER = "yen";

	public static Collection<Dish> MENU = new ArrayList<>();

	public static Set<String> OTHER_USERS = new HashSet<>();
	
	static {
		// TODO (vhphuc May 31, 2018): 
		// use your imagination to create sample dishes here
	}
	
	public static String CURRENT_USER = null;
	public static boolean GOD_MODE = false;
}
