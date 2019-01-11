package Utility;

import java.sql.Connection;

import Model.LoginBean;

public interface LoginBeanFactory {
	public static LoginBean getInstance(String username, String password, Connection conn){
		LoginBean lg = new LoginBean();
		
		lg.setUsername(username);
		lg.setPassword(password);
	
		
		return lg;
	} 
}
