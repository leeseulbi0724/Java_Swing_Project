package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Commons.Commons;
import JTableButton.JTableButtonEditor;
import JTableButton.JTableButtonRenderer;

public class User_MyPage_MyUI implements ActionListener {
private static final String Clients = null;
User_MyPageUI main;
JButton btn_delete;
JTable board_table;
	
	public User_MyPage_MyUI(User_MyPageUI main) {
		this.main = main;
		init();
	}	
	
	
	public void init() {
		main.switching(User_MyPageUI.My);
		
		btn_delete = new JButton("삭제");
		
		Object[] header = {"분류","제목","내용","작성날짜", "Button"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane();					
		
		Vector<Object> row1 = new Vector<Object>();
		row1.add("리뷰");
		row1.add("1");
		row1.add("2");
		row1.add("3");
		row1.add(btn_delete);		
		model.addRow(row1);
		
		JPanel center_panel = new JPanel();
		main.content_panel.add(center_panel);
		center_panel.setLayout(new BorderLayout(0, 0));
		center_panel.setBounds(12, 35, 507, 268);
				
		center_panel.add(scrollPane, BorderLayout.CENTER);
		
		board_table = new JTable(model);
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);		
		board_pane.setEnabled(false);
		
		JButton box = new JButton();
		box.setHorizontalAlignment(JLabel.CENTER);
		
		JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
        JTableButtonEditor buttonEditor = new JTableButtonEditor();
        board_table.getColumn("Button").setCellRenderer(buttonRenderer);
        board_table.getColumn("Button").setCellEditor(buttonEditor);
		
		JCheckBox checkbox_board = new JCheckBox("게시판");
		checkbox_board.setBounds(70, 6, 70, 23);
		main.content_panel.add(checkbox_board);
		
		JCheckBox checkbox_review = new JCheckBox("리뷰");
		checkbox_review.setBounds(12, 6, 54, 23);
		main.content_panel.add(checkbox_review);
		
		JButton btn_search = new JButton("검색");
		btn_search.setBounds(141, 6, 58, 23);
		main.content_panel.add(btn_search);
		btn_search.setBackground(Color.WHITE);
		
		btn_delete.addActionListener(this);
		
		/** 폰트설정 **/
		board_table.setFont(Commons.getFont());
		checkbox_board.setFont(Commons.getFont());
		checkbox_review.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_delete)) {
			
		}
		
		
	}
}
