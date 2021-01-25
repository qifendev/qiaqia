package site.qifen.qiaqia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.qifen.qiaqia.data.Result;
import site.qifen.qiaqia.repository.MessageRepository;

import javax.annotation.Resource;

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

        @GetMapping("/test/test")
        public Result<String> test() {
            return utils.error400("test test test!!!");
        }
    }


}
