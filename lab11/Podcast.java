package lab11;

public class Podcast extends Media {
    Episode[] episodes;
    private final int MAX = 10;

    public Podcast(String name, String author) {
        super(name, 0);
        episodes = new Episode[MAX];
        memory = 0;
        this.author = author;        
    }

    public void addEpisode(Episode episode) {
        for(int i = 0; i < MAX; i ++) {
            if(episodes[i] == null) {
                episodes[i] = episode;
                this.memory += episode.getMemory();
                this.duration += episode.getDuration();
            }
        }
    }

    @Override
    public int getMemory() {
        return memory;
    }

    @Override
    public Media play() {
        return this;
    }

    @Override
    public boolean isPodcast() {
        return true;
    }
}