public class TimeSlot {
    private String time;
    private boolean taken = false;
    public  TimeSlot(String time){
        this.time = time;// using 24 hours format 00:00
    }
    public void setTime(String Time){
        this.time = Time;
    }
    public String getTime(){
        return time;
    }
    public void take(){
        taken = true;
    }
    public boolean isTaken() {
        return taken;
    }
}

