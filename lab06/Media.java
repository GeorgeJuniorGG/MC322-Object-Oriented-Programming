package lab06;

public abstract class Media {
    private String name;
    protected int duration;
    protected String author;
    protected int memory;

    public Media(String name, int duration) {
        this.name = name;
        this.duration = duration;
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