package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Commons.Commons;

public class Admin_InsertUI {

	JFrame frame;
	Admin_MainUI main;


	public Admin_InsertUI(Admin_MainUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}

	private void init() {
		main.switching(Admin_MainUI.Insert);
		JPanel content_panel = new JPanel();
		content_panel.setBackground(SystemColor.activeCaption);
		content_panel.setBounds(192, 98, 535, 331);
		content_panel.setLayout(new BorderLayout(0, 0));
		main.content_panel.add(content_panel);
		main.btn_insert.setBackground(new Color(255, 228, 225));
		
		JLabel Label = new JLabel("도 서 등 록");
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(Label, BorderLayout.NORTH);

		JPanel center_panel = new JPanel();			
		content_panel.add(center_panel);
		center_panel.setLayout(null);
		
		JLabel number_lb = new JLabel("도서번호");
		JLabel name_lb = new JLabel("도서명");
		JLabel author_lb = new JLabel("저자 ");
		JLabel pblsh_lb = new JLabel("출판사");
		JLabel date_lb = new JLabel("출판일");
		JLabel price_lb = new JLabel("도서가격");
		
		number_lb.setBounds(135, 46, 75, 15);
		name_lb.setBounds(135, 71, 75, 15);
		author_lb.setBounds(135, 97, 75, 15);
		pblsh_lb.setBounds(135, 124, 75, 15);
		date_lb.setBounds(135, 150, 75, 15);
		price_lb.setBounds(135, 176, 75, 15);
		
		JTextField number_tf = new JTextField(15);
		JTextField name_tf = new JTextField(15);
		JTextField author_tf = new JTextField(15);
		JTextField pblsh_tf = new JTextField(15);
		JTextField date_tf = new JTextField(15);
		JTextField price_tf = new JTextField(15);
		
		number_tf.setBounds(222, 43, 171, 21);
		name_tf.setBounds(222, 68, 171, 21);
		author_tf.setBounds(222, 94, 171, 21);
		pblsh_tf.setBounds(222, 121, 171, 21);
		date_tf.setBounds(222, 147, 171, 21);
		price_tf.setBounds(222, 173, 171, 21);		
		
		number_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		name_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		author_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		pblsh_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		date_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		price_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		
		center_panel.add(number_lb);		center_panel.add(number_tf);
		center_panel.add(name_lb);			center_panel.add(name_tf);		
		center_panel.add(author_lb);			center_panel.add(author_tf);			
		center_panel.add(pblsh_lb);			center_panel.add(pblsh_tf);	 		
		center_panel.add(date_tf);				center_panel.add(date_lb);		
		center_panel.add(price_tf);			center_panel.add(price_lb);
		
		JButton book_insert = new JButton("등록");
		book_insert.setBounds(196, 240, 75, 23);
		center_panel.add(book_insert);
		
		JButton book_reset = new JButton("취소");
		book_reset.setBounds(294, 240, 75, 23);
		center_panel.add(book_reset);				
		
		
		/** 폰트 설정 (쓰실때 주석처리하시거나 본인 폰트로 설정하시면 됩니다! )**/
		number_tf.setFont(Commons.getFont());
		number_lb.setFont(Commons.getFont());
		name_tf.setFont(Commons.getFont());
		name_lb.setFont(Commons.getFont());
		author_lb.setFont(Commons.getFont());
		author_tf.setFont(Commons.getFont());
		pblsh_lb.setFont(Commons.getFont());
		pblsh_tf.setFont(Commons.getFont());
		date_lb.setFont(Commons.getFont());
		date_tf.setFont(Commons.getFont());
		price_lb.setFont(Commons.getFont());
		price_tf.setFont(Commons.getFont());
		book_insert.setFont(Commons.getFont());
		book_reset.setFont(Commons.getFont());
		Label.setFont(Commons.getFont());
		
		
	}

}
