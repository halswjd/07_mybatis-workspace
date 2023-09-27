<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer a{
		text-decoration:none;
		color:white;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>

    <div class="outer" align="center">
        <br>
        <h1 align="center">게시판 상세조회</h1>

        
        
        <table align="center" border="1">
            <tr>
                <td width="100">글번호</td>
                <td width="500">${ b.boardNo }</td>
            </tr>
            <tr>
                <td>제목</td>
                <td>${ b.boardTitle }</td>
            </tr>
            <tr>
                <td>작성자</td>
                <td>${ b.boardWriter }</td>
            </tr>
            <tr>
                <td>조회수</td>
                <td>${ b.count }</td>
            </tr>
            <tr>
                <td>작성일</td>
                <td>${ b.createDate }</td>
            </tr>
            <tr>
                <td>내용</td>
                <td height="100">
                    ${ b.boardContent }
                </td>
            </tr>
        </table>

		<c:if test="${ loginMember.userId eq b.boardWriter }">
        	<button id="dBtn">글 삭제</button>
        </c:if>
        
        <script>
        	$("#dBtn").click(function(){
        		if(confirm("해당 글을 삭제하시겠습니까?")){
        			location.href="delete.bo?bno=${b.boardNo}";
        		}
        		
        	})
        </script>
        <br>

        <table align="center" border="1">
            <tr>
                <th width="100">댓글작성</th>
                <th width="400"><textarea name=""></textarea></th>
                <th width="100"><button>등록</button></th>
            </tr>
            <tr>
                <td colspan="3">댓글(${ list.size() })</td> <!-- fn:length(list) -->
            </tr>
           	<c:forEach var="r" items="${ list }">
	            <tr>
	                <td>${ r.replyWriter }</td>
	                <td>${ r.replyContent }</td>
	                <td>${ r.createDate }</td>
	            </tr>
            </c:forEach>
        </table>

    </div>
</body>
</html>