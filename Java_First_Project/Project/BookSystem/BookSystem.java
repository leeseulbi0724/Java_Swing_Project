package BookSystem;

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
		
		/** 로그인 **/
		public boolean loginCheck(String id, String pass) {
			return mdao.getLoginResult(id,pass);
		}
		
		/** 회원가입 처리 **/
		public boolean join(MemberVO member) {
				return mdao.getJoinResult(member);
		}
}
