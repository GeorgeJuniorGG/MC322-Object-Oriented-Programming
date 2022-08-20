package lab8;

import java.util.Date;

public class Video extends Tweet {
    private int duration; //Em minutos
    private int size;
    private VideoExtension extension; //Em MB

    public Video(int duration, VideoExtension extension, String title, Date date) {
        super(title, date);
        this.duration = duration;
        this.extension = extension;
        this.size = 3 * duration;
    }
    
}