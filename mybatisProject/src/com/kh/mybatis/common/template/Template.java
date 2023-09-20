package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	/*
	 * 기존 JDBC
	 * 
	 * public static Connection getConnection(){
	 * 		// driver.properties 파일 읽어들여서 
	 * 		// 해당 DB와 접속된 Connection 객체 생성해서 반환
	 * }
	 * 
	 * public static void close(JDBC용 객체){
	 * 		// 전달받은 JDBC용 객체를 반납시키는 구문
	 * }
	 * 
	 * public static void commit|rollback(Connection conn){
	 * 		// 트랜젝션 처리
	 * }
	 * 
	 */
	
	// 마이바티스
	
	public static SqlSession getSqlSession() {
		
		// mybatis-config.xml 파일을 읽어들여서
		// 해당 DB와 접속된 SqlSession 객체 생성해서 반환
		
		SqlSession sqlSession = null;
		
		// SqlSession 생성하기 위해서 => SqlSessionFactory 필요
		// SqlSessionFactory 생성하기 위해서 => SqlSessionFactoryBuilder 필요
		
		String resource = "/mybatis-config.xml";
		// 이때 mybatis-config.xml 문서 읽어들임
		// 이때 등록시킨 mapper.xml 문서들도 다 읽어들여짐 => 기존에 dao에서 mapper.xml 계속 불러오는 기본생성자 역할 필요X
		// '/' == 소스폴더의 최상위경로를 의미
		// resources폴더를 '소스폴더'로 만든 이유
		
		
		try {
			// mybatis-config.xml은 외부파일이라서 내 프로그램이랑 연결하기위한 통로 필요
			InputStream stream = Resources.getResourceAsStream(resource); // 이 코드를 통해 mybatis~ 파일 읽음
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
						// openSession() : 기본값은 false
						// openSession(boolean flag) : 자동커밋 여부 (true면 하겠다, false면 안하겠다)
						// => 개발자가 autoCommit 여부를 직접 설정함
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	}
	
	
	

}
