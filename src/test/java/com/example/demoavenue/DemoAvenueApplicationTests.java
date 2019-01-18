package com.example.demoavenue;

import com.alibaba.fastjson.JSON;
import com.example.demoavenue.config.WebMvcConfig;
import com.example.demoavenue.utils.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoAvenueApplicationTests {
	
	@Test
	public void contextLoads() {
		// 遍历beanDefinitionNames，根据名字找对象，再跟Type匹配，做缓存
		final Object person = SpringUtil.getBean("person", WebMvcConfig.TestPerson.class);
		final Object person1 = SpringUtil.getBean("son", WebMvcConfig.TestSon.class);
//		final Object person2 = SpringUtil.getBean(WebMvcConfig.TestPerson.class);
		final Object person3 = SpringUtil.getBean(WebMvcConfig.TestSon.class);
		final Object person4 = SpringUtil.getBean("son", WebMvcConfig.TestPerson.class);
		final Map<String, WebMvcConfig.TestPerson> beansOfType = SpringUtil.getBeansOfType(WebMvcConfig.TestPerson.class);

		System.out.println("person:"+person.getClass().getName());
		System.out.println("person1:"+person1.getClass().getName());
//		System.out.println("person2:"+person2.getClass().getName());
		System.out.println("person3:"+person3.getClass().getName());
		System.out.println("person4:"+person4.getClass().getName());

		System.out.println(JSON.toJSONString(beansOfType));
		
	}

}
