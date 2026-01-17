import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


    public class Transaction {

        private String desc;
        private String title;
        private static List<User> list = new LinkedList<>();
        private static HashMap<User, List<Transaction>> UTmap = new HashMap<>();
        private String bankName;
        private static HashMap<Transaction, List<User>> TUmap = new HashMap<>();
        public Transaction(String s) {
            desc = s;
        }
        public static Map<User, List<Transaction>> getUTmap() {
            return UTmap;
        }

        public static Map<Transaction, List<User>> getTUmap() {
            return TUmap;
        }

        public String getDescription() {
            return this.desc;
        }
        public String getTitle() {
            return this.title;
        }

        public Transaction deposit(User u1, int x) {
            u1.deposit(x);
            String description = u1.userName+" deposited " + x + " $";
            Transaction t = new Transaction(description);
           if(u1!=null && UTmap.containsKey(u1)){
                List<Transaction> list = UTmap.get(u1);
                list.add(t);
            }
            else{
                List<Transaction> list = new LinkedList<>();
                list.add(t);
            UTmap.put(u1, list);
            }

            if(t!=null && TUmap.containsKey(t)){
                List<User> list2 = TUmap.get(t);
                list2.add(u1);
            }
            else{
                List<User> list2 = new LinkedList<>();
                list2.add(u1);
            TUmap.put(t, list2);
            }
            System.out.println(UTmap);
            t.title = "Deposit Transaction";
            return t;
        }

        public Transaction withdraw(User u1, int x) {
            u1.withdraw(x);
            String description = u1.userName+" withdrawn " + x + " $";
            Transaction t = new Transaction(description);
            if(u1!=null && UTmap.containsKey(u1)){
                List<Transaction> list = UTmap.get(u1);
                list.add(t);
            }
            else{
                List<Transaction> list = new LinkedList<>();
                list.add(t);
            UTmap.put(u1, list);
            }

            if(t!=null && TUmap.containsKey(t)){
                List<User> list2 = TUmap.get(t);
                list2.add(u1);
            }
            else {
                List<User> list2 = new LinkedList<>();
                list2.add(u1);
                TUmap.put(t, list2);
            }
            t.title = "Withdraw Transaction";
            return t;
        }
        public Transaction Transfer(User u1, User u2, int x) {
            u1.Transfer(u2,x);
            String description = u1.userName+" transfered " + x + " $ to "+u2.userName;
            Transaction t = new Transaction(description);
              if(u1!=null && UTmap.containsKey(u1)){
                List<Transaction> list = UTmap.get(u1);
                list.add(t);
            }
            else{
                List<Transaction> list = new LinkedList<>();
                list.add(t);
            UTmap.put(u1, list);
            }

            if(t!=null && TUmap.containsKey(t)){
                List<User> list2 = TUmap.get(t);
                list2.add(u1);
            }
            else {
                List<User> list2 = new LinkedList<>();
                list2.add(u1);
                TUmap.put(t, list2);
            }
            t.title = "Transfer Transaction";
            return t;
        }
    }

