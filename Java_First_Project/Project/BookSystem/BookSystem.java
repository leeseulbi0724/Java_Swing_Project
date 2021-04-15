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
			
			/** 사용자 - 마이페이지 - 정보수정 **/
			public boolean User_MyPage_Modify(MemberVO vo) {
				return mdao.getModifyResult(vo);
			}
			
}
