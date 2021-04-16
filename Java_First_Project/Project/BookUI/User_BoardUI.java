package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Commons.Commons;

public class User_BoardUI implements ActionListener {
	JFrame frame;
	User_MainUI main;
	JPanel content_panel;
	JButton btn_write;
	
	public User_BoardUI(User_MainUI main) {
		this.main = main;
		this.frame = main.f;
		init();
	}

	
	public void init() {
		main.switching(User_MainUI.BOARD);
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.activeCaption);
		content_panel.setBounds(133, 10, 531, 341);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		String[] colName = {"글번호", "제목", "내용", "작성자", "작성날짜"};
		DefaultTableModel model = new DefaultTableModel(colName, 0);
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JTable board_table = new JTable(model);
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);
		
		JLabel label_board = new JLabel("게시판");
		label_board.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(label_board, BorderLayout.NORTH);
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_faq = new JButton("질문FAQ");
		btn_faq.setHorizontalAlignment(SwingConstants.LEFT);
		button_panel.add(btn_faq);
		
		JButton btn_request = new JButton("요청사항");
		btn_request.setHorizontalAlignment(SwingConstants.LEFT);
		button_panel.add(btn_request);
		
		btn_write = new JButton("글쓰기");
		btn_write.addActionListener(this);
		button_panel.add(btn_write);
		
		content_panel.add(button_panel, BorderLayout.SOUTH);
		main.mainPanel.add(content_panel);
		
		label_board.setForeground(new Color(255,255,255));	
		content_panel.setBackground(new Color(255,192,203));
		btn_faq.setBackground(Color.WHITE);
		btn_request.setBackground(Color.WHITE);
		btn_write.setBackground(Color.WHITE);
		
		
		/** 폰트설정 **/
		label_board.setFont(Commons.getFont(25));
		btn_faq.setFont(Commons.getFont());
		btn_request.setFont(Commons.getFont());
		btn_write.setFont(Commons.getFont());	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_write)) {
			main.mainPanel.removeAll();
			main.mainPanel.setVisible(false);
			new User_WriteUI(main);
		}
		
	}
}

