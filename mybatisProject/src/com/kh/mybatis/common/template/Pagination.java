package com.kh.mybatis.common.template;

import com.kh.mybatis.common.model.vo.PageInfo;

public class Pagination {

	// 모듈화 : 자주쓸거같은 코드들을 메소드로 정의하는것
	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		
		int maxPage = (int) Math.ceil((double)listCount / boardLimit);;		// 가장 마지막페이지 (총 페이지 수)
		int startPage = (currentPage -1) / pageLimit * pageLimit + 1;;		// 페이징바의 시작수
		int endPage = startPage + pageLimit - 1;;		// 페이징바의 끝수
		
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		return pi;
	}
}
