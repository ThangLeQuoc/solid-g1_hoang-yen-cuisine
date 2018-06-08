
package hoang_yen_cuisine.basic;

public class Dish implements Comparable<Dish> {
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
		return "Dish " + id + ": " + name + ", price: " + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dish other = (Dish) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Dish other) {
		return Integer.compare(id, other.getId());
	}

}
