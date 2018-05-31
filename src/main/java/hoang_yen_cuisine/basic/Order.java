
package hoang_yen_cuisine.basic;

public class Order {
	private int id;

	private String username;

	private Dish dish;

	public Order(int id, String username, Dish dish) {
		super();
		this.id = id;
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
