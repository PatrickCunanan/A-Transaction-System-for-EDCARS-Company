package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBean {
	private String username, password, passQ, passA;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassQ() {
		return passQ;
	}

	public void setPassQ(String passQ) {
		this.passQ = passQ;
	}

	public String getPassA() {
		return passA;
	}

	public void setPassA(String passA) {
		this.passA = passA;
	}
	
	
	public ResultSet viewRecord(Connection conn, String db) throws SQLException{
			ResultSet result = null;
			
			String queryLog = "select * from " + db;
			
			try {
				PreparedStatement st = conn.prepareStatement(queryLog);
				result = st.executeQuery();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return result;
			
	}
	
	

}
