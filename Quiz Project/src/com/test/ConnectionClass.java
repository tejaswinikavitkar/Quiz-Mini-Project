package com.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	static Connection co;
	public static Connection createC()  {
		
		
		try {
			// load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String user="root";
			String password="root";
			String url="jdbc:mysql://localhost:3306/student";
				
			co=DriverManager.getConnection(url, user, password);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return co;
	}
}
