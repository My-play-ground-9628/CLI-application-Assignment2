import java.util.Scanner;

public class CLIappAssignment2{
    private static final Scanner Scanner = new Scanner(System.in);
    public static void main(String[] args) {
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";
        final String clear = "\033[H\033[2J";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_NEW_ACCOUNT = "Open new account";
        final String DEPOSIT = "Deposit money";
        final String WITHDRAWS = "Withdraw money";
        final String TRANSFER = "Transfer money";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Drop existing account";

        String screen = DASHBOARD;
        String[][] accounts = new String[0][];

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
                    int initialDeposit;
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
                        initialDeposit = Scanner.nextInt();
                        Scanner.nextLine();

                        if (initialDeposit < 5000) {
                            System.out.printf(ERROR_MSG, "Insufficient Amount");
                            valid = false;
                            continue;
                        }
                    }while(!valid);

                    
    

            }
        }while(true);    



    }
}