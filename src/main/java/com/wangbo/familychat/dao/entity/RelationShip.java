package com.wangbo.familychat.dao.entity;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;


@Data
public class RelationShip {
//
//      sb.append(", id=").append(id);
//        sb.append(", userId=").append(userId);
//        sb.append(", friendId=").append(friendId);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", updateTime=").append(updateTime);
//        sb.append(", relationState=").append(relationState);

    Long id;
    Long userId;
    Long friendId;
    Date createTime;
    Date updateTime;
    Integer relationState;


    public static RelationShip build(TRelationShip tRelationShip) {
        RelationShip relationShip = new RelationShip();
        BeanUtils.copyProperties(tRelationShip, RelationShip.class);
        return relationShip;
    }

}
