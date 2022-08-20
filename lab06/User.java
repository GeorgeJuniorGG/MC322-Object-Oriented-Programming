package lab06;
import java.util.Scanner;

public class User {
    private final int SUBSCRIBER = 900;
    private final int NON_SUBSCRIBER = 90; 

    private String name;
    private String cpf;
    private String birthDate;
    private UserGender gender;
    private boolean subscriber;
    private Playlist[] playlists;
    private int maxCapacity;
    private int numberOfPlaylists;
    private int currentCapacity;

    public User(String name, String cpf, String birthDate, UserGender gender, boolean subscriber) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.subscriber = subscriber;
        this.playlists = new Playlist [100];
        this.numberOfPlaylists = 0;
        this.currentCapacity = 0;
        if (subscriber) {
            this.maxCapacity = SUBSCRIBER;
        }
        else {
            this.maxCapacity = NON_SUBSCRIBER;
        }
	}
	public void addPlaylist(Playlist playlist) {
        if (playlist.isOnlyPodcasts()) {
            for (int i = 0; i < 100; i++) {
                if (this.playlists[i] == null) {
                    this.playlists[i] = playlist;
                    this.numberOfPlaylists ++;
                    this.currentCapacity += playlist.getCapacity();
                    break;
                }
            }
        }
        else {
            if (currentCapacity + playlist.getCapacity() > maxCapacity) 
                System.out.println("You cannot add this playlist!");
            else {
                for (int i = 0; i < 100; i++) {
                    if (this.playlists[i] == null) {
                        this.playlists[i] = playlist;
                        this.numberOfPlaylists ++;
                        this.currentCapacity += playlist.getCapacity();
                        break;
                    }
                }
            }
        }
    }
    public void removePlaylist(Playlist playlist) {
        int removed = 0;
        for (int i = 0; i < 100; i++) {
            if (playlist.getName() == this.playlists[i].getName()) {
                this.playlists[i] = null;
                this.numberOfPlaylists --;
                removed = i;
                break;
            }
        }
        for (int i = removed + 1; i < 100; i++) { //reorganiza o vetor
            this.playlists[i - 1] = this.playlists[i];
        }
        this.playlists[99] = null;
        currentCapacity -= playlist.getCapacity();
    }
	public Playlist[] getPlaylists() {
		return this.playlists;
	}
	public void getDetails() {
        if (this.numberOfPlaylists > 0) {
            int count = 1;
            System.out.print("User " + this.name + " has playlists: " );
            for (int i = 0; i < 100; i++) {
                if (this.playlists[i] != null) {
                    if (count != this.numberOfPlaylists) {
                        System.out.print(this.playlists[i].getName() + ", ");
                        count++;
                    }
                    else
                        System.out.println(this.playlists[i].getName() + ".");
                }
            }
        }
        else
            System.out.println("This user does not have any playlists!");
    }

    public int getCapacity() {
        return this.currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void transferPlaylist(Playlist playlist, User user) {
        if (user.getCapacity() + playlist.getCapacity() <= user.getMaxCapacity()) {
            for (int i = 0; i < 100; i++) {
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
            Scanner input = new Scanner(System.in);
            System.out.println("Are you sure? If you do this, you may lose all your playlists");
            System.out.print("Type 'true' to confirm and 'false to cancel:  ");
            boolean confirmation = input.nextBoolean();
            if (confirmation) {
                this.subscriber = false;
                this.maxCapacity = NON_SUBSCRIBER;
                if(this.currentCapacity > NON_SUBSCRIBER) {
                    for(int i = 0; i < 100; i ++) {
                        playlists[i] = null;
                    }
                }
            }
            input.close();
        }
        else {
            this.subscriber = true;
            this.maxCapacity = SUBSCRIBER;
        }
    }

    public boolean isSubcribed() {
        return subscriber;
    }
}