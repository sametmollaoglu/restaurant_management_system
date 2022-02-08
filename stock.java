import java.util.ArrayList;

public class stock {
	private String name;
	private double price;
	private int amount;
	private static ArrayList<String> mealnames = new ArrayList<String>();
	
	public stock(String name, double price, int amount){
		this.name= name;
		this.price= price;
		this.amount= amount;
		
		mealnames.add(this.name);
	}
	
	public static ArrayList<String> getMealnames() {
		return mealnames;
	}

	public static void setMealnames(ArrayList<String> mealnames) {
		stock.mealnames = mealnames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}