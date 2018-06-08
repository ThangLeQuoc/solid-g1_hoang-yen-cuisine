==== BASE ====

package hoang_yen_cuisine.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class DevModeData {

	public static final Collection<Dish> MENU = new ArrayList<>();

	static {
		Collections.addAll(MENU, 
				new Dish(1, "pork", 25000), 
				new Dish(2, "beef", 35000), 
				new Dish(3, "chicken", 25000), 
				new Dish(4, "french fries", 15000),
				new Dish(5, "rice", 5000), 
				new Dish(6, "pizza", 80000), 
				new Dish(7, "prawn", 35000), 
				new Dish(8, "salmon", 400000));
	}

	public static String USERNAME = "phuc";

	public static boolean GOD_MODE = false;
	
	// FIXME (vhphuc May 31, 2018):
	private static final Collection<?> STORAGE = null;
}
