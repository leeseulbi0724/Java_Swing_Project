package BookUI;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class User_WriteUI {

	User_MainUI main;
	JPanel content_panel;

	
	public User_WriteUI(User_MainUI main) {
		this.main = main;
		init();
	}


	public void init() {
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.info);
		content_panel.setBounds(133, 10, 531, 341);
		content_panel.setLayout(new BorderLayout(0, 0));
		main.mainPanel.setVisible(true);
		main.mainPanel.add(content_panel);
		
		JLabel Label = new JLabel(" 게 시 판 글 쓰 기 ");
		Label.setBackground(Color.GRAY);
		Label.setFont(new Font("굴림", Font.BOLD, 20));
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(Label, BorderLayout.NORTH);

		JPanel write_panel = new JPanel();			
		content_panel.add(write_panel);
		write_panel.setLayout(null);
		
		JLabel label_title = new JLabel("제목");
		JLabel label_content = new JLabel("내용");
		
		label_title.setBounds(12, 21, 75, 15);
		label_content.setBounds(12, 52, 75, 15);
		
		JTextField title_tf = new JTextField(50);
		JTextField content_tf = new JTextField(200);
		
		title_tf.setBounds(91, 18, 388, 21);
		content_tf.setBounds(91, 49, 388, 181);	
		
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_content.setHorizontalAlignment(SwingConstants.CENTER);
		
		write_panel.add(label_title);		
		write_panel.add(title_tf);
		write_panel.add(label_content);			
		write_panel.add(content_tf);		

		JButton btn_write = new JButton("글쓰기");
		btn_write.setBounds(153, 260, 75, 23);
		write_panel.add(btn_write);
		
		JButton btn_reset = new JButton("취소");
		btn_reset.setBounds(324, 260, 75, 23);
		write_panel.add(btn_reset);
	}

}
