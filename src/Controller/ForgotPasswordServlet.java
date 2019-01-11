package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.LoginBean;
import Utility.DBConnectionUtil;

/**
 * Servlet implementation class ForgotPasswordServlet
 */

public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private Connection conn = null;

	public void init() throws ServletException {
		conn = DBConnectionUtil.getDBConnection();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		RequestDispatcher fp = request.getRequestDispatcher("forgotpassword.jsp");
		RequestDispatcher q = request.getRequestDispatcher("questions.jsp");
		HttpSession session = request.getSession(true);

		if (conn != null) {
			String UN = request.getParameter("UN");
			String uN = "", pW = "";
			String[] question = new String[5], answer = new String[5];
			try {
				ResultSet s = new LoginBean().viewRecord(conn, "account");
				ResultSet t = new LoginBean().viewRecord(conn, "fpass_question");
				int arr = 0;
				if(s.next()){
					uN = s.getString("username");
					pW = s.getString("password");
				}
				while (t.next()) {
					question[arr] = t.getString("Password_Question");
					answer[arr++] = t.getString("Password_Answer");
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

			session.setAttribute("question", question);
			session.setAttribute("answer", answer);
			session.setAttribute("username", uN);
			session.setAttribute("password", pW);
			session.setAttribute("conf", "true");
			
			if (UN.equals(uN)) {
				
				q.forward(request, response);

			} else {
				
				request.setAttribute("remark", "You have entered an invalid username!");
				fp.include(request, response);
			}

		} else {
			System.out.println("Cannot connect to database");
		}
	}

}
