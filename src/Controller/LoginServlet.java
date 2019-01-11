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

import Utility.DBConnectionUtil;
import Utility.LoginBeanFactory;
import Model.LoginBean;



/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	private Connection conn = null;
    public void init() throws ServletException{		
		conn = DBConnectionUtil.getDBConnection();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession();  
        session.invalidate(); 
		request.setAttribute("logOutMessage", "You have successfully logged out!");
		request.getRequestDispatcher("login.jsp").include(request, response);  
        
        response.setContentType("text/html"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(conn != null){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String uN = "", pW = "";
				
				LoginBean lbf = LoginBeanFactory.getInstance(username, password, conn);
				request.setAttribute("login", lbf);
				
				try {
					ResultSet s = new LoginBean().viewRecord(conn, "account");
					while(s.next()){
						uN = s.getString("username");
						pW = s.getString("password");
					}
					//out.println(s.getString("password"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				RequestDispatcher r = request.getRequestDispatcher("indexTables.html");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					
				
				
				if(username.equals(uN) && password.equals(pW)){
					HttpSession session = request.getSession();
					session.setAttribute("username", uN);
					session.setAttribute("password", pW);
					session.setAttribute("log", "yes");
					r.forward(request, response);
					
				}else{
					request.setAttribute("remark", "You have entered a wrong password or username!");
					rd.include(request, response);
				}
				
			}
			else{
				System.out.println("Cannot connect to database");
			}
	}

}
