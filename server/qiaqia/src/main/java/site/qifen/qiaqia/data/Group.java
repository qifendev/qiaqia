package site.qifen.qiaqia.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`group`")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer groupId; //唯一id

    String groupHold; //群持有者id

    String groupName; //群名

    String groupNickName; //群名

    String groupSignature; //群签名

    String groupSlave; //群友id

    Integer groupTag; //标示

    Long groupFixTime;
    Long groupTime;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupHold() {
        return groupHold;
    }

    public void setGroupHold(String groupHold) {
        this.groupHold = groupHold;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNickName() {
        return groupNickName;
    }

    public void setGroupNickName(String groupNickName) {
        this.groupNickName = groupNickName;
    }

    public String getGroupSignature() {
        return groupSignature;
    }

    public void setGroupSignature(String groupSignature) {
        this.groupSignature = groupSignature;
    }

    public String getGroupSlave() {
        return groupSlave;
    }

    public void setGroupSlave(String groupSlave) {
        this.groupSlave = groupSlave;
    }

    public Integer getGroupTag() {
        return groupTag;
    }

    public void setGroupTag(Integer groupTag) {
        this.groupTag = groupTag;
    }

    public Long getGroupFixTime() {
        return groupFixTime;
    }

    public void setGroupFixTime(Long groupFixTime) {
        this.groupFixTime = groupFixTime;
    }

    public Long getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(Long groupTime) {
        this.groupTime = groupTime;
    }
}
