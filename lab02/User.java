package Lab02;
import java.util.Scanner;

public class User {
    public String name;
    public String cpf;
    public String birthDate;
    public String gender;
    public boolean subscriber; //se sim, tera ate 10 playlists, caso contrario, tera ate 3
    private final Playlist[] playlists;
    private int numberOfPlaylists;
    private int playlistLimit;
    private int songsPerPlaylistLimit;
    public User(final String name, final String cpf, final String birthDate, final String gender, final boolean subscriber) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.subscriber = subscriber;
        this.playlists = new Playlist [10];
        this.numberOfPlaylists = 0;
        if (subscriber) {
            this.playlistLimit = 10;
            this.songsPerPlaylistLimit = 100;
        }
        else {
            this.playlistLimit = 3;
            this.songsPerPlaylistLimit = 10;    
        }
	}
	public void addPlaylist(final Playlist playlist) {
        if (playlist.numberOfSongs > this.songsPerPlaylistLimit)
            System.out.println("This playlist has more than 10 songs, subscribe to be able to add this!");
        else {
            if (this.numberOfPlaylists == this.playlistLimit) 
                System.out.println("You have to remove a playlist before adding another!");
            else {
                for (int i = 0; i < this.playlistLimit; i++) {
                    if (this.playlists[i] == null) {
                        this.playlists[i] = playlist;
                        this.numberOfPlaylists ++;
                        break;
                    }
                }
            }
        }
    }
    public void removePlaylist(final Playlist playlist) {
        int removed = 0;
        for (int i = 0; i < this.playlistLimit; i++) {
            if (playlist.name == this.playlists[i].name) {
                this.playlists[i] = null;
                this.numberOfPlaylists --;
                removed = i;
                break;
            }
        }
        for (int i = removed + 1; i < this.playlistLimit; i++) { //reorganiza o vetor
            this.playlists[i - 1] = this.playlists[i];
        }
        this.playlists[this.playlistLimit - 1] = null;
    }
	public Playlist[] getPlaylists() {
		return this.playlists;
	}
	public void getDetails() {
        if (this.numberOfPlaylists > 0) {
            int count = 1;
            System.out.print("User " + this.name + " has playlists: " );
            for (int i = 0; i < this.playlistLimit; i++) {
                if (this.playlists[i] != null) {
                    if (count != this.numberOfPlaylists) {
                        System.out.print(this.playlists[i].name + ", ");
                        count++;
                    }
                    else
                        System.out.println(this.playlists[i].name + ".");
                }
            }
        }
        else
            System.out.println("This user does not have any playlists!");
    }
    public void transferPlaylist(final Playlist playlist, final User user) {
        if (playlist.numberOfSongs <= user.songsPerPlaylistLimit && user.numberOfPlaylists < user.playlistLimit) {
            for (int i = 0; i < user.playlistLimit; i++) {
                if (user.playlists[i] == null) {
                    user.playlists[i] = playlist;
                    user.numberOfPlaylists ++;
                    break;
                }
            }
        }
        else
            System.out.println ("The user has reached its maximum playlist capacity!");
    }
    public void changeSubscription () {
        if (this.subscriber) {
            final Scanner input = new Scanner(System.in);
            System.out.println("Are you sure? If you do this, you will only keep your top 3 playlists and playlists with more than 10 songs will be removed");
            System.out.print("Type 'true' to confirm and 'false to cancel:  ");
            final boolean confirmation = input.nextBoolean();
            if (confirmation) {
                this.subscriber = false;
                this.playlistLimit = 3;
                for (int i = 3; i < 10; i++) {
                    if (this.playlists[i] != null)
                        this.numberOfPlaylists--;
                    this.playlists[i] = null;
                }
                for (int i = 0; i < 3; i++) {
                    if (this.playlists[i] != null){
                        if (this.playlists[i].numberOfSongs > 10) {
                            this.removePlaylist(this.playlists[i]);
                            this.numberOfPlaylists--;
                        }
                    }
                }
                this.songsPerPlaylistLimit = 10;
            }
            input.close();
        }
        else {
            this.subscriber = true;
            this.playlistLimit = 10;
            this.songsPerPlaylistLimit = 100;
        }
    }

}