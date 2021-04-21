package BookSystem;

import java.util.ArrayList;

import BookDAO.BookDAO;
import BookDAO.MemberDAO;
import BookVO.BoardVO;
import BookVO.BookVO;
import BookVO.MemberVO;

public class BookSystem {
		//Field
		MemberDAO mdao = new MemberDAO();			
		BookDAO bdao = new BookDAO();
		
		//Constructor
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
		public boolean User_MyPage_Modify(MemberVO vo) {
			return mdao.getModifyResult(vo);
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
		public boolean Admin_Delete(String name) {
			return bdao.getResultDelete(name);
		}
		
		/** 관리자 - 도서검색 **/
		public ArrayList<BookVO> Admin_Search(String text) {
			return bdao.getResult(text);
		}
		
		/** 사용자 - 마이페이지 - 장바구니 내역 불러오기**/
		public ArrayList<BookVO> getBookList(String name) {
			return bdao.getResultBasket(name);
		}
		
		/** 사용자 - 장바구니 담기 **/
		public boolean User_Basket(BookVO vo, String name, boolean result) {
			return bdao.getResult(vo, name, result);
		}
		
		/** 사용자 - 장바구니 담기 전 이미 담겨 있는 도서인지 확인 **/
		public boolean Basket_check(BookVO vo, String name) {
			return bdao.getBasketCheck(vo, name);
		}
		
		/** 사용자 - 게시판 내용 저장 **/
		public boolean User_Board(BoardVO vo) {
			return bdao.getBoardInsert(vo);
		}
		
		/** 사용자, 관리자 - 게시판 목록 불러오기 **/
		public ArrayList<BoardVO> board_data() {
			return bdao.getBoardSelect();
		}
		
		/** 사용자 - 게시판 클릭 시 해당 값 가져오기 **/
		public BoardVO board_result(String content) {			
			return bdao.getBoardResult(content);			
		}
		
		/** 관리자 - 도서삭제 시 DB에 있는 도서명인지 체크하기 **/
		public ArrayList<BookVO> Book_Equals() {
			return bdao.getBookEquals();
		}
		
		/** 사용자 - 마이페이지 - 주문조회 (select) **/
		public ArrayList<BookVO> getOrderList(String name) {
			return bdao.getResultOrder(name);
		}
		
		/** 사용자 - 마이페아지 - 주문데이터 넘기기 (insert) **/
		public boolean User_Order(BookVO vo, String name) {
			return bdao.getOrder(vo, name);
		}
		
		/** 사용자 - 리뷰작성 DB 저장 **/
		public boolean User_ReviewResult(BoardVO vo) {
			return bdao.getReviewResult(vo);
		}
		
		/** 사용자 -- 리뷰 작성 전 이미 작성된 리뷰가 있는지 체크 **/
		public boolean ReviewCheck(String bookname, String username) {
			return bdao.getReviewCheck(bookname, username);
		}
		
		/** 사용자 - 모든 리뷰정보 가져오기 **/
		public ArrayList<BoardVO> All_Review(String bookname) {
			return bdao.getAllReview(bookname);
		}
		
		/** 사용자 - 마이페이지 메인화면 비밀번호 확인 후 화면 전환할때 비밀번호 가져오기 **/
		public boolean PassCheck(String name, String pass) {
			return mdao.getPassCheck(name, pass);
		}
			
//			/** 주문수량 가져오기 **/
//			public ArrayList<BookVO> Admin_Count(String name) {
//				System.out.println(bdao.getCount(name));
//				System.out.println("22");
//				return bdao.getCount(name);
//			}

}
