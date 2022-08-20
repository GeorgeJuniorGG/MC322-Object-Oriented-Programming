package Lab03;

public class Booking {
    public static void main (String [] args) {
        Hotel hotel1 = new Hotel("Praia Tropical", "Rua Tajuba, 201 - Florian ́opolis, SC", "3225-8997", 100.00, 900.00);
        Hotel hotel2 = new Hotel("Campo Floresta", "Rua Monteiro, 456 - Goiania, GO", "3654-8974", 50.00, 2000.00);
        User user1 = new User("Roberci Silva", "784245698-21", "12/04/1996", "masculino", 1000.00, true);
        User user2 = new User("Marcela Domingues", "269784061-45", "22/07/1998", "feminino", 2000.00, false);
        for (int i = 0; i < 10; i++) {
            hotel1.rooms[i] = new Room(true, true, true);
            hotel2.rooms[i] = new Room(true, true, true);
        }
        for (int i  = 10; i < 100; i++) {
            hotel1.rooms[i] = new Room(false, false, false);
            hotel2.rooms[i] = new Room(false, false, false);
        }
        bookRoom(user1, hotel1, 2, 1);
        bookRoom(user2, hotel2, 13, 4);
        bookRoom(user1, hotel1, 87, 1);
        cancelBooking(user2, hotel1, 22);
        bookRoom(user1, hotel2, 99, 1);
        cancelBooking(user1, hotel2, 99);
        bookRoom(user2, hotel2, 87, 1);
    }
    public static void bookRoom(User user, Hotel hotel, int roomNumber, int numberOfDays) {
        boolean[] status = hotel.getRoomsStatus();
        if (status[roomNumber - 1]) {
            System.out.println("O quarto desejado ja esta ocupado!");
        }
        else {
            if(user.smoker && !hotel.rooms[roomNumber - 1].smokers) {
                System.out.println("Esse quarto nao permite fumantes!");
            }
            else {
                if (roomNumber <= 10) {
                    if (user.getBalance() < hotel.vipPrice * numberOfDays) {
                        System.out.println("O cliente " + user.name + " nao possui dinheiro suficiente!");
                    }
                    else{
                        user.changeBalance(-hotel.vipPrice * numberOfDays);
                        hotel.rooms[roomNumber - 1].changeStatus();
                        status[roomNumber - 1] = true;
                        hotel.rooms[roomNumber - 1].ammountPaid = numberOfDays * hotel.vipPrice;
                    }
                }
                else {
                    if(user.getBalance() < hotel.regularPrice * numberOfDays) {
                        System.out.println("O cliente " + user.name + " nao possui dinheiro suficiente!");
                    }
                    else {
                        user.changeBalance(-hotel.regularPrice * numberOfDays);
                        hotel.rooms[roomNumber - 1].changeStatus();
                        status[roomNumber - 1] = true;
                        hotel.rooms[roomNumber - 1].ammountPaid = numberOfDays * hotel.regularPrice;
                    }
                }
            }
        }
    }
    public static void cancelBooking (User user, Hotel hotel, int roomNumber) {
        if (hotel.rooms[roomNumber - 1].isOccupied()) {
            user.changeBalance(0.70 * hotel.rooms[roomNumber - 1].ammountPaid);
            hotel.rooms[roomNumber - 1].changeStatus();
        }
    }
}