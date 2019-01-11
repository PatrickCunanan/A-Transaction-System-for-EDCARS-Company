package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.inventoryBean;
import Model.transactBean;
import Utility.DBConnectionUtil;

public class indexTablesResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		connection = DBConnectionUtil.getDBConnection();
	}
    public indexTablesResult() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		ResultSet sets1 = new inventoryBean().getAvailable(connection);
		request.setAttribute("sets1", sets1);
		
		ResultSet sets2 = new inventoryBean().getUnavailable(connection);
		request.setAttribute("sets2", sets2);
		
		ResultSet sets3 = new transactBean().getAllDesc(connection);
		request.setAttribute("sets3", sets3);
				
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
