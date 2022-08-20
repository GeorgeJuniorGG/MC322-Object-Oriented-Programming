package lab11;

public class Video extends Media{
    public Video(String name, String author, int duration) {
        super(name, duration);
        this.author = author;
        this.memory = 5 * duration + 1980 * 1080 /1000000;
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