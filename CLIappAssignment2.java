import java.util.Scanner;

public class CLIappAssignment2{
    private static final Scanner Scanner = new Scanner(System.in);
    final static String DASHBOARD = "Welcome to Smart Banking";
    final static String CREATE_NEW_ACCOUNT = "Open new account ";
    final static String DEPOSIT = "Deposit money";
    final static String WITHDRAWS = "Withdraw money";
    final static String TRANSFER = "Transfer money";
    final static String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
    final static String DELETE_ACCOUNT = "Drop existing account";

    final static String COLOR_BLUE_BOLD = "\033[34;1m";
    final static String COLOR_RED_BOLD = "\033[31;1m";
    final static String COLOR_GREEN_BOLD = "\033[33;1m";
    final static String RESET = "\033[0m";
    final static String clear = "\033[H\033[2J";

    final static String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
    final static String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);



    static String screen;
    static String[][] accounts = new String[0][];
    //static String[][] accounts = {{"SDB-00001","Shashi","10000"},{"SDB-00002","Shani","15000"},{"SDB-00003","sew","8000"}};
    static String accNum = "";
    static float newBalance = 0;

    public static void main(String[] args) {
        
        screen = DASHBOARD;
        mainLoop:
        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(clear);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD:
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an Option to Continue > ");

                    int option = Scanner.nextInt();
                    Scanner.nextLine();

                    switch (option) {
                        case 1: screen = CREATE_NEW_ACCOUNT; break;
                        case 2: screen = DEPOSIT; break;
                        case 3: screen = WITHDRAWS; break;
                        case 4: screen = TRANSFER; break;
                        case 5: screen = CHECK_ACCOUNT_BALANCE; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.out.println(clear); System.exit(0); break;
                    }
                    break;

                case CREATE_NEW_ACCOUNT:
                    String name;
                    double initialDeposit;
                    boolean valid = true;
                    System.out.printf("ID: SDB-%05d \n", (accounts.length + 1));
                    
                    //Name validation 
                    do{
                        valid = true;
                        System.out.print("Enter Customer Name: ");
                        name = Scanner.nextLine().toUpperCase();
                        if (!emptyCheck(name,"Name Can't be Empty")) {
                            valid = false;
                            continue;
                        }
                        else {
                            for (int i = 0; i < name.length(); i++) {
                                if (!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))) {
                                    System.out.printf(ERROR_MSG, "Invalid name");
                                    valid = false;
                                    break;
                                }
                            }
                        }
                    }while(!valid);

                    //Deposit validation
                    do{
                        valid = true;
                        System.out.print("Initial Deposit: ");
                        initialDeposit = Scanner.nextDouble();
                        Scanner.nextLine();

                        if (initialDeposit < 5000) {
                            System.out.printf(ERROR_MSG, "Minimum Initial Deposit Amount is 5000");
                            valid = false;
                            continue;
                        }
                    }while(!valid);

                    String[][] tempAccounts = new String[accounts.length + 1][3];
                    String accNum = String.format("SDB-%05d", tempAccounts.length);
                    for (int i = 0; i < accounts.length; i++) {
                        tempAccounts[i] = accounts[i];

                    }
                    tempAccounts[tempAccounts.length - 1][0] = accNum;
                    tempAccounts[tempAccounts.length - 1][1] = name;
                    tempAccounts[tempAccounts.length - 1][2] = initialDeposit + "";

                    accounts = tempAccounts;
                    System.out.println();
                    System.out.printf(SUCCESS_MSG, String.format("%s:%s added successfully \n", accNum, name));
                    System.out.print("Do you want to continue adding marks? (Y/n)");
                    if (!Scanner.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;

                    break;

                case DEPOSIT:
                    int index=0;
                    int depositAmount=0;
                    
                    do{
                        valid = true;
                        System.out.print("\tEnter Account Number: ");
                        accNum = Scanner.nextLine().toUpperCase().strip();
                        
                        if (accNum.isBlank()) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }
                        else if (!accNum.startsWith("SDB-")||(accNum.length()< 9)) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;

                        }else if ((Integer.valueOf(accNum.substring(8))) > accounts.length){
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }

                        for (int i = 0; i < accounts.length-1; i++) {
                            if (accounts[i][0].equals(accNum)) {
                                index = i;
                                break;
                            }
                        }
                        
                        System.out.printf("\tCurrent Balance: Rs.%,.2f\n", Float.valueOf(accounts[index][2]));
                        valid = true;
                        do{
                            System.out.print("\tDeposit amount: ");
                            depositAmount = Scanner.nextInt();
                            Scanner.nextLine();

                            if (depositAmount < 500) {
                                System.out.printf(ERROR_MSG, "Minimum Deposit Amount is 500");
                                valid = false;
                                screen = DASHBOARD;
                                continue;
                            }else valid=true;
                        }while(!valid);

                    }while(!valid);
                    newBalance = Float.valueOf(accounts[index][2])+ depositAmount;
                    accounts[index][2] = newBalance+""; 
                    System.out.printf("\tNew Balance: Rs: %,.2f\n",newBalance);
                    System.out.print("\tDo you want to continue ? (Y/n)");
                    if (!Scanner.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;

                    break;
                       
                case WITHDRAWS:
                    index=0;
                    int withdrawAmount=0;
                    
                    withdrawLoop:
                    do{
                        valid = true;
                        System.out.print("\tEnter Account Number: ");
                        accNum = Scanner.nextLine().toUpperCase().strip();
                        
                        if (accNum.isBlank()) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if (!accNum.startsWith("SDB-")) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if ((Integer.valueOf(accNum.substring(8))) > accounts.length){
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }
                        for (int i = 0; i < accounts.length-1; i++) {
                            if (accounts[i][0].equals(accNum)) {
                                index = i;
                                break;
                            }
                        }
                        System.out.printf("\tCurrent Balance: Rs.%s.00\n", accounts[index][2]);

                        do{
                            System.out.print("\tWithdraw amount: ");
                            withdrawAmount = Scanner.nextInt();
                            Scanner.nextLine();

                            if (withdrawAmount < 100) {
                                System.out.printf(ERROR_MSG, "Insufficient Amount");
                                valid=false;
                                //screen = DASHBOARD;
                                continue;
                            }else if (Float.valueOf(accounts[index][2])-withdrawAmount < 500){
                                System.out.printf(ERROR_MSG, "Remaining Account balance is less than 500");
                                valid = false;
                                //screen = DASHBOARD;
                                continue;
                            }
                            else valid = true;
                                

                        }while(!valid);
                        newBalance = Float.valueOf(accounts[index][2])- withdrawAmount;
                        accounts[index][2] = newBalance+""; 
                        System.out.printf("\tNew Balance: Rs: %,.2f\n",newBalance);

                    }while(!valid);
                    System.out.print("\tDo you want to continue ? (Y/n)");
                    if (!Scanner.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;
                    
                    break;
                
                case TRANSFER:
                    int index1 = 0;
                    int index2 = 0;
                    int transferAmount = 0;
                    String FromAccNum = "";
                    String ToAccNum = "";
                    float newBalance1 = 0;
                    float newBalance2 = 0;
                    
                    do{
                        valid = true;
                        System.out.print("\tEnter FROM Account Number: ");
                        FromAccNum = Scanner.nextLine().toUpperCase().strip();
                        
                        if (FromAccNum.isBlank()) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if (!FromAccNum.startsWith("SDB-")) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if ((Integer.valueOf(FromAccNum.substring(8))) > accounts.length){
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }
                        for (int i = 0; i < accounts.length-1; i++) {
                            if (accounts[i][0].equals(FromAccNum)) {
                                index1 = i;
                                break;
                            }
                        }
                        System.out.printf("\tFrom Account Name: %s\n", accounts[index1][1]);

                        valid = true;
                        System.out.print("\tEnter TO Account Number: ");
                        ToAccNum = Scanner.nextLine().toUpperCase().strip();
                        
                        if (ToAccNum.isBlank()) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if (!ToAccNum.startsWith("SDB-")) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if ((Integer.valueOf(ToAccNum.substring(8))) > accounts.length){
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }
                        for (int i = 0; i < accounts.length-1; i++) {
                            if (accounts[i][0].equals(ToAccNum)) {
                                index2 = i;
                                break;
                            }
                        }
                        System.out.printf("\tTo Account Name: %s\n", accounts[index2][1]);

                        do{
                            valid = true;
                            System.out.print("\tTransfer amount: ");
                            transferAmount = Scanner.nextInt();
                            Scanner.nextLine();

                            if (transferAmount < 100) {
                                System.out.printf(ERROR_MSG, "Minimum amount to transfer is 100");
                                valid=false;
                                screen = DASHBOARD;
                                continue;
                            }else if ((Float.valueOf(accounts[index1][2])-transferAmount) < 500){
                                System.out.printf(ERROR_MSG, "Remaining Account balance is less than 500");
                                valid = false;
                                continue;
                            }
                            
                        }while(!valid);
                        newBalance1 = Float.valueOf(accounts[index1][2])- transferAmount;
                        newBalance1 = newBalance1 * 98 /100;
                        if (newBalance1 < 500){
                            System.out.printf(ERROR_MSG, "Remaining FROM Account balance is less than 500");
                            valid = false;
                            continue;
                        }
                        
                    }while(!valid);
                    
                    accounts[index1][2] = newBalance1+""; 
                    System.out.printf("\tFROM account new balance: Rs: %,.2f\n",newBalance1);
                    newBalance2 = Float.valueOf(accounts[index2][2])+ transferAmount;
                    accounts[index2][2] = newBalance2+""; 
                    System.out.printf("\tTO account new balance: Rs: %,.2f\n",newBalance2);
                    System.out.print("\tDo you want to continue ? (Y/n)");
                    if (!Scanner.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;

                    break;
                    
                case CHECK_ACCOUNT_BALANCE:
                    index = 0;
                    do{
                        valid = true;
                        System.out.print("\tEnter Account Number: ");
                        accNum = Scanner.nextLine().toUpperCase().strip();
                        
                        if (accNum.isBlank()) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if (!accNum.startsWith("SDB-")) {
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }else if ((Integer.valueOf(accNum.substring(8))) > accounts.length){
                            valid = false;
                            System.out.print("\tDo you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }
                        for (int i = 0; i < accounts.length; i++) {
                            if (accounts[i][0].equals(accNum)) {
                                index = i;
                                break;
                            }
                        }
                        System.out.printf("\tAccount holder's Name: %s\n", accounts[index][1]);
                        System.out.printf("\tCurrent Balance: Rs.%s.00\n", accounts[index][2]);
                    }while(!valid);
                    newBalance = Float.valueOf(accounts[index][2]);
                    System.out.printf("\tCurrent account Balance: Rs: %,.2f\n",newBalance);
                    System.out.printf("\tAvailable withdraw Balance: Rs: %,.2f\n",newBalance-500);
                    System.out.println();
                    System.out.print("\tDo you want to continue ? (Y/n)");
                    if (!Scanner.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;

                    break;

                case DELETE_ACCOUNT:{
                    String delName = "";
                    do{
                        index = -1;
                        valid = true;
                        System.out.print("\tEnter Account Number: ");
                        accNum = Scanner.nextLine().toUpperCase().strip();
                        
                        if (!emptyCheck(accNum, "Do you want to try again? (Y/n)")) {
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }
                        
                            
                        else if (!accNum.startsWith("SDB-")) {
                            valid = false;
                            System.out.print("Do you want to try again? (Y/n)");
                            if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            continue;
                        }
                        
                        for (int i = 0; i < accounts.length; i++) {
                            if (accounts[i][0].equals(accNum)) {
                                index = i;
                                break;
                            }
                        }
                        tempAccounts = new String[accounts.length - 1][3];    
                        if (index >= 0){
                            delName = accounts[index][1];
                            System.out.printf("\tAccount holder's Name: %s\n", delName);
                            System.out.printf("\tCurrent Balance: Rs.%,.2f\n", Float.valueOf(accounts[index][2]));
                            
                            
                        
                            System.out.print("\tAre you sure you want to delete this account? (Y/n)");
                                if (!Scanner.nextLine().toUpperCase().strip().equals("Y")){
                                    valid = false;
                                    screen = DASHBOARD;
                                    break;
                                }else {
                                    for (int i = 0; i < accounts.length; i++) {
                                        if (i < index){
                                            tempAccounts[i] = accounts[i];
                                            
                                        }else if (i == index){
                                            continue;
                                        }else{
                                            tempAccounts[i - 1] = accounts[i];
                                        }
                                    }
                                }
                                    
                        }else {
                            System.out.println("\tAccount number doesn't exist"); 
                            break;
                        }
                    
                    
                    
                    accounts = tempAccounts;
                    
                    System.out.println();
                    System.out.printf(SUCCESS_MSG, String.format("%s : %s has been deleted successfully", accNum,delName));
                    
                    
                    }while(!valid);
                    System.out.print("\tDo you want to continue (Y/n)? ");
                    if (Scanner.nextLine().toUpperCase().strip().equals("Y")){
                        screen=DELETE_ACCOUNT;
                        continue mainLoop;
                        
                    }else {
                        screen = DASHBOARD;
                        break;
                    }
                   

            }default:
                    System.exit(0);
                break;
            }
        }while(true);    
        
       
    }

    public static boolean emptyCheck(String input, String errMsg){
        boolean valid = true;
        if (input.isBlank()) {
            System.out.printf(ERROR_MSG , errMsg);
            valid = false;
        }else valid = true;
        return valid;


    }

     public static boolean CharacterValidation(String input, String errMsg){
        boolean valid = true;
        for (int i = 0; i < input.length(); i++) {
            if (!(Character.isLetter(input.charAt(i)) || Character.isSpaceChar(input.charAt(i)))) {
                System.out.printf(ERROR_MSG, errMsg);
                valid = false;
                break;
            }   
        }
        return valid;
    }
    
    
   
}