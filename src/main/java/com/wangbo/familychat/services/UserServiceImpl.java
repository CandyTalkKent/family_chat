package com.wangbo.familychat.services;

import com.wangbo.familychat.dao.TUserInfoMapper;
import com.wangbo.familychat.dao.entity.TUserInfo;
import com.wangbo.familychat.dao.entity.TUserInfoExample;
import com.wangbo.familychat.dao.entity.User;
import com.wangbo.familychat.services.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TUserInfoMapper tUserInfoMapper;


    @Override
    public User getUserFromDbWithPhone(String phone) {
        TUserInfoExample example = new TUserInfoExample();
        example.or().andPhoneEqualTo(phone);
        List<TUserInfo> tUserInfos = tUserInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tUserInfos)) {
            return null;
        }

        TUserInfo tUserInfo = tUserInfos.get(0);
        return User.build(tUserInfo);

    }
}
