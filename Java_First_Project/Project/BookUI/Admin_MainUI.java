package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class Admin_MainUI  implements ActionListener {

	LoginUI main;
	JFrame frame;
	JPanel btn_panel, top_panel, content_panel, name_panel;
	JButton btn_delete, btn_insert, btn_user_select, btn_board, btn_logout, btn_home;
	JScrollPane scrollPane, book_pane;	
	DefaultTableModel model;
	JTable book_table;
	Object row[];
	ArrayList<Object> bookname_list = new ArrayList<Object>();
	BookSystem system;
	
	public static final int home = 0;
	public static final int Insert = 1;
	public static final int Delete = 2;
	public static final int User_select = 3;
	public static final int Board = 4;
	
	
	public Admin_MainUI(LoginUI main) {
		this.system = main.system;
		this.main = main;
		this.frame = main.frame;
		init();
	}
	
	public void init() {
		
		btn_panel = new JPanel();
		btn_panel.setBackground(Color.WHITE);
		btn_panel.setBounds(61, 98, 131, 331);
		frame.getContentPane().add(btn_panel);
		btn_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn_panel.setBackground(new Color(255, 245, 238));
		
		btn_insert = new JButton("  도서 등록 ");
		btn_panel.add(btn_insert);
		btn_insert.setBackground(Color.LIGHT_GRAY);
		
		btn_delete = new JButton("  도서 삭제 ");
		btn_delete.setBackground(Color.LIGHT_GRAY);
		btn_panel.add(btn_delete);
		
		btn_user_select = new JButton("  회원 조회 ");
		btn_user_select.setBackground(Color.LIGHT_GRAY);
		btn_panel.add(btn_user_select);
		
		btn_board = new JButton("회원 게시판");
		btn_board.setBackground(Color.LIGHT_GRAY);
		btn_panel.add(btn_board);
		
		top_panel = new JPanel();
		top_panel.setBounds(423, 23, 325, 56);
		top_panel.setBackground(new Color(255, 245, 238));
		
		btn_home = new JButton("");
		btn_home.setBackground(new Color(255, 245, 238));
		btn_home.setIcon(new ImageIcon("images/home.png"));
		btn_home.setBorderPainted(false);		
		top_panel.add(btn_home);
		
		frame.getContentPane().add(top_panel);
		
		JLabel title_label = new JLabel("관리자님 환영합니다");
		top_panel.add(title_label);
		
		btn_logout = new JButton("로그아웃");
		btn_logout.setBackground(Color.WHITE);
		top_panel.add(btn_logout);
		
		content_panel = new JPanel();
		content_panel.setBackground(SystemColor.activeCaption);
		content_panel.setBounds(192, 98, 535, 331);
		Object[] header = {"번호", "도서명", "저자", "출판사", "가격", "발행일", "수량"};
		model = new DefaultTableModel(header, 0);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel name_label = new JLabel("도 서 등 록 현 황");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		name_panel = new JPanel();
		name_panel.setBackground(SystemColor.activeCaption);
		name_panel.add(name_label);
		content_panel.add(name_panel, BorderLayout.NORTH);
				
		scrollPane = new JScrollPane();
		book_table = new JTable(model);
		book_pane = new JScrollPane(book_table);
		scrollPane.setViewportView(book_pane);		
		content_panel.add(scrollPane, BorderLayout.CENTER);		
		book_pane.setEnabled(false);
		
		content_panel.setVisible(true);
		frame.getContentPane().add(content_panel);
		frame.getContentPane().setBackground(new Color(255, 245, 238));		
		
		/** 버튼 이벤트 처리 **/		
		btn_delete.addActionListener(this);
		btn_logout.addActionListener(this);
		btn_insert.addActionListener(this);
		btn_user_select.addActionListener(this);
		btn_board.addActionListener(this);		
		btn_home.addActionListener(this);		
		
				
		/** 폰트 설정 (쓰실때 주석처리하시거나 본인 폰트로 설정하시면 됩니다! )**/
		btn_delete.setFont(Commons.getFont());
		btn_user_select.setFont(Commons.getFont());
		btn_board.setFont(Commons.getFont());
		title_label.setFont(Commons.getFont());
		btn_logout.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont());
		book_table.setFont(Commons.getFont());
		book_pane.setFont(Commons.getFont());
		btn_insert.setFont(Commons.getFont());
		
		/** 테이블 색 설정 **/
		JTableHeader head = book_table.getTableHeader();
		head.setBackground(new Color(173, 216, 230));
		head.setForeground(new Color(255,255,255));		
		
		 DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcmSchedule = book_table.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
			book_table.getColumnModel().getColumn(0).setPreferredWidth(10);
			book_table.getColumnModel().getColumn(1).setPreferredWidth(150);
			book_table.getColumnModel().getColumn(2).setPreferredWidth(30);
			book_table.getColumnModel().getColumn(3).setPreferredWidth(30);
			book_table.getColumnModel().getColumn(4).setPreferredWidth(30);
			book_table.getColumnModel().getColumn(5).setPreferredWidth(40);
			book_table.getColumnModel().getColumn(6).setPreferredWidth(20);
		
		
		/** 테이블에 DB 데이터 넣기 **/
		table_data();		
	}
	
	/** 스위칭 **/
	public void switching(int num) {
		content_panel.removeAll();
		content_panel.setVisible(false);
		btn_insert.setBackground(Color.LIGHT_GRAY);
		btn_delete.setBackground(Color.LIGHT_GRAY);
		btn_user_select.setBackground(Color.LIGHT_GRAY);
		btn_board.setBackground(Color.LIGHT_GRAY);
			switch(num) {
				case home :
					content_panel.setVisible(true);
					content_panel.add(name_panel, BorderLayout.NORTH);
					content_panel.add(BorderLayout.CENTER, scrollPane);
					table_data();
					break;
				case Insert :
					content_panel.setVisible(true);
					btn_insert.setBackground(new Color(255, 228, 225));
					break;
				case Delete :
					content_panel.setVisible(true);
					btn_delete.setBackground(new Color(255, 228, 225));
					break;
				case User_select :
					content_panel.setVisible(true);
					btn_user_select.setBackground(new Color(255, 228, 225));
					break;
				case Board :
					content_panel.setVisible(true);
					btn_board.setBackground(new Color(255, 228, 225));
					break;		
			}
		
	}
	
	public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
				if (obj.equals(btn_insert)) {			
					new Admin_InsertUI(Admin_MainUI.this); // 도서등록
				} else if (obj.equals(btn_delete)) {			
					new Admin_DeleteUI(Admin_MainUI.this); // 도서삭제
				} else if (obj.equals(btn_home)) {
					switching(home);
				} else if (obj.equals(btn_board)) {
					new Admin_BoardUI(Admin_MainUI.this); //게시판
				} else if (obj.equals(btn_user_select)) {
					new Admin_MemberViewsUI(Admin_MainUI.this); // 회원조회
				} else if (obj.equals(btn_logout)) { 
					int result = JOptionPane.showConfirmDialog(null, "정말로 로그아웃 하시겠습니까?"); // 로그아웃
					if (result == 0 ) {
						btn_panel.setVisible(false);
						top_panel.setVisible(false);
						content_panel.setVisible(false);
						main.login_panel.setVisible(true);
						new LoginUI();		
					}
			}
		
	}
	
	public void table_data() {
		model.setNumRows(0);
		book_table.removeAll();
		row = new Object[7];		
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		ArrayList<String> data_list = new ArrayList<String>();
		ArrayList<BookVO> countlist = new ArrayList<BookVO>();
		list = system.Admin_Select();
		for (BookVO book : list) {
			row[0] = book.getBno();
			row[1] = book.getBookname();
			row[2] = book.getAuthor();
			row[3] = book.getPblsh();
			row[4] = book.getPrice();
			row[5] = book.getPblshdate();			
			row[6] = 0;
			data_list.add(book.getBookname());
			model.addRow(row);			
		}		
		countlist = system.Admin_Count(data_list);
		for (BookVO vo : countlist) {			
		String name = vo.getBookname();
		for (int i = 0; i<data_list.size(); i++) {
				if (data_list.get(i).equals(name)) {				
					book_table.getModel().setValueAt(vo.getCount(), i, 6);						
				}				
			}
		}
		
		model.fireTableDataChanged();
			
	}
	
	
	
	
}
	

