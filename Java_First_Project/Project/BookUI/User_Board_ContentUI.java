package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import BookSystem.BookSystem;
import BookVO.BoardVO;
import Commons.Commons;

public class User_Board_ContentUI extends JDialog implements ActionListener{
	
	//Field
	User_MainUI main;
	LoginUI login = new LoginUI();
	BoardVO vo = new BoardVO();
	BookSystem system = new BookSystem();
	JTextField title_tf, comment_tf;
	JTextArea content_ta, comment_ta;
	String title, content;
	JButton write_btn;
	String name, bid;
	Admin_BoardUI ui;
	
	//Constructor
	public User_Board_ContentUI(BoardVO vo, String name, Window parent) {
		super(parent, "게시글 내용", ModalityType.APPLICATION_MODAL);
		this.title = vo.getTitle();
		this.content = vo.getContent();
		this.bid = vo.getBid();
		this.name = name;	
		init();
		comment_select();
	}
	
	public void init() {
		setBackground(new Color(211, 211, 211));
		setBounds(133, 10, 531, 480);
		setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		
//		JLabel Label = new JLabel(" 내 용 확 인 ");
//		Label.setHorizontalAlignment(SwingConstants.CENTER);
//		add(Label, BorderLayout.NORTH);

		JPanel write_panel = new JPanel();			
		add(BorderLayout.CENTER, write_panel);
		write_panel.setLayout(null);
		
		JLabel label_title = new JLabel("제목");
		JLabel label_content = new JLabel("내용");		
		JLabel label_comment = new JLabel("댓글");		
		
		label_title.setBounds(12, 40, 75, 15);
		label_content.setBounds(12, 70, 75, 15);	
		label_comment.setBounds(12, 290, 75, 15);
		
		title_tf = new JTextField(50);
		content_ta = new JTextArea();		
		comment_ta = new JTextArea();
		comment_tf = new JTextField(50);
		comment_tf.requestFocus();
		write_btn = new JButton("작성");
		title_tf.setBounds(91, 40, 388, 25);
		content_ta.setBounds(91, 75, 388, 186);			
		comment_ta.setBounds(91, 285, 388, 60);
		
		JPanel wpanel = new JPanel(new BorderLayout());
		wpanel.setBounds(91, 350, 388, 20);
		wpanel.add(BorderLayout.CENTER, comment_tf);		wpanel.add(BorderLayout.EAST, write_btn);
		
		JScrollPane scrollPane = new JScrollPane(comment_ta);
		scrollPane.setBounds(91, 285, 388, 60);
		scrollPane.setVisible(true);
		
		title_tf.setText(title); 		content_ta.setText(content);
		
		write_panel.add(label_title);		
		write_panel.add(title_tf);
		write_panel.add(label_content);			
		write_panel.add(content_ta);	
		write_panel.add(label_comment);
		write_panel.add(scrollPane);
		write_panel.add(wpanel);
		
		/** **/
//		Label.setFont(Commons.getFont());
		label_title.setFont(Commons.getFont());
		label_content.setFont(Commons.getFont());
		label_comment.setFont(Commons.getFont());
		title_tf.setFont(Commons.getFont());
		content_ta.setFont(Commons.getFont());
		comment_tf.setFont(Commons.getFont());
		write_btn.setFont(Commons.getFont());
		title_tf.setBackground(Color.WHITE);
		title_tf.setEditable(false);
		content_ta.setEditable(false);		
		comment_ta.setEditable(false);
		
		/** 버튼 이벤트 **/
		write_btn.addActionListener(this);				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if  (obj.equals(write_btn)) {
			comment_write();
		}		
	}	
	
	public void comment_select() {
		ArrayList<BoardVO> list = system.board_comment_select(bid);
		System.out.println(list.size());
		for (BoardVO vo : list) {
				comment_ta.append(" [ " + vo.getId()+"님 ] " + vo.getContent() + " "+vo.getDate()+"\n");
		}
	}
	
	public void comment_write() {
		BoardVO vo = new BoardVO();
		vo.setContent(comment_tf.getText());
		vo.setId(name);
		System.out.println(name);
		vo.setBid(bid);
		if (system.board_comment(vo)) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("댓글 작성 완료"));
			comment_tf.setText("");
			comment_select();
		} else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("댓글 작성 실패"));
		}
	}

}
