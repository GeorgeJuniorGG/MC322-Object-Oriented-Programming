package Lab03;

public class Hotel {
    public String name;
    public String address;
    public String phone;
    public Room[] rooms;
    public double regularPrice;
    public double vipPrice;
    public Hotel(String name, String address, String phone, double regularPrice, double vipPrice) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.regularPrice = regularPrice;
        this.vipPrice = vipPrice;
        this.rooms = new Room[100];
    }
    public void getPrices() {
        System.out.println("O preco da diaria comum e " + this.regularPrice + " e o preco da diaria vip e " + this.vipPrice + ".");
    }
    public boolean[] getRoomsStatus () {
        boolean[] status = new boolean[100];
        for (int i = 0; i < 100; i++) {
            if (this.rooms[i] != null) {
                if (this.rooms[i].isOccupied()) {
                    status[i] = true;
                }
                else {
                    status[i] = false;
                }
            }
        }
        return status;
    }
    public void getDetails() {
        boolean[] status = getRoomsStatus();
        int count = 0;
        System.out.println("Os quartos disponiveis sao:");
        for (int i = 0; i < 100; i++) {
            if (!status[i]) {
                System.out.print( (i + 1) + " cujo preco da diaria e ");
                count++;
                if (this.rooms[i].vip) {
                    System.out.println(this.vipPrice);
                }
                else {
                    System.out.println(this.regularPrice);
                }
            }
        }
        System.out.println("");
        System.out.println("O hotel " + this.name + " possui " + count + " quartos disponiveis.");
    }
}