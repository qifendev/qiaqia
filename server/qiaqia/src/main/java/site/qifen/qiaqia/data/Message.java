package site.qifen.qiaqia.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer messageId; //消息id
    String messageFrom;  //发送者
    String messageTo;  //接收者
    Long messageTime;  //日期
    Integer messageType; // 1确认身份  2私聊   3加好友
    String messageData; //数据内容
    Integer messageState; // 消息 》 -1发送失败  0发送成功未读   1发送成功已读 ---------  好友  》 0好友申请方from   1同意好友申请to

    public Message() {

    }



    public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public Long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Long messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }


    //发送状态
    @Transient
    public final static int MESSAGE_FAIL = -1; //发送消息失败
    @Transient
    public final static int MESSAGE_NOT_SEND = 0; //发送消息成功但是对方未在线
    @Transient
    public final static int MESSAGE_SEND = 1; //发送消息成功

    public final static int MESSAGE_READ = 2; //发送消息成功并且已读

    //发送类型
    @Transient
    public final static int MESSAGE_TOKEN = 1; //确认身份
    @Transient
    public final static int MESSAGE_FRIEND = 2; //私聊
    @Transient
    public final static int MESSAGE_GROUP = 3; //群聊
//    @Transient
//    public final static int MESSAGE_ADD_FRIEND = 4; //加好友
//    @Transient
//    public final static int MESSAGE_ADD_GROUP = 5; //加群


}
