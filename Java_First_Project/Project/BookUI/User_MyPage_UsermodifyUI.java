package BookUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Commons.Commons;

public class User_MyPage_UsermodifyUI implements ActionListener {
	User_MyPageUI main;
	JButton btn_modify_check;
	
	public User_MyPage_UsermodifyUI(User_MyPageUI main) {
		this.main = main;
		init();
	}
	
	public void init() {
		main.switching(User_MyPageUI.Usermodify);
		
		JLabel name_label = new JLabel("이름");
		name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		name_label.setBounds(163, 75, 39, 15);
		main.content_panel.add(name_label);		
		JTextField name_text = new JTextField();
		name_text.setBounds(210, 73, 153, 21);
		main.content_panel.add(name_text);
		name_text.setEditable(false);

		JLabel id_label = new JLabel("아이디");
		id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		id_label.setBounds(163, 105, 39, 15);
		main.content_panel.add(id_label);		
		JTextField id_text = new JTextField();
		id_text.setColumns(10);
		id_text.setBounds(210, 103, 153, 21);
		main.content_panel.add(id_text);
		id_text.setEditable(false);
		
		JLabel birthday_label = new JLabel("생년월일");
		birthday_label.setHorizontalAlignment(SwingConstants.RIGHT);
		birthday_label.setBounds(145, 135, 57, 15);
		main.content_panel.add(birthday_label);		
		JTextField birthday_text = new JTextField();
		birthday_text.setColumns(10);
		birthday_text.setBounds(210, 133, 153, 21);
		main.content_panel.add(birthday_text);
		birthday_text.setEditable(false);
		
		JLabel password_label = new JLabel("비밀번호");
		password_label.setHorizontalAlignment(SwingConstants.RIGHT);
		password_label.setBounds(145, 165, 57, 15);
		main.content_panel.add(password_label);		
		JTextField password_tf = new JTextField();
		password_tf.setColumns(10);
		password_tf.setBounds(210, 163, 153, 21);
		main.content_panel.add(password_tf);
		
		
		JLabel hp_label = new JLabel("휴대폰번호");
		hp_label.setHorizontalAlignment(SwingConstants.RIGHT);
		hp_label.setBounds(134, 193, 68, 15);
		main.content_panel.add(hp_label);		
		JTextField hp_text = new JTextField();
		hp_text.setColumns(10);
		hp_text.setBounds(210, 193, 153, 21);
		main.content_panel.add(hp_text);
		
		
		JLabel addr_label = new JLabel("주소");
		addr_label.setHorizontalAlignment(SwingConstants.RIGHT);
		addr_label.setBounds(163, 223, 39, 15);
		main.content_panel.add(addr_label);		
		JTextField addr_text = new JTextField();
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
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String str = e.getActionCommand();
		if(obj.equals(btn_modify_check)) {
			int result = JOptionPane.showConfirmDialog(null, "수정을 완료하시겠습니까?");
			
		}
	}

}
