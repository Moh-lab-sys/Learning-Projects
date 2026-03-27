import java.util.*;

public class test{
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Parking parking = new Parking("Mohammad",3,5);
        System.out.println("test");
        
        int x = 0;
        String signedinName = "";
        boolean signedin = false;
        while(x!=7) {
            System.out.println("Welcome to our parking ! +\n+ choose from the menu: ");
            System.out.println(
                "===== Parking System Menu =====\n" +
                        "1. Sign Up\n" +
                        "2. Already have an account -> sign In\n" +
                        "3. Park Vehicle\n" +
                        "4. Remove Vehicle\n" +
                        "5. View Your history\n" +
                        "6. Exit\n" +
                        "===============================\n" +
                        "7. History Display\n" +
                        "Enter your choice: "
            );
            x = input.nextInt();
            
            
            switch (x) {
                case 1: {
                    System.out.println("Enter your name , email, and password: ");
                    String name = input.next();
                    signedinName = name;
                    String email = input.next();
                    while (!email.contains("@") || !email.contains(".")) {
                        System.out.println("Invalid email");
                        email = input.next();
                        
                    }
                    String password = input.next();
                    System.out.println("Do you want to register as VIP ? Y/N ");
                    String vip = input.next();
                    User u = new User(name, email, password, vip.equalsIgnoreCase("Y"));
                    parking.register(u);
                    break;
                }
                case 2: {
                    System.out.println("Enter your name: ");
                    String name = input.next();
                   
                    signedin = parking.login(name);
                    
                    if (signedin) {
                        signedinName = name;
                    }
                    break;
                }
                case 3: {
                    if (!signedin) {
                        System.out.println("Please sign in first.");
                        break;
                    }
                    
                    String name = signedinName;
                    System.out.println("Vehicle Type : ");
                    String type = input.next();
                    System.out.println("Please enter which floor : ");
                    int floor = input.nextInt();
                    User u2 = null;
                    for (User u : parking.getUsers()) {
                        if (u.getName().equals(name)) u2 = u;
                    }
                    if(u2 == null) {
                        System.out.println("User not found.");
                        break;
                    }
                    Date date = new Date();
                    Date date2 = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date2);
                    System.out.println("How much do you want to keep it (days)?");
                    int xDays = input.nextInt();
                    cal.add(Calendar.DAY_OF_MONTH, xDays);
                    parking.park(u2, type, floor, date, cal.getTime());
                    break;
                }
                case 4: {
                    if (!signedin) {
                        System.out.println("Please sign in first.");
                        break;
                    }
                    
                    String name = signedinName;
                    Slot slot = null;
                    for (Map.Entry<User, Queue<Slot>> e : parking.getUS().entrySet()) {
                        if (e.getKey().getName().equals(name) && !e.getValue().isEmpty()) {
                            slot = e.getValue().poll();
                        }
                    }
                    parking.unpark(slot);
                    break;
                }
                case 5: {
                    if (!signedin) {
                        System.out.println("Please sign in first.");
                        break;
                    }
                    
                    String name = signedinName;
                    System.out.println("History saved in file: " + parking.history());
                    break;
                }
                case 6: {
                    System.exit(0);
                }
                case 7: {
                    System.out.println(parking.history());
                    break;
                }
            }
        }
    }
}