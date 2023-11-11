package com.koreaIT.java.am;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/doLogout") // url매핑
public class MemberDoLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8;");

		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginedMemberId") == null) {
			response.getWriter().append("<script>alert('로그인 후 이용해주세요.'); location.replace('../home/main');</script>");
			return;
		}
		
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMember");

		response.getWriter().append("<script>alert('로그아웃 되었습니다'); location.replace('../home/main');</script>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}