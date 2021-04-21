package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
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

import BookSystem.BookSystem;
import BookVO.BoardVO;
import Commons.Commons;

public class User_MyPage_MyUI extends JFrame{
	User_MyPageUI main;
	JButton btn_delete;
	ArrayList<Object> list = new ArrayList<Object>();
	Object[] header = {"분류","제목","내용","작성날짜", "Button"};
	DefaultTableModel model= new DefaultTableModel(header, 0);
	JTable board_table = new JTable(model);
	Object[] row = new Object[5];
	String id;
	BookSystem system = new BookSystem();

	
	public User_MyPage_MyUI(User_MyPageUI main) {
		super();		
		this.main = main;
		this.id = main.user_name;
		init();
	}	
	
	
	public void init() {
		main.switching(User_MyPageUI.My);
		
		JScrollPane scrollPane = new JScrollPane();					
		
		JPanel center_panel = new JPanel();
		main.content_panel.add(center_panel);
		center_panel.setLayout(new BorderLayout(0, 0));
		center_panel.setBounds(12, 35, 507, 268);
				
		center_panel.add(scrollPane, BorderLayout.CENTER);
		
		//table에 row data 추가
//		boardData();
//		model.setColumnIdentifiers(header);
//		board_table.setModel(model);
//		board_table.setRowHeight(20);
		
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);		
		board_pane.setEnabled(false);
		
		JButton box = new JButton();
		box.setHorizontalAlignment(JLabel.CENTER);
		
        board_table.getColumn("Button").setCellRenderer(new ButtonRenderer());
        board_table.getColumn("Button").setCellEditor(new ButtonEditor(new JTextField()));
		
		JCheckBox checkbox_board = new JCheckBox("게시판");
		checkbox_board.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//게시판 테이블 보이기
					boardData();
					model.setColumnIdentifiers(header);
					board_table.setModel(model);
					board_table.setRowHeight(20);
				}
			}
		});
		checkbox_board.setSelected(true);
		checkbox_board.setBounds(70, 6, 70, 23);
		main.content_panel.add(checkbox_board);
		
		JCheckBox checkbox_review = new JCheckBox("리뷰");
		checkbox_review.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//리뷰 테이블 보이기
					reviewData();
					model.setColumnIdentifiers(header);
					board_table.setModel(model);
					board_table.setRowHeight(20);
				}
			}
		});
		checkbox_review.setBounds(12, 6, 54, 23);
		main.content_panel.add(checkbox_review);
		
		//체크박스 여러개 중 하나만 선택되도록 설정
		ButtonGroup bg = new ButtonGroup();
		bg.add(checkbox_board);		bg.add(checkbox_review);
		
		JButton btn_search = new JButton("검색");
		btn_search.setBounds(141, 6, 58, 23);
		main.content_panel.add(btn_search);
		btn_search.setBackground(Color.WHITE);
		
		/** 폰트설정 **/
		board_table.setFont(Commons.getFont());
		checkbox_board.setFont(Commons.getFont());
		checkbox_review.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
	
	}//init

	
	//table에 출력되는 데이터 생성 - 일단은 게시판(카테고리, 제목, 내용, 날짜) 갖고오기
	public void boardData() {
		model.setNumRows(0);
		ArrayList<BoardVO> boardlist = system.All_Myboard(main.user_name);
		for(BoardVO board : boardlist) {
			row[0] = board.getCategory();
			row[1] = board.getTitle();
			row[2] = board.getContent();
			row[3] = board.getDate();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
	}
	
	//table에 출력되는 데이터 생성 - 일단은 리뷰(카테고리, 제목, 내용, 날짜) 갖고오기
		public void reviewData() {
			model.setNumRows(0);
			ArrayList<BoardVO> boardlist = system.All_Myreview(main.user_name);
			for(BoardVO board : boardlist) {
				row[0] = board.getCategory();
				row[1] = board.getTitle();
				row[2] = board.getContent();
				row[3] = board.getDate();
				
				model.addRow(row);
			}
			model.fireTableDataChanged();
		}
	
	
	/** 삭제 버튼 이벤트 처리 **/
	// 테이블에 버튼넣고 버튼 이벤트처리
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

	}//inner class
	
	
		
		
		
}//outer class
