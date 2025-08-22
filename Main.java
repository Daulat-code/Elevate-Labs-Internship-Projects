package JDBCpackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) {
		try {
			String url="jdbc:mysql://localhost:3306/";
			String userName ="root";
			String password= "glcpl194";
//			Class.forName("oracle.jdbc.driver.oracleDriver");
			Connection con = DriverManager.getConnection(url, userName, password);
			System.out.println("Succesfull");
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}

	}

}
