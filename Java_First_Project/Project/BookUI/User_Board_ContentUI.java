package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import Commons.Commons;

public class User_Board_ContentUI extends JDialog{
	
	//Field
	User_MainUI main;
	LoginUI login;
	BookSystem system = new BookSystem();
	JTextField title_tf;
	JTextArea content_ta, comment_ta;
	String title, content;
	
	//Constructor
	public User_Board_ContentUI(String title, String content, Window parent) {
		super(parent, "게시글 내용", ModalityType.APPLICATION_MODAL);
		this.title = title;
		this.content = content;
		init();
	}
	
	public void init() {
		setBackground(new Color(211, 211, 211));
		setBounds(133, 10, 531, 500);
		setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel Label = new JLabel(" 내 용 확 인 ");
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		add(Label, BorderLayout.NORTH);

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
		title_tf.setBounds(91, 40, 388, 25);
		content_ta.setBounds(91, 75, 388, 186);			
		comment_ta.setBounds(91, 285, 388, 60);
		
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
		
		/** **/
		Label.setFont(Commons.getFont());
		label_title.setFont(Commons.getFont());
		label_content.setFont(Commons.getFont());
		label_comment.setFont(Commons.getFont());
		title_tf.setFont(Commons.getFont());
		content_ta.setFont(Commons.getFont());
		title_tf.setBackground(Color.WHITE);
		title_tf.setEditable(false);
		content_ta.setEditable(false);		
				
	}	

}
