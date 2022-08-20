package lab8;

import java.util.Date;

public class Like {
    private User user;
    private Date date;

    public Like(User user, Date date) {
        this.user = user;
        this.date = date;
    } 
    
    User getUser() {
        return user;
    }
}