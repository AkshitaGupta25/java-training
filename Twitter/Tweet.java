package Twitter;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "TweetTable")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String tweet;
    private LocalDateTime localDateTime;

    public Tweet() {
    }

    public Tweet(String name, String email, String tweet, LocalDateTime localDateTime) {
        this.name = name;
        this.email = email;
        this.tweet = tweet;
        this.localDateTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tweet='" + tweet + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}