
import java.util.HashMap;


public class User {
    private double balance;
    String userName;
    private int ID;
    private static HashMap<Integer, User> users = new HashMap<>();
    
    public User(double x, String s, int y) {
        userName = s;
        ID = y;
        balance = x;
        users.put(y, this);
        this.ID = DBHelper.addUser(userName, balance); // save to DB
    }
    

    public String getName() {
        return userName;
    }

    public int getID() {
        return ID;
    }

    public void deposit(int x) {
        balance += x;
        System.out.println("deposit amount : " + x+"/n"+"Balance : "+balance);
    }

    public void withdraw(int x) {
        balance -= x;
        System.out.println("withdraw amount : " + x + "/n" + "Balance : " + balance);
    }

    public void Transfer(User user, int x) {
        if (x <= balance) {
            balance -= x;
            user.balance += x;
            return;
        }
        System.out.println("balance is not enough");
    }

    public void accountDetails() {
        System.out.println("UserName: " + userName + ", ID: " + ID);
        System.out.println("Balance: " + balance);
        System.out.println("Balance: " + DBHelper.getBalance(ID));
    }
     public void setBalance(double balance) {
        this.balance = balance;
        DBHelper.updateBalance(ID, balance);
    }

    public double getBalance() {
        return DBHelper.getBalance(ID);
    }

    // Get user from DB by ID
    public static User getUserByID(int id) {
        if (!DBHelper.userExists(id)) return null;
        double b = DBHelper.getBalance(id);
        return new User(b, "ExistingUser", id); // name placeholder
    }
}
