package BookUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookDAO.MemberDAO;
import BookVO.MemberVO;
import Commons.Commons;

public class FindPassUI extends JDialog implements ActionListener{

	//Field
	LoginUI ui;
	JTextField sign_id_tf;
	JTextField sign_name_tf;
	JTextField sign_birthday_tf;
	JPanel temp_panel;
	JPanel find_panel;
	JTextField temp_pass_tf;
	JButton find_btn, temp_check_btn;
	String tmp_pass;
	JLabel temp_pass_label;
	MemberDAO dao = new MemberDAO();
//	ArrayList<JTextField> tf_list;
	FindPassUIEvent eventObj = new FindPassUIEvent(this);
	
	//Constructor
	public FindPassUI(LoginUI ui) {
		this.ui = ui;
	}
	
	public FindPassUI(Window parent) {
		super(parent,"비밀번호 찾기",ModalityType.APPLICATION_MODAL);
		init();
		ui = new LoginUI();
	}
	
	//Method
	public void init() {
		setBounds(100, 100, 500, 465);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		find_panel = new JPanel();
		find_panel.setBounds(0, 10, 500, 423);
		find_panel.setBackground(Color.WHITE);
		getContentPane().add(find_panel);
		find_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(109, 10, 265, 43);
		find_panel.add(lblNewLabel);
		
		JButton back_btn = new JButton("New button");
		back_btn.setBackground(Color.WHITE);
		back_btn.setForeground(Color.WHITE);
		back_btn.setIcon(new ImageIcon("images/backicon.png"));
		back_btn.setBounds(22, 10, 33, 33);
		find_panel.add(back_btn);
		//뒤로가기 버튼 액션
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(63, 63, 125, 33);
		find_panel.add(nameLabel);
		
		sign_name_tf = new JTextField();
		sign_name_tf.setColumns(10);
		sign_name_tf.setBounds(63, 94, 265, 43);
		find_panel.add(sign_name_tf);
		
		JLabel birthdayLabel = new JLabel("생년월일");
		birthdayLabel.setBounds(63, 147, 125, 33);
		find_panel.add(birthdayLabel);
				
		sign_birthday_tf = new JTextField();
		sign_birthday_tf.setColumns(10);
		sign_birthday_tf.setBounds(63, 179, 265, 43);
		find_panel.add(sign_birthday_tf);
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(63, 232, 125, 33);
		find_panel.add(idLabel);
		
		sign_id_tf = new JTextField();
		sign_id_tf.setColumns(10);
		sign_id_tf.setBounds(63, 262, 265, 43);
		find_panel.add(sign_id_tf);
		
		find_btn= new JButton("비밀번호 찾기");
		find_btn.setBounds(0, 374, 488, 51);
		find_btn.setForeground(Color.WHITE);
		find_btn.setBackground(Color.PINK);
		find_panel.add(find_btn);
		find_btn.addActionListener(this); 
		

		/** 폰트 설정 **/
		lblNewLabel.setFont(Commons.getFont());
		idLabel.setFont(Commons.getFont());
		nameLabel.setFont(Commons.getFont());
		birthdayLabel.setFont(Commons.getFont());

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == find_btn) {
			//이름,생년월일,아이디 인증
			if(dao.CheckInfo(sign_name_tf.getText(), sign_birthday_tf.getText(), sign_id_tf.getText())) {
				
				JOptionPane.showMessageDialog(null, Commons.getMsg("임시 비밀번호를 제공합니다."));
				find_panel.setVisible(false);
				temp_panel = new JPanel();
				temp_panel.setBackground(Color.WHITE);
				temp_panel.setBounds(0, 0, 486, 428);
				getContentPane().add(temp_panel);
				temp_panel.setLayout(null);
				temp_panel.setVisible(true);
				
				JLabel temp_pass_label = new JLabel("임시 비밀번호");
				temp_pass_label.setHorizontalAlignment(SwingConstants.CENTER);
				temp_pass_label.setBounds(131, 117, 220, 53);
				temp_panel.add(temp_pass_label);
				temp_pass_label.setFont(Commons.getFont());
				
				temp_pass_tf = new JTextField();
				tmp_pass = String.valueOf(Math.round(Math.random()*1000000));
				temp_pass_tf.setText(tmp_pass);
				temp_pass_tf.setHorizontalAlignment(SwingConstants.CENTER);
				temp_pass_tf.setBounds(64, 180, 344, 75);
				temp_pass_tf.setColumns(10);
				temp_panel.add(temp_pass_tf);
				//event클래스를 만들어서 구현
				temp_check_btn = new JButton("확인");
				temp_check_btn.setForeground(Color.WHITE);
				temp_check_btn.setBackground(Color.PINK);
				temp_check_btn.setBounds(131, 312, 220, 45);
				temp_panel.add(temp_check_btn);
				temp_check_btn.addActionListener(eventObj);
			
			}else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("등록된 회원정보가 존재하지 않습니다."));
				sign_name_tf.setText("");
				sign_birthday_tf.setText("");
				sign_id_tf.setText("");
				
			}

		}	
			
	}
		
		
}


