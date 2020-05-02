package com.example.demo;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SpringBootMyBatisDemoApplicationTests {
	@Autowired  //sqlSession과 바인딩
	private SqlSession sqlSession;  //DatabaseConfiguration의 sqlSessionTemplate이다. 그런데 sqlSessionTemplate은 여기서 sqlSession으로 매칭. Q) 왜?
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void test1() {
		log.info("SqlSession is {}", this.sqlSession);
	}
}


