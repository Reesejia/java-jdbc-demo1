package test;

import com.mayikt.entity.UserEntity;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Test01 {
	public static void main(String[] args) throws PropertyVetoException, SQLException {
		// 1.创建数据库连接池, 可以通过构造方法的形式指定连接配置，没有指定则采用默认的default-config链接配置，如果有指定的则用指定的
		ComboPooledDataSource pool = new ComboPooledDataSource("droden-datasource");
//		// 2.设置jdbc连接信息  这些信息一般写在配置文件中，xml/config.properties/yml
//		pool.setUser("droden");
//		pool.setPassword("Aa0715__");
//		// 设置jdbc url
//		pool.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jdbc-test?serverTimezone=UTC");
//		// 设置加载驱动
//		pool.setDriverClass("com.mysql.cj.jdbc.Driver");
		// 框架会默认读取src/c3p0-config.xml 并自动加载到程序中了
		// 3.获取链接对象
		Connection connection = pool.getConnection();
		// 4.获取预编译执行者对象,防止sql注入
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user WHERE id=?");
		// 5.设置参数
		preparedStatement.setLong(1, 2); // parmas1: index   params2: 值
		// 6.执行sql语句
		ResultSet resultSet = preparedStatement.executeQuery();
		if(!resultSet.next()){
			return;
		}
		// 获取到该行数据的第一列
		Long id = resultSet.getLong("id"); // 数据库用的int，这里可以用int,也可以用Long; 定义的时候用Long，所以这里用Long
		String phone = resultSet.getString("phone");
		String pwd = resultSet.getString("pwd");
		UserEntity userEntiry = new UserEntity(id,phone, pwd);
		System.out.println(userEntiry);
		resultSet.close();
		preparedStatement.close(); // 关闭预编译
		connection.close();
	}
}