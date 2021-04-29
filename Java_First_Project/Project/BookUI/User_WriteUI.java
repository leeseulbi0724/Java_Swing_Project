package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import BookVO.BoardVO;
import Commons.Commons;

public class User_WriteUI implements ActionListener {

	User_MainUI main;
	JPanel content_panel;
	JButton btn_reset, btn_write;
	JComboBox comboBox;
	JTextField title_tf;
	JTextArea content_ta;
	String name, boxname;
	BookSystem system;
	
	public User_WriteUI(User_MainUI main) {
		this.main = main;
		this.name = main.name;
		this.system = main.system;
		init();
	}
	
	public void init() {
		
		content_panel = new JPanel();
		content_panel.setBackground(new Color(211, 211, 211));
		content_panel.setBounds(133, 10, 531, 341);
		content_panel.setLayout(new BorderLayout(0, 0));
		main.mainPanel.setVisible(true);
		main.mainPanel.add(content_panel);
		
		JLabel Label = new JLabel(" 게 시 판 글 쓰 기 ");
		Label.setForeground(Color.WHITE);
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(Label, BorderLayout.NORTH);

		JPanel write_panel = new JPanel();			
		content_panel.add(write_panel);
		write_panel.setLayout(null);
		
		JLabel label_title = new JLabel("제목");
		JLabel label_content = new JLabel("내용");		
		JLabel label_gory = new JLabel("분야");

		JPanel box_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		box_panel.setBounds(87, 5, 388, 32);
		write_panel.add(box_panel);
		
		label_title.setBounds(12, 40, 75, 15);
		label_content.setBounds(12, 70, 75, 15);
		
		comboBox = new JComboBox();
		comboBox.addItem("선택");	 comboBox.addItem("질문FAQ");		comboBox.addItem("도서요청");
		box_panel.add(comboBox);		
		
		title_tf = new JTextField(50);
		content_ta = new JTextArea();
		
		title_tf.setBounds(91, 40, 388, 25);
		content_ta.setBounds(91, 75, 388, 186);	
		
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_content.setHorizontalAlignment(SwingConstants.CENTER);
		
		write_panel.add(label_title);		
		write_panel.add(title_tf);
		write_panel.add(label_content);			
		write_panel.add(content_ta);		

		btn_write = new JButton("작성");
		btn_write.setBounds(153, 280, 75, 23);
		write_panel.add(btn_write);
		
		btn_reset = new JButton("취소");
		btn_reset.setBounds(324, 280, 75, 23);
		write_panel.add(btn_reset);		
		
		/** 폰트설정 **/
		label_title.setFont(Commons.getFont());
		label_content.setFont(Commons.getFont());
		btn_write.setFont(Commons.getFont());
		btn_reset.setFont(Commons.getFont());
		title_tf.setFont(Commons.getFont());
		content_ta.setFont(Commons.getFont());
		Label.setFont(Commons.getFont(20));
		comboBox.setFont(Commons.getFont());		
		
		/** 이벤트 처리 **/
		comboBox.addActionListener(this);		
		btn_reset.addActionListener(this);
		btn_write.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_reset)) {
			new User_BoardUI(main);
		} else if (obj.equals(comboBox)) {
			comboBox_form();
		} else if (obj.equals(btn_write)) {
			write_proc();
		}
	}
	
	/** 글쓰기 양식 **/
	public void comboBox_form() {
		boxname = comboBox.getSelectedItem().toString();
		if (boxname.equals("질문FAQ")) {
			title_tf.setText("질문합니다!");
			content_ta.setText("");
		} else if (boxname.equals("도서요청")) {
			title_tf.setText("도서 신청합니다!");
			content_ta.setText(" 도서명 : \n 저자 : \n 가격 : ");
		} else if (boxname.equals("선택")) {
			title_tf.setText("");
			content_ta.setText("");
		}
	}
	
	/** 내용 DB 저장 **/
	public void write_proc() {
		BoardVO board = new BoardVO();
		board.setCategory(boxname);
		board.setTitle(title_tf.getText());
		board.setContent(content_ta.getText());
		board.setId(name);
		if (system.User_Board(board)) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("작성이 완료되었습니다."));
			new User_BoardUI(main);
		} else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("작성이 실패되었습니다."));
		}
	}

}
