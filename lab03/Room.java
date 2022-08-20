package Lab03;

public class Room {
    public boolean vip;
    private boolean occupied;
    public boolean smokers;
    public boolean airConditioner;
    public double ammountPaid;
    public Room(boolean vip, boolean smokers, boolean airConditioner) {
        this.vip = vip;
        this.smokers = smokers;
        this.airConditioner = airConditioner;
    }
    public boolean isOccupied() {
        if(this.occupied) {
            return true;
        }
        else {
            return false;
        }     
    }
    public void changeStatus() {
        if (this.occupied) {
            this.occupied = false;
        }
        else {
            this.occupied = true;
        }
    }
}