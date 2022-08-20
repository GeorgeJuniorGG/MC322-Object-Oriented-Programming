package lab06;

public class Song extends Media {
    public Song (String name, String author, int duration) {
        super(name, duration);
        this.author = author;
        this.memory = 5 * this.getDuration();
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