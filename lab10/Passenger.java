package lab10;

import java.util.Date;

public class Passenger extends User {
    private int cardNumber;

    public Passenger(String name, String cpf, Date birthDate, int cardNumber) {
        super(name, cpf, birthDate);
        this.cardNumber = cardNumber;
    }
}