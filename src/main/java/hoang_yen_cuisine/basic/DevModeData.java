
package hoang_yen_cuisine.basic;

import java.util.ArrayList;
import java.util.Collection;

public abstract class DevModeData {

	public static final Collection<Dish> MENU = new ArrayList<>();
	
	static {
		// TODO (vhphuc May 31, 2018): 
		// use your imagination to create sample dishes here
	}
	
	public static String USERNAME = "phuc";
	public static boolean GOD_MODE = false;
}
