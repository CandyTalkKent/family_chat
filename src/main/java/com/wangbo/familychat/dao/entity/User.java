package com.wangbo.familychat.dao.entity;


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
     * 密码
     */
    private String password;


    /**
     * 电话
     */
    private String phone;

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
