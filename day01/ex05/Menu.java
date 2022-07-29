import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private boolean devMode = false;
    private final int exitNum;
    private TransactionsService transactionsService = new TransactionsService();
    private final String ERROR = "Wrong argument. Try again...";


    public Menu(boolean devMode) {
        this.devMode = devMode;
        if (devMode)
            exitNum = 7;
        else
            exitNum = 5;
    }

    private void printConsole() {
        int counter = 1;
        System.out.println(counter++ + ". Add a user");
        System.out.println(counter++ + ". View user balances");
        System.out.println(counter++ + ". Perform a transfer");
        System.out.println(counter++ + ". View all transactions for a specific user");
        if (devMode) {
            System.out.println(counter++ + ". DEV - remove a transfer by ID");
            System.out.println(counter++ + ". DEV - check transfer validity");
        }
        System.out.println(counter++ + ". Finish execution");
    }

    private void printLine() {
        System.out.println("---------------------------------------------------------");
    }

    private void addUser() {
        Scanner scanner = new Scanner(System.in);
        User user;

        System.out.println("Enter a user name and a balance");

        while (true) {
            String [] strings = scanner.nextLine().split(" ");

            if (strings.length != 2) {
                System.err.println(ERROR);
                continue;
            }

            try {
                user = new User(strings[0], Integer.parseInt(strings[1]));
                transactionsService.addUser(user);
                System.out.printf("User with id = %d is added\n", user.getIdentifier());
                return;
            } catch (NumberFormatException exception) {
                System.err.println(ERROR);
            }
        }
    }

    private void viewUserBalances() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a user ID");

        while (true) {
            try {
                int id = scanner.nextInt();
                System.out.println(transactionsService.getUserList().getById(id).getName() + " - " + transactionsService.getBalance(id));
                return;
            } catch (InputMismatchException | UserNotFoundException exception) {
                System.err.println(ERROR);
            }
        }
    }

    private void perfomeTransfer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

        while (true) {
            String [] strings = scanner.nextLine().split(" ");

            if (strings.length != 3) {
                System.err.println(ERROR);
                continue;
            }

            try {
                transactionsService.doTransfer(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]) , Integer.parseInt(strings[2]));
                return;
            } catch (NumberFormatException | IllegalTransactionException | UserNotFoundException exception) {
                System.err.println(ERROR);
            }
        }
    }

    private void viewUserTransactions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a user ID");

        while (true) {
            try {
                Transaction [] transactions = transactionsService.getTransactions(scanner.nextInt());
                if (transactions == null)
                    return;
                for (int i = 0; i < transactions.length; i++) {
                    if (transactions[i].getTransferCategory() == Transaction.transferType.CREDIT)
                        System.out.println("To " + transactions[i].getRecipient().getName() + "(id = " + transactions[i].getRecipient().getIdentifier() + ") -"
                                + transactions[i].getTransferAmount() + " with id = " +transactions[i].getIdentifier());
                    else if (transactions[i].getTransferCategory() == Transaction.transferType.DEBIT)
                        System.out.println("From " + transactions[i].getSender().getName() + "(id = " + transactions[i].getSender().getIdentifier() + ") -"
                                + transactions[i].getTransferAmount() + " with id = " +transactions[i].getIdentifier());
                }
                return;
            } catch (InputMismatchException | UserNotFoundException exception) {
                System.err.println(ERROR);
            }
        }
    }

    private void removeTransfer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a user ID and a transfer ID");

        while (true) {
            String [] strings = scanner.nextLine().split(" ");

            if (strings.length != 2) {
                System.err.println(ERROR);
                continue;
            }

            try {
                transactionsService.removeTransaction(UUID.fromString(strings[1]), Integer.parseInt(strings[0]));
                System.out.println("Transaction removed");
                return;
            } catch (UserNotFoundException | TransactionNotFoundException | IllegalArgumentException exception) {
                System.err.println(ERROR);
            }
        }
    }



    private void checkUnpairedTransfer() {
        Transaction [] transactions = transactionsService.getUnpairedTransactions();

        if (transactions.length == 0)
            return;

        for (Transaction t : transactions) {
            System.out.println(t.getRecipient().getName() + "(id = " + t.getRecipient().getIdentifier() + ") has an unacknowledged transfer id = "
                    + t.getIdentifier() + " from " + t.getSender().getName() + "(id = " + t.getSender().getIdentifier() + ") for " + t.getTransferAmount());
        }
    }

    private void action(int process) {
        switch (process) {
            case 1:
                addUser();
                break;
            case 2:
                viewUserBalances();
                break;
            case 3:
                perfomeTransfer();
                break;
            case 4:
                viewUserTransactions();
                break;
            default:
                if (process == exitNum) {
                    System.exit(0);
                }

                if (process == 5) {
                    removeTransfer();
                    break;
                }

                if (process == 6) {
                    checkUnpairedTransfer();
                    break;
                }

                System.out.println(ERROR);
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printConsole();
            while (true) {
                try {
                    action(scanner.nextInt());
                    break;
                } catch (InputMismatchException inputMismatchException) {
                    System.err.println(ERROR);
                    scanner.nextLine();
                }
            }
            printLine();
        }

    }
}
