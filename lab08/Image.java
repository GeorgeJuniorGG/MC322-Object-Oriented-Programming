package lab8;

import java.util.Date;

public class Image extends Tweet {
    private int resolution;
    private int size; //em MB
    private ImageExtension extension;
    private String description;

    public Image(String title, Date date, int resolution, ImageExtension extension, String description) {
        super(title, date);
        this.resolution = resolution;
        this.extension = extension;
        this.size = resolution * 1;
        this.description = description;
    }
    
}