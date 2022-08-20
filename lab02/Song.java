package Lab02;

public class Song {
    public String name;
    public String genre;
    public String artist;
    public int length; //dado em segundos
    public Song (String name, String genre, String artist, int length) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.length = length;
    }

    public void changeName(final String newName) {
        this.name = newName;
    }

    public void changeGenre(final String newGenre) {
        this.genre = newGenre;
    }

    public void changeArtist(final String newArtist) {
        this.artist = newArtist;
    }

    public void changeLength(final int newLength) {
        this.length = newLength;
    }
}