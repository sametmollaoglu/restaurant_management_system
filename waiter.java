import java.util.ArrayList;

public class waiter {
	private String name;
	private double salary;
	private static ArrayList<String> waiternames = new ArrayList<String>();
	private table waiterTableService[]=new table[3];
	private int tableCounter= 0;
	
	
	public waiter(String name, double salary) {
		this.name = name;
		this.salary = salary;
		
		waiternames.add(this.name);
	}
	
	
	public table[] getWaiterTableService() {
		return waiterTableService;
	}

	public void setWaiterTableService(table[] waiterTableService) {
		this.waiterTableService = waiterTableService;
	}

	public static ArrayList<String> getWaiternames() {
		return waiternames;
	}
	public static void setWaiternames(ArrayList<String> waiternames) {
		waiter.waiternames = waiternames;
	}
	public int getTableCounter() {
		return tableCounter;
	}
	public void setTableCounter(int tableCounter) {
		this.tableCounter = tableCounter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}