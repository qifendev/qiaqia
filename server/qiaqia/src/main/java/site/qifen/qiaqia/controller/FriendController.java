package site.qifen.qiaqia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.qifen.qiaqia.Utils;
import site.qifen.qiaqia.data.Friend;
import site.qifen.qiaqia.data.Result;
import site.qifen.qiaqia.repository.FriendRepository;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FriendController {


    @Resource
    FriendRepository friendRepository;

    @Resource
    Utils utils;

    @GetMapping("/myFriends")
    Result<List<Friend>> getMyFriends(@RequestParam String mail) {
        return utils.success200("操作成功", friendRepository.existsFriends(mail, mail));
    }

    @GetMapping("addFriend")
    Result<String> addFriend(@RequestParam String from, @RequestParam String to) {
        if (friendRepository.existsFriend(from, to) == null) {
            Friend friend = new Friend();
            friend.setFriendFrom(from);
            friend.setFriendTo(to);
            friend.setFriendTime(System.currentTimeMillis());
            friend.setFriendFixTime(System.currentTimeMillis());
            friendRepository.save(friend);
        } else {
            return utils.error400("你已经添加");
        }
        return utils.success200("添加成功", "");
    }
}








