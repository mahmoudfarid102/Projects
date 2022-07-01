import java.util.ArrayList;

public class Playground {

    private String name;
    private int capacity;
    private String location;
    private double hour_cost;
    private ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>(2);
    private Owner owner;
    private boolean permission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getHour_cost() {
        return hour_cost;
    }

    public void setHour_cost(double hour_cost) {
        this.hour_cost = hour_cost;
    }

    public ArrayList<TimeSlot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<TimeSlot> slots) {
        this.slots = slots;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void addSlot(TimeSlot ts){
        slots.add(ts);
    }


    public Playground(String name, int capacity, String location, double hour_cost, Owner owner) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.hour_cost = hour_cost;
        this.owner = owner;
    }

    public Playground(String name, int capacity, String location, double hour_cost, ArrayList<TimeSlot> slots, Owner owner) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.hour_cost = hour_cost;
        this.slots = slots;
        this.owner = owner;
    }


    public void showPlayground(){
        System.out.print( "Playground Name: " + name + "\nPlayground capacity: " + capacity
                +"\nPlayground location: " + location + "\nPlayground hour cost: " + hour_cost
                +"\nPlayground slots: ");

    }
    public void available_slots(){
        for(TimeSlot tms : slots){
            if(tms.isTaken()){
                continue;
            }
            else{
                System.out.println(tms.getTime());
            }
        }
    }
    public boolean take(String tms){
        for(TimeSlot T : slots){
            if(T.getTime().equalsIgnoreCase(tms) && T.isTaken() == false){// the slot exists and is available
                T.take(); // to take the slot as booked
                return true;
            }
        }
        // since we got here so the slot must be not exist
        return false;
    }

    public void setPermission(boolean permission){
        this.permission = permission;
    }
}
