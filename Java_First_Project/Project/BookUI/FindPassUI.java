package BookUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Commons.Commons;

public class FindPassUI extends JDialog{

	//Field
	LoginUI app;
	private JTextField sign_id_tf;
	private JTextField sign_name_tf;
	private JTextField sign_phone_tf;
	private JPanel temp_panel;
	private JPanel find_panel;
	private JTextField temp_pass_tf;
	String tmp_pass;
	
	//Constructor
	public FindPassUI(Window parent) {
		super(parent,"비밀번호 찾기",ModalityType.APPLICATION_MODAL);
		init();
		app = new LoginUI();
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
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(63, 63, 125, 33);
		find_panel.add(idLabel);
		
		sign_id_tf = new JTextField();
		sign_id_tf.setBounds(63, 94, 265, 43);
		find_panel.add(sign_id_tf);
		sign_id_tf.setColumns(10);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(63, 147, 125, 33);
		find_panel.add(nameLabel);
		
		sign_name_tf = new JTextField();
		sign_name_tf.setColumns(10);
		sign_name_tf.setBounds(63, 179, 265, 43);
		find_panel.add(sign_name_tf);
		
		JLabel phoneLabel = new JLabel("휴대폰번호");
		phoneLabel.setBounds(63, 232, 125, 33);
		find_panel.add(phoneLabel);
		
		sign_phone_tf = new JTextField();
		sign_phone_tf.setColumns(10);
		sign_phone_tf.setBounds(63, 262, 265, 43);
		find_panel.add(sign_phone_tf);
		
		JButton find_btn_1 = new JButton("비밀번호 찾기");
		find_btn_1.setForeground(Color.WHITE);
		find_btn_1.setBackground(Color.PINK);
		find_btn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("임시 비밀번호를 제공합니다."));
				find_panel.setVisible(false);
				temp_panel.setVisible(true);
			}
		});
		find_btn_1.setBounds(0, 374, 488, 51);
		find_panel.add(find_btn_1);
		
		
		temp_panel = new JPanel();
		temp_panel.setBackground(Color.WHITE);
		temp_panel.setBounds(0, 0, 486, 428);
		getContentPane().add(temp_panel);
		temp_panel.setLayout(null);
		temp_panel.setVisible(false);
		
		JLabel temp_pass_label = new JLabel("임시 비밀번호");
		temp_pass_label.setHorizontalAlignment(SwingConstants.CENTER);
		temp_pass_label.setBounds(131, 117, 220, 53);
		temp_panel.add(temp_pass_label);
		
		temp_pass_tf = new JTextField();
		tmp_pass = String.valueOf(Math.round(Math.random()*1000000));
		temp_pass_tf.setText(tmp_pass);
		temp_pass_tf.setHorizontalAlignment(SwingConstants.CENTER);
		temp_pass_tf.setBounds(64, 180, 344, 75);
		temp_panel.add(temp_pass_tf);
		temp_pass_tf.setColumns(10);
		
		JButton temp_check_btn = new JButton("확인");
		temp_check_btn.setForeground(Color.WHITE);
		temp_check_btn.setBackground(Color.PINK);
		temp_check_btn.setBounds(131, 312, 220, 45);
		temp_panel.add(temp_check_btn);
		//임시비밀번호창 확인 버튼 액션
		temp_check_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}			
			
		});
		/** 폰트 설정 **/
		lblNewLabel.setFont(Commons.getFont());
		idLabel.setFont(Commons.getFont());
		nameLabel.setFont(Commons.getFont());
		phoneLabel.setFont(Commons.getFont());
		temp_pass_label.setFont(Commons.getFont());
		temp_pass_tf.setFont(Commons.getFont());
		temp_check_btn.setFont(Commons.getFont());
	}
	
}
