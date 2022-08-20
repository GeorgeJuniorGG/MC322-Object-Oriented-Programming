package lab8;

import java.util.Date;

public class Tweet {
    private static int MAX_LIKES = 100;
    private static int MAX_COMMENTS = 100;
    private static int MAX_RETWEETS = 100;

    private Date date;
    private String title;
    private Like[] likes;
    private Retweet[] retweets;
    private Comment[] comments;

    public Tweet(String title, Date date) {
        this.date = date;
        this.title = title;
        this.likes = new Like[MAX_LIKES];
        this.retweets = new Retweet[MAX_RETWEETS];
        this.comments = new Comment[MAX_COMMENTS];
    }

    String getTitle() {
        return title;
    }

    Like[] getLikes() {
        return likes;
    }    

    Retweet[] getRetweets() {
        return retweets;
    }

    Comment[] getComments() {
        return comments;
    }

    void addLike(User user, Date date) {
        Like like = new Like(user, date);
        for(int i = 0; i < MAX_LIKES; i++) {
            if(likes[i] == null) {
                likes[i] = like;
                break;
            }
        }
    }
    void addRetweet(User user, Date date) {
        Retweet retweet = new Retweet(user, date);
        for(int i = 0; i < MAX_RETWEETS; i++) {
            if(retweets[i] == null) {
                retweets[i] = retweet;
                break;
            }
        }
    }
    void addComment(String text, Date date) {
        Comment comment = new Comment(text, date);
        for(int i = 0; i < MAX_COMMENTS; i++) {
            if(comments[i] == null) {
                comments[i] = comment;
                break;
            }
        }
    }
}