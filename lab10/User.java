package lab10;

import java.util.Date;

public class User {
    private String name;
    private String cpf;
    private Date birthDate;
    
    public User(String name, String cpf, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
    }

    String getName() {
        return name;
    }
    
}