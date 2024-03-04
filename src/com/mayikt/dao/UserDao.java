package com.mayikt.dao;

import com.mayikt.entity.StudentEntiry;
import com.mayikt.entity.UserEntity;
import com.mayikt.utils.MayiktJdbcUtils;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
	public int registUser(UserEntity userEntity){
		Connection connection = null;
		Statement statement= null;
		try{
			// 1. java链接mysql数据库查询所有数据
			// 2. 将这些数据存到 ArrayList<StudentEntiry>、
			// 2.注册驱动
			connection = MayiktJdbcUtils.getConnection();
			connection.setAutoCommit(false); // false 如果设定的值为false，则表示 开启事务是手动提交的模式；true自动提交
			// 4.获取执行者对象
			statement = connection.createStatement();
			// 5.执行sql语句并返回结果
			String insertUserSql = "INSERT INTO user VALUES(null, "+userEntity.getPhone()+",'"+userEntity.getPwd()+"')";
			System.out.println("insertUserSql: "+ insertUserSql);
			int result = statement.executeUpdate(insertUserSql);
			// 执行该sql语句，返回影响行数
			// 模拟代码发送bug,整体流程需要去回滚的
//			int j = 1 / 0;
			connection.commit(); // 提交事务
			return  result;
		}catch (Exception e){
			try{
				connection.rollback();
			}catch (SQLException throwables){
				e.printStackTrace();
			}
			return 0;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(statement, connection);
		}
	}

	// 根据手机号查询用户信息
	public UserEntity getByPhoneUser(String phone){
		if(phone == null || phone.length() == 0){
			return null;
		}

		Connection connection = null;
		Statement statement= null;
		ResultSet resultSet = null;
		try{
			connection = MayiktJdbcUtils.getConnection();
			// 4.获取执行者对象
			statement = connection.createStatement();
			// 5.执行sql语句并返回结果
			String getByPhoneUserSql = "select * from user where phone="+phone;
			System.out.println("getByPhoneUserSql:" + getByPhoneUserSql);
			resultSet = statement.executeQuery(getByPhoneUserSql);
			// 6. 对结果进行处理

			boolean result = resultSet.next();
			if(!result){
				return null;
			}
			// 获取到该行数据的第一列
			Long dbId = resultSet.getLong("id");// 数据库用的int，这里可以用int,也可以用Long; 定义的时候用Long，所以这里用Long
			String dbPhone = resultSet.getString("phone");
			String dbPwd = resultSet.getString("pwd");
			// 将db中查询到数据封装成java学生对象
			UserEntity userEntity = new UserEntity(dbId, dbPhone, dbPwd);
			return userEntity;

		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(resultSet ,statement, connection);
		}
	}

	public UserEntity login(UserEntity userEntity){
		if(userEntity == null){
			return null;
		}

		Connection connection = null;
		PreparedStatement statement= null;
		ResultSet resultSet = null;
		try{
			connection = MayiktJdbcUtils.getConnection();
			// 4.获取执行者对象
//			statement = connection.createStatement();
			// 5.执行sql语句并返回结果
//			String loginSql = " SELECT * from user WHERE phone='"+userEntity.getPhone()+"' and pwd='"+userEntity.getPwd()+"'";
			String loginSql = "SELECT * from user WHERE phone=? and pwd=?";
			statement = connection.prepareStatement(loginSql); //  预编译sql语句
			statement.setString(1, userEntity.getPhone());
			statement.setString(2, userEntity.getPwd());
			System.out.println("loginSql:" + loginSql);
			resultSet = statement.executeQuery(loginSql);
			// 6. 对结果进行处理

			boolean result = resultSet.next();
			if(!result){
				return null;
			}
			// 获取到该行数据的第一列
			Long dbId = resultSet.getLong("id");// 数据库用的int，这里可以用int,也可以用Long; 定义的时候用Long，所以这里用Long
			String dbPhone = resultSet.getString("phone");
			String dbPwd = resultSet.getString("pwd");
			// 将db中查询到数据封装成java学生对象
			UserEntity dbUserEntity = new UserEntity(dbId, dbPhone, dbPwd);
			return dbUserEntity;

		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(resultSet ,statement, connection);
		}
	}
}
