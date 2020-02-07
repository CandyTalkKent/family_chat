package com.wangbo.familychat.pojo;


import lombok.Data;

@Data
public class User {

    /**
     * userid
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户家乡
     */
    private String homeTown;
}
