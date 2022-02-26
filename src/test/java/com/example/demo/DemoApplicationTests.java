package com.example.demo;

import cn.hutool.core.date.DateUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        final ListOperations list = stringRedisTemplate.opsForList();
        list.leftPush("s", Lists.newArrayList("1"));
    }

    public static void main(String[] args) {
        boolean weekend = DateUtil.isWeekend(new Date());
        System.out.println(DateUtil.today());
        System.out.println(weekend);
    }

}
