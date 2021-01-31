package site.qifen.qiaqia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.qifen.qiaqia.Utils;
import site.qifen.qiaqia.data.Friend;
import site.qifen.qiaqia.data.Group;
import site.qifen.qiaqia.data.Result;
import site.qifen.qiaqia.repository.FriendRepository;
import site.qifen.qiaqia.repository.GroupRepository;
import site.qifen.qiaqia.repository.UserRepository;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GroupController {


    @Resource
    GroupRepository groupRepository;

    @Resource
    UserRepository userRepository;

    @Resource
    Utils utils;


    @GetMapping("/createGroup")
    Result<Group> createGroup(@RequestParam String mail,@RequestParam String name) {
        String randomInt = utils.randomInt();
        if (userRepository.existsByUserMail(mail)) {
            if (!groupRepository.existsGroupByGroupNickName(name)) {
                Group group = new Group();
                group.setGroupHold(mail);
                group.setGroupNickName(name);
                group.setGroupSlave(mail);
                group.setGroupFixTime(System.currentTimeMillis());
                group.setGroupTime(System.currentTimeMillis());
                group.setGroupName(randomInt);
                groupRepository.save(group);
            } else {
                return utils.error400("昵称存在", null);
            }
        } else {
            return utils.error400("用户不存在", null);
        }
        return utils.success200("创建成功", groupRepository.findGroupByGroupName(randomInt));
    }


    @GetMapping("/addGroup")
    Result<Group> addGroup(@RequestParam String mail,@RequestParam String groupName) {
        if (userRepository.existsByUserMail(mail)) {
            if (!groupRepository.existsGroupByGroupNameAndGroupSlave(groupName,mail)) {
                Group group = new Group();
                group.setGroupName(groupName);
                group.setGroupSlave(mail);
                group.setGroupFixTime(System.currentTimeMillis());
                group.setGroupTime(System.currentTimeMillis());
                groupRepository.save(group);
            } else {
                return utils.error400("群不存在或者已经添加", null);
            }
        } else {
            return utils.error400("用户不存在", null);
        }
        return utils.success200("操作成功",groupRepository.findGroupByGroupSlave(mail));
    }

    @GetMapping("/findGroup")
    Result<List<Group>> findGroup(@RequestParam String name) {
        return utils.success200("操作成功",groupRepository.findGroupsByGroupNickNameLike("%"+name+"%"));
    }

    @GetMapping("/myGroup")
    Result<List<Group>> myGroup(@RequestParam String mail) {
        return utils.success200("操作成功",groupRepository.findGroupsByGroupHoldOrGroupSlave(mail,mail));
    }

}








