package lab8;

import java.util.Date;

public class Website extends Tweet {
    private String url;
    private Date accessDate;

    public Website(String title, Date date, String url, Date accessDate) {
        super(title, date);
        this.url = url;
        this.accessDate = accessDate;
    }
    
}