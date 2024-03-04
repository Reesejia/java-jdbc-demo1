package com.mayikt.dao;

import com.mayikt.entity.StudentEntiry;
import com.mayikt.utils.MayiktJdbcUtils;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao {
	// 学生对象数据访问层

	/**
	 * 查询所有学生信息
	 * @return
	 */
	public ArrayList<StudentEntiry> allStudent() {
		Connection connection = null;
		Statement statement= null;
		ResultSet resultSet = null;
		try{
			// 1. java链接mysql数据库查询所有数据
			// 2. 将这些数据存到 ArrayList<StudentEntiry>、
			// 2.注册驱动
			// 3.获取数据库连接
			 connection = MayiktJdbcUtils.getConnection();
			// 4.获取执行者对象
			 statement = connection.createStatement();
			// 5.执行sql语句并返回结果
			 resultSet = statement.executeQuery("select * from stu1");
			ArrayList<StudentEntiry> studentEntiries = new ArrayList<StudentEntiry>();
			// 6. 对结果进行处理
			while (resultSet.next()){
				// 获取到该行数据的第一列
				long id = resultSet.getLong("id");// 数据库用的int，这里可以用int,也可以用Long; 定义的时候用Long，所以这里用Long
				String name = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				String address = resultSet.getString("address");
				// 将db中查询到数据封装成java学生对象
				StudentEntiry studentEntiry = new StudentEntiry(id, name, age, address);
				// 将该对象存入到集合中
				studentEntiries.add(studentEntiry);
			}
			return studentEntiries;

		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(resultSet, statement, connection);
		}
	}

	/**
	 * 根据学生id 查询学生信息
	 * @return
	 */
	public StudentEntiry getByIdStudent(Long stuId){
		if(stuId == null){
			return null;
		}

		Connection connection = null;
		Statement statement= null;
		ResultSet resultSet = null;
		try{
			// 1. java链接mysql数据库查询所有数据
			// 2. 将这些数据存到 ArrayList<StudentEntiry>、
			// 2.注册驱动
			connection = MayiktJdbcUtils.getConnection();
			// 4.获取执行者对象
			statement = connection.createStatement();
			// 5.执行sql语句并返回结果
			resultSet = statement.executeQuery("select * from stu1 where id="+stuId);
			ArrayList<StudentEntiry> studentEntiries = new ArrayList<StudentEntiry>();
			// 6. 对结果进行处理

			boolean result = resultSet.next();
			if(!result){
				return null;
			}
				// 获取到该行数据的第一列
				long id = resultSet.getLong("id");// 数据库用的int，这里可以用int,也可以用Long; 定义的时候用Long，所以这里用Long
				String name = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				String address = resultSet.getString("address");
				// 将db中查询到数据封装成java学生对象
				StudentEntiry studentEntiry = new StudentEntiry(id, name, age, address);
				// 将该对象存入到集合中
				studentEntiries.add(studentEntiry);
				return studentEntiry;

		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(resultSet ,statement, connection);
		}
	}
	/**
	 * 插入我们的学生
	 * @return number
	 */
	public  int insertStudent(StudentEntiry stu){
		Connection connection = null;
		Statement statement= null;
		try{
			// 1. java链接mysql数据库查询所有数据
			// 2. 将这些数据存到 ArrayList<StudentEntiry>、
			// 2.注册驱动
			connection = MayiktJdbcUtils.getConnection();
			// 4.获取执行者对象
			statement = connection.createStatement();
			// 5.执行sql语句并返回结果
			String insertStudentSql = "INSERT INTO stu1 VALUES(null, '"+stu.getName()+"', "+stu.getAge()+",'"+stu.getAddress()+"')";
			System.out.println("insertStudentSql: "+ insertStudentSql);
			int result = statement.executeUpdate(insertStudentSql);
			// 执行该sql语句，返回影响行数
			return  result;
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(statement, connection);
		}
	}

	// 修改学生信息
	public int updateStudent(StudentEntiry stu){
		Connection connection = null;
		Statement statement= null;
		try{
			// 1. java链接mysql数据库查询所有数据
			// 2. 将这些数据存到 ArrayList<StudentEntiry>、
			// 2.注册驱动
			connection = MayiktJdbcUtils.getConnection();
			// 4.获取执行者对象
			statement = connection.createStatement();
			// 5.执行sql语句并返回结果
			String updateStudentSql = "UPDATE stu1 SET name='"+stu.getName()+"', age="+stu.getAge()+",address='"+stu.getAddress()+"' WHERE id="+stu.getId()+"";
			System.out.println("updateStudentSql: "+ updateStudentSql);
			int result = statement.executeUpdate(updateStudentSql);
			// 执行该sql语句，返回影响行数
			return  result;
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(statement, connection);
		}
	}

	public int deleteStudent(Long id){
		if(id == null){
			return 0;
		}
		Connection connection = null;
		Statement statement= null;
		try{
			// 1. java链接mysql数据库查询所有数据
			// 2. 将这些数据存到 ArrayList<StudentEntiry>、
			// 2.注册驱动
			connection = MayiktJdbcUtils.getConnection();
			// 4.获取执行者对象
			statement = connection.createStatement();
			// 5.执行sql语句并返回结果
			String deleteStudentSql = "delete from stu1 where id=" + id;
			System.out.println("deleteStudentSql: "+ deleteStudentSql);
			int result = statement.executeUpdate(deleteStudentSql);
			// 执行该sql语句，返回影响行数
			return  result;
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}finally {
			// jdbc释放资源
			MayiktJdbcUtils.closeConnection(statement, connection);
		}
	}
}
