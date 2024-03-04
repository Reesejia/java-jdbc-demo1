package com.mayikt.service;
import com.mayikt.dao.StudentDao;
import com.mayikt.entity.StudentEntiry;
import java.util.ArrayList;

public class StudentService {
	private StudentDao studentDao =  new StudentDao();
	public ArrayList<StudentEntiry> allStudent(){
		// 在通过业务逻辑层调用dao层代码
		ArrayList<StudentEntiry> studentEntiries =  studentDao.allStudent();
		// 对studentEntiries脱敏操作，脱敏完返回
		return studentEntiries;
	}
	public StudentEntiry getByIdStudent(Long stuId){
			return  studentDao.getByIdStudent(stuId);
	}

	public int insertStudent (StudentEntiry stu){
		return  studentDao.insertStudent(stu);
	}

	public  int updateStudent(StudentEntiry stu){
		return studentDao.updateStudent(stu);
	}

	public  int deleteStudent(Long id){
		return studentDao.deleteStudent(id);
	}
}
