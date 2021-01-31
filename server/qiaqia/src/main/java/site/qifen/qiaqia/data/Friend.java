package site.qifen.qiaqia.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer friendId; //唯一id
    String friendFrom; //用户1
    String friendTo; //用户2
    String friendFromName; //用户1对用户2昵称
    String friendToName; //用户2对用户1昵称
    Integer friendState; //状态
    Long friendTime; //时间
    Long friendFixTime; //状态时间


    //发送状态
    @Transient
    public final static int FRIEND_REQUEST = 0; //申请中
    @Transient
    public final static int FRIEND = 1; //发送消息成功但是对方未在线


    public Long getFriendFixTime() {
        return friendFixTime;
    }

    public void setFriendFixTime(Long friendFixTime) {
        this.friendFixTime = friendFixTime;
    }

    public Integer getFriendState() {
        return friendState;
    }

    public void setFriendState(Integer friendState) {
        this.friendState = friendState;
    }

    public Long getFriendTime() {
        return friendTime;
    }

    public void setFriendTime(Long friendTime) {
        this.friendTime = friendTime;
    }

    public Friend() {

    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getFriendFrom() {
        return friendFrom;
    }

    public void setFriendFrom(String friendFrom) {
        this.friendFrom = friendFrom;
    }

    public String getFriendTo() {
        return friendTo;
    }

    public void setFriendTo(String friendTo) {
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
