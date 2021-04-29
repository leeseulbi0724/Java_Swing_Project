package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class Admin_InsertUI implements ActionListener {

	JFrame frame;
	Admin_MainUI main;
	JButton book_insert, book_reset;
	JTextField name_tf, author_tf, pblsh_tf, date_tf, price_tf;
	BookSystem system;


	public Admin_InsertUI(Admin_MainUI main) {
		this.system = main.system;
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
		
		JLabel name_lb = new JLabel("도서명");
		JLabel author_lb = new JLabel("저자 ");
		JLabel pblsh_lb = new JLabel("출판사");
		JLabel date_lb = new JLabel("출판일");
		JLabel price_lb = new JLabel("도서가격");
		
		name_lb.setBounds(135, 72, 75, 15);
		author_lb.setBounds(135, 98, 75, 15);
		pblsh_lb.setBounds(135, 124, 75, 15);
		date_lb.setBounds(135, 150, 75, 15);
		price_lb.setBounds(135, 176, 75, 15);
		
		name_tf = new JTextField(15);
		author_tf = new JTextField(15);
		pblsh_tf = new JTextField(15);
		date_tf = new JTextField(15);
		price_tf = new JTextField(15);
		
		name_tf.setBounds(222, 68, 171, 21);
		author_tf.setBounds(222, 94, 171, 21);
		pblsh_tf.setBounds(222, 121, 171, 21);
		date_tf.setBounds(222, 147, 171, 21);
		price_tf.setBounds(222, 173, 171, 21);		
		
		name_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		author_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		pblsh_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		date_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		price_lb.setHorizontalAlignment(SwingConstants.RIGHT);
		
		center_panel.add(name_lb);			center_panel.add(name_tf);		
		center_panel.add(author_lb);			center_panel.add(author_tf);			
		center_panel.add(pblsh_lb);			center_panel.add(pblsh_tf);	 		
		center_panel.add(date_tf);				center_panel.add(date_lb);		
		center_panel.add(price_tf);			center_panel.add(price_lb);
		
		book_insert = new JButton("등록");
		book_insert.setBounds(196, 240, 75, 23);
		center_panel.add(book_insert);
		
		book_reset = new JButton("취소");
		book_reset.setBounds(294, 240, 75, 23);
		center_panel.add(book_reset);				
		
		name_tf.requestFocus();
		
		/** 버튼 이벤트 처리 **/
		book_insert.addActionListener(this);
		book_reset.addActionListener(this);
		
		
		/** 폰트 설정 (쓰실때 주석처리하시거나 본인 폰트로 설정하시면 됩니다! )**/
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == book_insert) {
			insert_proc();
		} else if (obj == book_reset) {
			reset_proc();
		}
	}	
	
	public void insert_proc() {
		if (form_check()) {
			BookVO vo = new BookVO();
			vo.setBookname(name_tf.getText());
			vo.setAuthor(author_tf.getText());
			vo.setPblsh(pblsh_tf.getText());
			vo.setPblshdate(date_tf.getText());
			vo.setPrice(Integer.parseInt(price_tf.getText()));		

			if (system.Admin_Insert(vo)) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("등록이 완료되었습니다"));
				reset_proc();
				main.switching(Admin_MainUI.home);
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("등록이 실패되었습니다"));
			}	
		}
	}
	
	public void reset_proc() {
		name_tf.setText("");
		author_tf.setText("");
		pblsh_tf.setText("");
		date_tf.setText("");
		price_tf.setText("");
		name_tf.requestFocus();
	}
	
	public boolean form_check() {
		boolean result = false;
		
		 if (name_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("도서명을 입력해주세요"));
		} else if (author_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("저자를 입력해주세요"));
		} else if (pblsh_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("출판사를 입력해주세요"));
		} else if (date_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("출판일을 입력해주세요"));
		} else if (price_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("도서가격을 입력해주세요"));
		} else {
			result = true;
		}					
		return result;
	}

}
