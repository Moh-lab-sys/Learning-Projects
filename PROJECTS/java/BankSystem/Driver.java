import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        String s = "===== Welcome to the Online Banking System =====\r\n" +
                "Please select an option:\r\n" +
                "\r\n" +
                "1. Create a New Account\r\n" +
                "2. Deposit Money\r\n" +
                "3. Withdraw Money\r\n" +
                "4. Transfer Money\r\n" +
                "5. View Account Details\r\n" +
                "6. View Transaction History\r\n" +
                "7. Exit\r\n" +
                "\r\n" +
                "Enter your choice:\r\n";
                 int idd = DBHelper.addUser("Mohammad", 1000);
        System.out.println("Returned ID: " + idd);
        System.out.println(s);
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        while (choice != 7) {
            System.out.println(s);
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter your name and intial balance");
                    String name = in.next();
                    double b = in.nextDouble();
                    User u = new User(b, name, new Random().nextInt(1000));
                    System.out.println("Account created successfully! Your User ID is: " + u.getID());
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Enter your User ID:");
                    int id = in.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    int amount = in.nextInt();
                    Transaction t = new Transaction("");
                    User u1 = User.getUserByID(id);
                    t.deposit(u1, amount);
                    System.out.println("Money deposited successfully!");
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Enter your User ID:");
                    int id2 = in.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    int amount2 = in.nextInt();
                    Transaction t2 = new Transaction("");
                    User u2 = User.getUserByID(id2);
                    t2.withdraw(u2, amount2);
                    System.out.println("Money withdrawn successfully!");
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Enter your User ID:");
                    int id3 = in.nextInt();
                    System.out.print("Enter amount to transfer  : ");
                    int amount3 = in.nextInt();
                    System.out.println("Enter receiver's User ID:");
                    int id7 = in.nextInt();
                    Transaction t3 = new Transaction("");
                    User u3 = User.getUserByID(id3);
                    t3.Transfer(u3, User.getUserByID(id7), amount3);
                    System.out.println("Money transferred successfully!");
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Enter your User ID:");
                    int id4 = in.nextInt();
                    User u4 = User.getUserByID(id4);
                    u4.accountDetails();
                    System.out.println();
                    break;
                case 6:
                    Transaction t4 = new Transaction("");
                    System.out.println("Transaction history feature is under development.");
                    if (Transaction.getTUmap().isEmpty()) {
                        System.out.println("No transactions found.");
                        break;
                    }
                    boolean once = true;
                    String prev = "";
                    for (Map.Entry<Transaction, List<User>> entry : Transaction.getTUmap().entrySet()) {
                    String st = entry.getKey().getTitle();
                   // once = st.equals(prev);
                    if (once)
                        System.out.println("          Transaction Title: " + st + "\n");
                    prev = st;
                    once = !st.equals(prev);
                    System.out.println(entry.getKey().getDescription() + " : " + entry.getValue().toString());
                    System.out.println();
                   }

                    break;
                case 7:
                    System.out.println("Thank you for using the Online Banking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
