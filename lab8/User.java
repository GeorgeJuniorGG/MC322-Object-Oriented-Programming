package lab8;

import java.util.Date;
import java.util.Calendar;

public class User {
    private static int MAX_FOLLOW = 100;
    private static int MAX_FOLLOWERS = 100;
    private static int MAX_TWEETS = 100;


    private String name;
    private String email;
    private String password;
    private UserGender gender;
    private String country;
    private Date birthDate;
    private String nickname;
    private Follow[] following;
    private Follow[] followers;
    private Tweet[] tweets;

    public User(String name, String email, String password, UserGender gender, String country, Date birthDate, String nickname) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2002);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        Date eighteenYO = cal.getTime();
        if(birthDate.before(eighteenYO)) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.gender = gender;
            this.country = country;
            this.birthDate = birthDate;
            this.nickname = nickname;
            this.following = new Follow[MAX_FOLLOW];
            this.tweets = new Tweet[MAX_TWEETS];
            this.followers = new Follow[MAX_FOLLOWERS];
        }
        else{
            System.out.println("Voce precisa ter mais de 18 anos para ter um Twitter!");
        }
    }

    String getNickname() {
        return nickname;
    }

    void followUser(User user) {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.MAY, 15);
        Date date = cal.getTime();
        Follow follow = new Follow(user, date);
        for(int i = 0; i < MAX_FOLLOW; i++) {
            if(this.following[i] == null) {
                this.following[i] = follow;
                user.getFollowed(this, date);
                break;
            }
        }
    }

    void getFollowed(User user, Date date) {
        Follow follow = new Follow(user, date);
        for(int i = 0; i < MAX_FOLLOWERS; i++) {
            if(this.followers[i] == null) {
                this.followers[i] = follow;
                break;
            }
        }
    }

    void publishTweet(String title, Date date) {
        Tweet tweet = new Tweet(title, date);
        for(int i = 0; i < MAX_TWEETS; i++) {
            if(this.tweets[i] == null) {
                this.tweets[i] = tweet;
                break;
            }
        }
    }

    void publishTweet(String title, Date date, int duration, VideoExtension extension) {
        Video tweet = new Video(duration, extension, title, date);
        for(int i = 0; i < MAX_TWEETS; i++) {
            if(this.tweets[i] == null) {
                this.tweets[i] = tweet;
                break;
            }
        }
    }

    void publishTweet(String title, Date date, int resolution, ImageExtension extension, String description) {
        Image tweet = new Image(title, date, resolution, extension, description);
        for(int i = 0; i < MAX_TWEETS; i++) {
            if(this.tweets[i] == null) {
                this.tweets[i] = tweet;
                break;
            }
        }
    }

    void publishTweet(String title, Date date, String url, Date accessDate) {
        Website tweet = new Website(title, date, url, accessDate);
        for(int i = 0; i < MAX_TWEETS; i++) {
            if(this.tweets[i] == null) {
                this.tweets[i] = tweet;
                break;
            }
        }
    }

    void publishTweet(String title, Date date, String text, String language) {
        Text tweet = new Text(date, title, text, language);
        for(int i = 0; i < MAX_TWEETS; i++) {
            if(this.tweets[i] == null) {
                this.tweets[i] = tweet;
                break;
            }
        }
    }

    Tweet[] getTweets() {
        return tweets;
    }

    void likeTweet(Tweet tweet) {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.MAY, 15);
        Date date = cal.getTime();
        tweet.addLike(this, date);        
    }

    void comment(Tweet tweet, String comment) {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.MAY, 15);
        Date date = cal.getTime();
        tweet.addComment(comment, date);
    }

    void retweet(Tweet tweet) {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.MAY, 15);
        Date date = cal.getTime();
        tweet.addRetweet(this, date);
    }

    void printSummary() {
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
        System.out.println("Gender: " + this.gender);
        System.out.println("Country: " + this.country);
        System.out.println("Birthdate: " + this.birthDate);
        System.out.println("Nickname: " + this.nickname);
        System.out.print("This user follows: ");
        for(int i = 0; i < MAX_FOLLOW; i++) {
            if(following[i] != null) {
                System.out.print(following[i].getUser().getNickname() + " ");
            }
        }
        System.out.println("");
        System.out.print("This user is followed by: ");
        for(int i = 0; i < MAX_FOLLOWERS; i++) {
            if(followers[i] != null) {
                System.out.print(followers[i].getUser().getNickname() + " ");
            }
        }
        System.out.println("");
        if(this.tweets[0] != null) {
            System.out.println("This user tweeted the following: ");
        }
        for (int i = 0; i < MAX_TWEETS; i++) {
            if(this.tweets[i] != null) {
                System.out.println(this.tweets[i].getTitle());
                if(tweets[i].getLikes()[0] != null) {
                    System.out.print("This tweet was liked by: ");
                    for(int j = 0; j < tweets[i].getLikes().length; j++) {
                        if(tweets[i].getLikes()[j] != null) {
                            System.out.print(tweets[i].getLikes()[j].getUser().getNickname() + " ");
                        }
                    }
                    System.out.println("");
                }
                if(tweets[i].getRetweets()[0] != null) {
                    System.out.print("This tweet was retweeted by: ");
                    for(int j = 0; j < tweets[i].getRetweets().length; j++) {
                        if(tweets[i].getRetweets()[j] != null) {
                            System.out.print(tweets[i].getRetweets()[j].getUser().getNickname() + " ");
                        }
                    }
                    System.out.println("");
                }
                if(tweets[i].getComments()[0] != null) {
                    System.out.println("This tweet got the following comments: ");
                    for(int j = 0; j < tweets[i].getComments().length; j++) {
                        if(tweets[i].getComments()[j] != null) {
                            System.out.println(tweets[i].getComments()[j].getText());
                        }
                    }
                }
            }
        }
        System.out.println("================================");
    }

}