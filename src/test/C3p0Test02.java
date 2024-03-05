package test;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
public class C3p0Test02 {
	public static void main(String[] args) throws SQLException {
		// 1.创建c3p0数据库连接池  读取c3p0-config 配置文件； initialPoolSize,初始化5个链接，保持链接状态
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		for(int i =1;i <=20;i ++){
			// 从数据库连接池中获取链接
			Connection connection = comboPooledDataSource.getConnection();
			System.out.println("第" + i + connection);
			if(i == 10){
				connection.close(); // i=10,释放链接，归还到连接池，下面的的请求可以从链接池里面获取链接
			}
		}
	}
}
