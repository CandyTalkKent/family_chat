package com.wangbo.familychat.controllers;

import com.wangbo.familychat.dao.TUserInfoMapper;
import com.wangbo.familychat.dao.entity.TUserInfo;
import com.wangbo.familychat.dao.entity.TUserInfoExample;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@MapperScan("com.wangbo.familychat.dao")
@RestController
public class GreetingController {


    @Autowired
    private TUserInfoMapper tUserInfoMapper;


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public TUserInfo greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        TUserInfoExample tUserInfoExample = new TUserInfoExample();
        tUserInfoExample.or().andUserIdEqualTo(1l);
        List<TUserInfo> tUserInfos = tUserInfoMapper.selectByExample(tUserInfoExample);
        return tUserInfos.get(0);
    }


    public class Greeting {

        private final long id;
        private final String content;

        public Greeting(long id, String content) {
            this.id = id;
            this.content = content;
        }

        public long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }

}
