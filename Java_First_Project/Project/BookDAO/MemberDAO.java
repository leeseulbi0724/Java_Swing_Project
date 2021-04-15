package BookDAO;

import BookVO.MemberVO;

public class MemberDAO extends DBConn{
	
	/** User_MyPage_Usermodify (사용자 - 회원정보수정) **/
	public boolean getModifyResult(MemberVO member) {
		// id 비교해서 맞으면 비밀번호, 폰번호, 주소 수정
		boolean result = false;
		
		try {
			String sql = " update book_users set pass = ?, hp = ?, addr = ? where id = ? "; //id 비교? pass 비교?
			getPreparedStatement(sql);
			
			pstmt.setString(1, member.getPass());
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getAddr());
			pstmt.setString(4, member.getId());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
}
