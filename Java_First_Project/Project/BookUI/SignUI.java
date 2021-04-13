package BookUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import Commons.Commons;


/*로그인 창에서 회원가입 버튼을 누르면 나오는 창*/
public class SignUI extends JDialog{

	//Field
	LoginUI app;
	private JTextField sign_id_tf;
	private JPasswordField sign_pass_tf;
	private JPasswordField check_sign_pass_tf;
	private JTextField sign_name_tf;
	private JTextField sign_phone_tf;
	private JTextField adrr_tf;
	
	
	//Constructor
	public SignUI(Window parent) {
		super(parent,"회원가입",ModalityType.APPLICATION_MODAL);
		init();
		app = new LoginUI();
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
		back_btn.setIcon(new ImageIcon("image/backicon.png"));
		back_btn.setBounds(22, 10, 33, 33);
		sign_panel.add(back_btn);
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(63, 63, 125, 33);
		sign_panel.add(idLabel);
		
		JLabel passLabel = new JLabel("비밀번호");
		passLabel.setBounds(63, 147, 125, 33);
		sign_panel.add(passLabel);
		
		JLabel checkpassLabel = new JLabel("비밀번호 확인");
		checkpassLabel.setBounds(63, 243, 125, 33);
		sign_panel.add(checkpassLabel);
		
		sign_id_tf = new JTextField();
		sign_id_tf.setBounds(63, 94, 265, 43);
		sign_panel.add(sign_id_tf);
		sign_id_tf.setColumns(10);
		
		sign_pass_tf = new JPasswordField();
		sign_pass_tf.setBounds(63, 177, 265, 43);
		sign_panel.add(sign_pass_tf);
		
		check_sign_pass_tf = new JPasswordField();
		check_sign_pass_tf.setBounds(63, 272, 265, 43);
		sign_panel.add(check_sign_pass_tf);
		
		JButton check_id_btn = new JButton("중복확인");
		check_id_btn.setForeground(Color.PINK);
		check_id_btn.setBackground(Color.WHITE);
		check_id_btn.setBorder(BorderFactory.createLineBorder(null));
		check_id_btn.setBounds(340, 94, 125, 43);
		sign_panel.add(check_id_btn);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(63, 325, 125, 33);
		sign_panel.add(nameLabel);
		
		sign_name_tf = new JTextField();
		sign_name_tf.setColumns(10);
		sign_name_tf.setBounds(63, 358, 265, 43);
		sign_panel.add(sign_name_tf);
		
		JLabel phoneLabel = new JLabel("휴대폰번호");
		phoneLabel.setBounds(63, 411, 125, 33);
		sign_panel.add(phoneLabel);
		
		sign_phone_tf = new JTextField();
		sign_phone_tf.setColumns(10);
		sign_phone_tf.setBounds(63, 439, 265, 43);
		sign_panel.add(sign_phone_tf);
		
		JLabel adrLabel = new JLabel("주소");
		adrLabel.setBounds(63, 492, 125, 33);
		sign_panel.add(adrLabel);
		
		adrr_tf = new JTextField();
		adrr_tf.setColumns(10);
		adrr_tf.setBounds(63, 523, 265, 43);
		sign_panel.add(adrr_tf);
		
		JButton send_btn_1 = new JButton("회원가입");
		send_btn_1.setForeground(Color.WHITE);
		send_btn_1.setBackground(Color.PINK);
		//회원가입 전송버튼 액션
		send_btn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입이 완료되었습니다."));
				dispose();
			}
		});
		send_btn_1.setBounds(0, 576, 488, 50);
		sign_panel.add(send_btn_1);
		
		/** 폰트 설정 **/
		idLabel.setFont(Commons.getFont());
		passLabel.setFont(Commons.getFont());
		checkpassLabel.setFont(Commons.getFont());
		nameLabel.setFont(Commons.getFont());
		check_id_btn.setFont(Commons.getFont());
		phoneLabel.setFont(Commons.getFont());
		adrLabel.setFont(Commons.getFont());
		sign_name_tf.setFont(Commons.getFont());
		sign_id_tf.setFont(Commons.getFont());
		sign_phone_tf.setFont(Commons.getFont());
		adrr_tf.setFont(Commons.getFont());
	}
	
}
