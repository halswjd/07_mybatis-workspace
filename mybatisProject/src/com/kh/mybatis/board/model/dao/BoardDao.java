package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

public class BoardDao {
	
	public int selectListCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
		
	}

	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi){
		
//		sqlSession.selectList("boardMapper.selectList");
		
		// mybatis에서는 페이징 처리를 위해 RowBounds라는 클래스 제공
		// RowBounds rowBounds = new RowBounds(int offset, int limit);
		
		// * offset : 몇 개의 게시글 건너뛰고 조회할건지에 대한 값
		
		/*
		 * ex) boardLimit : 5
		 * 							offset(건너뛸숫자)		limit(조회할숫자)
		 * currentPage : 1	 1~5			0				5
		 * currentPage : 2	 6~10			5				5
		 * currentPage : 3	 11~15			10				5
		 * ...
		 * 
		 */
		
//		int offset = (현재페이지 - 1) * 몇개씩 보여줄껀지
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit(); // 걍 공식  
		int limit = pi.getBoardLimit(); // offset,limit 세팅때문에 pi객체 받는거임
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
//		sqlSession.selectList("매퍼의별칭.쿼리아이디", 쿼리가 불완전하면 채워줄 무언가, rowBounds);
		ArrayList<Board> list = (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds); // 쿼리가 완전해서 넘겨줄게 없으면 null 입력해야 rowBounds객체를 인식 가능함
		// selectList()는 반환형이 List라 ArrayList로 downCasting 필요
		
		return list;
		
	}
	
	public int increaseCount(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.update("boardMapper.increaseCount", boardNo);
		
	}
	
	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
		
	}
	
	public ArrayList<Reply> selectListReply(SqlSession sqlSession, int boardNo){
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
		
	}
	
	public int selectSearchCount(SqlSession sqlSession, HashMap<String, String> map) {
		
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
	}
	
	public ArrayList<Board> selectSearchList(SqlSession sqlSession, HashMap<String, String> map, PageInfo pi){
//		int offset = (현재페이지 -1) * boardLimit
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rowBounds);
	}
}
