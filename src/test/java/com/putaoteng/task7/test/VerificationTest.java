package com.putaoteng.task7.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.putaoteng.task7.dao.VerificationDao;
import com.putaoteng.task7.model.BasicVo;
import com.putaoteng.task7.model.User;
import com.putaoteng.task7.model.Verification;

public class VerificationTest {

	private VerificationDao verificationDao;
	
	@Before
	public void setUp(){
		@SuppressWarnings("resource")
		ApplicationContext context =
				new ClassPathXmlApplicationContext("application-context.xml");
		verificationDao =  (VerificationDao) context.getBean("verificationDao");
	}
	
	@Test
	public void saveTest() {
		Verification verification = new Verification();
		
		verification.setPhoneNumber("13599999999");
		verification.setCode("123456");
		verification.setEmail("zhangsan12345@163.com");
		verification.setEmailVerification((byte)-1);
		verification.setCreateAt(System.currentTimeMillis());
		verification.setUpdateAt(System.currentTimeMillis());
		verification.setCreateBy("xiaowenhou");
		verification.setUpdateBy("xiaowenhou");
	
		System.out.println(verificationDao.toString());
		int result = verificationDao.save(verification);
		System.out.println(result);
	}

	
	@Test
	public void saveBatchTest() {
		List<BasicVo> list = new ArrayList<BasicVo>();
		
		Verification verification = new Verification();
		
		verification.setPhoneNumber("18112345678");
		verification.setCode("888888");
		verification.setEmail("1111111111@qq.com");
		verification.setEmailVerification((byte)-1);
		verification.setCreateAt(System.currentTimeMillis());
		verification.setUpdateAt(System.currentTimeMillis());
		verification.setCreateBy("xiaowenhou");
		verification.setUpdateBy("xiaowenhou");
		list.add(verification);
		
		Verification verification2 = new Verification();
		verification2.setPhoneNumber("13500000000");
		verification2.setCode("666666");
		verification2.setEmail("2222222222@qq.com");
		verification2.setEmailVerification((byte)-1);
		verification2.setCreateAt(System.currentTimeMillis());
		verification2.setUpdateAt(System.currentTimeMillis());
		verification2.setCreateBy("xiaowenhou");
		verification2.setUpdateBy("xiaowenhou");
		list.add(verification2);
		
		int result = verificationDao.saveBatch(list);
		System.out.println(result);
	}
	
	@Test
	public void updateTest(){
		Verification verification = (Verification) verificationDao.findByPK(2L);
		System.out.println(verification.toString());
		
		
		verification.setPhoneNumber("18066666666");
		
		int result = verificationDao.update(verification);
		System.out.println(result);
	
		verification = (Verification) verificationDao.findByPK(2L);
		System.out.println(verification.toString());
	}
	
	
	@Test
	public void updateBachTest(){
		List<BasicVo> list = new ArrayList<BasicVo>();
		list = verificationDao.findAll();
		
		Verification verification1 = (Verification) list.get(0);
		verification1.setCode("345678");
		Verification verification2 = (Verification) list.get(1);
		verification2.setCode("789999");
		
		int result = verificationDao.updateBatch(list);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest(){
		Verification verification = (Verification) verificationDao.findByPK(3L);
		
		int result = verificationDao.delete(verification);
		System.out.println(result);
	}
	
	@Test
	public void deleteByPKTest(){
		int result = verificationDao.deleteByPK(6L);
		System.out.println(result);
	}
	
	@Test
	public void deleteBach(){
		List<BasicVo> list = new ArrayList<BasicVo>();
		list = verificationDao.findAll();
		
		int result = verificationDao.deleteBatch(list);
		System.out.println(result);
	}
	
	@Test
	public void deleteAllTest(){
		int result = verificationDao.deleteAll();
		System.out.println(result);
	}
	
	@Test
	public void countTest(){
		long result = verificationDao.count();
		System.out.println(result);
	}
	
	@Test
	public void findByPKTest(){
		long id = 6L;
		User user = (User) verificationDao.findByPK(id);
		
		System.out.println(user.toString());
	}
	
	@Test
	public void findByPhoneNumberTest(){
		String phoneNumber = "13599999999";
		Verification verification = (Verification) verificationDao.findByPhoneNumber(phoneNumber);
		
		System.out.println(verification.toString());
	}
	
	@Test
	public void findByEmailTest(){
		String email = "1111111111@qq.com";
		Verification verification = (Verification) verificationDao.findByEmail(email);
		
		System.out.println(verification.toString());
	}
	
	@Test
	public void findAllTest(){
		List<BasicVo> list = verificationDao.findAll();
		
		for (BasicVo basicVo : list) {
			System.out.println(basicVo.toString());
		}
	}
}
