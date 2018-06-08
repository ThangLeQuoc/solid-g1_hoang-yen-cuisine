
package hoang_yen_cuisine.basic;

public class Dish {
	private static int counter = 0;
	
	private int id;

	private String name;

	private int price;

	public Dish(String name, int price) {
		super();
		this.id = ++counter;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Dish " + id + ": " +  name + ", price: " + price;
	}

}
