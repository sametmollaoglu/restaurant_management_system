import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment2 {
	public static void main(String[] args) {
		ArrayList<stock> mealobjects = new ArrayList<stock>();
		ArrayList<employer> employerobjects = new ArrayList<employer>();
		ArrayList<waiter> waiterobjects = new ArrayList<waiter>();
		int xFor;
		int yFor;
		int zFor;
		int tFor;
		try{
			BufferedReader file = new BufferedReader(new FileReader("setup.txt"));		
			String setupline;
			
			while((setupline = file.readLine()) != null) {
				
				String setupsatir[] = setupline.split(" ");				
				
				if (setupsatir[0].equals("add_item")) {
					
					String mealFeatures[] = setupsatir[1].split(";");
					String yemekIsim= mealFeatures[0];
					double yemekFiyat= Double.parseDouble(mealFeatures[1]);
					int yemekAdet= Integer.parseInt(mealFeatures[2]);
					
					stock temp = new stock(yemekIsim,yemekFiyat,yemekAdet);
					
					mealobjects.add(temp);
				}
				else if(setupsatir[0].equals("add_employer")){
					
					String employerFeatures[] = setupsatir[1].split(";");
					String isverenIsim= employerFeatures[0];
					double isverenMaas= Double.parseDouble((employerFeatures[1]));
					
					employer temp = new employer(isverenIsim,isverenMaas);
					employerobjects.add(temp);
				}
				else if(setupsatir[0].equals("add_waiter")){
					
					String waiterFeatures[] = setupsatir[1].split(";");
					String garsonIsim= waiterFeatures[0];
					double garsonMaas= Double.parseDouble((waiterFeatures[1]));
					
					waiter temp= new waiter(garsonIsim,garsonMaas);
					waiterobjects.add(temp);
				}
				
			}
			file.close();
		}
		catch (IOException e) {
			System.out.println("Dosya okunamadı.");
		}
		try{		
			BufferedReader file = new BufferedReader(new FileReader("commands.txt"));
			String commandline;
			
			while((commandline = file.readLine()) != null) {
				
				String commandsatir[] = commandline.split(" ");
				
				if(commandsatir[0].equals("create_table")){
					
					String createTableFeatures[]= commandsatir[1].split(";");
					boolean isthere= false;
					for(xFor= 0; xFor< employerobjects.size(); xFor++) {
						
						if(createTableFeatures[0].equals((employerobjects.get(xFor)).getName())) {
							employerobjects.get(xFor).addTable(createTableFeatures[0],Integer.parseInt(createTableFeatures[1]));
							employerobjects.get(xFor).setSalary(employerobjects.get(xFor).getSalary()+(double)employerobjects.get(xFor).getSalary()/10);
							
							isthere= true;			
						}
						
					}
					if(isthere== false) {
						System.out.println("***********************************");							
						System.out.println("PROGRESSING COMMAND: create_table");
						System.out.println("There is no employer named "+createTableFeatures[0]);
						
					}
					
				}
				else if(commandsatir[0].equals("new_order")){
					boolean waiterExists=false;
					boolean appropriateTableExists=false;
					
					String newOrderFeatures[]= commandsatir[1].split(";");
					
					for(xFor=0;xFor<employer.getEmployertableobject().size();xFor++) {
						if(Integer.parseInt(newOrderFeatures[1])<=employer.getEmployertableobject().get(xFor).getCustomerCapacity()) {
							if(employer.getEmployertableobject().get(xFor).isCurrentService()==false) {
								
								appropriateTableExists=true;
								
								for(yFor=0;yFor<waiterobjects.size();yFor++) {
									if(newOrderFeatures[0].equals(waiterobjects.get(yFor).getName())) {
										
										waiterExists=true;
										
										if(waiterobjects.get(yFor).getWaiterTableService()[0]==null){
											
											waiterobjects.get(yFor).getWaiterTableService()[0]=employer.getEmployertableobject().get(xFor);
											employer.getEmployertableobject().get(xFor).setWaiterWhoHaveThisTable(newOrderFeatures[0]);
											employer.getEmployertableobject().get(xFor).setCurrentService(true);
											String whatOrder[]=newOrderFeatures[2].split(":");
											employer.getEmployertableobject().get(xFor).addToOrderTimes(1);
											int itemcounter=0;
											
											for(zFor=0;zFor<whatOrder.length;zFor++) {
												
												String forCountArray[]=whatOrder[zFor].split("-");
												
												////////////////
												for(tFor=0;tFor<mealobjects.size();tFor++) {
													if(mealobjects.get(tFor).getName().equals(forCountArray[0])) {
														if(mealobjects.get(tFor).getAmount()>=Integer.parseInt(forCountArray[1])) {
															itemcounter+=Integer.parseInt(forCountArray[1]);
														}
														else if(mealobjects.get(tFor).getAmount()<Integer.parseInt(forCountArray[1])) {
															itemcounter+=mealobjects.get(tFor).getAmount();
															
														}
													}
												}
												
											}
											
											employer.getEmployertableobject().get(xFor).addToOrderItemTimes(itemcounter);
											
											if(itemcounter<=10) {
												waiterobjects.get(yFor).setSalary(waiterobjects.get(yFor).getSalary()+(double)waiterobjects.get(yFor).getSalary()/20);
												employer.getEmployertableobject().get(xFor).addOrderToOwnTable(whatOrder,mealobjects);
												break;
											}
											
											else if(itemcounter>10) {
												System.out.println("***********************************");
												System.out.println("PROGRESSING COMMAND: new_order");
												System.out.println("Not allowed to exceed max number of orders!");
												break;
											}
											break;
										}
										else if(waiterobjects.get(yFor).getWaiterTableService()[1]==null){
											
											waiterobjects.get(yFor).getWaiterTableService()[1]=employer.getEmployertableobject().get(xFor);
											employer.getEmployertableobject().get(xFor).setWaiterWhoHaveThisTable(newOrderFeatures[0]);
											employer.getEmployertableobject().get(xFor).setCurrentService(true);
											String whatOrder[]=newOrderFeatures[2].split(":");											
											
											employer.getEmployertableobject().get(xFor).addToOrderTimes(1);
											int itemcounter=0;
											
											for(zFor=0;zFor<whatOrder.length;zFor++) {
										
												String forCountArray[]=whatOrder[zFor].split("-");
												
												///////////////7
												for(tFor=0;tFor<mealobjects.size();tFor++) {
													if(mealobjects.get(tFor).getName().equals(forCountArray[0])) {
														if(mealobjects.get(tFor).getAmount()>=Integer.parseInt(forCountArray[1])) {
															itemcounter+=Integer.parseInt(forCountArray[1]);
														}
														else if(mealobjects.get(tFor).getAmount()<Integer.parseInt(forCountArray[1])) {
															itemcounter+=mealobjects.get(tFor).getAmount();
															
														}
													}
												}
												
											}
										
											employer.getEmployertableobject().get(xFor).addToOrderItemTimes(itemcounter);
										
											if(itemcounter<=10) {	
												employer.getEmployertableobject().get(xFor).addOrderToOwnTable(whatOrder,mealobjects);
												break;
											}
										
											else if(itemcounter>10) {
												System.out.println("***********************************");
												System.out.println("PROGRESSING COMMAND: new_order");
												System.out.println("Not allowed to exceed max number of orders!");
												break;
											}
											break;
										}
									
										else if(waiterobjects.get(yFor).getWaiterTableService()[2]==null){
											waiterobjects.get(yFor).getWaiterTableService()[2]=employer.getEmployertableobject().get(xFor);
											employer.getEmployertableobject().get(xFor).setWaiterWhoHaveThisTable(newOrderFeatures[0]);
											employer.getEmployertableobject().get(xFor).setCurrentService(true);
											String whatOrder[]=newOrderFeatures[2].split(":");											
										
											employer.getEmployertableobject().get(xFor).addToOrderTimes(1);
											int itemcounter=0;
										
											for(zFor=0;zFor<whatOrder.length;zFor++) {
										
												String forCountArray[]=whatOrder[zFor].split("-");
												
												////////////////
												for(tFor=0;tFor<mealobjects.size();tFor++) {
													if(mealobjects.get(tFor).getName().equals(forCountArray[0])) {
														if(mealobjects.get(tFor).getAmount()>=Integer.parseInt(forCountArray[1])) {
															itemcounter+=Integer.parseInt(forCountArray[1]);
														}
														else if(mealobjects.get(tFor).getAmount()<Integer.parseInt(forCountArray[1])) {
															itemcounter+=mealobjects.get(tFor).getAmount();
															
														}
													}
												}
												
											}
										
											employer.getEmployertableobject().get(xFor).addToOrderItemTimes(itemcounter);
										
											if(itemcounter<=10) {	
												employer.getEmployertableobject().get(xFor).addOrderToOwnTable(whatOrder,mealobjects);
												break;
											}
										
											else if(itemcounter>10) {
												System.out.println("***********************************");
												System.out.println("PROGRESSING COMMAND: new_order");
												System.out.println("Not allowed to exceed max number of orders!");
												break;
											}
											break;
										}
										else {
											System.out.println("***********************************");
											System.out.println("PROGRESSING COMMAND: new_order");
											System.out.println("Not allowed to service max. number of tables, 3");
											break;
										}
									}
								}
								
								if(waiterExists==false) {
									System.out.println("***********************************");
									System.out.println("PROGRESSING COMMAND: new_order");
									System.out.println("There is no waiter named "+newOrderFeatures[0]);
									break;
								}
								break;
							}
						}
					}
				
					if(appropriateTableExists==false) {
						System.out.println("***********************************");							
						System.out.println("PROGRESSING COMMAND: new_order");
						System.out.println("There is no appropriate table for this order!");
				
					}
				}
			
				else if(commandsatir[0].equals("add_order")) {
					boolean waiterAssignThisTable= false;
					boolean isWaiterHere= false;					
					String addOrderFeatures[]= commandsatir[1].split(";");
					
					for(xFor=0;xFor<waiter.getWaiternames().size();xFor++) {
				
						if(waiter.getWaiternames().get(xFor).equals(addOrderFeatures[0])) {
							isWaiterHere=true;
				
						}
					}					
				
					if(isWaiterHere==false) {
						System.out.println("***********************************");
						System.out.println("PROGRESSING COMMAND: add_order");
						System.out.println("There is no waiter named "+addOrderFeatures[0]);
					}
					
					if(isWaiterHere==true) {
					
						for(xFor=0;xFor<employer.getEmployertableobject().size();xFor++) {
							
							if(addOrderFeatures[0].equals(employer.getEmployertableobject().get(xFor).getWaiterWhoHaveThisTable())) {	
								
								if(employer.getEmployertableobject().get(xFor).getTableID()==Integer.parseInt(addOrderFeatures[1])) { 
							
									if(employer.getEmployertableobject().get(xFor).isCurrentService()==true) {   
										employer.getEmployertableobject().get(xFor).addingOrderToOwnTable(addOrderFeatures, mealobjects);
										waiterAssignThisTable=true;
								
										for(yFor=0;yFor<waiterobjects.size();yFor++) {
								
											if(waiterobjects.get(yFor).getName().equals(addOrderFeatures[0])) {
												waiterobjects.get(yFor).setSalary(waiterobjects.get(yFor).getSalary()+(double)waiterobjects.get(yFor).getSalary()/20);
											}
										}
									}
								}
							}
						}
					}
				
					if(waiterAssignThisTable==false) {
						System.out.println("***********************************");
						System.out.println("PROGRESSING COMMAND: add_order");
						System.out.println("This table is either not in service now or "+addOrderFeatures[0]+" cannot be assigned this table!");
				
					}
				}
			
				else if(commandsatir[0].equals("check_out")) {
					boolean waiterIsHere= false;
					boolean waiterCanAssignThisTable= false;
					int arrayPointer=0;
					double totalPrice=0;
					double mealPrice=0;
					String mealForCheck;
					int mealTimes;
					String checkOutFeatures[]=commandsatir[1].split(";");
					
					for(xFor=0;xFor<waiter.getWaiternames().size();xFor++) {
				
						if(waiter.getWaiternames().get(xFor).equals(checkOutFeatures[0])) {
							waiterIsHere=true;
						}
					}					
					if(waiterIsHere==false) {
						System.out.println("***********************************");
						System.out.println("PROGRESSING COMMAND: check_out");
						System.out.println("There is no waiter named "+checkOutFeatures[0]);				
					}
				
					if(waiterIsHere==true) {

						for(xFor=0;xFor<employer.getEmployertableobject().size();xFor++) {
						
							if(checkOutFeatures[0].equals(employer.getEmployertableobject().get(xFor).getWaiterWhoHaveThisTable())) {						
							
								if(employer.getEmployertableobject().get(xFor).getTableID()==Integer.parseInt(checkOutFeatures[1])) {
							
									if(employer.getEmployertableobject().get(xFor).isCurrentService()==true) {
										waiterCanAssignThisTable=true;
										System.out.println("***********************************");
										System.out.println("PROGRESSING COMMAND: check_out");
									
										while(arrayPointer<10) {
											mealTimes=0;
											mealForCheck=employer.getEmployertableobject().get(xFor).getOrdersOfTable()[arrayPointer];											
											arrayPointer++;
											mealTimes++;
									
											while(arrayPointer<10 && mealForCheck==employer.getEmployertableobject().get(xFor).getOrdersOfTable()[arrayPointer]) {
												arrayPointer++;
												mealTimes++;
											}
										
											for(yFor=0;yFor<mealobjects.size();yFor++) {
										
												if(mealForCheck.equals(mealobjects.get(yFor).getName())) {
													mealPrice=(double) mealobjects.get(yFor).getPrice();
													break;
												}
												else {
													continue;
												}
											}
										
											int spaces=10;
											System.out.print(mealForCheck+": ");
											for(zFor=0;zFor<spaces-mealForCheck.length();zFor++) {
												System.out.print(" ");
											}
											System.out.println(mealPrice+" (x "+mealTimes+") "+(mealPrice*mealTimes+" $"));
											totalPrice+=mealPrice*mealTimes;
											
											if(arrayPointer==10) {
												System.out.println("Total:      "+totalPrice+" $");
												break;
											}
										
											if(employer.getEmployertableobject().get(xFor).getOrdersOfTable()[arrayPointer]==null) {
												System.out.println("Total:      "+totalPrice+" $");
												break;
											}
										}
										String emptyOrderForCheckOut[]=new String[10];
										int empytItemTimesForCheckout[]=new int[10];

										
										employer.getEmployertableobject().get(xFor).setCurrentService(false);
										employer.getEmployertableobject().get(xFor).setOrdersOfTable(emptyOrderForCheckOut);
										employer.getEmployertableobject().get(xFor).setWaiterWhoHaveThisTable(null);
										employer.getEmployertableobject().get(xFor).setOrderItemTimes(empytItemTimesForCheckout);
										employer.getEmployertableobject().get(xFor).setOrderTimes(0);
										
										break;
									}
								}
							}
						}						
					}
				
					if(waiterCanAssignThisTable==false && waiterIsHere==true) {
						System.out.println("***********************************");
						System.out.println("PROGRESSING COMMAND: check_out");
						System.out.println("This table is either not in service now or "+checkOutFeatures[0]+" cannot be assigned this table!");
					}
				}
			
				else if(commandsatir[0].equals("stock_status")) {
					System.out.println("***********************************");
					System.out.println("PROGRESSING COMMAND: stock_status");
					int spaceTime=14;
				
					for(xFor=0;xFor<mealobjects.size();xFor++) {
				
						System.out.print(mealobjects.get(xFor).getName()+":");
					
						for(yFor=0;yFor<spaceTime-mealobjects.get(xFor).getName().length();yFor++) {
					
							System.out.print(" ");
						}
						System.out.println(mealobjects.get(xFor).getAmount());
					}
				}
			
				else if(commandsatir[0].equals("get_table_status")) {
					System.out.println("***********************************");
					System.out.println("PROGRESSING COMMAND: get_table_status");
				
					for(xFor=0;xFor<employer.getEmployertableobject().size();xFor++) {
					
						if(employer.getEmployertableobject().get(xFor).isCurrentService()==true) {
							System.out.println("Table "+employer.getEmployertableobject().get(xFor).getTableID()+": Reserved ("
							+employer.getEmployertableobject().get(xFor).getWaiterWhoHaveThisTable()+")");
						}
					
						else if(employer.getEmployertableobject().get(xFor).isCurrentService()!=true) {
							System.out.println("Table "+employer.getEmployertableobject().get(xFor).getTableID()+": Free");
						}
					}
				}
			
				else if(commandsatir[0].equals("get_order_status")) {
					System.out.println("***********************************");
					System.out.println("PROGRESSING COMMAND: get_order_status");
				
					for(xFor=0;xFor<employer.getEmployertableobject().size();xFor++) {
				
						System.out.println("Table: "+employer.getEmployertableobject().get(xFor).getTableID());
						System.out.println("     "+employer.getEmployertableobject().get(xFor).getOrderTimes()+" order(s)");
				
						for(yFor=0;yFor<employer.getEmployertableobject().get(xFor).getOrderItemTimes().length;yFor++) {
				
							if(employer.getEmployertableobject().get(xFor).getOrderItemTimes()[yFor]!=0) {	
								System.out.println("          "+employer.getEmployertableobject().get(xFor).getOrderItemTimes()[yFor]+" item(s)");
							}	
						}
					}
				}
			
				else if(commandsatir[0].equals("get_employer_salary")) {
					int space=10;
					System.out.println("***********************************");
					System.out.println("PROGRESSING COMMAND: get_employer_salary");
				
					for(xFor=0;xFor<employerobjects.size();xFor++) {
				
						System.out.print("Salary for "+employerobjects.get(xFor).getName()+":");
				
						for(yFor=0;yFor<space-employerobjects.get(xFor).getName().length();yFor++) {
				
							System.out.print(" ");
						}
						System.out.println(employerobjects.get(xFor).getSalary());
					}
				}
			
				else if(commandsatir[0].equals("get_waiter_salary")) {
					int space=10;
					System.out.println("***********************************");
					System.out.println("PROGRESSING COMMAND: get_waiter_salary");
				
					for(xFor=0;xFor<waiterobjects.size();xFor++) {
						System.out.print("Salary for "+waiterobjects.get(xFor).getName()+":");
					
						for(yFor=0;yFor<space-waiterobjects.get(xFor).getName().length();yFor++) {
							System.out.print(" ");
						}
						System.out.println(waiterobjects.get(xFor).getSalary());
					}
				}				
		}
	
			file.close();
	
		}
		
		catch(IOException e) {
			System.out.println("Dosya okunamadı.");
		
		}
	}
}