package site.qifen.qiaqia.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer friendId; //唯一id
    Integer friendFrom; //用户1
    Integer friendTo; //用户2
    String friendFromName; //用户1对用户2昵称
    String friendToName; //用户2对用户1昵称

    public Friend() {
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getFriendFrom() {
        return friendFrom;
    }

    public void setFriendFrom(Integer friendFrom) {
        this.friendFrom = friendFrom;
    }

    public Integer getFriendTo() {
        return friendTo;
    }

    public void setFriendTo(Integer friendTo) {
        this.friendTo = friendTo;
    }

    public String getFriendFromName() {
        return friendFromName;
    }

    public void setFriendFromName(String friendFromName) {
        this.friendFromName = friendFromName;
    }

    public String getFriendToName() {
        return friendToName;
    }

    public void setFriendToName(String friendToName) {
        this.friendToName = friendToName;
    }
}
