package BookDAO;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import BookUI.Admin_MainUI;
import BookUI.Admin_MemberViewsUI;
import BookVO.BookVO;
import BookVO.MemberVO;
import Commons.Commons;

public class MemberDAO extends DBConn {
		
	/** 로그인 처리 **/
	public boolean getLoginResult(String id, String pass) {
		boolean result = false;
		
		try {
			String sql = " select count(*) from book_users where id=? and pass=? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)==1) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/** 회원가입 처리 **/
	public boolean getJoinResult(MemberVO member) {
		boolean result = false;
		
		try {
			String sql = " insert into book_users values(?,?,?,?,?,?,sysdate) ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getHp());
			pstmt.setString(6, member.getAddr());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 회원검색 **/
	public ArrayList<MemberVO> search(String category, String value) {
	ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			String sql = " SELECT ROWNUM RNO, ID, PASS, NAME, BIRTHDAY, HP, ADDR  "
					+ " FROM BOOK_USERS WHERE ";
		
			if(category.equals("아이디")) {
				sql = sql + " ID = ?";
			}else if(category.equals("이름")) {				
				sql = sql + " NAME = ?";
			}
			
			getPreparedStatement(sql);
			pstmt.setString(1, value);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setRno(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setHp(rs.getString(6));
				member.setAddr(rs.getString(7));
				list.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/** 관리자 회원조회 - 회원목록 가져오기 **/
	public ArrayList<MemberVO> getResultSelect(){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			
		try {
			String sql = " SELECT ROWNUM RNO, ID, PASS, NAME, BIRTHDAY, HP, ADDR " 
						+ " FROM (SELECT ID,PASS,NAME,BIRTHDAY,HP,ADDR FROM BOOK_USERS ORDER BY ID DESC) WHERE ID != '관리자' AND ID != 'admin'  ";
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setRno(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setHp(rs.getString(6));
				member.setAddr(rs.getString(7));
				list.add(member);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return list;
	}
	
	/** 관리자 - 회원삭제 **/
	public boolean delete(String id) {
		boolean result = false;		
		try {
			String sql = " DELETE FROM BOOK_USERS WHERE ID = ?  ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);			
			int val = pstmt.executeUpdate();		
			if (val != 0) {
				result = true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("삭제할 수 없는 회원입니다."));
		} 
		return result;
	}
	
	/** 회원가입 - ID 중복체크 **/
	public boolean CheckID(String id) {
		
		try {
			String sql = " select count(*) cnt from book_users where id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt>0) {
					return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/** 비밀번호 찾기 정보 인증 **/
	public boolean CheckInfo(String name, String birthday, String id) {
		boolean result = false;
		
		try {
			String sql = " select count(*) cnt from book_users where name = ? and birthday = ? and id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birthday);
			pstmt.setString(3, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cnt = rs.getInt(1);
				System.out.println(cnt);
				if(cnt>0) {
					result =  true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/** 비밀번호 찾이 임시비밀번호로 변경 **/
	public boolean getUpdatePassResult(String pass, String name, String birthday, String id) {
		boolean result = false;
		
		try {
			String sql = " update book_users set pass = ? where name = ? and birthday = ? and id = ? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, birthday);
			pstmt.setString(4, id);
			
			int val = pstmt.executeUpdate();
			if(val != 0 ) result = true;
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	/** 마이페이지 - 비밀번호 확인 **/
	public boolean CheckPass(String pass,String id) {
		
		try {
			String sql = " select count(*) cnt from book_users where pass = ? and id = ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt>0) {
					return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/** 마이페이지 - 회원정보 가져오기 **/
	public MemberVO MemberInfo(String id) {
		MemberVO member = new MemberVO();
		try {
			String sql = " select name, id, birthday, hp, addr from book_users where id = ?  ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				member.setName(rs.getString(1));
				member.setId(rs.getString(2));
				member.setBirthday(rs.getString(3));
				member.setHp(rs.getString(4));
				member.setAddr(rs.getString(5));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
		
		
	}

	/** User_MyPage_Usermodify (사용자 - 회원정보수정) **/
	public int getModifyResult(MemberVO member,String id) {
		// id 비교해서 맞으면 비밀번호, 폰번호, 주소 수정
		int result = 0;
		
		try {
			
			String sql =  " update book_users "
					+ " set pass = ?, hp=?, addr=? "
					+ " where id =?"; //id 비교? pass 비교?
			getPreparedStatement(sql);
			pstmt.setString(1, member.getPass());
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getAddr());
			pstmt.setString(4, id);
			
		
			int val = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
		
	public void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt !=  null) pstmt.close();
			if(conn != null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
