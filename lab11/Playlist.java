package lab11;

import java.util.Random;
import java.util.Scanner;

public class Playlist {
    private static int MAX = 100;
    private static int NON_SUBSCRIBER = 90;

    private int next;
    private int numberOfMedia;
    private String name;
    private int currentCapacity;
    private Media[] content;
    private boolean onlyPodcasts;
    private boolean subOnly;

    public Playlist(String name) {
        this.name = name;
        this.numberOfMedia = 0;
        this.next = -1;
        this.currentCapacity = 0;;
        this.content = new Media[MAX];
        this.onlyPodcasts = true;
        this.subOnly = false;
    }
    
    public Playlist() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("What's the playlist name?");
            this.name = input.next();
            this.numberOfMedia = 0;
            this.next = -1;
            this.currentCapacity = 0;;
            this.content = new Media[MAX];
            this.onlyPodcasts = true;
            this.subOnly = false;            
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        finally {
            input.close();
        }

    }

    public void addMedia(Media media) {
        try {
            if(this.numberOfMedia == MAX) {
                throw new MaxSongsException("Media limit exceeded!");
            }
            for (int i = 0; i < MAX; i++) {
                if(content[i] == null) {
                    content[i] = media;
                    this.currentCapacity += media.getMemory();
                    this.numberOfMedia++;
                    if(!media.isPodcast()) {
                        this.onlyPodcasts = false;
                    }
                    if(currentCapacity > NON_SUBSCRIBER && !onlyPodcasts) {
                        this.subOnly = true;
                    }
                    break;
                }
            }
        }
        catch(MaxSongsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeMedia (Media media) {
        for (int i = 0; i < MAX; i++) {
            if(content[i].equals(media)) {
                content[i] = null;
                this.numberOfMedia--;
                this.currentCapacity -= media.getMemory();
                break;
            }
        }
    }

    public Media getLongest() {
        int max = content[0].getDuration();
        int index = 0;
        for(int i = 1; i < MAX; i++) {
            if (content[i] != null) {
                if(content[i].getDuration() > max) {
                    index = i;
                    max = content[i].getDuration();
                }
            }
        }
        return content[index];
    }

    public Media getShortest() {
        int min = content[0].getDuration();
        int index = 0;
        for(int i = 1; i < MAX; i++) {
            if (content[i] != null) {
                if(content[i].getDuration() < min) {
                    index = i;
                    min = content[i].getDuration();
                }
            }
        }
        return content[index];

    }

    public int getAverageDuration() {
        int total = 0;
        int number = 0;
        for (int i = 0; i < MAX; i++) {
            if (content[i] != null) {
                number++;
                total += content[i].getDuration();
            }
        }
        return total/number;
    }

    public int getTotalDuration() {
        int total = 0;
        for (int i = 0; i < MAX; i++) {
            if (content[i] != null) {
                total += content[i].getDuration();
            }
        }
        return total;
    }

    public String getName() {
        return name;
    }

    public boolean isSubOnly() {
        return subOnly;
    }

    public int getCapacity() {
        return currentCapacity;
    }

    public boolean isOnlyPodcasts() {
        return onlyPodcasts;
    }

    public Media play() {
        this.next++;
        try {
            if(content[next] == null) {
                throw new EmptyMediaException();
            }
            return content[next].play();
        }
        catch (EmptyMediaException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Media play(boolean shuffle) {
        if (shuffle) {
            Random random  = new Random();
            int aux = (int) (random.nextDouble() * (double) this.numberOfMedia);
            while (aux == (this.next - 1) % (this.numberOfMedia))
                aux = (int) (random.nextDouble() * (double) this.numberOfMedia);
            return content[aux].play();
        }
        return null;
    }
}