package com.putaoteng.task7.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.putaoteng.task7.dao.UserDao;
import com.putaoteng.task7.model.BasicVo;
import com.putaoteng.task7.model.User;
import com.putaoteng.task7.utils.authentication.MD5Encryption;

public class UserTest {

	@Resource (name="userDao")
	private UserDao userDao;
	
	@Before
	public void setUp(){
		@SuppressWarnings("resource")
		ApplicationContext context =
				new ClassPathXmlApplicationContext("application-context.xml");
		userDao = (UserDao) context.getBean("userDao");
	}
	
	@Test
	public void saveTest() {
		User user = new User();
		
		user.setUsername("xiuzhenyuan");
		user.setPassword("123456");
		user.setPhoneNumber(13599999999L);
		user.setEmail("zhangsan12345@163.com");
//		user.setImage("/images/a/b/c/d/hahaha.jpg");
		user.setCreateAt(20170715133333L);
		user.setLoginAt(20170715133356L);
	
		System.out.println(userDao.toString());
		int result = userDao.save(user);
		System.out.println(result);
	}

	
	@Test
	public void saveBatchTest() {
		List<BasicVo> list = new ArrayList<BasicVo>();
		
		User user = new User();
		user.setUsername("xiuzhenyuan");
		user.setPassword("password");
		user.setPhoneNumber(18112345678L);
		user.setEmail("1111111111@qq.com");
//		user.setImage("/images/a/b/c/d/hahaha.png");
		user.setCreateAt(20170715133333L);
		user.setLoginAt(20170715133356L);
		list.add(user);
		
		User user2 = new User();
		user2.setUsername("wahahah");
		user2.setPassword("999999");
		user2.setPhoneNumber(14588888888L);
		user2.setEmail("wangwuzhaoliu1234567890@163.com");
//		user2.setImage("/images/hello/hahaha.png");
		user2.setCreateAt(20170715133333L);
		user2.setLoginAt(20170715133356L);
		list.add(user2);
		
		int result = userDao.saveBatch(list);
		System.out.println(result);
	}
	
	@Test
	public void updateTest(){
		User user = (User) userDao.findByPK(2L);
		System.out.println(user.toString());
		
		user.setUsername("xiaowenhou");
		user.setPhoneNumber(18066666666L);
		
		int result = userDao.update(user);
		System.out.println(result);
	
		user = (User) userDao.findByPK(2L);
		System.out.println(user.toString());
	}
	
	
	@Test
	public void updateBachTest(){
		List<BasicVo> list = new ArrayList<BasicVo>();
		list = userDao.findAll();
		
		User user1 = (User) list.get(0);
		user1.setUsername("zhangsan");
		User user2 = (User) list.get(1);
		user2.setUsername("admin");
		
		int result = userDao.updateBatch(list);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest(){
		User user = (User) userDao.findByPK(3L);
		
		int result = userDao.delete(user);
		System.out.println(result);
	}
	
	@Test
	public void deleteByPKTest(){
		int result = userDao.deleteByPK(6L);
		System.out.println(result);
	}
	
	@Test
	public void deleteBach(){
		List<BasicVo> list = new ArrayList<BasicVo>();
		list = userDao.findAll();
		
		int result = userDao.deleteBatch(list);
		System.out.println(result);
	}
	
	@Test
	public void deleteAllTest(){
		int result = userDao.deleteAll();
		System.out.println(result);
	}
	
	@Test
	public void countTest(){
		long result = userDao.count();
		System.out.println(result);
	}
	
	@Test
	public void findByPKTest(){
		long id = 6L;
		User user = (User) userDao.findByPK(id);
		
		System.out.println(user.toString());
	}
	
	@Test
	public void findByUsernameTest(){
		String username = "aaa";
		String encry;
		try {
			encry = MD5Encryption.EncoderByMd5(username);
			User user = userDao.findByUserName(encry);
			if (user != null){
				System.out.println(user.toString());
			} else{
				System.out.println("user null");
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void findAllTest(){
		List<BasicVo> list = userDao.findAll();
		
		for (BasicVo basicVo : list) {
			System.out.println(basicVo.toString());
		}
	}
}
