package test;

import com.mayikt.entity.StudentEntiry;
import com.mayikt.service.StudentService;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.Scanner;

public class IndexTest {

	public static void main(String[] args) {
		mainMenu();
	}
	// 定义主菜单程序的入口
	public  static  void mainMenu(){
		while (true){
			System.out.println("欢迎主菜单");
			System.out.println("1.查询所有学生信息");
			System.out.println("2.根据学生id查询学生信息");
			System.out.println("3.新增学生信息");
			System.out.println("4.根据学生id修改学生信息");
			System.out.println("5.根据学生id删除学生信息");
			System.out.println("请选择对应的序号");
			Scanner scanner = new Scanner(System.in);
			int result = scanner.nextInt();
			switch (result){
				case 1:
					showAllStudent();
					break; // 如果是return  会将while循环退出
				case 2:
					findByIdStudent();
					break;
				case 3:
					insertStudent();
					break;
					case 4:
						updateByIdStudent();
					break;
				case 5:
					deleteStudent();
					break;

			}
		}
	}

	public static void  findByIdStudent(){
		System.out.println("根据id查学生信息");
		Scanner scanner= new Scanner(System.in);
		Long result = scanner.nextLong();
		// 根据用户输入的学生id查询学生信息
		StudentService studentService = new StudentService();
		StudentEntiry student= studentService.getByIdStudent(result);
		if(student == null){
			System.out.println("根据id查不到学生信息");
			return;
		}
		System.out.println("学生信息： " + student);
	}

	// 查询所有学生信息
	public static void showAllStudent(){
		System.out.println("查询到的所有学生信息");
		StudentService studentService = new StudentService();
		ArrayList<StudentEntiry> studentEntiries= studentService.allStudent();
		for(StudentEntiry stu: studentEntiries ){
			System.out.println(stu); // 底层封装好了，默认会调用对象的toString方法
		}
	}

	public  static void deleteStudent(){
		StudentService studentService =  new StudentService();
		System.out.println("请输入要删除的学生id");
		Scanner scanner = new Scanner(System.in);
		Long result = scanner.nextLong();
		int res = studentService.deleteStudent(result);
		if(res > 0){
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}

	// 插入我们的学生信息
	public static void insertStudent(){
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入学生的id:" );
		Long id = sc.nextLong();

		System.out.println("请输入学生的name:" );
		String name = sc.nextLine();

		System.out.println("请输入学生的年龄" );
		Integer age = sc.nextInt();

		System.out.println("请输入学生的地址" );
		sc.nextLine(); // 跳过
		String address = sc.nextLine();

		StudentService studentService = new StudentService();
		StudentEntiry studentEntiry = new StudentEntiry(name, age, address);
		int result = studentService.insertStudent(studentEntiry);
		if(result >0){
			System.out.println("插入学生信息成功");
		}else {
			System.out.println("插入学生信息失败");
		}
	}

	public static void updateByIdStudent(){
		// 需要先根据学生的id查询该学生信息，如果查询到了再去修改
		System.out.println("输入学生信息id");
		Scanner sc = new Scanner(System.in);
		Long id = sc.nextLong();
		StudentService studentService = new StudentService();
		 StudentEntiry student =studentService.getByIdStudent(id);
		 if(student == null){
			 System.out.println("该学生不存在");
			 return;
		 }else {
			 Scanner scanner = new Scanner(System.in);
			 System.out.println("请输入学生的name:" );
			 String name = scanner.nextLine();

			 System.out.println("请输入学生的年龄" );
			 Integer age = scanner.nextInt();

			 System.out.println("请输入学生的地址" );
			 scanner.nextLine(); // 跳过
			 String address = scanner.nextLine();
			 StudentEntiry stu = new StudentEntiry(id, name, age, address);
			 int result = studentService.updateStudent(stu);
			 if(result > 0){
				 System.out.println("修改成功" + stu);
			 }else {
				 System.out.println("修改失败");
			 }
		 }
	}
}
