package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import BookVO.BoardVO;
import Commons.Commons;

public class User_ReviewWriteUI extends JDialog implements ActionListener{
	
	//Field
	User_MainUI main;
	LoginUI login;
	BookSystem system = new BookSystem();
	JTextField title_tf;
	JTextArea content_ta;
	String title, content;
	String bookname, username;
	JButton btn_write, btn_cancle;
	JComboBox comboBox;
	
	//Constructor
	public User_ReviewWriteUI(Window parent, String bookname, String user_name) {
		super(parent, "리뷰작성", ModalityType.APPLICATION_MODAL);
		this.bookname = bookname;
		this.username = user_name;
		init();
	}
	
	public void init() {
		setBackground(new Color(211, 211, 211));
		setBounds(133, 10, 531, 341);
		setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel Label = new JLabel(" 리 뷰 작 성 ");
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		add(Label, BorderLayout.NORTH);

		JPanel write_panel = new JPanel();			
		add(BorderLayout.CENTER, write_panel);
		write_panel.setLayout(null);
		
		JLabel label_title = new JLabel("도서명");
		JLabel label_content = new JLabel("내용");
		JLabel label_score = new JLabel("별점");
		
		comboBox = new JComboBox();
		comboBox.addItem("☆☆☆☆☆");			comboBox.addItem("★☆☆☆☆");  
		comboBox.addItem("★★☆☆☆");    		comboBox.addItem("★★★☆☆");  
		comboBox.addItem("★★★★☆");   		comboBox.addItem("★★★★★");
		comboBox.setForeground(Color.YELLOW);
		comboBox.setBounds(91, 15, 388, 20);
		
		label_score.setBounds(12 ,15, 75, 20);
		label_title.setBounds(12, 45, 75, 15);
		label_content.setBounds(12, 70, 75, 15);	
		
		title_tf = new JTextField(50);
		content_ta = new JTextArea();		
		title_tf.setBounds(91, 40, 388, 25);
		content_ta.setBounds(91, 75, 388, 150);	
		
		title_tf.setText(bookname); 
		
		JPanel btn_panel = new JPanel();		
		btn_write = new JButton("등록");
		btn_cancle = new JButton("취소");
		btn_panel.add(btn_write);  		btn_panel.add(btn_cancle);
		
		btn_write.setBounds(153, 280, 75, 23);
		btn_cancle.setBounds(324, 280, 75, 23);
		
		write_panel.add(label_score);
		write_panel.add(comboBox);
		write_panel.add(label_title);		
		write_panel.add(title_tf);
		write_panel.add(label_content);			
		write_panel.add(content_ta);		
		
		add(BorderLayout.SOUTH, btn_panel);
		
		/**  **/
		Label.setFont(Commons.getFont());
		label_title.setFont(Commons.getFont());
		label_content.setFont(Commons.getFont());
		label_score.setFont(Commons.getFont());
		title_tf.setFont(Commons.getFont());
		content_ta.setFont(Commons.getFont());
		btn_write.setFont(Commons.getFont());
		btn_cancle.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		comboBox.setBackground(Color.GRAY);
		title_tf.setBackground(Color.LIGHT_GRAY);
		title_tf.setEditable(false);
		
		/** 이벤트 처리 **/
		btn_cancle.addActionListener(this);			
		btn_write.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_cancle)) {
			dispose();
		} else if (obj.equals(btn_write)) {
			review_write();
		}		
	}
	
	public void review_write() {
		BoardVO vo = new BoardVO();
		int score = 0;
		vo.setId(username);
		vo.setTitle(bookname);
		vo.setContent(content_ta.getText());
		if (comboBox.getSelectedItem().toString().equals("☆☆☆☆☆")) {
			score = 0;
		} else if (comboBox.getSelectedItem().toString().equals("★☆☆☆☆")){
			score = 1;
		} else if (comboBox.getSelectedItem().toString().equals("★★☆☆☆")){
			score = 2;
		} else if (comboBox.getSelectedItem().toString().equals("★★★☆☆")){
			score = 3;
		} else if (comboBox.getSelectedItem().toString().equals("★★★★☆")){
			score = 4;
		} else if (comboBox.getSelectedItem().toString().equals("★★★★★")){
			score = 5;
		}
		vo.setScore(score);		
		if (system.User_ReviewResult(vo)) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("리뷰 작성이 완료되었습니다."));
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("리뷰 작성이 실패되었습니다."));
		}
	}
}
