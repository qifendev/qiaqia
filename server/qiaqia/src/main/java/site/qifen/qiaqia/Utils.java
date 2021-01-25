package site.qifen.qiaqia;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import site.qifen.qiaqia.data.Message;
import site.qifen.qiaqia.data.Result;
import site.qifen.qiaqia.data.User;
import site.qifen.qiaqia.repository.UserRepository;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Random;

@Service
public class Utils {

    @Resource
    UserRepository userRepository;
    @Resource
    PasswordEncoder passwordEncoder;

    @Value("${qiaqia.key}")
    String key = "qifen";


    public static String message2Json(Message message) {
        return JSON.toJSONString(message);
    }

    public Message json2Message(String message) {
        return JSON.parseObject(message,Message.class);
    }

    public Result<String> getJwt(String password, String mail) {
        //HashMap<String,Object> map = new HashMap<String, Object>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MONTH, 1);
        return success200("登录成功", JWT.create()
                //.withHeader(map) //可设置算法
                //设置Payload
                .withClaim("password", password)
                .withClaim("mail", mail)
                //设置token过期时间(60s)
                .withExpiresAt(instance.getTime())
                //设置签名
                .sign(Algorithm.HMAC256(key)));
    }


    public Result<String> parseJwt(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
            DecodedJWT verify = verifier.verify(token);
            String password = verify.getClaim("password").asString();
            String mail = verify.getClaim("mail").asString();
            User user = userRepository.findUserByUserMail(mail);
            if (user != null) {
                if (!passwordEncoder.matches(password, user.getUserPassword())) {
                    return error400("密码错误");
                } else {
                    return success200("令牌有效", null);
                }
            } else {
                return error400("用户不存在");
            }
        } catch (TokenExpiredException e) {
            return error400("登录过期");
        } catch (Exception e) {
            e.printStackTrace();
            return error400("令牌有误");
        }
    }


    public String jwtIsOk(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
            DecodedJWT verify = verifier.verify(token);
            String password = verify.getClaim("password").asString();
            String mail = verify.getClaim("mail").asString();
            User user = userRepository.findUserByUserMail(mail);
            if (user != null && passwordEncoder.matches(password, user.getUserPassword())) {
                return mail;
            } else {
                return null;
            }
        } catch (TokenExpiredException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getMailToken(Integer code, String mail) {
        //HashMap<String,Object> map = new HashMap<String, Object>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 5);
        return JWT.create()
                //.withHeader(map) //可设置算法
                //设置Payload
                .withClaim("code", code)
                .withClaim("mail", mail)
                //设置token过期时间(60s)
                .withExpiresAt(instance.getTime())
                //设置签名
                .sign(Algorithm.HMAC256(key));
    }

    public Result<String> parseRegisterMailToken(String token, String mail, int code, String password) {
        if (!userRepository.existsByUserMail(mail)) {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
                DecodedJWT verify = verifier.verify(token);
                if (!verify.getClaims().get("mail").asString().equals(mail)) {
                    return error400("邮箱错误");
                } else if (verify.getClaims().get("code").asInt() != code) {
                    return error400("邮箱验证码错误");
                } else {
                    User user = new User();
                    user.setUserMail(mail);
                    String encode = passwordEncoder.encode(password);
                    user.setUserPassword(encode);
                    userRepository.save(user);
                    return success200("操作成功", null);
                }
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                return error400("验证码过期");
            } catch (Exception e) {
                e.printStackTrace();
                return error400("未知错误");
            }
        } else {
            return error400("用户已经注册");
        }
    }


    public Result<String> parseForgetMailToken(String token, String mail, int code, String password) {
        if (userRepository.existsByUserMail(mail)) {
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
                DecodedJWT verify = verifier.verify(token);
                if (!verify.getClaims().get("mail").asString().equals(mail)) {
                    return error400("邮箱错误");
                } else if (verify.getClaims().get("code").asInt() != code) {
                    return error400("邮箱验证码错误");
                } else {
                    User user = userRepository.findUserByUserMail(mail);
                    String encode = passwordEncoder.encode(password);
                    user.setUserPassword(encode);
                    userRepository.save(user);
                    return success200("操作成功", null);
                }
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                return error400("验证码过期");
            } catch (Exception e) {
                e.printStackTrace();
                return error400("未知错误");
            }
        } else {
            return error400("用户还没注册");
        }
    }


    public Result<String> error400(String message) {
        return new Result<>(400, message, null);
    }

    public Result<String> success200(String message, String data) {
        return new Result<>(200, message, data);
    }


    public int getCode() {
        return new Random().nextInt(899999) + 100000;
    }


}


