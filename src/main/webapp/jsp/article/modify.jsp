<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Map<String, Object> articleMap = (Map<String, Object>) request.getAttribute("articleMap");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article modify</title>
</head>
<body>
	<h1><%= articleMap.get("id") %>번 게시물 수정</h1>
	
	<form action="doModify" method="post">
		<input name="id" type="text" value="<%= (int) articleMap.get("id") %>" />
		<div> 번호 : <%= (int) articleMap.get("id") %></div>
		<div> 작성일 : <%= (LocalDateTime) articleMap.get("regDate") %></div>
		<div> 수정일 : <%= (LocalDateTime) articleMap.get("updateDate") %></div>
		<div> 제목 : <input name="title" type="text" value="<%= (String) articleMap.get("title") %>"/></div>
		<div> 내용 : <textarea name="body"><%= (String) articleMap.get("body") %></textarea></div>
		
		<div>
			<a href="list">목록</a>
			<button>수정</button>
			<a href="delete?id=<%= (int) articleMap.get("id") %>">삭제</a>
		</div>
	</form>
</body>
</html>