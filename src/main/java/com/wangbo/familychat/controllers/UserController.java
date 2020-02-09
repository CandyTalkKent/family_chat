package com.wangbo.familychat.controllers;

import com.wangbo.familychat.common.ResponseType;
import com.wangbo.familychat.common.ResultData;
import com.wangbo.familychat.dao.entity.User;
import com.wangbo.familychat.services.impl.IUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@MapperScan("com.wangbo.familychat.dao")
@RestController
public class UserController {


    @Autowired
    private IUserService userService;


    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/check.do")
    public String checkDo() {
        return "ok";
    }

    @PostMapping("/register")
    public ResultData register(User user) {
        boolean flag = userService.registerUser(user);
        if (!flag){
            return ResultData.fail("注册失败,手机已被绑定");
        }
        return ResultData.success(user,ResponseType.REGISTER_SUCCESS);
    }

    @GetMapping("/friend/add")
    public ResultData addFriend(@RequestParam("userId") Long userId, @RequestParam("friendId") Long friendId){

        boolean flag =  userService.addFriend(userId,friendId);
        if (!flag){
            return ResultData.fail("添加好友失败,已经存在好友关系");
        }
        return ResultData.success(null,ResponseType.REGISTER_SUCCESS);
    }


    @GetMapping("/contact/list")
    public ResultData contactList(@RequestParam Long userId) {

        List<User> friends = userService.getFriends(userId);
        if (CollectionUtils.isEmpty(friends)) {
            return ResultData.success("无联系人,空空如也", ResponseType.FRIENDS_NOT_FOUND);
        }

        return ResultData.success(friends, ResponseType.FRIENDS_FOUND);
    }


}
