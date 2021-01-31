package site.qifen.qiaqia.data;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;//用户标识id
    private String userPassword;//密码
    private Integer userSex;//用户性别
    private String userAvatar;//用户头像
    private String userNickName;//用户头像
    private String userSignature;//用户签名
    private String userMail;//用户邮箱
    private String userPhone;//用户电话
    private Long userCanLogin;//禁止用户登录时间
    private Long userFixTime;//修改资料时间
    private String userNotCanLoginInfo;//用户禁止登录的信息

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public long getUserCanLogin() {
        return userCanLogin;
    }

    public void setUserCanLogin(long userCanLogin) {
        this.userCanLogin = userCanLogin;
    }

    public String getUserNotCanLoginInfo() {
        return userNotCanLoginInfo;
    }

    public void setUserNotCanLoginInfo(String userNotCanLoginInfo) {
        this.userNotCanLoginInfo = userNotCanLoginInfo;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Long getUserFixTime() {
        return userFixTime;
    }

    public void setUserFixTime(Long userFixTime) {
        this.userFixTime = userFixTime;
    }
}
