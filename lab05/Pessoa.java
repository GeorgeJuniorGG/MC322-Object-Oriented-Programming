package lab05;

public class Pessoa {
    private String name;
    private String email;
    private int phoneNumber;
    private boolean confirmed;

    public Pessoa(String name, String email, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.confirmed = false;
    }

    public String getName() {
        return name;
    }

    public  String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public boolean getStatus() {
        return confirmed;
    }

    public void confirmPerson() {
        confirmed = true;
    }
}