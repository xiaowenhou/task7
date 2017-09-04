package com.putaoteng.task7.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.putaoteng.task7.utils.authentication.JsonWebToken;
import com.putaoteng.task7.utils.authentication.MD5Encryption;

public class JWTTest {
	@Test
	public void tokenTest() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String username = "zhangsan";
		String loginAt = System.currentTimeMillis() + "";
		
		String encryCookieValue1 = MD5Encryption.EncoderByMd5(username);
		String encryCookieValue2 = MD5Encryption.EncoderByMd5(loginAt);
		// 然后再将用户名和登陆时间再按照某种规则进行加密(这里也可以使用其他的加密手段)
		String encry = MD5Encryption.EncoderByMd5("[" + encryCookieValue1 + "$" + "]" + encryCookieValue2);

		String token = JsonWebToken.createJWT(encryCookieValue1, encryCookieValue2, encry, 30);
		System.out.println(token);
		
		System.out.println(JsonWebToken.parseJWT(token));
	}
}
