

public class Request {
    private String timeSlot;
    private Playground pg;
    static int count =0;
    private int ID;
    private String Email;
    public Request(String timeSlot, Playground pg) {
        ID = ++count;
        this.timeSlot = timeSlot;
        this.pg = pg;
    }
    public Request(String timeSlot, Playground pg, String email) {
        Email = email;
        ID = ++count;
        this.timeSlot = timeSlot;
        this.pg = pg;
    }

    public  Request(Playground pg) {
        this.pg = pg;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Playground getPlayground() {
        return pg;
    }

    public void setPlayground(Playground pg) {
        this.pg = pg;
    }
    public void show(){
        System.out.println("ID: " + ID);
        pg.showPlayground();
        System.out.println(timeSlot);
    }
    public int getID(){
        return ID;
    }
    public boolean activate(){
        for(int i=0 ;i < List.playerList.size(); i++){
            if(List.playerList.get(i).getEmail().equalsIgnoreCase(Email)){
                // add the booking to the player
                List.playerList.get(i).addToBooks(ID);
            }
        }
        return pg.take(timeSlot);
    }
}
