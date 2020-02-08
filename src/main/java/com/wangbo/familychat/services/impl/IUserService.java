package com.wangbo.familychat.services.impl;

import com.wangbo.familychat.dao.entity.User;

public interface IUserService {

    /**
     * 通过phone 来获取user
     * @param phone
     * @return
     */
    User getUserFromDbWithPhone(String phone);
}
