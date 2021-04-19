package BookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	//Field
	String url = "jdbc:oracle:thin:@127.0.0.1:1522";
	String user = "scott";
	String pass = "tiger";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//Constructor
	public DBConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1단계 성공");
			
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("2단계 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	/** PreparedStatement 생성 **/
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

