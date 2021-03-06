package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_OrderUI implements ActionListener, MouseListener{
	
	User_MyPageUI main;
	JPanel content_panel;
	String[] colName = {"NO", "도서명", "저자", "출판사", "가격", "수량", "주문일자"};
	DefaultTableModel model = new DefaultTableModel(colName, 0);
	Object[] row = new Object[7];
	JTable board_table = new JTable(model);
	BookSystem system;
	JButton btn_review;
	String bookname, user_name;
	
	public User_MyPage_OrderUI(User_MyPageUI main) {
		this.main = main;
		this.system = main.system;
		this.user_name = main.user_name;
		init();
	}
	
	public void init() {
		main.switching(User_MyPageUI.Order);
		
		content_panel = new JPanel();
//		content_panel.setBackground(new Color(255, 240, 245));
		content_panel.setBounds(0, 0, 531, 301);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		//table에 row data 추가
		orderData();
		model.setColumnIdentifiers(colName);
		board_table.setModel(model);
		board_table.setRowHeight(20);		
		
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btn_review = new JButton("리뷰작성");
		button_panel.add(btn_review);		
		button_panel.setBackground(Color.WHITE);
		
		content_panel.add(button_panel, BorderLayout.SOUTH);
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		main.content_panel.setVisible(true);
		main.content_panel.add(content_panel);
		
		/** 폰트 **/
		board_table.setFont(Commons.getFont());
		scrollPane.setFont(Commons.getFont());
		btn_review.setFont(Commons.getFont());
		
		/** 버튼 이벤트 **/
		btn_review.addActionListener(this);
		board_table.addMouseListener(this);
		
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
		
		/** 컬럼 길이조절, 가운데정렬 **/
		board_table.getColumnModel().getColumn(0).setPreferredWidth(1);
		board_table.getColumnModel().getColumn(1).setPreferredWidth(150);
		board_table.getColumnModel().getColumn(2).setPreferredWidth(30);
		board_table.getColumnModel().getColumn(3).setPreferredWidth(30);
		board_table.getColumnModel().getColumn(4).setPreferredWidth(10);
		board_table.getColumnModel().getColumn(5).setPreferredWidth(1);
		board_table.getColumnModel().getColumn(6).setPreferredWidth(100);
		 DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcmSchedule = board_table.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
	}//init
	
	//table에 출력되는 데이터 (BOOKNAME, AUTHOR, PBLSH, PRICE) 생성
	public void orderData() {
		model.setNumRows(0);
		for(BookVO book : main.system.getOrderList(main.main.name)) {
			
			row[0] = book.getBno();
			row[1] = book.getBookname();
			row[2] = book.getAuthor();
			row[3] = book.getPblsh();
			row[4] = book.getPrice();
			row[5] = book.getCount();
			row[6] = book.getPblshdate();
			model.addRow(row);
			
		}
		model.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_review)) {
			if (system.ReviewCheck(bookname, user_name)) {
				if (bookname != null) {
					String category = "등록";
					User_ReviewWriteUI rw = new User_ReviewWriteUI(category, main.frame, bookname, user_name, system);
					rw.setVisible(true);				
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("리뷰를 작성하실 주문서를 클릭해주세요."));
				}				
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("이미 리뷰 작성이 완료된 도서입니다."));
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		 //선택한 셀의 행 번호계산 
		  int row = board_table.getSelectedRow();		  
		  //테이블의 모델객체 얻어오기
		  TableModel data = board_table.getModel();		  
		  bookname = (String)data.getValueAt(row,1);  
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	

	
}// class 
