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


    }
}