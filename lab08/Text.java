package lab8;

import java.util.Date;

public class Text extends Tweet {
    private String text;
    private String language;

    public Text(Date date, String title, String text, String language) {
        super(title, date);
        this.text = text;
        this.language = language;
    }
    
}