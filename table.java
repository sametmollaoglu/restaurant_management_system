import java.util.ArrayList;

public class table{
	private String employerName;
	private int customerCapacity;
	private boolean currentService = false;
	private int tableID;
	private String ordersOfTable[]=new String[10];
	private String waiterWhoHaveThisTable;
	private int orderItemTimes[]=new int[10];
	private int orderTimes=0;
	
	public void addToOrderItemTimes(int addThis) {
		int a;

		for(a=0;a<orderItemTimes.length;a++) {
		
			if(orderItemTimes[a]==0) {
				orderItemTimes[a]=addThis;
				break;
			}
			else {
				continue;
			}
		}
	}

	public int[] getOrderItemTimes() {
		return orderItemTimes;
	}
	
	public void setOrderItemTimes(int[] orderItemTimes) {
		this.orderItemTimes = orderItemTimes;
	}
	
	public void addToOrderTimes(int addThis) {
		orderTimes+=addThis;
	}

	public int getOrderTimes() {
		return orderTimes;
	}

	public void setOrderTimes(int orderTimes) {
		this.orderTimes = orderTimes;
	}

	public void addingOrderToOwnTable(String addOrderFeatures[],ArrayList<stock> mealobjects) {
		String addOrders[]=addOrderFeatures[2].split(":");
		addToOrderTimes(1);
		int newItems=0;
		int oldItems=0;
		int rFor;
		int yFor;
		int zFor;
		boolean mealIsHere;
	
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: add_order");
		
		for(rFor=0;rFor<ordersOfTable.length;rFor++) {
		
			if(ordersOfTable[rFor]!=null) {
				oldItems++;
			}
		}
	
		for(rFor=0;rFor<addOrders.length;rFor++) {
		
			String singleAddOrder[]=addOrders[rFor].split("-");
			////////////////////7
			for(yFor=0;yFor<mealobjects.size();yFor++) {
				if(mealobjects.get(yFor).getName().equals(singleAddOrder[0])){
					if(mealobjects.get(yFor).getAmount()>=Integer.parseInt(singleAddOrder[1])) {
						newItems+=Integer.parseInt(singleAddOrder[1]);
					}
					else if(mealobjects.get(yFor).getAmount()<Integer.parseInt(singleAddOrder[1])) {
						newItems+=mealobjects.get(yFor).getAmount();
						
					}
				}
			}
			
		}
	
		addToOrderItemTimes(newItems);
		
		if(newItems+oldItems>10) {			
			System.out.println("Not allowed to exceed max number of orders!");			
		}
	
		else if(newItems+oldItems<=10) {
	
			for(rFor=0;rFor<addOrders.length;rFor++) {
		
				mealIsHere=false;
				String singleAddOrder[]=addOrders[rFor].split("-");
		
				for(yFor=0;yFor<mealobjects.size();yFor++) {
		
					if(mealobjects.get(yFor).getName().equals(singleAddOrder[0])) {
						mealIsHere=true;
						
						for(zFor=0;zFor<Integer.parseInt(singleAddOrder[1]);zFor++) {
				
							if(mealobjects.get(yFor).getAmount()>0) {
								mealobjects.get(yFor).setAmount(mealobjects.get(yFor).getAmount()-1);
								
								if(ordersOfTable[0]==null) {
									ordersOfTable[0]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
						
								else if(ordersOfTable[1]==null) {
									ordersOfTable[1]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
						
								else if(ordersOfTable[2]==null) {
									ordersOfTable[2]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
							
								else if(ordersOfTable[3]==null) {
									ordersOfTable[3]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
							
								else if(ordersOfTable[4]==null) {
									ordersOfTable[4]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
							
								else if(ordersOfTable[5]==null) {
									ordersOfTable[5]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
							
								else if(ordersOfTable[6]==null) {
									ordersOfTable[6]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
							
								else if(ordersOfTable[7]==null) {
									ordersOfTable[7]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
							
								else if(ordersOfTable[8]==null) {
									ordersOfTable[8]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
							
								else if(ordersOfTable[9]==null) {
									ordersOfTable[9]=singleAddOrder[0];
									System.out.println("Item "+singleAddOrder[0]+" added into order");
								}
								
							}
						
							else if(mealobjects.get(yFor).getAmount()==0) {
								System.out.println("Sorry! No "+mealobjects.get(yFor).getName()+" in the stock!");
							}
						}
					}
				
					else {
						continue;
					}
				}
			
				if(mealIsHere==false) {
					System.out.println("Unknown item "+singleAddOrder[0]);
				}
			}
		}					
	}

	public String getWaiterWhoHaveThisTable() {
		return waiterWhoHaveThisTable;
	}
	
	public void setWaiterWhoHaveThisTable(String waiterWhoHaveThisTable) {
		this.waiterWhoHaveThisTable = waiterWhoHaveThisTable;
	}
	
	public table(String employerName, int customerCapacity) {
		this.employerName = employerName;
		this.customerCapacity = customerCapacity;	
	}
	
	public void addOrderToOwnTable(String orders[], ArrayList<stock> mealobjects){
		int z;
		int u;
		int k;
		boolean mealIsHere;
	
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: new_order");
		System.out.println("Table (= ID "+this.tableID+") has been taken into service");
	
		for(z=0;z<orders.length;z++) {
		
			mealIsHere=false;
			String singleOrder[]=orders[z].split("-");
		
			for(u=0;u<mealobjects.size();u++) {
			
				if(mealobjects.get(u).getName().equals(singleOrder[0])) {
					mealIsHere=true;
					
					for(k=0;k<Integer.parseInt(singleOrder[1]);k++) {
				
						if(mealobjects.get(u).getAmount()>0) {
							mealobjects.get(u).setAmount(mealobjects.get(u).getAmount()-1);
														
							if(ordersOfTable[0]==null) {
								ordersOfTable[0]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
				
							else if(ordersOfTable[1]==null) {
								ordersOfTable[1]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
				
							else if(ordersOfTable[2]==null) {
								ordersOfTable[2]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
				
							else if(ordersOfTable[3]==null) {
								ordersOfTable[3]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
					
							else if(ordersOfTable[4]==null) {
								ordersOfTable[4]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
					
							else if(ordersOfTable[5]==null) {
								ordersOfTable[5]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
					
							else if(ordersOfTable[6]==null) {
								ordersOfTable[6]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
					
							else if(ordersOfTable[7]==null) {
								ordersOfTable[7]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
					
							else if(ordersOfTable[8]==null) {
								ordersOfTable[8]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
						
							else if(ordersOfTable[9]==null) {
								ordersOfTable[9]=singleOrder[0];
								System.out.println("Item "+singleOrder[0]+" added into order");
							}
						}
					
						else if(mealobjects.get(u).getAmount()==0) {
							System.out.println("Sorry! No "+mealobjects.get(u).getName()+" in the stock!");
						}
					}
					
					break;
				}
			
				else {
					continue;
				}
			}
		
			if(mealIsHere==false) {
				System.out.println("Unknown item "+singleOrder[0]);
			}
		}
	}
	
	public String[] getOrdersOfTable() {
		return ordersOfTable;
	}

	public void setOrdersOfTable(String[] ordersOfTable) {
		this.ordersOfTable = ordersOfTable;
	}
	
	public boolean isCurrentService() {
		return currentService;
	}
	
	public void setCurrentService(boolean currentService) {
		this.currentService = currentService;
	}
	
	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	
	public String getEmployerName() {
		return employerName;
	}
	
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	public int getCustomerCapacity() {
		return customerCapacity;
	}
	
	public void setCustomerCapacity(int customerCapacity) {
		this.customerCapacity = customerCapacity;
	}
	
}