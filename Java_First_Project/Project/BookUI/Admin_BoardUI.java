package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Commons.Commons;

public class Admin_BoardUI {
	JFrame frame;
	Admin_MainUI main;
	JScrollPane scrollPane;
	
	public Admin_BoardUI(Admin_MainUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}
	
	public void init() {
		main.switching(Admin_MainUI.Board);
		JPanel content_panel = new JPanel();
		content_panel.setBackground(SystemColor.activeCaption);
		content_panel.setBounds(0, 109, 804, 431);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		String[] colNames = {"제목","내용","작성자"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		
		JScrollPane scrollPane = new JScrollPane();
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		JTable board_table = new JTable(model);
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);		
		
		JPanel Button_panel = new JPanel();
		Button_panel.setBounds(192, 98, 535, 41);
		Button_panel.setBackground(new Color(176, 196, 222));
		Button_panel.setLayout(new BorderLayout(0,0));
		
		JButton btn_answer = new JButton("F&Q");
		btn_answer.setSize(5, 5);
		Button_panel.add(btn_answer, BorderLayout.WEST);
		
		JButton btn_request = new JButton("도서요청");
		btn_request.setSize(5, 5);
		Button_panel.add(btn_request, BorderLayout.CENTER);
		content_panel.add(Button_panel, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("게 시 판");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(label, BorderLayout.NORTH);
		label.setBackground(SystemColor.activeCaption);
	
		main.content_panel.add(content_panel);		
		
		
		/** 폰트 설정 (쓰실때 주석처리하시거나 본인 폰트로 설정하시면 됩니다! )**/
		btn_answer.setFont(Commons.getFont());
		btn_request.setFont(Commons.getFont());
		board_table.setFont(Commons.getFont());
		label.setFont(Commons.getFont());
	}
}