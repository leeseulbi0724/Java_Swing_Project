package BookSystem;

import java.util.ArrayList;

import BookDAO.BoardDAO;
import BookDAO.BookDAO;
import BookDAO.MemberDAO;
import BookVO.BoardVO;
import BookVO.BookVO;
import BookVO.MemberVO;

public class BookSystem {
		//Field
		public MemberDAO mdao = new MemberDAO();			
		BookDAO bdao = new BookDAO();
		BoardDAO adao = new BoardDAO();
		
		////Constructor
		public BookSystem() {			

		}
		
		//Method
		/** 관리자 - 도서등록 **/
		public boolean Admin_Insert(BookVO vo) {			
			return bdao.getResultInsert(vo);
		}
		
		/** 로그인 **/
		public boolean loginCheck(String id, String pass) {
			return mdao.getLoginResult(id,pass);
		}
		
		/** 회원가입 처리 **/
		public boolean join(MemberVO member) {
				return mdao.getJoinResult(member);
		}
			
		/** 사용자 - 마이페이지 - 정보수정 **/
		public int User_MyPage_Modify(MemberVO vo, String id) {
			return mdao.getModifyResult(vo,id);
		}
		
		/** 관리자 - 회원조회 (회원목록 가져오기) **/
		public ArrayList<MemberVO> Admin_MemberSelect(){
				return mdao.getResultSelect();
		}		
		
		/** 관리자 - 메인화면, 삭제화면 (도서목록가져오기) **/
		public ArrayList<BookVO> Admin_Select() {
			return bdao.getResultSelect();				
		}
		
		/** 관리자 - 도서삭제 **/
		public boolean Admin_Delete(String comboname, String name) {
			return bdao.getResultDelete(comboname, name);
		}
		
		/** 관리자 - 도서검색 **/
		public ArrayList<BookVO> Admin_Search(String comboname, String text) {
			return bdao.getResult(comboname, text);
		}
		
		/** 사용자 - 마이페이지 - 장바구니 내역 불러오기**/
		public ArrayList<BookVO> getBookList(String name) {
			return bdao.getResultBasket(name);
		}
		
		/** 사용자 - 장바구니 담기 **/
		public boolean User_Basket(BookVO vo, String name, boolean result) {
			return bdao.getResult(vo, name, result);
		}
		
		/** 사용자 - 주문조회 후 장바구니 삭제하기 **/
		public boolean User_Basket_Delete(String id, BookVO vo) {
			return bdao.getBasketDelete(id, vo);
		}
		
		/** 사용자 - 장바구니 담기 전 이미 담겨 있는 도서인지 확인 **/
		public boolean Basket_check(BookVO vo, String name) {
			return bdao.getBasketCheck(vo, name);
		}
		
		/** 사용자 - 게시판 내용 저장 **/
		public boolean User_Board(BoardVO vo) {
			return adao.getBoardInsert(vo);
		}
		
		/** 사용자, 관리자 - 게시판 목록 불러오기 **/
		public ArrayList<BoardVO> board_data() {
			return adao.getBoardSelect();
		}
		
		/** 사용자 - 게시판 클릭 시 해당 값 가져오기 **/
		public BoardVO board_result(String bid) {			
			return adao.getBoardResult(bid);			
		}
		
		/** 관리자 - 도서삭제 시 DB에 있는 도서명인지 체크하기 **/
		public ArrayList<BookVO> Book_Equals(String comboname) {
			return bdao.getBookEquals(comboname);
		}
		
		/** 사용자 - 마이페이지 - 주문조회 (select) **/
		public ArrayList<BookVO> getOrderList(String name) {
			return bdao.getResultOrder(name);
		}
		
		/** 사용자 - 마이페아지 - 주문데이터 넘기기 (insert) **/
		public boolean User_Order(String id, BookVO vo) {
			return bdao.getOrder(id, vo);
		}
		
		/** 장바구니 테이블 -> 주문조회 테이블에서 책 정보 가져옴 **/
		public ArrayList<BookVO> getBookinfo(String bookname) {
			return bdao.getResultBookinfo(bookname);
		}
		
		/** 사용자 - 리뷰작성 DB 저장 **/
		public boolean User_ReviewResult(BoardVO vo) {
			return adao.getReviewResult(vo);
		}
		
		/** 사용자 -- 리뷰 작성 전 이미 작성된 리뷰가 있는지 체크 **/
		public boolean ReviewCheck(String bookname, String username) {
			return adao.getReviewCheck(bookname, username);
		}
		
		/** 사용자 - 모든 리뷰정보 가져오기 **/
		public ArrayList<BoardVO> All_Review(String bookname) {
			return adao.getAllReview(bookname);
		}
		
		/** 사용자 - 마이페이지 - My이력 (내가쓴 모든 게시판글) **/
		public ArrayList<BoardVO> All_Myboard(String id) {
			return adao.getMyboard(id);
		}
		
		/** 사용자 - 마이페이지 - My이력 (내가 쓴 모든 리뷰들)  **/
		public ArrayList<BoardVO> All_Myreview(String id) {
			return adao.getMyreview(id);
		}
		
		/** 사용자 - 게시판 댓글 달기 DB 저장 **/
		public boolean board_comment(BoardVO vo) {
			return adao.getBoardComment(vo);
		}
	
		/** 사용자 - 게시판 댓글 불러오기 **/
		public ArrayList<BoardVO> board_comment_select (String bid) {
			return adao.getBoardCommentSelect(bid);
		}		
		
		/** 사용자 - 마이페이지 - 게시판 글 삭제 **/
		public int MyDelete_Board(String bid) {
			return adao.getBoardDelete(bid);
		}
		
		/** 사용자 - 마이페이지 - 리뷰 삭제 **/
		public int MyDelete_Review(String bid) {
			return adao.getReviewDelete(bid);
		}
		
		/** 주문수량 가져오기 **/
		public ArrayList<BookVO> Admin_Count(ArrayList<String> list) {
			return bdao.getCount(list);
		}
		
		/** 사용자 - 회원가입 **/
		public boolean CheckID(String name) {
			return mdao.CheckID(name);
		}
		
		/** 비밀번호찾기 - 회원체크 **/
		public boolean CheckInfo(String name, String birthday, String id) {
			return mdao.CheckInfo(name, birthday, id);
		}
		
		/** 사용자, 관리자 - 게시판 목록 불러오기 **/
		public ArrayList<BoardVO> getBoardSelect() {
			return adao.getBoardSelect();
		}
		
		/** 사용자 - 게시판 클릭 시 해당 게시판 내용 가져오기 **/
		public ArrayList<MemberVO> getResultSelect() {
			return mdao.getResultSelect();
		}
		
		/** 회원검색 **/
		public ArrayList<MemberVO> search(String combo, String name) {
			return mdao.search(combo, name);
		}
		
		/** 마이페이지 - 회원정보 가져오기 **/
		public MemberVO MemberInfo(String name) {
			return mdao.MemberInfo(name);
		}
		
		/** 마이페이지 - 비밀번호 확인 **/
		public boolean CheckPass(String pass, String name) {
			return mdao.CheckPass(pass, name);
		}
		
		/** 도서 구매 순위 구하기 **/
		public ArrayList<BookVO> getRank() {
			return bdao.getRank();
		}
		
		/** 회원 삭제 **/		
		public boolean User_delete(String name) {
			return mdao.getUserDelete(name);
		}
		
		/** 리뷰 - 수정 **/
		public boolean User_ReviewUpdate(String rid, BoardVO vo) {
			return adao.User_ReviewUpdate(rid, vo);
		}
		
		/** 게시판 - 수정 **/
		public boolean User_Board_Update(String bid, BoardVO vo) {
			return adao.User_Board_Update(bid, vo);
		}
		


}
