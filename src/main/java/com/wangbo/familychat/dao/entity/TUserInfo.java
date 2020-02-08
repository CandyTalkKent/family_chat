package com.wangbo.familychat.dao.entity;

import java.io.Serializable;

public class TUserInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.user_nick_name
     *
     * @mbggenerated
     */
    private String userNickName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_info.hometown
     *
     * @mbggenerated
     */
    private String hometown;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.user_id
     *
     * @return the value of t_user_info.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.user_id
     *
     * @param userId the value for t_user_info.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.user_name
     *
     * @return the value of t_user_info.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.user_name
     *
     * @param userName the value for t_user_info.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.password
     *
     * @return the value of t_user_info.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.password
     *
     * @param password the value for t_user_info.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.phone
     *
     * @return the value of t_user_info.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.phone
     *
     * @param phone the value for t_user_info.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.user_nick_name
     *
     * @return the value of t_user_info.user_nick_name
     *
     * @mbggenerated
     */
    public String getUserNickName() {
        return userNickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.user_nick_name
     *
     * @param userNickName the value for t_user_info.user_nick_name
     *
     * @mbggenerated
     */
    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName == null ? null : userNickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.address
     *
     * @return the value of t_user_info.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.address
     *
     * @param address the value for t_user_info.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.email
     *
     * @return the value of t_user_info.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.email
     *
     * @param email the value for t_user_info.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_info.hometown
     *
     * @return the value of t_user_info.hometown
     *
     * @mbggenerated
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_info.hometown
     *
     * @param hometown the value for t_user_info.hometown
     *
     * @mbggenerated
     */
    public void setHometown(String hometown) {
        this.hometown = hometown == null ? null : hometown.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_info
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", userNickName=").append(userNickName);
        sb.append(", address=").append(address);
        sb.append(", email=").append(email);
        sb.append(", hometown=").append(hometown);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}