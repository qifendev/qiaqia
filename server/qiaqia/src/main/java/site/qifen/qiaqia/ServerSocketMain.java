package site.qifen.qiaqia;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.qifen.qiaqia.data.Message;
import site.qifen.qiaqia.repository.MessageRepository;

import javax.annotation.Resource;
import java.io.*;
import java.net.*;
import java.sql.Time;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Controller
public class ServerSocketMain implements ApplicationRunner {

    ConcurrentHashMap<String, RegisterClient> concurrentHashMap = new ConcurrentHashMap<>();

    ExecutorService executorService = Executors.newCachedThreadPool();

    @Resource
    Utils utils;

    @Resource
    MessageRepository messageRepository;

    @Override
    public void run(ApplicationArguments args) {



        try {
            ServerSocket serverSocket = new ServerSocket(9999, 1000);
            while (true) {
                Socket socket = serverSocket.accept();
                String client = socket.getRemoteSocketAddress().toString();
                System.out.println(client + " connected");
                executorService.execute(new RegisterClient(socket));
            }
        } catch (SocketException e) {
            System.out.println("socket exception main");
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("eof exception main");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", concurrentHashMap);
        return "list";
    }


    @GetMapping("/close")
    public String close(@RequestParam String key) {
        concurrentHashMap.get(key).close();
        return "redirect:/list";
    }


    public class RegisterClient implements Runnable {

        volatile short connect = 0; // -1为连接失败 0为连接未验证  1为已经连接和验证

        Socket socket;

        volatile String name;


        RegisterClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while (connect >= 0) { //验证身份并且没有异常才连接
                    String s = new DataInputStream(socket.getInputStream()).readUTF();
                    Message message = utils.json2Message(s);
                    Integer messageType = message.getMessageType();
                    String messageTo = message.getMessageTo();
                    if (messageType == 1) { //验证身份
                        name = utils.jwtIsOk(message.getMessageFrom());
                        System.out.println("socket connect " + name);
                        concurrentHashMap.put(name, this);
                        connect = 1;

//                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//                        for (Message message1: messageRepository.findUserUnReadMessage(name)){
//                            dataOutputStream.writeUTF(JSON.toJSONString(message1));
//                        }
//                        messageRepository.readMessage(name);


                    }




                    if (messageType == 2) {
                        if (concurrentHashMap.containsKey(messageTo)) {

//                            String messageFrom = message.getMessageFrom();
//                            message.setMessageFrom(message.getMessageTo());
//                            message.setMessageTo(messageFrom);

                            DataOutputStream dataOutputStream = new DataOutputStream(concurrentHashMap.get(messageTo).socket.getOutputStream());

                            dataOutputStream.writeUTF(JSON.toJSONString(message));
                            System.out.println("write " + JSON.toJSONString(message));
                            dataOutputStream.flush();
                            message.setMessageState(1);
                        } else {
                            message.setMessageState(0);
                        }
                        messageRepository.save(message);
                    }
                }
            } catch (SocketException e) {
                System.out.println("socket exception" + name);
                close();
            } catch (EOFException e) {
                System.out.println("eof exception" + name);
                close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" exception" + name);
                close();
            }
        }

        public void close() {
            try {
                connect = -1;
                socket.close();
                concurrentHashMap.remove(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}




