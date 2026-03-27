import java.io.*;
import java.util.Date;
import java.util.Random;

public class Slot implements Comparable<Slot> {
    private String vehicleType;
    private double price;
    private Date dateEntrance;
    private Date dateExit;
    private int registrationNum;
    private User user;

    public Slot(String vehicleType, int floorNUM, Date date1, Date date2, int registrationNum, User user) {
        this.vehicleType = vehicleType;
        this.registrationNum = registrationNum;
        this.dateEntrance = date1;
        this.dateExit = date2;
        this.registrationNum = new Random().nextInt(floorNUM > 0 ? floorNUM : 1);
        this.user = user;
    }
    public String getVehicleType() {
        return vehicleType;
    }

    public double getPrice(int time) {
        return 0.05*time; 
    }

    public Date getDateEntrance() {
        return dateEntrance;
    }
    public Date getDateExit() {
        return dateExit;
    }
    public User getUser() {
        return user;
    }
    public void print(){
        System.out.print("Vehicle Type: " + this.vehicleType + "\n");
        System.out.print("Price: " + this.price + "\n");
        System.out.print("Date Entrance: " + this.dateEntrance + "\n");
        System.out.print("Date Exit: " + this.dateExit + "\n");
    }

    @Override
    public int compareTo(Slot o) {
        return this.dateEntrance.compareTo(o.dateEntrance);
    }
}