package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {
	
	public int insertMember(SqlSession sqlSession, Member m) {
		
		/*
       * int result = 0;
       * PreparedStatement pstmt = null;
       * String sql = prop.getProperty("insertMember");
       * 
       * try{
       *       pstmt = conn.prepareStatement(sql);
       *        pstmt.setString(1, m.getUserId());
       *        pstmt.setString(2, m.getUserPwd());
       *       .....
       * 
       *       result = pstmt.executeUpdate();
       * 
       * } catch(xxx){
       * 
       * } finally{
       *     close(pstmt);
       * }
       * 
       */
		
		/*
		 * sqlSession에서 제공하는 메소드를 통해서 sql문 찾아서 실행하고 바로 결과 받음
		 * 
		 * 결과 = sqlSession.sql문 종류에 맞는 메소드("매퍼의별칭.해당sql문의고유한id", [불완전한 sql문이라면 sql문 완성시킬 객체]);
		 * 
		 */
		
//		int result = sqlSession.insert("memberMapper.insertMember", m);
//		return result;
		return sqlSession.insert("memberMapper.insertMember", m);
		
	}
	
	public Member loginMember(SqlSession sqlSession, Member m) {
		
		// 실행할 sql문 (select문) => 한행 조회됨 => selectOne 메소드 : 조회결과가 없으면 null 반환
//		Member loginMember = sqlSession.selectOne("mapper의별명.쿼리id")
		Member loginMember = sqlSession.selectOne("memberMapper.loginMember", m);
		return loginMember;
//		return sqlSession.selectOne("memberMapper.loginMember", m);
		
	}

}
