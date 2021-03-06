package BookDAO;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import BookVO.BoardVO;
import BookVO.BookVO;
import BookVO.MemberVO;
import Commons.Commons;

public class BookDAO extends DBConn {
	
	
	/** 관리자 - 도서 등록 **/
	public boolean getResultInsert(BookVO vo) {
		boolean result = false;
		try {
			String sql = "Insert Into BOOK_DATA values(SEQU_BOOK_DATA_BNO.NEXTVAL,?,?,?,?,?,SYSDATE)";
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getBookname());
			pstmt.setString(2, vo.getAuthor());
			pstmt.setString(3, vo.getPblsh());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setString(5, vo.getPblshdate());
			
			int val = pstmt.executeUpdate();
			if (val !=0 ) {
				result = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	

	/** 사용자 - 메인 추천도서 텍스트 받아오기 **/
	public String[][] recommendList(boolean flag) {		
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql;
		
		try {
			if(flag==false) sql = "SELECT*FROM BOOK_DATA WHERE BOOKNAME='죽고 싶지만 떡볶이는 먹고 싶어'";	
			else if(flag==true) sql = "SELECT*FROM BOOK_DATA WHERE BOOKNAME='나는 나로 살기로 했다'";	
			else sql = null;
			
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new String[] {
				String.valueOf(rs.getInt("BNO")),
				rs.getString("BOOKNAME"),
				rs.getString("AUTHOR"),
				rs.getString("PBLSH"),
				String.valueOf(rs.getInt("PRICE")),
				rs.getString("PBLSHDATE")
				});
			}
			
			String[][] array = new String[list.size()][6];
			return list.toArray(array);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/** 관리자, 사용자 - 메인화면, 삭제화면 (도서목록불러오기) **/
	public ArrayList<BookVO> getResultSelect() {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			String sql = " Select BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE"
					 			+ " from BOOK_DATA" + " ORDER BY BNO";				
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookVO vo = new BookVO();
				vo.setBno(rs.getInt(1));
				vo.setBookname(rs.getString(2));
				vo.setAuthor(rs.getString(3));
				vo.setPblsh(rs.getString(4));
				vo.setPrice(rs.getInt(5));
				vo.setPblshdate(rs.getString(6));
				
				list.add(vo);			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return list;
	}
	
	
	/** 관리자 - 도서삭제 **/
	public boolean getResultDelete(String comboname, String name) {
		boolean result = false;		
		try {			
			if (comboname == "도서번호") {
				String sql = " DELETE FROM BOOK_DATA WHERE BNO = ?";				
				getPreparedStatement(sql);				
			} else {
				String sql = " DELETE FROM BOOK_DATA WHERE BOOKNAME = ?";				
				getPreparedStatement(sql);				
			}
			pstmt.setString(1, name);						
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			
		}		
		return result;
	}
	
	/** 도서 검색 시 같은 도서번호 or 도서명 조회 **/
	public ArrayList<BookVO> getBookEquals(String comboname) {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			if (comboname == "도서번호") {
				String sql = "SELECT BNO FROM BOOK_DATA";				
				getPreparedStatement(sql);
			} else {
				String sql = "SELECT BOOKNAME FROM BOOK_DATA";	
				getPreparedStatement(sql);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookname(rs.getString(1));	
				
				list.add(vo);
			}
		} catch (Exception e) {
			
		}
		return list;
		
	}
	
	/** 관리자 - 도서조회 **/
	public ArrayList<BookVO> getResult(String comboname, String text) {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {		
			if (comboname == "도서번호") {
				String sql = "SELECT BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE FROM BOOK_DATA WHERE BNO = ? ";		
				getPreparedStatement(sql);
			} else {
				String sql = "SELECT BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE FROM BOOK_DATA WHERE BOOKNAME = ? ";		
				getPreparedStatement(sql);
			}			
			pstmt.setString(1, text);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BookVO vo = new BookVO();
				vo.setBno(rs.getInt(1));
				vo.setBookname(rs.getString(2));
				vo.setAuthor(rs.getString(3));
				vo.setPblsh(rs.getString(4));
				vo.setPrice(rs.getInt(5));
				vo.setPblshdate(rs.getString(6));	
				
				System.out.println(vo.getBookname());
				list.add(vo);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	
	/** 사용자 - 마이페이지 - 장바구니조회 **/
	public ArrayList<BookVO> getResultBasket(String name) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();
		
		try {
			String sql = " select bookname, price, count from BOOK_USER_BASKET where id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookname(rs.getString(1));
				book.setPrice(rs.getInt(2));
				book.setCount(rs.getInt(3));
				
				booklist.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booklist;
	}
	
	
	/** 사용자 - 장바구니 담기 **/
	public boolean getResult(BookVO vo, String name, boolean check_result) {
		boolean result = false;
		boolean result_2 = check_result;		
		try {
			if (result_2) {				
				String sql = "UPDATE BOOK_USER_BASKET SET COUNT = COUNT+? WHERE BOOKNAME = ? AND ID = ?";
				getPreparedStatement(sql);
				pstmt.setInt(1, vo.getCount());
				pstmt.setString(2, vo.getBookname());
				pstmt.setString(3, name);
			} else {
				String sql = "INSERT INTO BOOK_USER_BASKET VALUES (?,?,?,?) ";
				getPreparedStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, vo.getBookname());
				pstmt.setInt(3, vo.getPrice());
				pstmt.setInt(4, vo.getCount());
			}						
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/** 사용자 - 장바구니 담기전 이미 담겨있는 도서인지 체크 **/
	public boolean getBasketCheck(BookVO vo, String name) {
		boolean result = false;
		
		try {
			String sql = " SELECT BOOKNAME FROM BOOK_USER_BASKET WHERE BOOKNAME = ? AND ID = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBookname());
			pstmt.setString(2, name);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookname(rs.getString(1));			
				if (book != null) {
					result = true;
				}
			}
		} catch (Exception e) {
			
		}
		return result;
	}
	
	
	/** 사용자 - 마이페이지 - 주문조회  **/
	public ArrayList<BookVO> getResultOrder(String name) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();
		
		try {
			String sql = " select rownum, bookname, author, pblsh, price, count, orderdate from " 
					+ "(select bookname, author, pblsh, price, count, orderdate from BOOK_USER_ORDER  where id = ? order by orderdate desc) ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBno(rs.getInt(1));
				book.setBookname(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPblsh(rs.getString(4));
				book.setPrice(rs.getInt(5));
				book.setCount(rs.getInt(6));
				book.setPblshdate(rs.getString(7));
				
				booklist.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return booklist;
	}
	
	/** 사용자 - 주문조회 후 장바구니 삭제 하기 **/
	public boolean getBasketDelete(String id, BookVO vo) {
		boolean result = false;
		try {
			String sql = " DELETE FROM BOOK_USER_BASKET WHERE ID = ? AND BOOKNAME = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, vo.getBookname());
						
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result =true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	/** 사용자 - 마이페아지 - 주문데이터 넘기기 **/
	public boolean getOrder(String id, BookVO vo) {
		boolean result = false;
		
		try {
			String sql = " insert into BOOK_USER_ORDER values(?,?,?,?,?, sysdate, ?) "; 
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);		//id받아서 넣기
			pstmt.setString(2, vo.getBookname());
			pstmt.setString(3, vo.getAuthor());
			pstmt.setString(4, vo.getPblsh());
			pstmt.setInt(5, vo.getPrice());
			pstmt.setInt(6, vo.getCount());
			
			int val = pstmt.executeUpdate();
			if (val != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 장바구니 테이블 -> 주문조회 테이블에서 책 정보 가져옴 **/
	public ArrayList<BookVO> getResultBookinfo(String bookname) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();		
		try {
			String sql = " select bookname, author, pblsh, price from book_data where bookname = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, bookname);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookname(rs.getString(1));
				vo.setAuthor(rs.getString(2));
				vo.setPblsh(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				
				booklist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return booklist;
	}	

	
	/** 수량 가져오기 **/
	public ArrayList<BookVO> getCount(ArrayList<String> nlist) {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			String sql = " SELECT BOOKNAME, SUM(COUNT)" +
					" FROM BOOK_USER_ORDER"
					+ " GROUP BY BOOKNAME";
			getPreparedStatement(sql);		
		
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookVO book = new BookVO();
				book.setBookname(rs.getString(1));
				book.setCount(rs.getInt(2));		
				
				list.add(book);
				
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/** 도서 구매 순위 구하기 **/
	public ArrayList<BookVO> getRank() {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			String sql = " SELECT BOOKNAME, RANK() OVER(ORDER BY COUNT DESC) RANK, SUM(COUNT) COUNT"+ 
					" FROM (" + 
					" SELECT BOOKNAME, SUM(COUNT) COUNT" + 
					" FROM (" + 
					" SELECT BOOKNAME, SUM(COUNT) COUNT" + 
					" FROM BOOK_USER_ORDER" + 
					" GROUP BY BOOKNAME, COUNT)" + 
					" GROUP BY BOOKNAME)"
					+ " GROUP BY BOOKNAME, COUNT";
			getPreparedStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookVO book = new BookVO();
				book.setBookname(rs.getString(1));
				book.setBno(rs.getInt(2));
				book.setCount(rs.getInt(3));
				
				list.add(book);
			}
			
		} catch (Exception e) {
		}
		return list;
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
