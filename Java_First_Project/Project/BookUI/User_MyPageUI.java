package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import Commons.Commons;

public class User_MyPageUI implements ActionListener {

	User_MainUI main;
	JPanel content_panel, main_panel, btn_panel;
	JFrame frame;
	JButton btn_usermodify, btn_basket, btn_order, btn_my, btn_pass;
	BookSystem system = new BookSystem();
	String user_name;
	JPasswordField pass_textField;
	
	public User_MyPageUI(User_MainUI main) {
		this.frame = main.f;
		this.main = main;
		this.user_name = main.name;
		init();
	}
	
	public static final int Information = 0;
	public static final int Usermodify = 1;
	public static final int Basket = 2;
	public static final int Order = 3;
	public static final int My = 4;

	
	public void init() {
		main.switching(User_MainUI.MYPAGE);								
		
		btn_panel = new JPanel();
		btn_panel.setBackground(Color.WHITE);
		
		btn_usermodify = new JButton("정보수정");
		btn_panel.add(btn_usermodify);
		
		btn_basket = new JButton("장바구니");
		btn_panel.add(btn_basket);
		
		btn_order = new JButton("주문조회");
		btn_panel.add(btn_order);
		
		btn_my = new JButton("    My   ");
		btn_panel.add(btn_my);
		
		main_panel = new JPanel(new BorderLayout());
		main_panel.setBackground(Color.LIGHT_GRAY);
		main_panel.setBounds(133, 10, 531, 341);
		main_panel.add(BorderLayout.NORTH, btn_panel);
		
		content_panel = new JPanel();
		content_panel.setLayout(null);
		JLabel title_label = new JLabel("마이페이지 이용을 위해 비밀번호를 입력해주세요");
		title_label.setBounds(175, 106, 224, 15);
		content_panel.add(title_label);
		
		pass_textField = new JPasswordField();
		pass_textField.setBounds(199, 131, 153, 21);
		content_panel.add(pass_textField);
		pass_textField.setColumns(10);
		
		main_panel.add(BorderLayout.CENTER, content_panel);
		
		main.mainPanel.add(main_panel);
		
		btn_usermodify.setBackground(Color.WHITE);
		btn_basket.setBackground(Color.WHITE);
		btn_order.setBackground(Color.WHITE);
		btn_my.setBackground(Color.WHITE);
		
		btn_pass = new JButton("확인");
		btn_pass.setBounds(233, 162, 91, 23);
		content_panel.add(btn_pass);		
		
		/** 버튼 이벤트 **/
		btn_usermodify.addActionListener(this);
		btn_basket.addActionListener(this);
		btn_order.addActionListener(this);
		btn_my.addActionListener(this);
		btn_pass.addActionListener(this);
		
		/** 폰트 **/
		btn_usermodify.setFont(Commons.getFont());
		btn_basket.setFont(Commons.getFont());
		btn_order.setFont(Commons.getFont());
		btn_my.setFont(Commons.getFont());
		title_label.setFont(Commons.getFont());
		btn_pass.setFont(Commons.getFont());
		
		
	}
	
	public void switching(int num) {
		content_panel.removeAll();
		content_panel.setVisible(false);
		btn_usermodify.setBackground(Color.WHITE);
		btn_basket.setBackground(Color.WHITE);
		btn_order.setBackground(Color.WHITE);
		btn_my.setBackground(Color.WHITE);
		switch (num) {
				case Information :
					content_panel.setVisible(true);
					User_information();
					break;
				case Usermodify :
					content_panel.setVisible(true);
					btn_usermodify.setBackground(Color.PINK);
					break;
				case Basket :
					content_panel.setVisible(true);
					btn_basket.setBackground(Color.PINK);
					break;
				case Order :
					content_panel.setVisible(true);
					btn_order.setBackground(Color.PINK);
					break;
				case My :
					content_panel.setVisible(true);
					btn_my.setBackground(Color.PINK);					
		
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		boolean pass_result = false;
			if (obj.equals(btn_usermodify)) { 
				if (pass_result) {
					new User_MyPage_UsermodifyUI(User_MyPageUI.this);					
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("비밀번호 입력 후 이용가능한 서비스입니다."));
				}
			} else if (obj.equals(btn_basket)) {
				if (pass_result) {
					new User_MyPage_BasketUI(User_MyPageUI.this);				
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("비밀번호 입력 후 이용가능한 서비스입니다."));
				}				
			} else if (obj.equals(btn_order)) {
				if (pass_result) {
					new User_MyPage_OrderUI(User_MyPageUI.this);			
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("비밀번호 입력 후 이용가능한 서비스입니다."));
				}						
			} else if (obj.equals(btn_my)) {
				if (pass_result) {
					new User_MyPage_MyUI(User_MyPageUI.this);	
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("비밀번호 입력 후 이용가능한 서비스입니다."));
				}		
			}
		if (obj.equals(btn_pass)) {
			if (system.PassCheck(user_name, pass_textField.getText())) {
				switching(Information);				
				pass_result = true;
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("비밀번호가 틀렸습니다. 다시 입력해주세요"));
				pass_textField.setText("");
				pass_textField.requestFocus();
			}
		}		
	}
	
	public void User_information() {
		JLabel name_label = new JLabel("이름");
		name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		name_label.setBounds(163, 95, 39, 15);
		content_panel.add(name_label);		
		JTextField name_text = new JTextField();
		name_text.setBounds(210, 93, 153, 21);
		content_panel.add(name_text);
		name_text.setEditable(false);
		
		JLabel id_label = new JLabel("아이디");
		id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		id_label.setBounds(163, 126, 39, 15);
		content_panel.add(id_label);		
		JTextField id_text = new JTextField();
		id_text.setColumns(10);
		id_text.setBounds(210, 124, 153, 21);
		content_panel.add(id_text);
		id_text.setEditable(false);
		
		JLabel birthday_label = new JLabel("생년월일");
		birthday_label.setHorizontalAlignment(SwingConstants.RIGHT);
		birthday_label.setBounds(145, 157, 57, 15);
		content_panel.add(birthday_label);		
		JTextField birthday_text = new JTextField();
		birthday_text.setColumns(10);
		birthday_text.setBounds(210, 155, 153, 21);
		content_panel.add(birthday_text);
		birthday_text.setEditable(false);
		
		JLabel hp_label = new JLabel("휴대폰번호");
		hp_label.setHorizontalAlignment(SwingConstants.RIGHT);
		hp_label.setBounds(134, 188, 68, 15);
		content_panel.add(hp_label);		
		JTextField hp_text = new JTextField();
		hp_text.setColumns(10);
		hp_text.setBounds(210, 186, 153, 21);
		content_panel.add(hp_text);
		hp_text.setEditable(false);
		
		JLabel addr_label = new JLabel("주소");
		addr_label.setHorizontalAlignment(SwingConstants.RIGHT);
		addr_label.setBounds(163, 222, 39, 15);
		content_panel.add(addr_label);		
		JTextField addr_text = new JTextField();
		addr_text.setColumns(10);
		addr_text.setBounds(210, 220, 153, 21);
		content_panel.add(addr_text);
		addr_text.setEditable(false);
		
		JLabel title_label = new JLabel("회 원 님 의 정 보");
		title_label.setBounds(230, 66, 133, 15);
		content_panel.add(title_label);
		
		/** 폰트설정 **/
		name_label.setFont(Commons.getFont());
		name_text.setFont(Commons.getFont());
		id_label.setFont(Commons.getFont());
		id_text.setFont(Commons.getFont());
		birthday_label.setFont(Commons.getFont());
		birthday_text.setFont(Commons.getFont());
		hp_label.setFont(Commons.getFont());
		hp_text.setFont(Commons.getFont());
		addr_label.setFont(Commons.getFont());
		addr_text.setFont(Commons.getFont());
		title_label.setFont(Commons.getFont());
		
		/** 임시 **/
		name_text.setText("미입력");
		id_text.setText("미입력");
		birthday_text.setText("미입력");
		hp_text.setText("미입력");
		addr_text.setText("미입력");
		
		name_text.setBackground(Color.LIGHT_GRAY);
		id_text.setBackground(Color.LIGHT_GRAY);
		birthday_text.setBackground(Color.LIGHT_GRAY);
		hp_text.setBackground(Color.LIGHT_GRAY);
		addr_text.setBackground(Color.LIGHT_GRAY);
	}
}
