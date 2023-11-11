package com.koreaIT.java.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.koreaIT.java.am.config.Config;
import com.koreaIT.java.am.util.DBUtil;
import com.koreaIT.java.am.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/list") // url매핑
public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		HttpSession session = request.getSession();
		
		int loginedMemberId = -1;
		Map<String, Object> memberMap = null;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			memberMap = (Map<String, Object>) session.getAttribute("loginedMember");
		}
		
		try {
			Class.forName(Config.getDBDriverName());
			String url = Config.getDBUrl();
			conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassWd());

			int page = 1;
			
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int itemInAPage = 10;
			
			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*) FROM article");
			
			int totalCnt = DBUtil.selectRowIntValue(conn, sql);
			int totalPage = (int) Math.ceil((double) totalCnt / itemInAPage);
			int limitFrom = (page - 1) * itemInAPage;
			
			int pageSize = 5;
			
			int from = page - pageSize;
			if (from < 1) {
				from = 1;
			}
			
			int end = page + pageSize;
			if (end > totalPage) {
				end = totalPage;
			}
			
			sql = new SecSql();
			sql.append("SELECT A.*, M.name AS writerName");
			sql.append("FROM article AS A");
			sql.append("INNER JOIN `member` AS M");
			sql.append("ON A.memberId = M.id");
			sql.append("ORDER BY A.id DESC");
			sql.append("LIMIT ?, ?", limitFrom, itemInAPage);
			
			List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);
			
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMember", memberMap);
			request.setAttribute("from", from);
			request.setAttribute("end", end);
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("articleListMap", articleListMap);
			
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			
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

}