package lab10;

public class Vehicle {
    private String placa;
    private int fabricationYear;
    private Driver proprietary;
    private VehicleType type;

    public Vehicle(String placa, int fabricationYear, VehicleType type) {
        this.type = type;
        this.placa = placa;
        this.fabricationYear = fabricationYear;
        this.proprietary = null;
    }
    
    void registerProprietary(Driver driver) {
        this.proprietary = driver;
    }

    VehicleType getType() {
        return type;
    }
}