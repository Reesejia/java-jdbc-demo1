package test;
import com.mayikt.entity.StudentEntiry;
import com.mayikt.service.StudentService;
import java.util.ArrayList;
public class StudentTest {
	public static void main(String[] args) {
		StudentService studentService = new StudentService();
		ArrayList<StudentEntiry> studentEntiries = studentService.allStudent();
		for (StudentEntiry stu: studentEntiries){
			System.out.println(stu);
		}
		StudentEntiry student = studentService.getByIdStudent(12l);
		System.out.println(student);


//		StudentService studentService = new StudentService();
//		StudentEntiry studentEntiry = new StudentEntiry("mayikt66", 22, "乡宁");
//		int result  = studentService.insertStudent(studentEntiry);
//		if(result > 0){
//			System.out.println("插入成功");
//		}else {
//			System.out.println("插入失败");
//		}
//		updateTestStudent();
//		deleteStudentTest(12l);
	}
	public static  void updateTestStudent(){
		StudentService studentService = new StudentService();
		Long stuId = 11L;
		StudentEntiry studentEntiry = studentService.getByIdStudent(stuId);
		if(studentService == null){
			 return; // 不会继续 往下执行
		}
		studentEntiry.setName("droden111");
		studentEntiry.setAge(27);
		studentEntiry.setName("shanxi");
		int result = studentService.updateStudent(studentEntiry);
		if(result > 0){
			System.out.println("修改成功");
		}else {
				System.out.println("修改失败");
		}
	}

	public static void  deleteStudentTest(Long id){
		// 1。删除学生信息

		StudentService studentService = new StudentService();
		StudentEntiry student = studentService.getByIdStudent(id);
		if(student ==null){
			System.out.println("数据库中没有该数据 无法删除" + id);
			return;
		}
		int result = studentService.deleteStudent(id);
		if(result > 0){
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}
}
