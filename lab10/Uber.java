package lab10;

import java.util.Calendar;
import java.util.Date;

public class Uber {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1998);
        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        Date date1 = cal.getTime();
        Passenger user1 = new Passenger("Marcos", "145678798", date1, 369874);
        Vehicle car1 = new Vehicle("ABC-1234", 2009, VehicleType.LUXO);
        Vehicle car2 = new Vehicle("OOP-2020", 2013, VehicleType.COMUM);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 1997);
        cal2.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 12);
        Date date2 = cal2.getTime();
        Vehicle[] vehicles = {car1, car2};
        Driver user2 = new Driver("Maria", "248679108", date2, 483530, 987654, vehicles);
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR, 2002);
        cal3.set(Calendar.MONTH, Calendar.JANUARY);
        cal3.set(Calendar.DAY_OF_MONTH, 3);
        Date date3 = cal3.getTime();
        Passenger user3 = new Passenger("Joao", "654973652", date3, 785632);
        Passenger[] passengers1 = {user1};
        Ride viagem1 = new Ride(passengers1, user2, car1, 500);
        viagem1.addStop(200);
        viagem1.addStop(400);
        viagem1.printSummary();
        Passenger[] passengers2 = {user1, user3};
        Ride viagem2 = new Ride(passengers2, user2, car2, 700);
        viagem2.addStop(200);
        viagem2.addStop(400);
        viagem2.addStop(600, user3);
        viagem2.printSummary();
        Passenger[] passengers3 = {user3};
        Ride viagem3 = new Ride(passengers3, user2, car1, 700);
        viagem3.addStop(150);
        viagem3.addStop(450);
        viagem3.addStop(575);
        viagem3.printSummary();        
    }
    
}