package BookSystem;

import java.util.ArrayList;

import BookDAO.BookDAO;
import BookDAO.MemberDAO;
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
			
			/** 사용자 - 마이페이지 - 정보수정 **/
			public boolean User_MyPage_Modify(MemberVO vo) {
				return mdao.getModifyResult(vo);
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
			public ArrayList<BookVO> Admin_Search(String text, String name) {
				return bdao.getResult(text, name);
			}
			
			/** 사용자 - 마이페이지 - 장바구니 **/
			public ArrayList<BookVO> getBookList() {
				return bdao.getResultBasket();
			}
			
//			/** 주문수량 가져오기 **/
//			public ArrayList<BookVO> Admin_Count(String name) {
//				System.out.println(bdao.getCount(name));
//				System.out.println("22");
//				return bdao.getCount(name);
//			}
}
