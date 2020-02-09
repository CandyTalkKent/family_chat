package com.wangbo.familychat.services.impl;

import com.wangbo.familychat.dao.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * 通过phone 来获取user
     * @param phone
     * @return
     */
    User getUserFromDbWithPhone(String phone);


    /**
     * 获取联系人列表
     * @param
     * @return
     */
    List<User> getFriends(Long userId);

    boolean registerUser(User user);

    boolean addFriend(Long userId, Long friendId);
}
