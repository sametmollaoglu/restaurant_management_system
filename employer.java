import java.util.ArrayList;

public class employer {
	private String name;
	private double salary;
	private static ArrayList<String> employernames = new ArrayList<String>();
	private static ArrayList<table> employertableobject = new ArrayList<table>();
	private int tableCounter= 0;
	private static int IDCounter= 0;
	
	public employer(String name, double salary) {
		this.name= name;
		this.salary= salary;
		employernames.add(this.name);
	}
	
	public void addTable(String employerName,int customerCapacity) {
		
		if(tableCounter<2 && employertableobject.size()<10) {
			table temp= new table(employerName,customerCapacity);
			
			employertableobject.add(temp);
			temp.setTableID(IDCounter);
			
			IDCounter++;
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: create_table");
			System.out.println("A new table has succesfully been added");
			tableCounter++;
		}
		
		else if(employertableobject.size()==10){
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: create_table");
			System.out.println("Not allowed to exceed max. number of tables, 10");
		}
		
		else if(tableCounter==2){
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: create_table");
			System.out.println(this.name+" has already created 2 tables!");
		}
	}
	
	public static ArrayList<table> getEmployertableobject() {
		return employertableobject;
	}

	public static void setEmployertableobject(ArrayList<table> employertableobject) {
		employer.employertableobject = employertableobject;
	}

	public static ArrayList<String> getEmployernames() {
		return employernames;
	}
	public static void setEmployernames(ArrayList<String> employernames) {
		employer.employernames = employernames;
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

	public int getTableCounter() {
		return tableCounter;
	}

	public void setTableCounter(int tableCounter) {
		this.tableCounter = tableCounter;
	}
}
