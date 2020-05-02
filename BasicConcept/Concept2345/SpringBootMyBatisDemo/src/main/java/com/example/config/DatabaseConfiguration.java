package com.example.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j  //이것을 쓰면 좋은 점은 log. 을 사용할 수 있다.
@Configuration
@PropertySource("classpath:application.properties")  //application.properties의 경로를 잡기 위해.
public class DatabaseConfiguration { //이 클래스가 실제로 datasource와 mabatis와 연동한다. 그래서  @PropertSource로 properties를 가져온다.
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean //hikari가 oracle과 연결 (hicari를 만들려면 환경설정이 필요함)
	@ConfigurationProperties(prefix="spring.datasource.hikari") //환경설정한 것 들고옴
	public HikariConfig hikariConfig() { //HikariConfig : application.properties 파일에서 접두사가 spring.datasource.hikari인것을 가져온다.
		return new HikariConfig();
	}
	
	@Bean //datasource가 hikari와 연결(dataSource를 만들려면 hicari가 필요함)
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig());  //이 hicari가 있어야 datasource만든다.
		log.info("dataSource = " + dataSource);
		return dataSource; 
	}
	
	@Bean //mybatis와 datasource 연결
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		Resource resource = 
				new PathMatchingResourcePatternResolver().
				getResource("classpath:/mapper/mybatis-config.xml");
		sqlSessionFactory.setConfigLocation(resource);
		sqlSessionFactory.setMapperLocations(
				this.applicationContext.getResources("classpath:/mapper/mybatis-mapper.xml")); 
		
		return sqlSessionFactory.getObject();
	}
	
	@Bean //sqlSessionTemplate이 있어야 sqlsession이 나온다
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
} 



