package com.mayikt.jdbc.demo01;
import com.mysql.jdbc.Driver;

import java.sql.*;

public class JdbcDemo01 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc-test?serverTimezone=UTC", "droden", "Aa0715__");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from user");
		while (resultSet.next()){
			System.out.println("id:" + resultSet.getString("name") +"pwd:" +resultSet.getString("pwd"));
		}

		connection.close();
		statement.close();
	}
}
