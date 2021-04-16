package BookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BookVO.MemberVO;

public class MemberDAO extends DBConn {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521";
	String user = "scott";
	String pass = "tiger";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("11------------>>");
			
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("22------------>>");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 로그인 처리 **/
	public boolean getLoginResult(String id, String pass) {
		boolean result = false;
		
		try {
			String sql = " select count(*) from book_users where id=? and pass=? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)==1) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/** 회원가입 처리 **/
	public boolean getJoinResult(MemberVO member) {
		boolean result = false;
		
		try {
			String sql = " insert into book_users values(?,?,?,?,?,?,sysdate) ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getHp());
			pstmt.setString(6, member.getAddr());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt !=  null) pstmt.close();
			if(conn != null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
