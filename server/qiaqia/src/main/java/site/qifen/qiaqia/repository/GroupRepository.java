package site.qifen.qiaqia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.qifen.qiaqia.data.Friend;
import site.qifen.qiaqia.data.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {


//    @Query(nativeQuery = true, value = "select * from group where friend_from = ?1 or friend_to =?2 and friend_to = ?1 or friend_from = ?2")
//    List<Friend> existsFriends(String friendFrom, String friendTo);
//
//    @Query(nativeQuery = true, value = "select * from group where friend_from = ?1 or friend_to =?2 and friend_to = ?1 or friend_from = ?2")
//    Friend existsFriend(String friendFrom, String friendTo);
//

    boolean existsGroupByGroupNickName(String groupNickName);


    Group findGroupByGroupName(String groupName);


    Group findGroupByGroupSlave(String groupSlave);


    List<Group> findGroupsByGroupNickNameLike(String groupNickName);


    boolean existsGroupByGroupNameAndGroupSlave(String groupName,String groupSlave);

    List<Group> findGroupsByGroupHold(String groupHold);


    List<Group> findGroupsByGroupName(String groupName);


    List<Group> findGroupsByGroupSlave(String groupSlave);

    List<Group> findGroupsByGroupHoldOrGroupSlave(String groupHold,String groupSlave);




}













