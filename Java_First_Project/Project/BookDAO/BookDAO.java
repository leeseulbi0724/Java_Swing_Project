package BookDAO;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import BookVO.BoardVO;
import BookVO.BookVO;
import Commons.Commons;

public class BookDAO extends DBConn {
	
	
	/** 관리자 - 도서 등록 **/
	public boolean getResultInsert(BookVO vo) {
		boolean result = false;
		try {
			String sql = "Insert Into BOOK_DATA values(?,?,?,?,?,?,SYSDATE)";
			getPreparedStatement(sql);
			
			pstmt.setInt(1, vo.getBno());
			pstmt.setString(2, vo.getBookname());
			pstmt.setString(3, vo.getAuthor());
			pstmt.setString(4, vo.getPblsh());
			pstmt.setInt(5, vo.getPrice());
			pstmt.setString(6, vo.getPblshdate());
			
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
			if(flag==false) sql = "SELECT*FROM BOOK_DATA WHERE BOOKNAME='정보처리기사'";	
			else if(flag==true) sql = "SELECT*FROM BOOK_DATA WHERE BOOKNAME='언어의온도'";	
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
					 			+ " from BOOK_DATA" + " ORDER BY SDATE DESC";			
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
	public boolean getResultDelete(String name) {
		boolean result = false;		
		try {			
			String sql = " DELETE FROM BOOK_DATA WHERE BOOKNAME = ?";				
			getPreparedStatement(sql);
			pstmt.setString(1, name);						
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("삭제하실 수 없는 도서입니다."));
		}		
		return result;
	}
	
	public ArrayList<BookVO> getBookEquals() {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			String sql = "SELECT BOOKNAME FROM BOOK_DATA";
			getPreparedStatement(sql);
			
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
	public ArrayList<BookVO> getResult(String text) {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		String sql;
		try {		
			sql = "SELECT BNO,BOOKNAME,AUTHOR,PBLSH,PRICE,PBLSHDATE FROM BOOK_DATA WHERE BOOKNAME = ? ";			
			getPreparedStatement(sql);
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
	public boolean getResult(BookVO vo, String name) {
		boolean result = false;
		
		try {
			String sql = "INSERT INTO BOOK_USER_BASKET VALUES (?,?,?,?) ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, vo.getBookname());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setInt(4, vo.getCount());
			
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/** 사용자 - 게시판 DB저장 **/
	public boolean getBoardInsert(BoardVO vo) {
		boolean result = false;
		
		try {
			String sql = " INSERT INTO BOOK_BOARD VALUES (?,?,?,?,SYSDATE)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getId());
			
			int val = pstmt.executeUpdate();
			if (val != 0 ) {
				result = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/** 사용자, 관리자 - 게시판 목록 불러오기 **/
	public ArrayList<BoardVO> getBoardSelect() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
		String sql = " SELECT ROWNUM, CATEGORY, TITLE, CONTENT, ID, WDATE FROM"
				+ " (SELECT CATEGORY, TITLE, CONTENT, ID, WDATE FROM BOOK_BOARD ORDER BY WDATE)"
				+ " ORDER BY ROWNUM DESC";
		getPreparedStatement(sql);		
		rs = pstmt.executeQuery();
		while (rs.next()) {
			BoardVO vo = new BoardVO();
			vo.setRownum(rs.getInt(1));
			vo.setCategory(rs.getString(2));
			vo.setTitle(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setId(rs.getString(5));
			vo.setDate(rs.getString(6));
			
			list.add(vo);
		}
		
		} catch (Exception e) {
			
		}
		return list;
	}
	
	/** 사용자 - 게시판 클릭 시 해당 게시판 내용 가져오기 **/
	public BoardVO getBoardResult(String content) {
		BoardVO vo = null;
		
		try {
			String sql = "SELECT TITLE, CONTENT FROM BOOK_BOARD WHERE CONTENT = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, content);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));		
				
			}
			
		} catch (Exception e) {
			
		}			
		return vo;
		
	}
	
	
	/** 사용자 - 마이페이지 - 주문 데이터 넘기기 **/
	public ArrayList<BookVO> getResultOrder(String name) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();
		
		try {
			String sql = " select bookname, author, pblsh, price from BOOK_USER_ORDER where id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookname(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setPblsh(rs.getString(3));
				book.setPrice(rs.getInt(4));
				
				booklist.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return booklist;
	}
	
	public boolean getOrder(BookVO vo, String name) {
		boolean result = false;
		
		try {
			String sql = " insert into BOOK_USER_ORDER values(?,?,?,?,?, sysdate) "; 
			getPreparedStatement(sql);
			
			pstmt.setString(1, name);		//id받아서 넣기
			pstmt.setString(2, vo.getBookname());
			pstmt.setString(3, vo.getAuthor());
			pstmt.setString(4, vo.getPblsh());
			pstmt.setInt(5, vo.getPrice());
			
			int val = pstmt.executeUpdate();
			if (val != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 사용자 - 리뷰 등록 DB저장 **/
	public boolean getReviewResult(BoardVO vo) {
		boolean result = false;
		try {
			String sql = " INSERT INTO BOOK_REVIEW VALUES(?,?,?,?,SYSDATE)";
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setInt(4, vo.getScore());
			
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			
		}
		return result;
	}
	
	/** 사용자 - 리뷰 작성 전 이미 작성된 리뷰가 있는지 체크 **/
	public boolean getReviewCheck(String bookname, String username) {
		boolean result = true;
			try {
				String sql = " SELECT BOOKNAME FROM BOOK_REVIEW WHERE BOOKNAME = ? AND ID = ?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, bookname);
				pstmt.setString(2, username);
				
				rs = pstmt.executeQuery();		
				while (rs.next()) {
					BoardVO vo = new BoardVO();
					vo.setTitle(rs.getString(1));
					if (vo != null) {
						result = false;
					}
				}
				
			} catch (Exception e) {
				
			}
			
			return result;
			
	}
 	
	
	/** 수량 가져오기 **/
//	public ArrayList<BookVO> getCount(String name) {
//		ArrayList<BookVO> list = new ArrayList<BookVO>();
//		try {
//			String sql = " SELECT BOOKNAME, COUNT(BOOKNAME)" +
//					" FROM BOOK_USER_ORDER"  +
//					" WHERE BOOKNAME = ?"
//					+ " GROUP BY BOOKNAME";
//			getPreparedStatement(sql);					
//			pstmt.setString(1, name);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BookVO vo = new BookVO();
//				vo.setBookname(rs.getString(1));
//				vo.setCount(rs.getInt(2));					
//				list.add(vo);
//			}		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}

	
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
