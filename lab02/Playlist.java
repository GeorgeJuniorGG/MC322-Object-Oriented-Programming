package Lab02;
import java.util.Random;

public class Playlist {
    public String name;
    public String genre;
    public int numberOfSongs;
    private int next;
    private Song[] songs;
    public Playlist(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.numberOfSongs = 0;
        this.next = 0;
        this.songs = new Song[100];
	}
	public void addSong (Song song) { //adiciona musicas enquanto as organiza em ordem alfabetica
        if (this.numberOfSongs < 100) {
            if (this.numberOfSongs == 0) {
                this.songs[0] = song;
                this.numberOfSongs ++;
            }
            else {
                int next = this.numberOfSongs;
                for (int i = 0; i < this.numberOfSongs; i ++) {
                    if (song.name.compareTo(this.songs[i].name) < 0) {
                        next = i;
                        break;
                    }
                }
                for (int i = this.numberOfSongs; i > next; i --) {
                    this.songs[i] = this.songs[i - 1];
                }
                this.songs[next] = song;
                this.numberOfSongs ++;
            }
        }
        else {
            System.out.println("You have reached the maximum amount of songs! You have to delete one before adding a new one!");
        }
    }
    public void removeSong (Song song) {
        int removed = 0;
        for (int i = 0; i < this.numberOfSongs; i++) {
            if (this.songs[i] != null) {
                if (this.songs[i].name == song.name) {
                    this.songs[i] = null;
                    this.numberOfSongs--;
                    removed = i;
                    break;
                }
            }
        }
        for (int i = removed + 1; i < this.numberOfSongs + 1; i++) { //reorganiza o vetor
            this.songs[i - 1] = this.songs[i];
        }
        this.songs[this.numberOfSongs] = null;
    }
    public void getShortestSong () {
        int min = 0;
        for (int i = 1; i < this.numberOfSongs; i++) {
            if (this.songs[i].length < this.songs[min].length)
                min = i;
        }
        System.out.println("The shortest song in playlist " + this.name + " is " + this.songs[min].name);
    }
    public void getLongestSong () {
        int max = 0;
        for (int i = 1; i < this.numberOfSongs; i++) {
            if (this.songs[i].length > this.songs[max].length)
                max = i;
        }
        System.out.println("The longest song in playlist " + this.name + " is " + this.songs[max].name);
    }
    public void getAverageLength () {
        double average = 0;
        for (int i = 0; i < this.numberOfSongs; i++) {
            average += this.songs[i].length;
        }
        average = average/this.numberOfSongs;
        System.out.println("The average length in playlist " + this.name + " is " + average);
    }
    public void getTotalLength () {
        int total = 0;
        for (int i = 0; i < this.numberOfSongs; i++) {
            total += this.songs[i].length;
        }
        System.out.println("The total length in playlist " + this.name + " is " + total);    
    }
    public void getMostMusicArtist () {
        String artists[] = new String [this.numberOfSongs];
        int count[] = new int [this.numberOfSongs];
        boolean marked = false;
        int max = 0;
        for (int i = 0; i < this.numberOfSongs; i++) {
            marked = false;
            for (int j = 0; j < this.numberOfSongs; j++) {
                if (this.songs[i].artist == artists[j]) {
                    count[j] ++;
                    marked = true;
                    break;
                }
            }
            if (!marked) {
                for (int j = 0; j < this.numberOfSongs; j++) {
                    if (artists[j] == null) {
                        artists[j] = this.songs[i].artist;
                        count[j] ++;
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < this.numberOfSongs; i++) {
            if (count[i] > count[max]) {
                max = i;
            }
        }
        System.out.println("The artist with more songs in playlist " + this.name + " is " + artists[max]);
    }
    public void play() { //retorna a proxima musica, estando elas em ordem alfabetica
        if (this.numberOfSongs != 0) {
            int aux = this.next;
            if (this.songs[this.next + 1] != null)
                this.next = (this.next + 1) % (this.numberOfSongs);
            System.out.println("Now Playing " + this.songs[aux].name);
        }
    }
    public void play(Boolean shuffle) {
        if (shuffle) {
            Random random  = new Random();
            int aux = (int) (random.nextDouble() * (double) this.numberOfSongs);
            while (aux == (this.next - 1) % (this.numberOfSongs))
                aux = (int) (random.nextDouble() * (double) this.numberOfSongs);        
            System.out.println("Now Playing " + this.songs[aux].name);
        }
    }
    public void getDetails () {
        System.out.print("Playlist " + this.name + " has songs: ");
        int count = 1;
        for (int i = 0; i < 100; i++) {
            if (this.songs[i] != null) {
                if (count != this.numberOfSongs) {
                    System.out.print(this.songs[i].name + ", ");
                    count++;
                }
                else
                    System.out.println(this.songs[this.numberOfSongs - 1].name + ".");
            }
        }
    }
}