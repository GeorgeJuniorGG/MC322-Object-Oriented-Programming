package lab06;

public class Episode extends Podcast {
    int memory;

    public Episode(String name,int duration, String author) {
        super(name, author);
        memory = duration * 3;
        this.duration = duration;
    }

    public int getMemory() {
        return memory;
    }
}