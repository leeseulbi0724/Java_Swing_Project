package BookDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookListDAO {
	//Field
	
	//Constructor
	
	//Method
	
	//데이터베이스 연결
	public static Connection getConnection() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521";
		String user = "scott";
		String pass = "tiger";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,pass);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}//getConnection
	
	//데이터베이스 데이터 불러오기
	public static String[][] getBookList(){
		try {
			Connection conn = getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT ISBN, NAME, AUTHOR, PUBLISHER FROM BOOKLIST");
			
			ResultSet result = st.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			
			while(result.next()) {
				list.add(new String[] {
						result.getString("ISBN"),
						result.getString("NAME"),
						result.getString("AUTHOR"),
						result.getString("PUBLISHER"),
				});
			}
			
			String[][] array = new String[list.size()][4];
			return list.toArray(array);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}//getBookList
	
	
}
