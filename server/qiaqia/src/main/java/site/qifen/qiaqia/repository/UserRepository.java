package site.qifen.qiaqia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.qifen.qiaqia.data.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUserMail(String userMail);

    @Query(nativeQuery = true, value = "select * from user where user_mail like ?1 or user_nick_name like ?1")
    List<User> findUser(String text);

    User findUserByUserMail(String userMail);

}
