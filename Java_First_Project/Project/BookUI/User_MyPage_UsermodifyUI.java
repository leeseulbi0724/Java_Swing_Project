package BookUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookDAO.MemberDAO;
import BookSystem.BookSystem;
import BookVO.MemberVO;
import Commons.Commons;
import oracle.sql.ARRAY;

public class User_MyPage_UsermodifyUI implements ActionListener {
	User_MyPageUI main;
	JButton btn_modify_check;
	JTextField name_text,id_text, birthday_text, password_tf, hp_text, addr_text;
	BookSystem system;
	String name;
	MemberDAO mdao = new MemberDAO();
	
	
	public User_MyPage_UsermodifyUI(User_MyPageUI main) {
		this.main = main;
		this.system = main.system;
		this.name = main.user_name;
		init();
	}
	
	public User_MyPage_UsermodifyUI() {
		init();
	}
	
	public void init() {
		main.switching(User_MyPageUI.Usermodify);
		
		name_text = new JTextField();
		id_text = new JTextField();
		birthday_text = new JTextField();
		name_text.setText(mdao.MemberInfo(main.user_name).getName());
		id_text.setText(mdao.MemberInfo(main.user_name).getId());
		birthday_text.setText(mdao.MemberInfo(main.user_name).getBirthday());
		
		
		
		
		
		
		
		JLabel name_label = new JLabel("이름");
		name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		name_label.setBounds(163, 75, 39, 15);
		main.content_panel.add(name_label);	
		
		name_text.setBounds(210, 73, 153, 21);
		main.content_panel.add(name_text);
		name_text.setEditable(false);

		JLabel id_label = new JLabel("아이디");
		id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		id_label.setBounds(163, 105, 39, 15);
		main.content_panel.add(id_label);	
		
		id_text.setColumns(10);
		id_text.setBounds(210, 103, 153, 21);
		main.content_panel.add(id_text);
		id_text.setEditable(false);
		
		JLabel birthday_label = new JLabel("생년월일");
		birthday_label.setHorizontalAlignment(SwingConstants.RIGHT);
		birthday_label.setBounds(145, 135, 57, 15);
		main.content_panel.add(birthday_label);		
		
		birthday_text.setColumns(10);
		birthday_text.setBounds(210, 133, 153, 21);
		main.content_panel.add(birthday_text);
		birthday_text.setEditable(false);
		
		JLabel password_label = new JLabel("비밀번호");
		password_label.setHorizontalAlignment(SwingConstants.RIGHT);
		password_label.setBounds(145, 165, 57, 15);
		main.content_panel.add(password_label);		
		password_tf = new JTextField();
		password_tf.setColumns(10);
		password_tf.setBounds(210, 163, 153, 21);
		main.content_panel.add(password_tf);
		
		
		JLabel hp_label = new JLabel("휴대폰번호");
		hp_label.setHorizontalAlignment(SwingConstants.RIGHT);
		hp_label.setBounds(134, 193, 68, 15);
		main.content_panel.add(hp_label);	
		hp_text = new JTextField();
		hp_text.setColumns(10);
		hp_text.setBounds(210, 193, 153, 21);
		main.content_panel.add(hp_text);
		
		
		JLabel addr_label = new JLabel("주소");
		addr_label.setHorizontalAlignment(SwingConstants.RIGHT);
		addr_label.setBounds(163, 223, 39, 15);
		main.content_panel.add(addr_label);		
		
		addr_text = new JTextField();
		addr_text.setColumns(10);
		addr_text.setBounds(210, 223, 153, 21);
		main.content_panel.add(addr_text);
		
		JLabel title_label = new JLabel("정 보 수 정");
		addr_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(250, 46, 133, 15);
		main.content_panel.add(title_label);
		
		btn_modify_check = new JButton(" 수 정 ");
		btn_modify_check.setHorizontalAlignment(SwingConstants.CENTER);
		btn_modify_check.setBounds(218, 253, 133, 15);
		main.content_panel.add(btn_modify_check);
		
		btn_modify_check.addActionListener(this);
		
		/** 폰트설정 **/
		title_label.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont());
		name_text.setFont(Commons.getFont());
		id_label.setFont(Commons.getFont());
		id_text.setFont(Commons.getFont());
		password_label.setFont(Commons.getFont());
		password_tf.setFont(Commons.getFont());
		birthday_label.setFont(Commons.getFont());
		birthday_text.setFont(Commons.getFont());
		hp_label.setFont(Commons.getFont());
		hp_text.setFont(Commons.getFont());
		addr_label.setFont(Commons.getFont());
		addr_text.setFont(Commons.getFont());
		title_label.setFont(Commons.getFont());
		btn_modify_check.setFont(Commons.getFont());
		
	} //init
	
	@Override
	/** [수정하기] 버튼 누르면 행해지는 액션이벤트 **/
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(btn_modify_check)) {
			int result = JOptionPane.showConfirmDialog(null, "수정을 완료하시겠습니까?");
			if(result == 0) {
				modify_proc();
			}
		}
	}
	
	/** 수정완료 시 **/
	public void modify_proc() {
		if(form_check()) {
			MemberVO vo = new MemberVO();
			vo.setPass(password_tf.getText());
			vo.setHp(hp_text.getText());
			vo.setAddr(addr_text.getText());
			int result = main.system.User_MyPage_Modify(vo,password_tf.getText());
			
			if(result == 0) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("수정이 완료되었습니다"));
				resetTf_proc();
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("수정에 실패하였습니다"));
			}
		}
	}
	
	public void resetTf_proc() {
		password_tf.setText("");
		hp_text.setText("");
		addr_text.setText("");
	}
	

	
	/** 수정하는 tf(비밀번호, 핸드폰, 주소) 중 비어있는 칸 알림 **/
	public boolean form_check() {
		boolean result = false;
		if(password_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("수정할 비밀번호를 입력해주세요"));
			password_tf.requestFocus();
		} else if (hp_text.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("수정할 핸드폰번호를 입력해주세요"));
			hp_text.requestFocus();
		} else if (addr_text.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("수정할 주소를 입력해주세요"));
			addr_text.requestFocus();
		} else {
			result = true;
		}
		return result;
	}
	
	
} //class
