
package hoang_yen_cuisine.basic;

public class LunchOrder {
	private String name;

	private int price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "LunchOrder [name=" + name + ", price=" + price + "]";
	}

}
