package test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mayikt.entity.UserEntity;
import test.utils.DataSourceUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DruildTest02 {
	public static void main(String[] args) throws Exception {
		// 1.读取druild.properties 配置易文件

		Connection connection = DataSourceUtils.getConnection();
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
		DataSourceUtils.close(connection, preparedStatement, resultSet);
	}
}
