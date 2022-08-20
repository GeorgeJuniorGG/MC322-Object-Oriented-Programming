package lab10;

public class Ride {
    private static double PRECO_FIXO_COMUM = 3;
    private static double PRECO_100_METROS_COMUM = 2;
    private static double PRECO_PARADA_COMUM = 1.5;
    private static double PRECO_FIXO_LUXO = 7;
    private static double PRECO_100_METROS_LUXO = 3.5;
    private static double PRECO_PARADA_LUXO = 2.7;
    private static int MAX_STOPS = 10;

    private Passenger[] passengers;
    private Passenger[] exPassengers;
    private Driver driver;
    private Vehicle vehicle;
    private int distance;
    private int stops;
    private String[] stopPoints;

    public Ride(Passenger[] passengers, Driver driver, Vehicle vehicle, int distance) {
        this.passengers = passengers;
        this.driver = driver;
        this.vehicle = vehicle;
        this.distance = distance;
        this.stops = 0;
        this.stopPoints = new String[MAX_STOPS];
        this.exPassengers = new Passenger[passengers.length];
    }

    void addStop(int stop) {
        this.stops ++;
        for(int i = 0; i < MAX_STOPS; i++) {
            if(stopPoints[i] == null) {
                stopPoints[i] = String.valueOf(stop) + "m";
                break;
            }
        }
    }

    void addStop(int stop, Passenger passenger) {
        this.stops++;
        for(int i = 0; i < MAX_STOPS; i++) {
            if(stopPoints[i] == null) {
                stopPoints[i] = String.valueOf(stop) + "m (" + passenger.getName() + " saiu)";
                break;
            }
        }
        for(int i = 0; i < passengers.length; i++) { //se o passageiro ja estiver no veiculo, ele e removido
            if (passengers[i] == passenger) {
                passengers[i] = null;
                break;
            }
            else if (i == passengers.length) {
                for (int j = 0; j < passengers.length; j++) { //caso contrario, ele e adicionado
                    if(passengers[j] == null) {
                        passengers[j] = passenger;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < passengers.length; i++) {
            if (exPassengers[i] == null) {
                exPassengers[i] = passenger;
                break;
            }
        }
    }

    private double getPrice() {
        double price = 0;
        if(this.vehicle.getType() == VehicleType.COMUM) {
            price = PRECO_FIXO_COMUM;
            for(int i = 0; i < distance/100; i++) {
                price += PRECO_100_METROS_COMUM;
            }
            for(int i = 0; i < stops; i++) {
                price += PRECO_PARADA_COMUM;
            }
        }
        else if(this.vehicle.getType() == VehicleType.LUXO) {
            price = PRECO_FIXO_LUXO;
            for(int i = 0; i < distance/100; i++) {
                price += PRECO_100_METROS_LUXO;
            }
            for(int i = 0; i < stops; i++) {
                price += PRECO_PARADA_LUXO;
            }
        }
        return price;
    }

    void printSummary() {
        System.out.println("==== RESUMO DA VIAGEM ==== ");
        System.out.println("Motorista: " + driver.getName());
        System.out.print("Passageiros: ");
        for (int i  = 0; i < passengers.length; i++) {
            if(passengers[i] != null) {
                System.out.print(passengers[i].getName() + " ");
            }
            if(exPassengers[i] != null) {
                System.out.print(exPassengers[i].getName() + " ");;
            }
        }
        System.out.println("");
        System.out.println("Distancia percorrida: " + distance + "m");
        System.out.print("Paradas: ");
        for(int i = 0; i < stopPoints.length; i++) {
            if(stopPoints[i] != null) {
                System.out.print(stopPoints[i] +" ");
            }
        }
        System.out.println("");
        System.out.println("Preco total: " + getPrice());
    }
    
}