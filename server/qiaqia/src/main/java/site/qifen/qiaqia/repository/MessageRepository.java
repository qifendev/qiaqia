package site.qifen.qiaqia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.qifen.qiaqia.data.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {


    Page<List<Message>> findMessagesByMessageFromAndMessageTo(String messageFrom, String messageTo, Pageable pageable);

    @Query(nativeQuery = true,value = "select * from message where message_state = 0 and message_from = ?1")
    List<Message> findUserUnReadMessage(String messageMail);

    @Query(nativeQuery = true,value = "update message set message_state = 1 and message_from = ?1")
    List<Message> readMessage(String messageMail);

}
