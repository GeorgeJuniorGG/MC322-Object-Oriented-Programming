package lab06;

public class Album extends Media{
    private Song[] songs;
    private final int MAX = 20;

    public Album(String name) {
        super(name, 0);
        songs = new Song[MAX];  
        author = "Nenhum";
        memory = 0;    
    }

    public void addSong(Song song) {
        boolean check = false;
        for(int i = 0; i < MAX; i++) {
            if (songs[i] == null) {
                songs[i] = song;
                memory += song.getDuration() * 5;
                check = getAuthor();
                this.duration += song.getDuration();
                if(check) {
                    this.author = songs[i].author;
                }
                else {
                    this.author = "Varios Autores";
                }                
            }
        }
    }

    private boolean getAuthor() {
        String check = songs[0].author;
        for(int i = 1; i < MAX; i++) {
            if (songs[i] != null) {
                if(!songs[i].author.equals(check)) {
                    return false;
                }                
            }
        }
        return true;
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
        return false;
    }
}