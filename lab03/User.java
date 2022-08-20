package Lab03;

public class User {
    public String name;
    private String cpf;
    private String birthDate;
    private String gender;
    private double balance;
    public boolean smoker;
    public User (String name, String cpf, String birthDate, String gender, double balance, boolean smoker) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.balance = balance;
        this.smoker = smoker;
    }
    public double getBalance() {
        return this.balance;
    }
    public void changeBalance(double amount) {
        this.balance += amount;        
    }
    public void getDetails() {
        System.out.print("O usuario " + this.name + " possui cpf " + this.cpf + ", sua data de nascimento e " + this.birthDate);
        System.out.print(", seu genero e: " + this.gender + ", seu saldo e " + this.balance);
        if (this.smoker) {
            System.out.println(" e e fumante.");
        }
        else {
            System.out.println(" e nao e fumante.");
        }
    }
}