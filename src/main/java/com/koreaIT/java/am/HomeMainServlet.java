package com.koreaIT.java.am;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int loginedMemberId = -1;
		Map<String, Object> memberMap = null;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			memberMap = (Map<String, Object>) session.getAttribute("loginedMember");
		}
		
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", memberMap);
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

}