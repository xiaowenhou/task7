package com.putaoteng.task7.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.putaoteng.task7.utils.other.SendMailTemplate;

public class SendMailTemplateTest {

	@Before
	public void setUp() throws Exception {
	}

	@SuppressWarnings("resource")
	@Test
	public void test() {
		String address = "916363275@qq.com";
		String user = "admin";
		
		ApplicationContext context = new 
				ClassPathXmlApplicationContext("application-context.xml");
		
		SendMailTemplate sendMailTemplate = (SendMailTemplate) context.getBean("sendMailTemplate");
		
		boolean result = sendMailTemplate.sendTemplate(address, user);
		System.out.println("结果是:" + result);
	}
}
