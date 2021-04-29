package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BookDAO.DBConn;
import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_BookListSearchUI implements ActionListener, MouseListener, KeyListener  {

	JFrame f;
	JPanel bookViewPanel;
	JLabel lblNewLabel;
	JTextField search_tf;
	User_MainUI main;
	
	DBConn booklist;
	JPanel tablePanel;
	JTable table;
	JButton btn_basket, btn_review;
	BookVO vo;
	String name;
	BookSystem system;
	
	public User_BookListSearchUI(User_MainUI main) {
		this.system = main.system;
		this.name = main.name;
		this.main = main;
		this.f=main.f;
		init();
	}
	

	
	public void init() {
		main.switching(User_MainUI.BOOKLIST);	
		
		String[] header = new String[] {"도서번호","도서명","저자","출판사","가격"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		bookViewPanel = new JPanel();
		bookViewPanel.setBackground(new Color(255, 240, 245));
		bookViewPanel.setBounds(133, 10, 531, 341);
		f.getContentPane().add(bookViewPanel);
		bookViewPanel.setLayout(new BorderLayout());
		
		JPanel top_panel = new JPanel(new BorderLayout());
		lblNewLabel = new JLabel("도서명 검색 >");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 10, 79, 30);
		
		search_tf = new JTextField();
		search_tf.setBackground(Color.WHITE);
		search_tf.setBounds(161, 10, 232, 30);
		search_tf.setColumns(20);
		
		JPanel title_panel = new JPanel();
		JLabel title_label = new JLabel("도 서 전 체 리 스 트");
		title_panel.add(title_label);
		top_panel.add(BorderLayout.NORTH, title_panel);			
		
		JPanel search_panel = new JPanel();
		search_panel.add(lblNewLabel);		search_panel.add(search_tf);
		top_panel.add(BorderLayout.CENTER, search_panel);
		bookViewPanel.add(BorderLayout.NORTH, top_panel);
		
		JPanel btn_panel = new JPanel();
		btn_basket = new JButton("장바구니");
		btn_review = new JButton("리뷰보기");
		
		btn_panel.add(btn_review);		btn_panel.add(btn_basket);
		bookViewPanel.add(BorderLayout.SOUTH, btn_panel);		
		
		table = new JTable(model);
		table.setRowHeight(30);
		table.setPreferredScrollableViewportSize(new Dimension(400,400));		

		JScrollPane board_pane = new JScrollPane(table);
		bookViewPanel.add(BorderLayout.CENTER, board_pane);		
		
		/** 배경 설정 **/
		title_panel.setBackground(Color.WHITE);
		top_panel.setBackground(Color.WHITE);
		search_panel.setBackground(Color.WHITE);
		btn_panel.setBackground(Color.WHITE);
		btn_basket.setBackground(Color.WHITE);
		btn_review.setBackground(Color.WHITE);
		
		/** 폰트설정 **/
		lblNewLabel.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());
		table.setFont(Commons.getFont());
		title_label.setFont(Commons.getFont(18));
		btn_basket.setFont(Commons.getFont());
		btn_review.setFont(Commons.getFont());
		
		/** 테이블 모양 헤더 변경 **/
		JTableHeader head = table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));
		
		/** 버튼 이벤트 **/
		btn_basket.addActionListener(this);
		btn_review.addActionListener(this);
		table.addMouseListener(this);
		table.addKeyListener(this);
		
		/** 테이블 검색 기능 **/
		search_tf.addKeyListener(this);
		
		/** 테이블 데이터 불러오기 **/
		Object row[] = new Object[5];
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		list = system.Admin_Select();
		for ( BookVO vo : list ) {
			row[0] = vo.getBno();
			row[1] = vo.getBookname();
			row[2] = vo.getAuthor();
			row[3] = vo.getPblsh();
			row[4] = vo.getPrice();			
			model.addRow(row);
		}		
		
		bookViewPanel.setVisible(true);
		main.mainPanel.add(bookViewPanel);		
		

		}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_basket)) {
			if (vo != null ) {
				User_BookBasketUI basket = new User_BookBasketUI(f, vo, name, system);
				basket.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("책을 선택해주세요"));
			}			
		} else if (obj.equals(btn_review)) {
			if (vo != null) {
				User_BookReviewUI review = new User_BookReviewUI(f, vo.getBookname(), system);
				review.setVisible(true);				
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("책을 선택해주세요"));
			}	
		}
	}

	public void mouseClicked(MouseEvent e) {
		//선택한 셀의 행 번호계산 
		  int row = table.getSelectedRow();			
		  //테이블의 모델객체 얻어오기
		  TableModel data = table.getModel();	
		  String name = (String)data.getValueAt(row,1);
		  int price = (int) data.getValueAt(row,4);			  
		  vo = new BookVO();
		  vo.setBookname(name);
		  vo.setPrice(price);
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
	@Override
	public void keyPressed(KeyEvent e) {	
		Object obj = e.getSource();
		 if (obj.equals(table)) {
			String name = (String)table.getValueAt(table.getSelectedRow(), 1);
			int price = (int)table.getValueAt(table.getSelectedRow(), 4);
			vo.setBookname(name);
			vo.setPrice(price);					
		}	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		Object obj = e.getSource();
		if (obj.equals(search_tf)) {
			String search = search_tf.getText();
			TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(trs);			
			trs.setRowFilter(RowFilter.regexFilter(search));				
		} 
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
