package site.qifen.qiaqia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.qifen.qiaqia.data.Result;
import site.qifen.qiaqia.data.User;
import site.qifen.qiaqia.repository.MessageRepository;
import site.qifen.qiaqia.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
public class QiaqiaApplication {


    public static void main(String[] args) {
        SpringApplication.run(QiaqiaApplication.class, args);


    }


    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @RestController
    static
    class T {
        @Resource
        Utils utils;
        @Resource
        UserRepository userRepository;

        @GetMapping("/test/test")
        public Result<String> test() {
            return utils.error400("test test test!!!");
        }


        @GetMapping("/allUser")
        public List<User> allUser() {
            return userRepository.findAll();
        }

    }


}
