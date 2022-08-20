package lab8;

import java.util.Calendar;
import java.util.Date;

public class Twitter {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1986);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 2);
        Date date1 = cal.getTime();
        User user1 = new User("Joao Costa", "joaoc@gmail.com", "jo@o12345", UserGender.MASCULINO, "Brasil", date1, "joaoc");
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 1975);
        cal2.set(Calendar.MONTH, Calendar.APRIL);
        cal2.set(Calendar.DAY_OF_MONTH, 6);
        Date date2 = cal2.getTime();
        User user2 = new User("Maria Silva", "msilva@outlook.com", "m@ri@", UserGender.FEMININO, "Argentina", date2, "msilva");      
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR, 2001);
        cal3.set(Calendar.MONTH, Calendar.DECEMBER);
        cal3.set(Calendar.DAY_OF_MONTH, 21);
        Date date3 = cal3.getTime();
        User user3 = new User("Carlos Vargas", "carlos.vargas@gmail.com", "carlos123", UserGender.MASCULINO, "Brasil", date3, "varguinhas");
        user1.followUser(user2);
        user1.followUser(user3);
        user2.followUser(user1);
        user2.followUser(user3);
        user3.followUser(user1);
        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.YEAR, 2020);
        cal4.set(Calendar.MONTH, Calendar.MAY);
        cal4.set(Calendar.DAY_OF_MONTH, 15);
        Date date4 = cal4.getTime();
        user1.publishTweet("Primeiro dia na praia", date4, 20, VideoExtension.avi);
        user3.likeTweet(user1.getTweets()[0]);
        user3.comment(user1.getTweets()[0], "Parabens pelo passeio");
        Calendar cal5 = Calendar.getInstance();
        cal5.set(Calendar.YEAR, 2020);
        cal5.set(Calendar.MONTH, Calendar.MAY);
        cal5.set(Calendar.DAY_OF_MONTH, 16);
        Date date5 = cal5.getTime();
        user3.publishTweet("Cachorro dormindo", date5, 1080, ImageExtension.gif, "cachorro dormindo na varanda");
        user1.likeTweet(user3.getTweets()[0]);
        user1.retweet(user3.getTweets()[0]);
        user2.likeTweet(user3.getTweets()[0]);
        user2.comment(user3.getTweets()[0], "Como cresceu seu cachorro");
        user1.printSummary();
        user2.printSummary();
        user3.printSummary();
    }
}