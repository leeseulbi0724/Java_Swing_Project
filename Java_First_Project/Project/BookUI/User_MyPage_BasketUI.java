package BookUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_BasketUI {

	JFrame frame;
	User_MyPageUI main;
	JPanel content_panel, btn_panel;
	JTextField textField;
	String[] colName = {"도서명", "도서가격", "가격", "수량"};
	DefaultTableModel model = new DefaultTableModel(colName, 0);
	Object[] row = new Object[4];
	JTable board_table = new JTable(model);
	BookSystem system;

	
	public User_MyPage_BasketUI(User_MyPageUI main) {
		this.main = main;
		this.frame = main.frame;
		this.system = main.system;
		init();
	}

	
	public void init() {
		main.switching(User_MyPageUI.Basket);
		
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.info);
		content_panel.setBounds(0, 0, 531, 301);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		//table에 row data 추가
		basketData();
		model.setColumnIdentifiers(colName);
		board_table.setModel(model);
		board_table.setRowHeight(20);

		
		JScrollPane scrollPane = new JScrollPane();
		
		
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);
		
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_order = new JButton("주문하기");
		btn_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btn_order)) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("주문 완료 되었습니다."));
				}
			}
		});
		button_panel.add(btn_order);
		
		
		content_panel.add(button_panel, BorderLayout.SOUTH);
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		main.content_panel.setVisible(true);
		main.content_panel.add(content_panel);
		
		/** 폰트 **/
		board_table.setFont(Commons.getFont());
		scrollPane.setFont(Commons.getFont());
		btn_order.setFont(Commons.getFont());
		
	} //init

	//table에 출력되는 데이터 (BOOKNAME, AUTHOR, PRICE) 생성
	public void basketData() {
		model.setNumRows(0);
		for(BookVO book : main.system.getBookList()) {
			row[0] = book.getBookname();
			row[1] = book.getAuthor();
			row[2] = book.getPrice();
			row[3] = book.getCount();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
	}
	
	
}//class
