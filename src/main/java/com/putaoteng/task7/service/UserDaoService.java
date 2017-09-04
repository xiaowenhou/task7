package com.putaoteng.task7.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.putaoteng.task7.dao.UserDao;
import com.putaoteng.task7.model.BasicVo;
import com.putaoteng.task7.model.User;

@Service(value = "userDaoService")
public class UserDaoService {

	@Resource(name = "userDao")
	private UserDao userDao;

	public int save(User user) {
		return userDao.save(user);
	}

	public int saveBatch(List<BasicVo> list) {
		return userDao.saveBatch(list);
	}

	public int update(User user) {
		return userDao.update(user);
	}

	public int updateIgnoreNull(User user) {
		return userDao.updateIgnoreNull(user);
	}

	public int updateBatch(List<BasicVo> list) {
		return userDao.updateBatch(list);
	}

	public int delete(User user) {
		return  userDao.delete(user);
	}

	public int deleteBatch(List<BasicVo> list) {
		return userDao.deleteBatch(list);
	}

	public int deleteByPK(Long id) {
		return userDao.deleteByPK(id);
	}

	public int deleteAll() {
		return userDao.deleteAll();
	}

	public long count() {
		return userDao.count();
	}

	public User findByPK(Long id) {
		return (User) userDao.findByPK(id);
	}

	// public List find(Map<String, Object> paramMap, PageBounds pageBounds);

	public User findByUsername(String username) {
		return userDao.findByUserName(username);
	}

	public List<BasicVo> findAll() {
			return userDao.findAll();
	}
}
