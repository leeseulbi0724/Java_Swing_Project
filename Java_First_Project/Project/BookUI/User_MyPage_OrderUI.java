package BookUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_OrderUI{
	
	User_MyPageUI main;
	JPanel content_panel;
	String[] colName = {"주문번호", "도서명", "저자", "출판사", "가격", "주문일자"};
	DefaultTableModel model = new DefaultTableModel(colName, 0);
	Object[] row = new Object[6];
	JTable board_table = new JTable(model);
	BookSystem system = new BookSystem();
	
	public User_MyPage_OrderUI(User_MyPageUI main) {
		this.main = main;
		this.system = main.system;
		init();
	}
	
	public void init() {
		main.switching(User_MyPageUI.Order);
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.info);
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
		button_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_review = new JButton("리뷰작성");
		btn_review.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btn_review)) {
					String bookname = JOptionPane.showInputDialog("리뷰를 작성할 도서명을 입력해주세요");
					// 리뷰쓰는 창이랑 이어지게.. DB연동해서 DB함수 끌어오기 getReviewResult 같은...
					if(bookname.equals("ddd")) System.out.println("잘 뜨나 test중");
				}
			}
		});
		button_panel.add(btn_review);
		
		
		content_panel.add(button_panel, BorderLayout.SOUTH);
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		main.content_panel.setVisible(true);
		main.content_panel.add(content_panel);
		
		/** 폰트 **/
		board_table.setFont(Commons.getFont());
		scrollPane.setFont(Commons.getFont());
		btn_review.setFont(Commons.getFont());
		
	}//init
	
	//table에 출력되는 데이터 (BOOKNAME, AUTHOR, PBLSH, PRICE) 생성
	public void orderData() {
		model.setNumRows(0);
		System.out.println("여긴 잘 되지?? 1111111");
		for(BookVO book : main.system.getOrderList(main.main.name)) {
			
			row[1] = book.getBookname();
			row[2] = book.getAuthor();
			row[3] = book.getPblsh();
			row[4] = book.getPrice();
			System.out.println("여긴 잘 되지?? 2222222");
			model.addRow(row);
			System.out.println("여긴 잘 되지?? 3333333");
		}
		model.fireTableDataChanged();
	}
	

	
}// class 
