package test;

import com.mayikt.entity.UserEntity;
import com.mayikt.service.UserService;

import java.util.Scanner;

public class UserTest {
	static private UserService userService = new UserService();

	public static void main(String[] args) {
		UserTest userTest = new UserTest();
		userTest.index();
	}

	 public void  index(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用1:用户注册");
		System.out.println("请输入2:用户登录");
		int num = scanner.nextInt();
		if(num  == 1){
			registerUser();
		}
		if(num  == 2){
			registerLogin();
		}

	}
	static public void registerUser(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户的手机号码");
		String phone = scanner.nextLine();

		System.out.println("请输入用户的密码");
		String pwd = scanner.nextLine();

		// 值得学习，这里需要封装成UserEntity对象，需要构造函数
		UserEntity userEntity2 = new UserEntity(phone,pwd);
		int result = userService.registUser(userEntity2);
		if(result > 0){
			System.out.println("用户注册成功");
		}else {
			System.out.println("用户注册失败");
		}
	}

	static public void registerLogin(){
		for (int i =0;i < 3; i++){
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入用户的手机号码");
			String phone = scanner.nextLine();

			System.out.println("请输入用户的密码");
			String pwd = scanner.nextLine();

			// 值得学习，这里需要封装成UserEntity对象，需要构造函数
			UserEntity userEntity2 = new UserEntity(phone,pwd);
			UserEntity result = userService.login(userEntity2);
			if(result != null){
				System.out.println("用户登陆成功");
				return;
			}else {
				System.out.println("用户登陆失败,手机号或密码不正确，错误次数为"+ i +"次");
				if(i==2){
					System.out.println("错误次数超过" + i + 1+"次");
				}
			}
		}
	}
}
