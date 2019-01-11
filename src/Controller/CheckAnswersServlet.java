package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.passwordBean;
import Utility.DBConnectionUtil;

/**
 * Servlet implementation class CheckAnswers
 */

public class CheckAnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
    public void init() throws ServletException{		
		conn = DBConnectionUtil.getDBConnection();
    }
	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String q1 = request.getParameter("q1");
		String q2 = request.getParameter("q2");
		String q3 = request.getParameter("q3");
		String q4 = request.getParameter("q4");
		String q5 = request.getParameter("q5");
		
		
		
		HttpSession session = request.getSession(false);
		String[] answer = (String[]) session.getAttribute("answer");
			
		session.setAttribute("username", session.getAttribute("username"));
		session.setAttribute("password", session.getAttribute("password"));
		request.setAttribute("conf", "true");
		
		if (q1.toLowerCase().equals(answer[0].toLowerCase()) && q2.toLowerCase().equals(answer[1].toLowerCase()) && q3.toLowerCase().equals(answer[2].toLowerCase()) && q4.toLowerCase().equals(answer[3].toLowerCase())
				&& q5.toLowerCase().equals(answer[4].toLowerCase())) {
			passwordBean pass = new passwordBean();
			pass.setPassword("10291959");
			pass.updatePassword(conn);
			
			RequestDispatcher rd = request.getRequestDispatcher("pass.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("remark", "Sorry, you are not authorized to proceed!");
			RequestDispatcher rf = request.getRequestDispatcher("login.jsp");
			response.setContentType("text/html");
			rf.include(request, response);
		}
	}

}
