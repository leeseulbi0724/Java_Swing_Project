package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_BasketUI implements MouseListener, ActionListener{

	JFrame frame;
	User_MyPageUI main;
	JPanel content_panel,button_panel;
	JTextField textField;
	String[] colName = {"도서명", "도서가격", "수량"};
	DefaultTableModel model = new DefaultTableModel(colName, 0);
	Object[] row = new Object[3];
	JTable board_table = new JTable(model);
	BookSystem system;
	BookVO vo;
	String bookname, userid;
	JButton btn_order, btn_all_order;
	int count;
	int all_price = 0;
	
	ArrayList<BookVO> list = new ArrayList<BookVO>();

	public User_MyPage_BasketUI(User_MyPageUI main) {
		this.main = main;
		this.frame = main.frame;
		this.system = main.system;
		this.userid = main.user_name;
		init();
	}

	
	public void init() {
		main.switching(User_MyPageUI.Basket);		
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.info);
		content_panel.setBounds(0, 0, 531, 310);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		//table에 row data 추가
		basketData();
		model.setColumnIdentifiers(colName);
		board_table.setModel(model);
		board_table.setRowHeight(20);
		
		// 테이블 길이 조절 가운데정렬
		board_table.getColumnModel().getColumn(2).setPreferredWidth(5);
		board_table.getColumnModel().getColumn(1).setPreferredWidth(5);
		board_table.getColumnModel().getColumn(0).setPreferredWidth(300);
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = board_table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}		
		
		JScrollPane scrollPane = new JScrollPane();		
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);		
		
		button_panel = new JPanel();
		button_panel.setBackground(Color.WHITE);
		
		btn_order = new JButton("결제/주문");		
		
				
		/** 총합계 버튼 패널에 추가 **/
		JLabel price_lb = new JLabel();
		price_lb.setText(" 총 주문금액 : " + all_price+ "원");
		price_lb.setHorizontalAlignment(JLabel.CENTER);
		button_panel.add(price_lb);
		main.content_panel.setBackground(Color.WHITE);
		
		content_panel.add(button_panel, BorderLayout.SOUTH);
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		main.content_panel.setVisible(true);
		main.content_panel.add(content_panel);
		
		button_panel.add(btn_order);
		
		
		/** 폰트 **/
		board_table.setFont(Commons.getFont());
		scrollPane.setFont(Commons.getFont());
		btn_order.setFont(Commons.getFont());
		price_lb.setFont(Commons.getFont());
		
		/** 테이블 헤더 설정 **/
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
		
		/** 이벤트 처리 **/
		btn_order.addActionListener(this);
		board_table.addMouseListener(this);
		
		
	} //init

	//table에 출력되는 데이터 (BOOKNAME, AUTHOR, PRICE) 생성
	public void basketData() {
		
		model.setNumRows(0);
		for(BookVO book : main.system.getBookList(main.main.name)) {
			row[0] = book.getBookname();
			row[1] = book.getPrice();
			row[2] = book.getCount();			
			model.addRow(row);			
			all_price = all_price + (book.getPrice()*book.getCount());

		}
		model.fireTableDataChanged();
		
	}	
	
	/** 주문 **/
	public void Order_proc() {
		if (bookname != null) {
			vo = new BookVO();		
			ArrayList<BookVO> booklist = new ArrayList<BookVO>();
			booklist = system.getBookinfo(bookname);		
			vo.setBookname(bookname);
			vo.setCount(count);
			for(BookVO book : booklist) {
				vo.setAuthor(book.getAuthor());
				vo.setPblsh(book.getPblsh());
				vo.setPrice(book.getPrice());
				vo.setPblshdate(book.getPblshdate());
			}	
			if(system.User_Order(userid, vo)) {	
				if (system.User_Basket_Delete(userid, vo)) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("선택한 도서의 주문이 완료되었습니다."));	
					new User_MyPage_OrderUI(main);
				}			
			}else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("도서 주문에 실패하였습니다."));
			}					
		} else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("주문 하실 도서를 선택해주세요."));
		}
	}	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		  //선택한 셀의 행 번호계산 
		  int row = board_table.getSelectedRow();		  
		  //테이블의 모델객체 얻어오기
		  TableModel data = board_table.getModel();		  
		  //0번째 도서명을 받아 setBookname으로 넘겨줌, 수량도 받아서 넘겨줌
		  bookname = (String)data.getValueAt(row,0);
		  count = (int) data.getValueAt(row, 2);
		  vo = new BookVO();
		  vo.setBookname(bookname);		
		  vo.setCount(count);
	}	
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {	
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_order)) {
			Order_proc();
		}
		
	}			
	
	
}//class