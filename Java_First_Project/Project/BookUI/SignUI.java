package BookUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import BookVO.MemberVO;
import Commons.Commons;


/*로그인 창에서 회원가입 버튼을 누르면 나오는 창*/
public class SignUI extends JDialog implements ActionListener {

	//Field
	LoginUI ui;
	JTextField sign_id_tf;
	JPasswordField sign_pass_tf;
	JPasswordField check_sign_pass_tf;
	JTextField sign_name_tf;
	JTextField sign_phone_tf;
	JTextField adrr_tf;
	JButton sign_btn;
	JTextField sign_birthday_tf;
	ArrayList<Object> list = new ArrayList<Object>();
	Admin_MainUI main;
	
	
	//Constructor
	public SignUI(LoginUI ui) {
		this.ui = ui;
	}
	
	public SignUI(Window parent) {
		super(parent,"회원가입",ModalityType.APPLICATION_MODAL);
		init();
		ui = new LoginUI();
	}
	
	
	//Method
	public void init() {		
		
		setBounds(100, 100, 500, 700);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel sign_panel = new JPanel();
		sign_panel.setBounds(0, 10, 500, 650);
		sign_panel.setBackground(Color.WHITE);
		getContentPane().add(sign_panel);
		sign_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(109, 10, 265, 43);
		sign_panel.add(lblNewLabel);
		
		JButton back_btn = new JButton();
		back_btn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		back_btn.setBackground(Color.WHITE);
		back_btn.setForeground(Color.WHITE);
		back_btn.setIcon(new ImageIcon("images/backicon.png"));
		back_btn.setBounds(22, 10, 33, 33);
		sign_panel.add(back_btn);
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(63, 43, 125, 33);
		sign_panel.add(idLabel);
		
		JLabel passLabel = new JLabel("비밀번호");
		passLabel.setBounds(63, 117, 125, 33);
		sign_panel.add(passLabel);
		
		JLabel checkpassLabel = new JLabel("비밀번호 확인");
		checkpassLabel.setBounds(63, 193, 125, 33);
		sign_panel.add(checkpassLabel);
		
		sign_id_tf = new JTextField();
		sign_id_tf.setBounds(63, 74, 265, 43);
		sign_panel.add(sign_id_tf);
		sign_id_tf.setColumns(10);
		list.add(sign_id_tf);
		
		sign_pass_tf = new JPasswordField();
		sign_pass_tf.setBounds(63, 147, 265, 43);
		sign_panel.add(sign_pass_tf);
		list.add(sign_pass_tf);
		
		check_sign_pass_tf = new JPasswordField();
		check_sign_pass_tf.setBounds(63, 222, 265, 43);
		sign_panel.add(check_sign_pass_tf);
		list.add(check_sign_pass_tf);
		
		JButton check_id_btn = new JButton("중복확인");
		check_id_btn.setForeground(Color.PINK);
		check_id_btn.setBackground(Color.WHITE);
		check_id_btn.setBorder(BorderFactory.createLineBorder(null));
		check_id_btn.setBounds(340, 74, 125, 43);
		sign_panel.add(check_id_btn);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(63, 265, 125, 33);
		sign_panel.add(nameLabel);
		
		sign_name_tf = new JTextField();
		sign_name_tf.setColumns(10);
		sign_name_tf.setBounds(63, 298, 265, 43);
		sign_panel.add(sign_name_tf);
		list.add(sign_name_tf);
		
		JLabel birthdayLabel = new JLabel("생년월일");
		birthdayLabel.setBounds(63, 345, 125, 33);
		sign_panel.add(birthdayLabel);
		
		sign_birthday_tf = new JTextField();
		sign_birthday_tf.setColumns(10);
		sign_birthday_tf.setBounds(63, 374, 265, 43);
		sign_panel.add(sign_birthday_tf);
		list.add(sign_birthday_tf);
		
		JLabel phoneLabel = new JLabel("휴대폰번호");
		phoneLabel.setBounds(63, 421, 125, 33);
		sign_panel.add(phoneLabel);
		
		sign_phone_tf = new JTextField();
		sign_phone_tf.setColumns(10);	
		sign_phone_tf.setBounds(63, 449, 265, 43);
		sign_panel.add(sign_phone_tf);
		list.add(sign_phone_tf);
		
		JLabel adrLabel = new JLabel("주소");
		adrLabel.setBounds(63, 492, 125, 33);
		sign_panel.add(adrLabel);
		
		adrr_tf = new JTextField();
		adrr_tf.setColumns(10);
		adrr_tf.setBounds(63, 523, 265, 43);
		sign_panel.add(adrr_tf);
		list.add(adrr_tf);
		
		sign_btn= new JButton("회원가입");
		sign_btn.setForeground(Color.WHITE);
		sign_btn.setBackground(Color.PINK);
		sign_btn.addActionListener(this);
		
		sign_btn.setBounds(0, 576, 488, 50);
		sign_panel.add(sign_btn);
		
		/** 폰트 설정 **/
		idLabel.setFont(Commons.getFont());
		passLabel.setFont(Commons.getFont());
		checkpassLabel.setFont(Commons.getFont());
		nameLabel.setFont(Commons.getFont());
		check_id_btn.setFont(Commons.getFont());
		phoneLabel.setFont(Commons.getFont());
		adrLabel.setFont(Commons.getFont());
		birthdayLabel.setFont(Commons.getFont());
		sign_name_tf.setFont(Commons.getFont());
		sign_id_tf.setFont(Commons.getFont());
		sign_phone_tf.setFont(Commons.getFont());
		adrr_tf.setFont(Commons.getFont());
		sign_btn.setFont(Commons.getFont());
		sign_birthday_tf.setFont(Commons.getFont());
	}
	
	//회원가입 전송버튼 액션
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == sign_btn) {
			//유효성 체크
			if(form_check()) {
				//JTextField 형변환
				ArrayList<JTextField> jlist = new ArrayList<JTextField>();
				for(Object tf : list) {
					JTextField jtf = (JTextField)tf;
					jlist.add(jtf);
				}
				
				MemberVO member = new MemberVO();
				member.setId(jlist.get(0).getText());
				member.setPass(jlist.get(1).getText());
				member.setCpass(jlist.get(2).getText());
				member.setName(jlist.get(3).getText());
				member.setBirthday(jlist.get(4).getText());
				member.setHp(jlist.get(5).getText());
				member.setAddr(jlist.get(6).getText());
				
				boolean result = ui.system.join(member);
				
				if(result) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입이 완료되었습니다."));
					for(Object obj2 : list) {
						JTextField tf = (JTextField)obj2;
						tf.setText("");
					}
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입에 실패하셨습니다."));
				}
			}
		}else {
			for(Object obj2 : list) {
				JTextField tf = (JTextField)obj2;
				tf.setText("");
			}
		}
		
		
	};
	
	
	/** 폼 체크 **/
	public boolean form_check() {
		boolean result = false;
		
		for(int i=0;i<list.size();i++) {
			JTextField tf = (JTextField)list.get(i);
			
			if(tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("빈 공간을 입력해주세요"));
				tf.requestFocus();
				i=list.size();
			}else if(i == list.size()-1) {
				result = true;
			}
			
		}
		
		
	 return result;
		
	}
	
}
