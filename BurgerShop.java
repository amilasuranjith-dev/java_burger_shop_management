import java.util.*;
class BurgerShop{
	public static final double BURGERPRICE = 500;
	public static final int PREPARING = 0; 
	public static final int DELIVERED = 1; 
	public static final int CANCEL = 2;
	
	public static String[] orderIdArray  = {"B0001", "B0002", "B0003", "B0004", "B0005", "B0006", "B0007","B0008","B0009","B0010"};		
	public static String[] custIdArray   = {"0770000001", "0770000002", "0770000003", "0770000001", "0770000004", "0770000003", "0770000001", "0770000003", "0770000005", "0770000004"};
	public static String[] custNameArray = {"Sanath", "Malan", "Sujith", "Sanath", "Nimal", "Sujith", "Sanath", "Sujith", "Aravinda", "Nimal"};
	public static int[]    quantityArray = {5, 10, 15, 10, 4, 5, 15, 3, 6, 8};
	public static int[] orderStatusArray = {1, 1, 1, 2, 1, 0, 1, 2, 1, 0};	
	
	//--- HOMEPAGE METHOD                     
	public static void homepage(){
		L1:do{
			clearConsole();
			Scanner input = new Scanner(System.in);
			System.out.println("=======================================================================");
			System.out.println("|                            iHungry Burger                           |");
			System.out.println("=======================================================================");
			System.out.println();
			//ASCII art genarate by patorjk.com			
			//System.out.println("       )          )        (       )              (               (     \r\n    ( /(       ( /( (      )\\ ) ( /(     (        )\\ ) (          )\\ )  \r\n (  )\\())   (  )\\()))\\ )  (()/( )\\())  ( )\\    ( (()/( )\\ )   (  (()/(  \r\n )\\((_)\\    )\\((_)\\(()/(   /(_)|(_)\\   )((_)   )\\ /(_)|()/(   )\\  /(_)) \r\n((_)_((_)_ ((_)_((_)/(_))_(_))__ ((_) ((_)_ _ ((_|_))  /(_))_((_)(_))   \r\n (_) || | | | | \\| (_)) __| _ \\ \\ / /  | _ ) | | | _ \\(_)) __| __| _ \\  \r\n | | __ | |_| | .` | | (_ |   /\\ V /   | _ \\ |_| |   /  | (_ | _||   /  \r\n |_|_||_|\\___/|_|\\_|  \\___|_|_\\ |_|    |___/\\___/|_|_\\   \\___|___|_|_\\  \r\n                                                                        ");
			System.out.println();
			System.out.println();
			System.out.println("[1] Place Order  \t\t [2] Search Best Customer");
			System.out.println("[3] Search Order \t\t [4] Search Customer");
			System.out.println("[5] View Orders  \t\t [6] Update Order Details");
			System.out.println("[7] Exit");
			System.out.println();
			System.out.print("Enter an option to cotinue > ");
			String opStr = input.next();
			boolean validOption = validOptionCharacter(opStr);
			
			if(!validOption){
				do{
					System.out.println();
					System.out.println("\t Invalid option input...");
					System.out.println();
					System.out.print("Do you want to input option again (Y/N) ? ");
					char op = input.next().charAt(0);
					if(op=='Y' || op=='y'){
						continue L1;
					}else if(op=='N' || op=='n'){
						exit();
					}else{
						continue;
					}
				}while(true);
			}else{
				switch(opStr.charAt(0)){
					case '1': 
						placeOrder(); break;
					case '2': 
						searchBestCustomer(); break;
					case '3': 
						searchOrder(); break;
					case '4': 
						searchCustomer(); break;
					case '5': 
						viewOrders(); break;
					case '6': 
						updateOrderDetails(); break;
					case '7': 
						exit(); 
					default: 
						do{
							System.out.println();
							System.out.println("\t Invalid option input...");
							System.out.println();
							System.out.print("Do you want to input option again (Y/N) ? ");
							char op = input.next().charAt(0);
							if(op=='Y' || op=='y'){
								continue L1;
							}else if(op=='N' || op=='n'){
								exit();
							}else{
								continue;
							}
						}while(true);				
				}			
			}	
		}while(true);
	}
	
	//---[01] PLACE ORDER
	public static void placeOrder(){
		L1:do{
			clearConsole();
			Scanner input = new Scanner(System.in);
			System.out.println("----------------------------------------------------------------");
			System.out.println("|                        PLACE ORDER                           |");
			System.out.println("----------------------------------------------------------------");
			
			//genarate order ID
			String orderId = orderIdGenerator();
			System.out.println("\nORDER ID - "+orderId);
			System.out.println("====================");
			L2:do{
				System.out.println();
				System.out.print("Enter Customer ID(Phone no.) : ");
				String custId = input.next();
				
				//check is valid customer ID
				boolean validCustId = isValidCustomerId(custId);
				if(!validCustId){
					System.out.println("Invalid customer ID input...");
					continue L2;
				}else{
					//check the customer ID is already added to a Order
					int index = indexOf(custIdArray, custId);
					String custName = "";
					if(index!=-1){
						custName = custNameArray[index];
						System.out.println("Customer Name                : "+custName);
					}else{
						System.out.print("Enter Customer Name          : ");						
						custName = input.next();
					}
					L3:do{
						System.out.print("Enter Burger Quantity        : ");
						int qty = input.nextInt();
						if(!(qty>0)){
							System.out.println("\t Invalid quantity input...");
							continue L3;
						}else{
							System.out.printf("Total value : %6.2f%n",qty*BURGERPRICE);
							L4:do{
								System.out.println();
								System.out.print("\t Are you confirm order (Y/N) - ");
								char op = input.next().charAt(0);
								if(op=='Y' || op=='y'){
									extendArrays();
									orderIdArray[orderIdArray.length-1] = orderId;
									custIdArray[custIdArray.length-1] = custId;
									custNameArray[custNameArray.length-1] = custName;
									quantityArray[quantityArray.length-1] = qty;
									orderStatusArray[orderStatusArray.length-1] = PREPARING;
									
									System.out.println();
									System.out.println("\t Your order is enter to the system successfully...");	
									break L4;							
								}else if(op=='N' || op=='n'){
									System.out.println();
									System.out.println("\t Your order details are not added to the system...");
									break L4;
								}else{
									System.out.println("\t Invalid input...");
									continue L4;
								} 								
							}while(true);
							do{
								System.out.println();
								System.out.print("Do you want to place another order (Y/N) : ");
								char op = input.next().charAt(0);
								if(op=='Y' || op=='y'){
									continue L1;
								}else if(op=='N' || op=='n'){
									break L1;
								}else{
									System.out.println("\t Invalid input...");
									continue;
								}	
							}while(true);
						}
					}while(true);
				}
			}while(true);
		}while(true);
	}
	
	//---[02] SEARCH BEST CUSTOMER
	public static void searchBestCustomer(){
		L1:do{
			clearConsole();
			Scanner input = new Scanner(System.in);
			System.out.println("----------------------------------------------------------------");
			System.out.println("|                         BEST CUSTOMER                        |");
			System.out.println("----------------------------------------------------------------");
			System.out.println();
			//Create duplicate removed 1.custIdArray, 2.custNameArray, 3.totalQtyArray(Status==delivered) ignoring canceled and preparing orders
			String[] drCIDArray = new String[0];
			String[] drCNameArray = new String[0];			
			
			for(int i=0; i<custIdArray.length; i++){
				if(!searcIsExsist(drCIDArray,custIdArray[i]) && orderStatusArray[i]==1){
					String[] tempCIDArray = new String[drCIDArray.length+1];
					String[] tempCNameArray = new String[drCNameArray.length+1];
					for(int j=0; j<drCIDArray.length; j++){
						tempCIDArray[j] = drCIDArray[j];
						tempCNameArray[j] = drCNameArray[j];
					}
					tempCIDArray[tempCIDArray.length-1] = custIdArray[i];
					tempCNameArray[tempCNameArray.length-1] = custNameArray[i];
					drCIDArray = tempCIDArray;
					drCNameArray = tempCNameArray;
				}
			}
			int[] totalQtyArray = new int[drCIDArray.length];
			for(int i=0; i<totalQtyArray.length; i++){
				int total = 0;
				for(int j=0; j<custIdArray.length; j++){
					if(custIdArray[j].equalsIgnoreCase(drCIDArray[i]) && orderStatusArray[j]==1){
						total+=quantityArray[j];
					}						
				}
				totalQtyArray[i] = total;
			}
			//Sort 3 arrays(using bubble sort->descending order)
			for(int j=totalQtyArray.length-1; j>0; j--){
				for(int i=0; i<j; i++){
					if(totalQtyArray[i]<totalQtyArray[i+1]){
						int temp = totalQtyArray[i];
						totalQtyArray[i] = totalQtyArray[i+1];
						totalQtyArray[i+1] = temp;
						
						String temp1 = drCIDArray[i];
						drCIDArray[i] = drCIDArray[i+1];
						drCIDArray[i+1] = temp1;
						
						String temp2 = drCNameArray[i];
						drCNameArray[i] = drCNameArray[i+1];
						drCNameArray[i+1] = temp2;
					}
				}
			}
			//print details(Best>lowest)
			System.out.println();
			System.out.println("------------------------------------------------");
			System.out.println(" CustomerID          Name           TotalValue  ");
			System.out.println("------------------------------------------------");
			for(int i=0; i<totalQtyArray.length; i++){
				System.out.printf (" %-20s%-15s%10.2f  %n",drCIDArray[i], drCNameArray[i], totalQtyArray[i]*BURGERPRICE);
				System.out.println("------------------------------------------------");
			}		
			
			L2:do{
				System.out.println();
				System.out.print("Do you want to go back to Homepage ? (Y/N) >");
				char op = input.next().charAt(0);
				if(op=='Y' || op=='y'){
					break L1;
				}else if(op=='N' || op=='n'){
					continue L1;
				}else{
					System.out.println("\t Invalid input...");
					continue L2;
				}	
			}while(true);
		}while(true);			
	}
	
	//---[03] SEARCH ORDER
	public static void searchOrder(){
		L1:do{
			clearConsole();
			Scanner input = new Scanner(System.in);
			System.out.println("----------------------------------------------------------------");
			System.out.println("|                   SEARCH ORDER DETAILS                       |");
			System.out.println("----------------------------------------------------------------");
			System.out.println();
			System.out.print("Enter order ID : ");
			String orderId = input.next();
			//search entered orderId is available in existing orderIdArray
			int index = indexOf(orderIdArray, orderId);
			if(index==-1){
				L2:do{
					System.out.println();
					System.out.print("Invalid order ID. Do you want to enter again? (Y/N) >");
					char op = input.next().charAt(0);
					if(op=='Y' || op=='y'){
						continue L1;
					}else if(op=='N' || op=='n'){
						break L1;
					}else{
						System.out.println("\t Invalid input...");
						continue L2;
					}	
				}while(true);
			}else{
				double orderValue = quantityArray[index]*BURGERPRICE;
				String orderStatus = orderStatusArray[index]==0? "Prepairing": orderStatusArray[index]==1? "Delivered" : "Cancelled";
				System.out.println();
				System.out.println("------------------------------------------------------------------------");
				System.out.println("| OrderID   CustomerID  Name        Quantity  OrderValue  OrderStatus  |");
				System.out.println("------------------------------------------------------------------------");
				System.out.printf ("| %-10s%-12s%-12s%-10d%-12.2f%-12s |%n",orderIdArray[index], custIdArray[index], custNameArray[index], quantityArray[index], orderValue, orderStatus);
				System.out.println("------------------------------------------------------------------------");				
			}		
			L2:do{
				System.out.println();
				System.out.print("Do you want to search another order details ? (Y/N) >");
				char op = input.next().charAt(0);
				if(op=='Y' || op=='y'){
					continue L1;
				}else if(op=='N' || op=='n'){
					break L1;
				}else{
					System.out.println("\t Invalid input...");
					continue L2;
				}	
			}while(true);	
		}while(true);
	}
	
	//---[04] SEARCH CUSTOMER
	public static void searchCustomer(){
		L1:do{
			clearConsole();
			Scanner input = new Scanner(System.in);
			System.out.println("----------------------------------------------------------------");
			System.out.println("|                 SEARCH CUSTOMER DETAILS                      |");
			System.out.println("----------------------------------------------------------------");			
			L2:do{
				System.out.println();
				System.out.print("Enter customer ID : ");
				String custId = input.next();
				//check validation
				boolean validCustId = isValidCustomerId(custId);
				if(!validCustId){
					System.out.println();
					System.out.println("Invalid customer ID input...");
					continue L2;
				}else{			
					//search entered custId is available in existing custIdArray
					int index = indexOf(custIdArray, custId);
					if(index==-1){	
						System.out.println();			
						System.out.println("\t This customer ID is not added yet...");
					}else{
						//Print customer ID & Name
						System.out.println();
						System.out.println("Customer ID   : "+custIdArray[index]);
						System.out.println("Customer Name : "+custNameArray[index]);
						System.out.println();
						//Print Customer's order details
						System.out.println("Customer Order Details");
						System.out.println("======================");
						System.out.println();
						System.out.println("------------------------------------------------");
						System.out.println("| OrderID   Quantity  OrderValue  OrderStatus  |");
						System.out.println("------------------------------------------------");					
						for(int i=0; i<custIdArray.length; i++){
							if(custIdArray[i].equalsIgnoreCase(custId)){
								String orderStatus = orderStatusArray[i]==0? "Preparing": orderStatusArray[i]==1? "Delivered" : "Cancelled";
								System.out.printf("| %-10s%-10d%-12.2f%-12s |%n",orderIdArray[i], quantityArray[i], (double)quantityArray[i]*BURGERPRICE, orderStatus);
							}
						}
						System.out.println("------------------------------------------------");				
					}
					L3:do{
						System.out.println();
						System.out.print("Do you want to search another customer details ? (Y/N) >");
						char op = input.next().charAt(0);
						if(op=='Y' || op=='y'){
							continue L1;
						}else if(op=='N' || op=='n'){
							break L1;
						}else{
							System.out.println("\t Invalid input...");
							continue L3;
						}	
					}while(true);
				}
			}while(true);
		}while(true);
	}
	
	//---[05] VIEW ORDERS
	public static void viewOrders(){
		L1:do{
			clearConsole();
			Scanner input = new Scanner(System.in);
			System.out.println("----------------------------------------------------------------");
			System.out.println("|                      VIEW ORDER LIST                         |");
			System.out.println("----------------------------------------------------------------");
			System.out.println();
			System.out.println("\t[1] Delivered Orders");
			System.out.println("\t[2] Preparing Orders");
			System.out.println("\t[3] Canceled Orders");
			System.out.println("\t[4] All Order details");
			System.out.println();
			System.out.print("Enter can option to continue > ");
			String opStr = input.next();
			boolean validOption = validOptionCharacter(opStr);
			
			if(!validOption){
				L2:do{
					System.out.println();
					System.out.println("\t Invalid option input...");
					System.out.println();
					System.out.print("Do you want to input option again (Y/N) ? ");
					char op = input.next().charAt(0);
					if(op=='Y' || op=='y'){
						continue L1;
					}else if(op=='N' || op=='n'){
						break L1;
					}else{
						continue L2;
					}
				}while(true);
			}else{
				char op = opStr.charAt(0);
				if(op=='1'){
					clearConsole();
					System.out.println("----------------------------------------------------------------");
					System.out.println("|                    DELIVERED ORDERS                          |");
					System.out.println("----------------------------------------------------------------");
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println("  OrderID   CustomerID     Name        Quantity   OrderValue  ");
					System.out.println("--------------------------------------------------------------");				
					for(int i=0; i<orderStatusArray.length; i++){
						if(orderStatusArray[i]==1){
							System.out.printf ("  %-10s%-15s%-12s  %-8d%10.2f  %n",orderIdArray[i], custIdArray[i], custNameArray[i], quantityArray[i], (double)quantityArray[i]*BURGERPRICE);
							System.out.println("--------------------------------------------------------------");	
						}
					}
				}else if(op=='2'){
					clearConsole();
					System.out.println("----------------------------------------------------------------");
					System.out.println("|                    PREPARING ORDERS                          |");
					System.out.println("----------------------------------------------------------------");
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println("  OrderID   CustomerID     Name        Quantity   OrderValue  ");
					System.out.println("--------------------------------------------------------------");				
					for(int i=0; i<orderStatusArray.length; i++){
						if(orderStatusArray[i]==0){							
							System.out.printf ("  %-10s%-15s%-12s  %-8d%10.2f  %n",orderIdArray[i], custIdArray[i], custNameArray[i], quantityArray[i], (double)quantityArray[i]*BURGERPRICE);
							System.out.println("--------------------------------------------------------------");	
						}
					}
				}else if(op=='3'){
					clearConsole();
					System.out.println("----------------------------------------------------------------");
					System.out.println("|                     CANCELLED ORDERS                         |");
					System.out.println("----------------------------------------------------------------");
					System.out.println();
					System.out.println("--------------------------------------------------------------");
					System.out.println("  OrderID   CustomerID     Name        Quantity   OrderValue  ");
					System.out.println("--------------------------------------------------------------");				
					for(int i=0; i<orderStatusArray.length; i++){
						if(orderStatusArray[i]==2){
							System.out.printf ("  %-10s%-15s%-12s  %-8d%10.2f  %n",orderIdArray[i], custIdArray[i], custNameArray[i], quantityArray[i], (double)quantityArray[i]*BURGERPRICE);
							System.out.println("--------------------------------------------------------------");	
						}
					}
				}else if(op=='4'){
					clearConsole();
					System.out.println("----------------------------------------------------------------------------");
					System.out.println("|                           All ORDER DETAILS                              |");
					System.out.println("----------------------------------------------------------------------------");
					System.out.println();
					System.out.println("----------------------------------------------------------------------------");
					System.out.println("  OrderID   CustomerID     Name        Quantity   OrderValue    OrderStatus");
					System.out.println("----------------------------------------------------------------------------");				
					for(int i=0; i<orderIdArray.length; i++){
						String orderStatus = orderStatusArray[i]==0? "Preparing": orderStatusArray[i]==1? "Delivered" : "Cancelled";
						System.out.printf ("  %-10s%-15s%-12s  %-8d%10.2f  %14s%n",orderIdArray[i], custIdArray[i], custNameArray[i], quantityArray[i], (double)quantityArray[i]*BURGERPRICE, orderStatus);
						System.out.println("----------------------------------------------------------------------------");
					}
				}else{
					System.out.println();
					System.out.println("\t Invalid option input...");
				}				
			}
			L2:do{				
				System.out.println();
				System.out.print("Do you want to go to Homepage ? (Y/N) > ");
				char op = input.next().charAt(0);
				if(op=='Y' || op=='y'){
					break L1;
				}else if(op=='N' || op=='n'){
					continue L1;
				}else{
					System.out.println("\t Invalid option input...");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	//---[06] UPDATE ORDER DETAILS
	public static void updateOrderDetails(){
		L1:do{
			clearConsole();
			Scanner input = new Scanner(System.in);
			System.out.println("----------------------------------------------------------------");
			System.out.println("|                   UPDATE ORDER DETAILS                       |");
			System.out.println("----------------------------------------------------------------");
			System.out.println();
			System.out.print("Enter order ID : ");
			String orderId = input.next();
			//search entered orderId is available in existing orderIdArray
			int index = indexOf(orderIdArray, orderId);
			if(index==-1){
				System.out.println();
				System.out.println("Invalid order ID...");
			}else{
				if(orderStatusArray[index]==1){
					System.out.println("\nThis order is already delivered...You can not update this order...");
				}else if(orderStatusArray[index]==2){
					System.out.println("\nThis order is already cancelled...You can not update this order...");
				}else{
					//Display the current details(If entered orderID is valid and status==prepairing
					System.out.println();
					System.out.println("Order ID     : "+orderIdArray[index]);
					System.out.println("Customer ID  : "+custIdArray[index]);
					System.out.println("Name         : "+custNameArray[index]);
					System.out.println("Quantity     : "+quantityArray[index]);
					System.out.println("Order Value  : "+(double)quantityArray[index]*BURGERPRICE);
					System.out.println("Order Status : "+(orderStatusArray[index]==0? "Preparing": orderStatusArray[index]==1? "Delivered" : "Cancelled"));
					System.out.println();
					System.out.println("What do you want to update > ");
					System.out.println("\t (01) Quantity");
					System.out.println("\t (02) Status");
					L2:do{
						System.out.println();
						System.out.print("Enter an option > ");
						int op = input.nextInt();
						if(op==1){
							//UPDATE QUANTITY
							clearConsole();
							System.out.println("Quantity update");
							System.out.println("===============");
							System.out.println();
							System.out.println("Order ID     : "+orderIdArray[index]);
							System.out.println("Customer ID  : "+custIdArray[index]);
							System.out.println("Name         : "+custNameArray[index]);
							L3:do{
								System.out.println();
								System.out.print("Enter your quantity update value : ");
								int qty = input.nextInt();
								if(!(qty>0)){
									System.out.println();
									System.out.println("\t Invalid quantity input...");	
									continue L3;							
								}else{
									quantityArray[index] = qty;
									System.out.println();
									System.out.println("\t Update order quantity successfully...!");
									System.out.println();
									System.out.println("new order quantity : "+quantityArray[index]);
									System.out.println("new order value    : "+(double)quantityArray[index]*BURGERPRICE);								
									break L3;
								}
							}while(true);							
							break L2;
						}else if(op==2){
							//UPDATE STATUS
							clearConsole();
							System.out.println("Status update");
							System.out.println("===============");
							System.out.println();
							System.out.println("Order ID     : "+orderIdArray[index]);
							System.out.println("Customer ID  : "+custIdArray[index]);
							System.out.println("Name         : "+custNameArray[index]);
							System.out.println();
							System.out.println("\t (0) Cancel");
							System.out.println("\t (1) Preparing");
							System.out.println("\t (2) Delivered");							
							L3:do{
								System.out.println();
								System.out.print("Enter new order status : ");
								int status = input.nextInt();
								if(status==0){
									orderStatusArray[index] = CANCEL;
								}else if(status==1){
									orderStatusArray[index] = PREPARING;															
								}else if(status==2){
									orderStatusArray[index] = DELIVERED;																
								}else{
									System.out.println();
									System.out.println("\t Invalid status input...");	
									continue L3;							
								}
								System.out.println();
								System.out.println("\t Update order status successfully...\n");
								System.out.println("new order status - "+(orderStatusArray[index]==0? "Preparing": orderStatusArray[index]==1? "Delivered" : "Cancelled"));
								break L3;
							}while(true);							
							break L2;
						}else{
							System.out.println();
							System.out.println("\t Invalid option....");
							continue L2;
						}		
					}while(true);			
				}				
			}			
			L2:do{
				System.out.println();
				System.out.print("Do you want update another order details ? (Y/N) >");
				char op = input.next().charAt(0);
				if(op=='Y' || op=='y'){
					continue L1;
				}else if(op=='N' || op=='n'){
					break L1;
				}else{
					System.out.println("\t Invalid input...");
					continue L2;
				}	
			}while(true);
		}while(true);
	}
	
	//--- search isExsisting
	public static boolean searcIsExsist(String[] ar, String data){
		for(int i=0; i<ar.length; i++){
			if(ar[i].equalsIgnoreCase(data)){
				return true;
			}
		}
		return false;
	}
	
	//--- search Index
	public static int indexOf(String[] ar, String data ){
		for(int i=0; i<ar.length; i++){
			if(ar[i].equalsIgnoreCase(data)){
				return i;
			}
		}
		return -1;
	}

	//--- Extend Arrays
	public static void extendArrays(){
		String[] tempOrderIdArray  = new String[orderIdArray.length+1];
		String[] tempCustIdArray   = new String[custIdArray.length+1];
		String[] tempCustNameArray = new String[custNameArray.length+1];
		int[]    tempQuantityArray = new int[quantityArray.length+1];
		int[] tempOrderStatusArray = new int[orderStatusArray.length+1];
		
		//copy elements
		for(int i=0; i<orderIdArray.length; i++){
			tempOrderIdArray[i] = orderIdArray[i];
			tempCustIdArray[i] = custIdArray[i];
			tempCustNameArray[i] = custNameArray[i];
			tempQuantityArray[i] = quantityArray[i];
			tempOrderStatusArray[i] = orderStatusArray[i];
		}
		orderIdArray = tempOrderIdArray;
		custIdArray = tempCustIdArray;
		custNameArray = tempCustNameArray;
		quantityArray = tempQuantityArray;
		orderStatusArray = tempOrderStatusArray;
	}
	
	//--- customerId Validation
	public static boolean isValidCustomerId(String custId){
		// get confirmation all are numbers
		for(int i=0; i<custId.length(); i++){
			if(!(custId.charAt(i)>=48 && custId.charAt(i)<=57)){
				return false;
			}
		}		
		// all digits are number -> check length must be 10 & 1st digit is 0
		if(!(custId.length()==10 && custId.charAt(0)=='0')){
			return false;
		}
		//check code(1st 3 digit) is Sri lankan network numbers
		String code = custId.substring(0,3);
		if(code.equals("077")||code.equals("074")||code.equals("076")||code.equals("071")||code.equals("070")||code.equals("072")||code.equals("078")||code.equals("075")){
			return true; //valid cutsomer ID
		}else{
			return false;
		}
	}
	
	//--- orderID generator
	public static String orderIdGenerator(){
		if(orderIdArray.length==0){
			return "B0001";
		}else{
			int num = Integer.parseInt(orderIdArray[orderIdArray.length-1].substring(1));
			return String.format("B%04d",++num);
		}
	}
	
	//--- MAIN METHOD
	public static void main(String args[]){
		homepage();		
	}
	
	//--- Valid Option Character
	public static boolean validOptionCharacter(String opStr){
		// check it is a numaric character
		if(opStr.length()==1 && (opStr.charAt(0)>=48 && opStr.charAt(0)<=57)){
			return true;
		}else{
			return false;
		}
	}
	
	//--- EXIT METHOD
	public static void exit(){
		clearConsole();
		System.out.println("\n\t\tYou left the program...\n");
		System.exit(0);
	}	
	
	//--- CLEAR CONSOLE METHOD
	public final static void clearConsole() {
		try{
			final String os =
			System.getProperty("os.name");
			if(os.contains("Windows")){
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}else{
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		}catch(final Exception e) {
			e.printStackTrace();
			// Handle any exceptions
		}
	}
}
