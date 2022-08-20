package lab8;

import java.util.Date;

public class Follow {
    private User user;
    private Date date;

    public Follow(User user, Date date) {
        this.user = user;
        this.date = date;
    }

    User getUser() {
        return user;
    }

    Date getDate() {
        return date;
    }
    
}