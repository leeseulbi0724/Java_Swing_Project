package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Commons.Commons;

public class User_MyPage_MyUI extends JFrame{
User_MyPageUI main;
JButton btn_delete;
JTable board_table;
ArrayList<Object> list = new ArrayList<Object>();
DefaultTableModel model;

	
	public User_MyPage_MyUI(User_MyPageUI main) {
		super();		
		this.main = main;
		init();
	}	
	
	
	public void init() {
		main.switching(User_MyPageUI.My);
		
		Object[] header = {"분류","제목","내용","작성날짜", "Button"};
		model = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane();					
		
		Object row[];
		row = new Object[7];
		row[0] = "FAQ";
		row[1] = "문의 내용좀 확인해주세요";
		row[2] = "~";
		row[3] = "04/14";
		row[4] = "삭제";
		list.add(row);
		model.addRow(row);
		
		Object row1[];
		row1 = new Object[7];
		row1[0] = "리뷰";
		row1[1] = "이 책은 정말 좋아요";
		row1[2] = "~";
		row1[3] = "04/14";
		row1[4] = "삭제";
		list.add(row1);
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
		
        board_table.getColumn("Button").setCellRenderer(new ButtonRenderer());
        board_table.getColumn("Button").setCellEditor(new ButtonEditor(new JTextField()));
		
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
		
//		btn_delete.addActionListener(this);
		
		/** 폰트설정 **/
		board_table.setFont(Commons.getFont());
		checkbox_board.setFont(Commons.getFont());
		checkbox_review.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
	
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {
		 public ButtonRenderer() {
		  setOpaque(true);
	}
		 
		 @Override
		 public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
			int col) {
			 	setText((obj == null) ? "" : obj.toString());
			  	return this;
			 }
		}

		class ButtonEditor extends DefaultCellEditor { 

		JButton btn_delete;
		String lbl;
		 Boolean clicked;

		 public ButtonEditor(JTextField txt) {
		  super(txt);
		  btn_delete = new JButton();
		  btn_delete.setOpaque(true);
		  btn_delete.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent e) {
			   fireEditingStopped();
			  }
		  });

		 }

		 @Override
		 public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
			  lbl = (obj == null) ? "" : obj.toString();
			  btn_delete.setText(lbl);
			  clicked = true;
			  return btn_delete;

		 }

		 @Override

		 public Object getCellEditorValue() {
		  if (clicked) {
			 int confirm = JOptionPane.showConfirmDialog(btn_delete, Commons.getMsg("정말로 삭제하시겠습니까?"));
			if (confirm == 0) {
				list.remove(board_table.getSelectedRow());
				model.removeRow(board_table.getSelectedRow());
			}
		  }
		  clicked = false;
		  return new String(lbl);

		 }

		 @Override
		 public boolean stopCellEditing() {
		  clicked = false;
		  return super.stopCellEditing();
		 }

		 @Override
		 public void fireEditingStopped() {
			 super.fireEditingStopped();
		 }

		}


}
