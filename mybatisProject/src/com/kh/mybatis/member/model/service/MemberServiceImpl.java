package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService { /* MemberService를 구현한 Class */
	
	private MemberDao mDao = new MemberDao();

	@Override
	public int insertMember(Member m) {
		
		/*
		 * 이전 방식
		 * Connection conn = JDBCTemplate.getConnection();
		 * int result = new MemberDao().insertMember(conn, m);
		 * 
		 * if(result > 0){
		 * 		JDBCTemplate.commit(conn);
		 * }else{
		 * 		JDBCTemplate.rollback(conn);
		 * } 
		 * 
		 * JDBCTemplate.close(conn);
		 * 
		 * return result;
		 * 
		 */
		
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = mDao.insertMember(sqlSession, m);
		
		// commit을 수동으로 설정해서 else필요없음
		if(result > 0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public Member loginMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		Member loginMember = mDao.loginMember(sqlSession, m);
		
		sqlSession.close();
		
		return loginMember;
	}

	@Override
	public int updateMember(Member m) {
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		return 0;
	} 
	
	

}
