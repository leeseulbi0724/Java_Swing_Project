package BookUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.sun.tools.javac.Main;

import BookSystem.BookSystem;
import Commons.Commons;

public class LoginUI implements ActionListener {

	//Field
	 JFrame frame;
	 ImagePanel login_panel;
	 JTextField id_tf;
	 JLabel id_label;
	 JLabel password_label;
	 JPasswordField password_tf;
	 JButton login_btn;
	
	 JLabel lblNewLabel;
	 JPanel main_panel;
	 BookSystem system = new BookSystem();
		
	
	
	//Constructor
	public LoginUI() {		
		init();
	}

	//init
	public void init() {
		frame = new JFrame("도서 관리 프로그램");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 804, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		login_panel = new ImagePanel(new ImageIcon("images/LoginBackGroundimg.jpg").getImage());
		login_panel.setBounds(0, 0, 800, 513);
		frame.getContentPane().add(login_panel);
		login_panel.setLayout(null);
		login_panel.setVisible(true);
		
		id_tf = new JTextField();
		id_tf.setBounds(47, 197, 266, 51);
		login_panel.add(id_tf);
		id_tf.setColumns(10);
		
		id_label = new JLabel("아이디");
		id_label.setBounds(47, 150, 144, 37);
		login_panel.add(id_label);
		
		password_label = new JLabel("패스워드");
		password_label.setBounds(47, 263, 144, 37);
		login_panel.add(password_label);
		
		login_btn = new JButton("");
		login_btn.setBorderPainted(false);
		login_btn.setIcon(new ImageIcon("images/LoginBtn.png"));
		login_btn.setBackground(Color.WHITE);
		login_btn.setBounds(100, 377, 125, 51);
		login_panel.add(login_btn);
		login_btn.addActionListener(this);
		
		
		
		JButton sign_btn = new JButton("회원가입");
		sign_btn.setBackground(Color.WHITE);
		sign_btn.setBorderPainted( false );
		sign_btn.setBounds(33, 438, 125, 23);
		login_panel.add(sign_btn);
		//회원가입 버튼 액션
		sign_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUI sign = new SignUI(frame);
				sign.setVisible(true);
			}
		});
		
		JButton findPassword_btn = new JButton("비밀번호 찾기");
		findPassword_btn.setBackground(Color.WHITE);
		findPassword_btn.setBorderPainted( false );
		findPassword_btn.setBounds(184, 438, 125, 23);
		login_panel.add(findPassword_btn);
		//비밀번호 찾기 액션
		findPassword_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FindPassUI find = new FindPassUI(frame);
				find.setVisible(true);
			}
		});
		
		password_tf = new JPasswordField();
		password_tf.setBounds(47, 310, 266, 51);
		login_panel.add(password_tf);
		
		/** 폰트 설정 **/
		id_label.setFont(Commons.getFont());
		password_label.setFont(Commons.getFont());
		login_btn.setFont(Commons.getFont());
		id_tf.setFont(Commons.getFont());
		login_btn.setFont(Commons.getFont());
		sign_btn.setFont(Commons.getFont());
		findPassword_btn.setFont(Commons.getFont());
	}
	
	//로그인 버튼
	@Override
	public void actionPerformed(ActionEvent e) {
		Object Obj = e.getSource();
		
		if(Obj == login_btn) {
			login_proc();
		}
		
	}
	
	//로그인 유효성 체크
	public void login_proc() {
		if (id_tf.getText().equals("admin") && password_tf.getText().equals("1234")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("관리자 로그인에 성공하셨습니다."));
			id_tf.setText(""); password_tf.setText("");
			new Admin_MainUI(LoginUI.this);
			login_panel.setVisible(false);
		}else if(id_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력해주세요"));
			id_tf.requestFocus();
		}else if(password_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("패스워드를 입력해주세요"));
			password_tf.requestFocus();
		}else {
			//로그인 체크 : system.loginCheck(아이디, 패스워드);
			boolean result = system.loginCheck(id_tf.getText(), password_tf.getText());
			if(result) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("사용자 로그인에 성공하셨습니다."));
				new User_MainUI(LoginUI.this, id_tf.getText());	
				id_tf.setText(""); password_tf.setText("");
				login_panel.setVisible(false);
				
			}else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("사용자 로그인에 실패하셨습니다."));
			}
			
		}			
	}
			
	
	
}//LoginUI


	//이미지 패널 class 배경에 이미지 넣기
	class ImagePanel extends JPanel {
		//Field
		private Image img;
		
		//Constructor
		public ImagePanel(Image img) {
			this.img=img;
			setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
			setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
			setLayout(null);
		}
		
		public int getWidth() {
			return img.getWidth(null);
		}
		public int getHeight() {
			return img.getHeight(null);
		}
		//Method
		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}//ImagePanel class





