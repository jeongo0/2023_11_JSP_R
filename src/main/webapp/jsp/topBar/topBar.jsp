<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	int loginedMemberId = (int) request.getAttribute("loginedMemberId");
	Map<String, Object> loginedMember = (Map<String, Object>) request.getAttribute("loginedMember");
%>

<% if (loginedMemberId == -1) { %>
	<div><a href="../member/join">회원가입</a></div>
	<div><a href="../member/login">로그인</a></div>
<% } %>
<% if (loginedMemberId != -1) { %>
	<div><%= loginedMember.get("name") %>님 환영합니다</div>
	<div><a href="../member/doLogout">로그아웃</a></div>
	<div><a href="../article/write">글쓰기</a></div>
<% } %>