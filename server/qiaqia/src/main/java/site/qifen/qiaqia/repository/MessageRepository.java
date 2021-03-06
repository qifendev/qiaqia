package site.qifen.qiaqia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import site.qifen.qiaqia.data.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {




    @Query(nativeQuery = true,value = "select * from message where message_state=0 and  message_to=?1")
    List<Message> findUserUnReadMessage(String messageMail);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update message set message_state=1 where message_to=?1")
    void readMessage(String messageMail);


    @Query(nativeQuery = true, value = "select * from message where message_from = ?1 or message_to = ?1 and message_data like ?2")
    List<Message> findMessage(String mail, String data);








}
