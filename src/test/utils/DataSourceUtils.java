package test.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtils {
	private static 	DruidDataSource druidDataSource;
	// 工具类 静态方法
	// 1. 将DataSourceUtils私有化，不能new
	private  DataSourceUtils(){

	}
	// 使用静态代码 读取配置文件内容 加载 Druid数据连接池
	public static void main(String[] args) {

	}

	static {
		try{
			// 读取src 下druild.properties配置文件
			Properties properties = new Properties();
			InputStream resourceAsStream = DataSourceUtils.class.getClassLoader().getResourceAsStream("druild.properties");
			properties.load(resourceAsStream);
			// 交给Druid数据连接池中
			 druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 封装获取Connection的方法
	 */
	public static Connection getConnection(){
		try{
			return druidDataSource.getConnection();
		}catch (Exception e){
			return null;
		}
	}

	// 释放jdbc资源
	public static void close(Connection connection, Statement statement, ResultSet resultSet){
		try {
			if(connection != null){
				connection.close();
			}

			if(statement != null){
				statement.close();
			}

			if(resultSet != null){
				resultSet.close();
			}
		}catch (SQLException throwables){
			throwables.printStackTrace();
		}
	}

	public static void close(Connection connection, Statement statement){
		 close(connection, statement, null);
	}
}
