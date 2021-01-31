package site.qifen.qiaqia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.qifen.qiaqia.data.Friend;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Integer> {


    @Query(nativeQuery = true, value = "select * from friend where friend_from = ?1 or friend_to =?2 and friend_to = ?1 or friend_from = ?2")
    List<Friend> existsFriends(String friendFrom, String friendTo);

    @Query(nativeQuery = true, value = "select * from friend where friend_from = ?1 and friend_to = ?2")
    Friend existsFriend(String friendFrom, String friendTo);

}
