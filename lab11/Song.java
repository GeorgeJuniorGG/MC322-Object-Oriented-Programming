package lab11;

import java.util.Scanner;

public class Song extends Media {
    public Song (String name, String author, int duration) {
        super(name, duration);
        this.author = author;
        this.memory = 5 * this.getDuration();
    }

    public Song() {
        super();
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Who's the author?");
            this.author = input.next();
            this.memory = 5 * this.getDuration();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        finally {
            input.close();
        }
    }
    @Override
    public Media play() {
        return this;
    }

    @Override
    public int getMemory() {
        return memory;
    }

    @Override
    public boolean isPodcast() {
        return false;
    }

}