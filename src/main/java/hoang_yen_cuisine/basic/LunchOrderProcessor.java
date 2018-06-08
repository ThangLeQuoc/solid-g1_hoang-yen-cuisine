
package hoang_yen_cuisine.basic;

import static hoang_yen_cuisine.basic.MotherOfRepositories.CURRENT_USER;
import static hoang_yen_cuisine.basic.MotherOfRepositories.GOD_MODE;
import static hoang_yen_cuisine.basic.MotherOfRepositories.MENU;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import hoang_yen_cuisine.payment.PaymentByCard;
import hoang_yen_cuisine.payment.PaymentByCash;

public class LunchOrderProcessor {
	
	private static final LunchOrderProcessor INSTANCE = new LunchOrderProcessor();

	private static final int MAX_PAY_ATTEMPTS = 3;
	
	public static LunchOrderProcessor getInstance() {
		return INSTANCE;
	}
	
	private LunchOrderProcessor() {}

	// dirty cheap counter ;)
	private int orderCounter = 0;
	
	private Map<String, List<Order>> database = new HashMap<>();

	/**
	 * Creates a lunch order with a given username and {@link Dish}
	 * 
	 * @param username
	 * @param dish
	 * @return
	 */
	public Order makeLunchOrder(Dish dish) {
		List<Order> orders = database.computeIfAbsent(CURRENT_USER, k -> new ArrayList<>());
		Order newOrder = new Order(++orderCounter, CURRENT_USER, dish);
		orders.add(newOrder);
		return newOrder;
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
		 *  - views the total amount he needs to pay (use random util)
		 *  - pick kind of payment, for now, support only these two methods
		 *    - cash
		 *    - credit card
		 *  - if payment method is credit, then validate that card
		 *  - ask user to confirm
		 *  - always return true for cash, for credit card, based on the validation (use random for boolean too)
		 */
		try (Scanner reader = new Scanner(System.in)) {
			Random random = new Random();
			int price = 0;
			while (price <= 0) {
				price = random.nextInt();
			}
			System.out.println("Your bill is: "+ price +"k VND\n");
			
			boolean isPaySuccessfully = false;
			int attempts = 0;
			while (!isPaySuccessfully && attempts < MAX_PAY_ATTEMPTS) {
				System.out.println("You have two payment method: \t 1. Card \t 2. Cash \nWhich one you choose? \n");
				int paymentMethod = reader.nextInt();
				boolean isCardValidationPassed = false;
				boolean isAddressValidationPassed = false;
				
				switch (paymentMethod) {
				case 1:
					PaymentByCard paymentByCard = new PaymentByCard();
					isCardValidationPassed = paymentByCard.validateCardInfo();
					isAddressValidationPassed = paymentByCard.validateAddress();
					isPaySuccessfully = paymentByCard.processPayment(isCardValidationPassed, isAddressValidationPassed);
					if (isPaySuccessfully) {
						System.out.println("SUCCESS");
						return true;
					}
					else {
						System.out.println("DO NOT SUCCESS");
						attempts++;
					}
					break;
				case 2:
					PaymentByCash paymentByCash = new PaymentByCash();
					isCardValidationPassed = paymentByCash.validateCardInfo();
					isAddressValidationPassed = paymentByCash.validateAddress();
					isPaySuccessfully = paymentByCash.processPayment(isCardValidationPassed, isAddressValidationPassed);
					if (isPaySuccessfully) {
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
		}
		return false;
	}
}
