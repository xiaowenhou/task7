package com.putaoteng.task7.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.putaoteng.task7.model.BasicVo;

@Repository (value="studentDao")
public interface StudentDao extends BasicDao{
	public List<BasicVo> findByStudentName(String name);
}
