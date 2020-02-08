package com.wangbo.familychat.common;


import lombok.Data;

@Data
public class ResultData<T> {

    /**
     * 数据载体
     */
    private T data;

    /**
     * 200 ok
     */
    private String resCode;


    /**
     * 附带消息
     */
    private String message;


    /**
     * 响应类型   1: 登陆成功  2: 消息转发
     */
    private String type;

    public static <T> ResultData<T> success(T data){
        ResultData<T> res = new ResultData<>();
        res.setData(data);
        res.setMessage("login success");
        res.setType("1");
        res.setResCode("200");
        return res;
    }

    public static <T> ResultData<T> fail(String  errormsg){
        ResultData<T> res = new ResultData<>();
        res.setMessage(errormsg);
        res.setType("0");
        res.setResCode("500");
        return res;
    }
}
