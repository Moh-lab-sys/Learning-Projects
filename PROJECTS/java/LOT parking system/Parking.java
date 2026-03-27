import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Parking {
    private String name;
    private int id;
    private int floors;
    private int slotsPerfloor;
    private TreeSet<Slot> slots;
    private TreeSet<Slot> VIPslots;
    public HashSet<User> users;
    private HashMap<User, Queue<Slot>> US;
    private List<Boolean> a;

    private File history;
    private File registers;

    public Parking (String name,int floors, int slotsPerfloor){
        this.name = name;
        id = name.hashCode();
        this.floors = floors;
        this.slotsPerfloor = slotsPerfloor;
        this.slots = new TreeSet<>();
        this.users = new HashSet<>();
        history = new File("history.txt");
        registers = new File("registers.txt");
        US = new HashMap<>();
        VIPslots = new TreeSet<>();
        a = new ArrayList<>();
        a.add(false); 
    }
    public int getId(){
        return id;
    }
    public int getFloors(){
        return floors;
    }
    public int getSlotsPerfloor(){
        return slotsPerfloor;
    }
    public TreeSet<Slot> getSlots(){
        return slots;
    }

    public HashSet<User> getUsers(){
        return users;
    }
    public HashMap<User, Queue<Slot>> getUS(){
        return US;
    }
    public void register(User user) throws IOException {
        if(!users.contains(user)){
            users.add(user);
            US.put(user, new LinkedList<>());
            System.out.println("Successfully registered.");
            FileWriter fw3 = new FileWriter("registers.txt", true);
            fw3.write("User : "+user.getName()+" registered at "+new Date().toString()+"\n");
            fw3.close();
            return;
        }

       System.out.println("User already exists.");
    }
    public boolean login(String name) throws IOException {
        boolean b = false;
        for(User user:users){
            if(user.getName().equals(name)){
                b = true;
            }

        }

        String s = b ? "Welcome " + name : "Not found, Sign Up ";
        System.out.println(s);
        if(b) {
            FileWriter fw4 = new FileWriter("registers.txt", true);
            fw4.write("User : "+name+" signed in at "+new Date().toString()+"\n");
            fw4.close();
        }
        return b;
    }
    public String history(){
        Scanner reader = new Scanner("history.txt");
        String print = "";
            // Loop through the file until there are no more lines
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                print += data + "\n";
            }
            
            reader.close();
        return print;
    }
    public void park(User user, String v,int f,Date e, Date e2) throws Exception{
        if(!users.contains(user)){
            System.out.println("User doesn't exists.");
            System.out.println("Register first.");
            return;
        }

        boolean full= false;
        for(boolean b : a) {
            full = full || b;
        }

            if (full){
                System.out.println("Sorry no slots available.");
                return;
            }
            Slot s = new Slot(v,f, e,e2,v.hashCode(),user);
         if(user.isVIP() && !full){
             VIPslots.add(s);
         }
         else if(!full){
             slots.add((s));
         }

        users.add(user);
         US.get(user).add(s);


        System.out.println("Successfully Booked .");
        FileWriter fw = new FileWriter("history.txt", true);
        fw.write("User "+user.getName()+" parked at "+new Date().toString()+"\n");
        fw.close();
    }
    public void unpark(Slot slot) throws IOException {
        if(slot == null || (!slots.contains(slot) && !VIPslots.contains(slot))){
            System.out.println("NOT FOUND.");
            return;
        }
        slots.remove(slot);
        VIPslots.remove(slot);
        User user = slot.getUser();
        US.get(user).remove(slot);
        System.out.println("Successfully unparked .");
        slot.print();
        FileWriter fw2 = new FileWriter("history.txt", true);
        fw2.write("User "+user.getName()+" unparked at "+new Date().toString()+"\n");
        fw2.close();
    }
}