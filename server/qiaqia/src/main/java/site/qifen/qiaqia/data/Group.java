package site.qifen.qiaqia.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`group`")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer groupId; //唯一id

    Integer groupHold; //群持有者id

    String groupName; //群名

    String groupSignature; //群签名

    Integer groupSlave; //群友id

    Integer groupTag; //标示

    public Group() {
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupHold() {
        return groupHold;
    }

    public void setGroupHold(Integer groupHold) {
        this.groupHold = groupHold;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupSignature() {
        return groupSignature;
    }

    public void setGroupSignature(String groupSignature) {
        this.groupSignature = groupSignature;
    }

    public Integer getGroupSlave() {
        return groupSlave;
    }

    public void setGroupSlave(Integer groupSlave) {
        this.groupSlave = groupSlave;
    }

    public Integer getGroupTag() {
        return groupTag;
    }

    public void setGroupTag(Integer groupTag) {
        this.groupTag = groupTag;
    }
}
