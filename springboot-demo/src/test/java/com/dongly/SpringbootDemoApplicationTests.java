package com.dongly;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.dongly.test.entity.RedisModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

	@Autowired
	private  ListOperations<String, RedisModel> listOperations;

	@Autowired
	private  ListOperations<String, Object> operations;

	@Test
	public void contextLoads() {
		RedisModel model = new RedisModel("one", "小明", new Date(),22);

		System.out.println(operations.rightPush(model.getRedisKey(), model));
		System.out.println((RedisModel)operations.range(model.getRedisKey(), 0L, -1L).get(0));
	}

}
