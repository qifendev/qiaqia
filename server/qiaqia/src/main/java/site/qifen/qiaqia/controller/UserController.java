package site.qifen.qiaqia.controller;


import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.qifen.qiaqia.Utils;
import site.qifen.qiaqia.data.Result;
import site.qifen.qiaqia.data.User;
import site.qifen.qiaqia.repository.UserRepository;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    JavaMailSender javaMailSender;

    @Resource
    Utils utils;

    @Resource
    UserRepository userRepository;

    @Resource
    PasswordEncoder passwordEncoder;


    @PostMapping("/reg")
    public Result<String> reg(@RequestParam String mail, @RequestParam String token, @RequestParam String password, @RequestParam int code) {
        return utils.parseRegisterMailToken(token, mail, code, password);
    }


    @PostMapping("/login")
    public Result<String> login(@RequestParam String mail, @RequestParam String password) {
        User user = userRepository.findUserByUserMail(mail);
        if (user != null && passwordEncoder.matches(password, user.getUserPassword())) {
            return utils.getJwt(password, mail);
        } else {
            return utils.error400("账号或者密码错误");
        }
    }

    @GetMapping("/token")
    public Result<String> token(@RequestParam String token) {
        return utils.parseJwt(token);
    }

    @GetMapping("/mail")
    public Result<String> sendVerifyCodeMail(@RequestParam String mail) {//发送验证码到邮箱
        SimpleMailMessage msg = new SimpleMailMessage();
        int code = utils.getCode();//获取随机验证码100000-999999
        msg.setFrom("858810078@qq.com");//发送方地址
        msg.setTo(mail);//接收方地址
        msg.setSubject(code + "-验证码" + "-恰恰");//标题
        msg.setText("欢迎使用恰恰,你的验证码是" + code + ",验证码有效期5分钟。");//内容
        try {
            javaMailSender.send(msg);//发送
        } catch (MailException e) {
            System.err.println(e.getMessage());
            return utils.error400("发送验证码到邮箱失败");
        }
        return utils.success200("发送验证码到邮箱成功", utils.getMailToken(code, mail));
    }


    @PostMapping("/forget")
    public Result<String> forget(@RequestParam String mail, @RequestParam int code, @RequestParam String token, @RequestParam String password) {
        return utils.parseForgetMailToken(token, mail, code, password);
    }

}










