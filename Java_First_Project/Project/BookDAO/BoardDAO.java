package BookDAO;

import java.util.ArrayList;

import BookVO.BoardVO;
import BookVO.BookVO;

public class BoardDAO extends DBConn {
 	/** 사용자 - 마이페이지 - My이력 (내가 쓴 모든 리뷰들)  **/
 	public ArrayList<BoardVO> getMyreview(String id) {
 		ArrayList<BoardVO> writelist = new ArrayList<BoardVO>();
		
		try {
			String sql = " SELECT BOOKNAME, CONTENT, RDATE FROM BOOK_REVIEW WHERE ID = ? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setCategory("리뷰");
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));
				vo.setDate(rs.getString(3));
				
				writelist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return writelist;
 	}
 	
	/** 사용자 - 마이페이지 - My이력 (내가 쓴 모든 게시판글)  **/
 	public ArrayList<BoardVO> getMyboard(String id) {
 		ArrayList<BoardVO> writelist = new ArrayList<BoardVO>();
		
		try {
			String sql = " SELECT CATEGORY, TITLE, CONTENT, WDATE FROM BOOK_BOARD WHERE ID = ? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setCategory(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setDate(rs.getString(4));
				
				writelist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return writelist;
 	}
 	
	/** 사용자 - 모든 리뷰 가져오기 **/
	public ArrayList<BoardVO> getAllReview(String bookname) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			String sql = " SELECT ID, SCORE, CONTENT FROM BOOK_REVIEW WHERE BOOKNAME = ?";
			getPreparedStatement(sql);
			
			pstmt.setString(1, bookname);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setId(rs.getString(1));
				vo.setScore(rs.getInt(2));
				vo.setContent(rs.getString(3));
				
				list.add(vo);
			}
		} catch (Exception e) {
			
		}
		
		return list;
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
	
	/** 사용자 - 게시판 클릭 시 해당 게시판 내용 가져오기 **/
	public BoardVO getBoardResult(String bid) {
		BoardVO vo = null;
		
		try {
			String sql = "SELECT TITLE, CONTENT, BID FROM BOOK_BOARD WHERE BID = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));	
				vo.setBid(rs.getString(3));
				
			}
			
		} catch (Exception e) {
			
		}			
		return vo;
		
	}
	
	/** 사용자, 관리자 - 게시판 목록 불러오기 **/
	public ArrayList<BoardVO> getBoardSelect() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
		String sql = " SELECT ROWNUM, BID, CATEGORY, TITLE, CONTENT, ID, WDATE FROM"
				+ " (SELECT BID, CATEGORY, TITLE, CONTENT, ID, WDATE FROM BOOK_BOARD ORDER BY WDATE)"
				+ " ORDER BY ROWNUM DESC";
		getPreparedStatement(sql);		
		rs = pstmt.executeQuery();
		while (rs.next()) {
			BoardVO vo = new BoardVO();
			vo.setRownum(rs.getInt(1));
			vo.setBid(rs.getString(2));
			vo.setCategory(rs.getString(3));
			vo.setTitle(rs.getString(4));
			vo.setContent(rs.getString(5));
			vo.setId(rs.getString(6));
			vo.setDate(rs.getString(7));
			
			list.add(vo);
		}
		
		} catch (Exception e) {
			
		}
		return list;
	}
	
	/** 사용자 - 게시판 DB저장 **/
	public boolean getBoardInsert(BoardVO vo) {
		boolean result = false;
		
		try {
			String sql = " INSERT INTO BOOK_BOARD VALUES ('B_'||SEQU_BOOK_BOARD_BID.NEXTVAL,?,?,?,?,SYSDATE)";
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
	
	/** 사용자 - 게시판 댓글 DB저장 **/
	public boolean getBoardComment(BoardVO vo) {
		
		boolean result = false;
		try {
			String sql = " INSERT INTO BOOK_BOARD_COMMENT VALUES (?,?,?,SYSDATE)";
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getBid());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			
			int val = pstmt.executeUpdate();
			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			
		}
		
		return result;
		
	}
	
	/** 사용자 - 게시판 댓글 불러오기 **/
	public ArrayList<BoardVO> getBoardCommentSelect(String bid) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			String sql = " SELECT CID, MENT, ID, CDATE FROM BOOK_BOARD_COMMENT WHERE CID = ? ORDER BY CDATE ";
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBid(rs.getString(1));
				vo.setContent(rs.getString(2));
				vo.setId(rs.getString(3));
				vo.setDate(rs.getString(4));
				
				list.add(vo);				
			}
			
		} catch (Exception e) {
		}
		
		return list;
	}
	
	
	/** 사용자 - 마이페이지 - 게시판 글 삭제 **/
	public int getBoardDelete(String bid) {
		int result = 0;
		
		try {
			
			String sql = "delete from book_board where bid = ? ";	
			
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			
			result = pstmt.executeUpdate();	
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 사용자 - 마이페이지 - 게시판 글 삭제 (데이터 가져오기)  **/
	public ArrayList<BoardVO> getDeleteBoard(String id) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			String sql = " select bid from book_board where id = ? "; 
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBid(rs.getString(1));
				
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
	
}
