package site.qifen.qiaqia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.qifen.qiaqia.Utils;
import site.qifen.qiaqia.data.Message;
import site.qifen.qiaqia.data.Result;
import site.qifen.qiaqia.repository.MessageRepository;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MessageController {


    @Resource
    MessageRepository messageRepository;

    @Resource
    Utils utils;

//    public Result<Message> getMessage(@RequestParam String jwt,@RequestParam String messageTo) {
//        messageRepository.findMessagesByMessageFromAndMessageTo();
//    }


    @GetMapping("findMessage")
        public Result<List<Message>> findMessage(@RequestParam String mail,@RequestParam String data) {
            return utils.success200("操作成功",messageRepository.findMessage(mail, "%"+data+"%"));
    }


}
