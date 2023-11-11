<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int from = (int) request.getAttribute("from");
	int end = (int) request.getAttribute("end");
	int cPage = (int) request.getAttribute("page");
	int totalPage = (int) request.getAttribute("totalPage");
	List<Map<String, Object>> articleListMap = (List<Map<String, Object>>) request.getAttribute("articleListMap");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.paging > a.red {
		color: red;
		font-size: 1.5rem;
	}
</style>
<title>Article list</title>
</head>
<body>
	<div>게시물 리스트 v4</div>
	
	<%@ include file="../topBar/topBar.jsp"%>
	
	<table border="1">
		<colgroup>
			<col />
			<col width="200" />
		</colgroup>
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<% for(Map<String, Object> articleMap : articleListMap) { %>
			<tr>
				<td><%= articleMap.get("id") %></td>
				<td><%= articleMap.get("regDate") %></td>
				<td><a href="detail?id=<%= articleMap.get("id") %>"><%= articleMap.get("title") %></a></td>
				<td><%= articleMap.get("writerName") %></td>
			</tr>
		<% } %>
	</table>

	<div class="paging">
		<% if (cPage > 1) { %>
			<a href="list?page=1">◀</a>
		<% } %>
		
		<% for (int i = from; i <= end; i++) { %>
			<a class="<%= cPage == i ? "red" : "" %>" href="list?page=<%= i %>"><%= i %></a>
		<% } %>
		
		<% if (cPage < totalPage) { %>
			<a href="list?page=<%= totalPage %>">▶</a>
		<% } %>
	</div>
	<div><a href="../home/main">메인 페이지</a></div>
</body>
</html>