
package hoang_yen_cuisine.basic;

import java.util.Collection;

public class LunchOrderProcessor {

	// TODO (vhphuc May 31, 2018): implement memory storage here: a list or whatever

	/**
	 * Creates a lunch order with a given username and {@link Dish}
	 * 
	 * @param username
	 * @param dish
	 * @return
	 */
	public Order makeLunchOrder(String username, Dish dish) {
		// TODO (vhphuc May 31, 2018):
		// store its to memory storage
		throw new UnsupportedOperationException();
	}

	/**
	 * Updates a lunch order by replacing dish
	 * 
	 * @param orderId
	 * @param dish
	 */
	public void updateLunchOrder(int orderId, Dish dish) {
		// TODO (vhphuc May 31, 2018):
		throw new UnsupportedOperationException();
	}

	/**
	 * Generates reports about order placed within a week for Yen
	 * 
	 * @param orders
	 * @return a collection of reports
	 *         <br>
	 *         <b>Note</b>: <em> for simple version, format of report is just a
	 *         string whose contents is total expense of each person</em>
	 */
	public String generateReport(Collection<Order> orders) {
		// TODO (vhphuc May 31, 2018):
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Retrieve a menu for users to pick a dish
	 * @return a collection of {@link Dish}
	 */
	public Collection<Dish> viewMenu() {
		// TODO (vhphuc May 31, 2018):
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Pays for the orders you places within a week
	 * @return <code>true</code> if your payment is successful
	 */
	public boolean pay() {
		// TODO (vhphuc May 31, 2018):
		/*
		 * Algorithm:
		 *  - views the total amount he needs to pay (use random util)
		 *  - pick kind of payment, for now, support only these two methods
		 *    - cash
		 *    - credit card
		 *  - if payment method is credit, then validate that card
		 *  - ask user to confirm
		 *  - always return true for cash, for credit card, based on the validation (use random for boolean too)
		 */
		throw new UnsupportedOperationException();
	}
}
