package com.koreaIT.java.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.koreaIT.java.am.config.Config;
import com.koreaIT.java.am.util.DBUtil;
import com.koreaIT.java.am.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8;");
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		HttpSession session = request.getSession();
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		Connection conn = null;

		try {
			Class.forName(Config.getDBDriverName());
			String url = Config.getDBUrl();
			conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassWd());

			SecSql sql = new SecSql();
			sql.append("INSERT INTO article");
			sql.append("SET regDate = NOW(),");
			sql.append("updateDate = NOW(),");
			sql.append("memberId = 1,", loginedMemberId);
			sql.append("title = ?,", title);
			sql.append("body = ?", body);
			
			int id = DBUtil.insert(conn, sql);
			
			response.getWriter().append(String.format("<script>alert('%d번 게시물이 생성되었습니다'); location.replace('detail?id=%d');</script>", id, id));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}