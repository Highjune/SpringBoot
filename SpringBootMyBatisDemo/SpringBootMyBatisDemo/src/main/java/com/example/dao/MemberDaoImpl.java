package com.example.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.MemberVO;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private SqlSession sqlSession; //바인딩
	
	@Override
	public void create(MemberVO member) {
		this.sqlSession.insert("insert", member);  //mapper
	}

	@Override
	public void read(Map map) { 
		this.sqlSession.selectOne("select", map);
	}

	@Override
	public void readAll(Map map) {
		this.sqlSession.selectList("selectAll", map);
	}

	@Override
	public void update(MemberVO member) {
		this.sqlSession.update("update", member);
	}

	@Override
	public void delete(String userid) {
		this.sqlSession.delete("delete", userid);
	}

}
