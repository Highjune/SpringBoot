package com.example.demo;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SpringBootRestAPIDemoApplicationTests {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void test1() {
		log.info("SqlSession is {}", this.sqlSession);
	}

}
