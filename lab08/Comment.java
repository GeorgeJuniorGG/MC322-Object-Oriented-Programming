package lab8;

import java.util.Date;

public class Comment {
    private String text;
    private Date date;

    public Comment(String text, Date date) {
        this.date = date;
        this.text = text;
    }
    
    String getText() {
        return text;
    }
}