package lab8;

import java.util.Date;

public class Retweet {
    private User user;
    private Date date;    

    public Retweet(User user, Date date) {
        this.user = user;
        this.date = date;
    }

    User getUser() {
        return user;
    }
}