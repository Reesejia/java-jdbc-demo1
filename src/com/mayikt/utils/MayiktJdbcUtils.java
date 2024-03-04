package com.mayikt.utils;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class MayiktJdbcUtils {
	/**
	 * 1.需要将我们的构造方法私有化，工具类是不需要new出来，是通过类名称.方法名称访问
	 * 2.定义工具需要的声明 变量
	 * 3.使用到静态代码块，来给我们声明好的jdbc变量赋值（读取config.properties）
	 * 4.封装链接方法
	 * 5.封装释放链接方法
	 */
	// 1.需要将我们的构造方法私有化，工具类是不需要new出来，是通过类名称.方法名称访问
	private MayiktJdbcUtils(){

	}

	//  2.定义工具需要的声明 变量
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;

	// 3.使用到静态代码块，来给我们声明好的jdbc变量赋值（读取config.properties）
	static {
		try{
			// 1.读取config.properties  IO 相对路径
			// 	MayiktJdbcUtils.class.getClassLoader() 相当于会直接定位到src目录下
			InputStream resourceAsStream = MayiktJdbcUtils.class.getClassLoader().getResourceAsStream("config.properties");
			// 2.赋值给声明好的变量
			Properties properties = new Properties();
			// Properties api 可以实现，给个配置文件，获取key对应的value值
			properties.load(resourceAsStream);
			driverClass =properties.getProperty("driverClass");
			url =properties.getProperty("url");
			user =properties.getProperty("user");
			password =properties.getProperty("password");
			// 3.注册驱动类
			Class.forName(driverClass);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 当class MayiktJdbcUtils被加载时，会优先执行static静态代码块里面，做些初始化操作
		System.out.println("MayiktJdbcUtils.driverClass ," + MayiktJdbcUtils.driverClass);
	}

	// 4.封装链接方法
	public static Connection getConnection(){
		try {
			Connection connection = DriverManager.getConnection(url, user,password);
			return connection;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	// 5.封装释放链接方法
	public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
		// 释放链接分两种场景： 1. 查询的时候释放 2.增删改 的时候释放
		//  1. 查询的时候释放 resultSet statement 执行者对象 connection
		//  2.增删改 的时候释放  statement connection
		try{
			if(resultSet != null){
				resultSet.close();
			}

			if(statement != null){
				statement.close();
			}

			if(connection != null){
				connection.close();
			}
		}catch (Exception e){
				e.printStackTrace();
		}
	}
	public static void closeConnection( Statement statement, Connection connection) {
		closeConnection(null, statement, connection);
	}
}
