package Twitter;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name="Follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String email;
    String following;

    public Follow() {
    }

    public Follow(String email, String following) {
        this.email = email;
        this.following = following;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}
