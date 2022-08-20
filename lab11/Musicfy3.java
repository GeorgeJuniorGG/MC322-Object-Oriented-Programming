package lab11;

public class Musicfy3 {
    public static void main(String[] args) {
        User user1 = new User("Eu", "123345654-52", "23/04/1989", UserGender.Masculino, true);
        User user2 = new User("Teste", "123432123-12", "12/12/2002", UserGender.Feminino, false);
        Song song1 = new Song("musica", "naosei", 7);
        Song song2 = new Song("outramusica", "naosei", 8);
        Album album = new Album("boa pergunta");
        album.addSong(song1);
        album.addSong(song2);
        Episode episode = new Episode("A volta dos que nao foram", 0, "Eu");
        Podcast podcast = new Podcast("podcast", "Eu");
        podcast.addEpisode(episode);
        Video video = new Video("video teste", "naosei", 40);
        Playlist playlist = new Playlist("Melhor playlist");
        playlist.addMedia(album);
        playlist.addMedia(song1);
        playlist.addMedia(podcast);
        playlist.addMedia(video);
        System.out.println("Playing " + playlist.play().getName());
        System.out.println("Playing " + playlist.play(true).getName());
        user1.addPlaylist(playlist);
        user2.addPlaylist(playlist);
        playlist.removeMedia(video);
        user1.addPlaylist(playlist);
        System.out.println("The average duration is " + playlist.getAverageDuration() + " seconds!");
        user1.transferPlaylist(playlist, user2);
        user1.cancelSubscription();
        user1.addPlaylist(playlist);
    }

}