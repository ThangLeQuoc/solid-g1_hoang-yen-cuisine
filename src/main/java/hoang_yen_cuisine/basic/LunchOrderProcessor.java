
package hoang_yen_cuisine.basic;

import static hoang_yen_cuisine.basic.MotherOfRepositories.CURRENT_USER;
import static hoang_yen_cuisine.basic.MotherOfRepositories.GOD_MODE;
import static hoang_yen_cuisine.basic.MotherOfRepositories.MENU;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import hoang_yen_cuisine.payment.IPayProcessor;
import hoang_yen_cuisine.payment.PaymentByCard;
import hoang_yen_cuisine.payment.PaymentByCash;

public class LunchOrderProcessor {
	
	private static final LunchOrderProcessor INSTANCE = new LunchOrderProcessor();

	private static final int MAX_PAY_ATTEMPTS = 3;
	
	public static LunchOrderProcessor getInstance() {
		return INSTANCE;
	}
	
	private LunchOrderProcessor() {}

	private Scanner reader = new Scanner(System.in);
	
	private Map<String, List<Order>> database = new HashMap<>();

	/**
	 * Creates a lunch order with a given dishId for current user
	 * 
	 * @param dishId
	 * @return
	 */
	public Order makeLunchOrder(int dishId) {
		final Dish dish = findDish(dishId);
		List<Order> orders = database.computeIfAbsent(CURRENT_USER, k -> new ArrayList<>());
		Order newOrder = new Order(CURRENT_USER, dish);
		orders.add(newOrder);
		System.out.println("You have made an order: " + newOrder);
		return newOrder;
	}
	
	private Dish findDish(int id) {
		// for simplicity, use linear search
		for (Dish d: MENU) {
			if (d.getId() == id) {
				return d;
			}
		}
		throw new IllegalStateException("Dish not found with this ID: " + id);
	}

	/**
	 * Generates reports about order placed within a week for Yen
	 * 
	 * @return a collection of reports
	 *         <br>
	 *         <b>Note</b>: <em> for simple version, format of report is just a
	 *         string whose contents is total expense of each person</em>
	 */
	public String generateReport() {
		if (!GOD_MODE) {
			throw new IllegalAccessError("You ain't Yen!!!");
		}
		StringBuilder sb = new StringBuilder();
		for (Entry<String, List<Order>> ordersByUser : database.entrySet()) {
			int total = 0;
			sb.append(ordersByUser.getKey());
			for (Order order : ordersByUser.getValue()) {
				sb.append(String.format("%n\t Order %d:  %s %d", order.getId(), order.getDish().getName(), order.getDish().getPrice()));
				total += order.getDish().getPrice();
			}
			sb.append("\nTotal: ").append(total);
			sb.append("\n----------------------------\n");
		}
		return sb.toString();
	}
	
	/**
	 * Retrieve a menu for users to pick a dish
	 * @return a collection of {@link Dish}
	 */
	public Collection<Dish> viewMenu() {
		System.out.println(">>> MENU for today <<<");
		String menu = StringUtils.join(MENU, "\n");
		System.out.println(menu);
		return MENU;
	}
	
	/**
	 * Pays for the orders you places within a week
	 * @return <code>true</code> if your payment is successful
	 */
	public boolean pay() {
		/*
		 * Algorithm:
		 *  - views the total amount he needs to pay
		 *  - pick kind of payment, for now, support only these two methods
		 *    - cash
		 *    - credit card
		 *  - if payment method is credit, then validate that card
		 *  - ask user to confirm
		 *  - always return true for cash, for credit card, based on the validation (use random for boolean too)
		 */
		if (CURRENT_USER == null) {
			System.err.println("Must log in first by -user option first");
			return false;
		}
		List<Order> myOrders = database.get(CURRENT_USER);
		StringBuilder sb = new StringBuilder();
		sb.append("My lunch orders:");
		for (Order order : myOrders) {
			sb.append("\n\t#").append(order.getId()).append(": ");
			sb.append(order.getDish().getName());
			sb.append(" - ").append(order.getDish().getPrice());
		}
		sb.append("\n----------------");
		int myTotalCost = myOrders.stream().mapToInt(o -> o.getDish().getPrice()).sum();
		sb.append("\nYour bill is ").append(myTotalCost).append("k VND\n");
		System.out.println(sb.toString());

		boolean isPaySuccessfully = false;
		int attempts = 0;
		while (!isPaySuccessfully && attempts < MAX_PAY_ATTEMPTS) {
			System.out.println("You have two payment method: \t 1. Card \t 2. Cash \nWhich one you choose? \n");
			int paymentMethod = reader.nextInt();

			switch (paymentMethod) {
			case 1:
				IPayProcessor paymentByCard = new PaymentByCard();
				isPaySuccessfully = paymentByCard.processPayment();
				if (isPaySuccessfully) {
					database.remove(CURRENT_USER);
					System.out.println("SUCCESS");
					return true;
				}
				else {
					System.out.println("DO NOT SUCCESS");
					attempts++;
				}
				break;
			case 2:
			    IPayProcessor paymentByCash = new PaymentByCash();
				isPaySuccessfully = paymentByCash.processPayment();
				if (isPaySuccessfully) {
					database.remove(CURRENT_USER);
					System.out.println("SUCCESS");
					return true;
				}
				else {
					System.out.println("DO NOT SUCCESS");
					attempts++;
				}
				break;
			default:
				System.out.println("Sorry, no payment method available for this option!");
				break;
			}

			if (attempts == 3) {
				System.out.println("YOU TRY TOO MANY TIME");
			}
		}
		return false;
	}
	
	public void tearDown() {
		// it's a hack
		reader.close();
	}
}
