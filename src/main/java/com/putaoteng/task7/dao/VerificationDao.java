package com.putaoteng.task7.dao;

import org.springframework.stereotype.Repository;

import com.putaoteng.task7.model.Verification;

@Repository (value = "verificationDao")
public interface VerificationDao extends BasicDao{
	public Verification findByPhoneNumber(String phoneNumber);
	
	public Verification findByEmail(String email);
	
	public Verification findByCreateAt(long createTime);
}
