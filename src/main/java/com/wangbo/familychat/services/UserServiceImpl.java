package com.wangbo.familychat.services;

import com.wangbo.familychat.dao.TRelationShipMapper;
import com.wangbo.familychat.dao.TUserInfoMapper;
import com.wangbo.familychat.dao.entity.*;
import com.wangbo.familychat.services.impl.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TUserInfoMapper tUserInfoMapper;


    @Autowired
    TRelationShipMapper tRelationShipMapper;

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

    @Override
    public List<User> getFriends(Long userId) {
        List<User> res = new ArrayList<>();
        List<Long> friendIds = new ArrayList<>();
        TRelationShipExample example = new TRelationShipExample();
        example.or().andUserIdEqualTo(userId);
        List<TRelationShip> tRelationShips = tRelationShipMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tRelationShips)) {
            return null;
        }


        for (TRelationShip ship : tRelationShips) {
            friendIds.add(ship.getFriendId());
        }

        List<TUserInfo> tUserInfos = tUserInfoMapper.batchSelectByUserId(friendIds);
        for (TUserInfo tUserInfo : tUserInfos) {
            res.add(User.build(tUserInfo));
        }

        return res;
    }

    @Override
    public boolean registerUser(User user) {
        TUserInfo tUserInfo = new TUserInfo();
        BeanUtils.copyProperties(user, tUserInfo);
        int i = tUserInfoMapper.insert(tUserInfo);
        return i > 0;
    }

    @Override
    public boolean addFriend(Long userId, Long friendId) {

        //检查是否已经是好友关系

        TRelationShipExample example = new TRelationShipExample();
        example.or().andUserIdEqualTo(userId);
        List<TRelationShip> tRelationShips = tRelationShipMapper.selectByExample(example);
        for (TRelationShip tRelationShip : tRelationShips) {
            if (tRelationShip.getFriendId().equals(friendId)) {

                //如果存在好友关系则不添加
                return false;
            }
        }


        TRelationShip tRelationShipA = makeRelationShip(userId, friendId);
        TRelationShip tRelationShipB = makeRelationShip(friendId, userId);

        int numA = tRelationShipMapper.insert(tRelationShipA);
        int numB = tRelationShipMapper.insert(tRelationShipB);


        return (numA > 0) && (numB > 0);
    }

    private TRelationShip makeRelationShip(Long userId, Long friendId) {
        Date now = new Date();
        //双向添加
        TRelationShip tRelationShip = new TRelationShip();
        tRelationShip.setUserId(userId);
        tRelationShip.setFriendId(friendId);
        tRelationShip.setCreateTime(now);
        tRelationShip.setUpdateTime(now);
        tRelationShip.setRelationState(1);
        return tRelationShip;
    }
}
