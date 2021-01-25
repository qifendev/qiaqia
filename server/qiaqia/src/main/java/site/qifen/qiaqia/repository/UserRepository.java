package site.qifen.qiaqia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.qifen.qiaqia.data.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUserMail(String userMail);

    User findUserByUserMail(String userMail);

}
