
package hoang_yen_cuisine.basic;

public class Order {
	
	private static int counter = 0;
	
	private int id;

	private String username;

	private Dish dish;

	public Order(String username, Dish dish) {
		super();
		this.id = ++counter;
		this.username = username;
		this.dish = dish;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", username=" + username + ", dish=" + dish + "]";
	}

}
