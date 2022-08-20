package lab10;

import java.util.Date;

public class Driver extends User {
    private int cardNumber;
    private int licenseNumber;
    private Vehicle[] vehicles;

    public Driver(String name, String cpf, Date birthDate, int cardNumber, int licenseNumber, Vehicle[] vehicles) {
        super(name, cpf, birthDate);
        this.cardNumber = cardNumber;
        this.licenseNumber = licenseNumber;
        this.vehicles = vehicles;
        addVehicles(vehicles);
    }

    private void addVehicles(Vehicle[] vehicles) {
        for(int i = 0; i < vehicles.length; i++) {
            vehicles[i].registerProprietary(this);
        }
    }
}