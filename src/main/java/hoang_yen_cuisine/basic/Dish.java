
package hoang_yen_cuisine.basic;

public class Dish {
	private int id;

	private String name;

	private int price;

	public Dish(int id, String name, int price) {
		super();
		this.id = id;
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
		return "LunchOrder [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}
