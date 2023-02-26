package com.pj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {
    @Autowired(required = false)//表示忽略当前要注入的bean，如果有直接注入，没有跳过，不会报错
    private RedisTemplate redisTemplate;
    @Test
    public void testSet() {
        redisTemplate.boundValueOps("name").set("XiaoKe");
    }
    @Test
    public void testGet(){
        Object name = redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }

}
