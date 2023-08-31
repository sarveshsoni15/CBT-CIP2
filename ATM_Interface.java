import java.util.*;
interface ATM {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(String recipient, double amount);
    double getBalance();
    void displayMenu();
    void displayTransactionHistory();
}

class ATMImpl implements ATM {
    private double balance;
    private String userId;
    private String userPin;
    private List<String> transactionHistory;

    public ATMImpl(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String pin) {
        return userPin.equals(pin);
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited:  ₹" + amount);
        System.out.println("Deposited:  ₹" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn:  ₹" + amount);
            System.out.println("Withdrawn:  ₹" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(String recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Transferred  ₹" + amount + " to " + recipient);
            System.out.println("Transferred  ₹" + amount + " to " + recipient);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void displayMenu() {
        System.out.println("Welcome, " + userId + "!");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter your user PIN: ");
        String userPin = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        ATMImpl atm = new ATMImpl(userId, userPin, initialBalance);

        while (true) {
            System.out.print("Enter your PIN: ");
            String pin = scanner.next();

            if (atm.authenticate(pin)) {
                atm.displayMenu();

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter recipient's user ID: ");
                        String recipientId = scanner.next();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        atm.transfer(recipientId, transferAmount);
                        break;
                    case 4:
                        System.out.println("Your balance: ₹" + atm.getBalance());
                        break;
                    case 5:
                        atm.displayTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
        }
    }
}