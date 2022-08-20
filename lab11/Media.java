package lab11;

import java.util.Scanner;

public abstract class Media {
    private String name;
    protected int duration;
    protected String author;
    protected int memory;

    public Media(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public Media() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("What's the media name?");
            this.name = input.next();
            System.out.println("What's the media duration?");
            this.duration = input.nextInt();
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        finally {
            input.close();
        }
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public abstract Media play();
    public abstract int getMemory();
    public abstract boolean isPodcast();

}