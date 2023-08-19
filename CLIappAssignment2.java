import java.util.Scanner;

public class CLIappAssignment2{
    private static final Scanner Scanner = new Scanner(System.in);
    final static String DASHBOARD = "Welcome to Smart Banking";
    final static String CREATE_NEW_ACCOUNT = "Open new account";
    final static String DEPOSIT = "Deposit money";
    final static String WITHDRAWS = "Withdraw money";
    final static String TRANSFER = "Transfer money";
    final static String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
    final static String DELETE_ACCOUNT = "Drop existing account";


    static String screen = DASHBOARD;
    // String[][] accounts = new String[0][]{};
    static String[][] accounts = {{"SDB-00001","Shashi","5000"}};
    static String accNum = "";
    static float newBalance = 0;

    public static void main(String[] args) {
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";
        final String clear = "\033[H\033[2J";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

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
                    System.out.println("\t[4]. Exit\n");
                    System.out.print("Enter an Option to Continue > ");

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
                        System.out.print("\tEnter Customer Name: ");
                        name = Scanner.nextLine().toUpperCase();
                        if (name.isBlank()) {
                            System.out.printf(ERROR_MSG , "Name Can't be empty");
                            valid = false;
                            continue;
                        }else {
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
                        System.out.print("\tInitial Deposit: ");
                        initialDeposit = Scanner.nextDouble();
                        Scanner.nextLine();

                        if (initialDeposit < 5000) {
                            System.out.printf(ERROR_MSG, "Insufficient Amount");
                            valid = false;
                            continue;
                        }
                    }while(!valid);

                    String[][] tempAccounts = new String[accounts.length + 1][3];
                    String accNum = String.format("SDB-%05d", tempAccounts.length);
                    tempAccounts[tempAccounts.length - 1][0] = accNum;
                    tempAccounts[tempAccounts.length - 1][1] = name;
                    tempAccounts[tempAccounts.length - 1][2] = initialDeposit + "";

                    accounts = tempAccounts;
                    System.out.println();
                    System.out.printf(SUCCESS_MSG, String.format("%s:%s added successfully \n", accNum, name));
                    System.out.print("\tDo you want to continue adding marks? (Y/n)");
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
                        }else if (!accNum.startsWith("SDB-")||(accNum.length()< 9)) {
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
                        index = Integer.valueOf(accNum.substring(8));
                        System.out.printf("\tCurrent Balance: Rs.%,.2f\n", Float.valueOf(accounts[index-1][2]));
                        valid = true;
                        do{
                            System.out.print("\tDeposit amount: ");
                            depositAmount = Scanner.nextInt();
                            Scanner.nextLine();

                            if (depositAmount < 500) {
                                System.out.printf(ERROR_MSG, "Insufficient Amount");
                                valid = false;
                                screen = DASHBOARD;
                                continue;
                            }else valid=true;
                        }while(!valid);

                    }while(!valid);
                    newBalance = Float.valueOf(accounts[index-1][2])+ depositAmount;
                    accounts[index-1][2] = newBalance+""; 
                    System.out.printf("\tNew Balance: Rs: %,.2f\n",newBalance);
                    System.out.print("\tDo you want to continue ? (Y/n)");
                    if (!Scanner.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;

                    break;
                       
                case WITHDRAWS:
                    index=0;
                    int withdrawAmount=0;
                    
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
                        index = Integer.valueOf(accNum.substring(8));
                        System.out.printf("\tCurrent Balance: Rs.%s.00\n", accounts[index-1][2]);

                        do{
                            System.out.print("\tWithdraw amount: ");
                            withdrawAmount = Scanner.nextInt();
                            Scanner.nextLine();

                            if (withdrawAmount < 100) {
                                System.out.printf(ERROR_MSG, "Insufficient Amount");
                                valid=false;
                                screen = DASHBOARD;
                                continue;
                            }else if (Float.valueOf(accounts[index-1][2])-withdrawAmount < 500){
                                System.out.printf(ERROR_MSG, "Remaining Account balance is less than 500");
                                valid = false;
                                //screen = DASHBOARD;
                                continue;
                            }
                        }while(!valid);
                    }while(!valid);
                    newBalance = Float.valueOf(accounts[index-1][2])- withdrawAmount;
                    accounts[index-1][2] = newBalance+""; 
                    System.out.printf("\tNew Balance: Rs: %,.2f\n",newBalance);
                    System.out.print("\tDo you want to continue ? (Y/n)");
                    if (!Scanner.nextLine().toUpperCase().strip().equals("Y"))
                        screen = DASHBOARD;

                    break;
                
                case TRANSFER:
                    index=0;
                    int transferAmount = 0;
                    String FromAccNum = "";
                    String ToAccNum = "";
                    
                    do{
                        valid = true;
                        System.out.print("\tEnter from Account Number: ");
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
                        index = Integer.valueOf(FromAccNum.substring(8));
                        System.out.printf("\tFrom Account Name: %s\n", accounts[index-1][1]);

                        valid = true;
                        System.out.print("\tEnter ToAccount Number: ");
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
                        index = Integer.valueOf(FromAccNum.substring(8));
                        System.out.printf("\tFrom Account Name: %s\n", accounts[index-1][1]);


                        do{
                            System.out.print("\tTransfer amount: ");
                            withdrawAmount = Scanner.nextInt();
                            Scanner.nextLine();

                            if (withdrawAmount < 100) {
                                System.out.printf(ERROR_MSG, "Insufficient Amount");
                                valid=false;
                                screen = DASHBOARD;
                                continue;
                            }else if (Float.valueOf(accounts[index-1][2])-transferAmount < 500){
                                System.out.printf(ERROR_MSG, "Remaining Account balance is less than 500");
                                valid = false;
                                //screen = DASHBOARD;
                                continue;
                            }
                        }while(!valid);
                    }while(!valid);



                    break;
                    

                default:
                    System.exit(0);
            }
        }while(true);    
        
       
    }

    // public static final String accNumValidate(String Input) {
    //     boolean valid = true;
    //     accNumValidation:
    //     do{
    //         valid = true;
    //         System.out.printf("Enter %s: ",Input);
    //         accNum = Scanner.nextLine().strip();
    //         if (accNum.isBlank()) {
    //             valid = false;
    //             System.out.print("\tDo you want to try again? (Y/n)");
    //             if (Scanner.nextLine().strip().toUpperCase().equals("Y")) {
    //                 continue accNumValidation;
    //             }
    //             else {
    //                 screen = DASHBOARD;
    //                 accNum = screen;
    //                 continue;

    //             }
                

    //         }else if ((!accNum.startsWith("SDB-"))||(accNum.length()< 9)) {
    //             valid = false;
    //             System.out.print("\tDo you want to try again? (Y/n)");
    //             if (!Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
    //             screen = DASHBOARD;
    //             continue;
    //         }else if ((Integer.valueOf(accNum.substring(8))) > accounts.length){
    //             valid = false;
    //             System.out.print("\tDo you want to try again? (Y/n)");
    //             if (Scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
    //             screen = DASHBOARD;
    //             continue;
    //         }e
            
    //     }while(!valid);
    //     return accNum;
        
    // }
    
    
   
}