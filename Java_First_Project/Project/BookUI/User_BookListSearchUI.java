package BookUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class User_BookListSearchUI {

	JFrame f;
	JPanel bookViewPanel;
	JLabel lblNewLabel;
	JTextField search_tf;
	User_MainUI main;
	DBConn booklist;
	JPanel tablePanel;
	JTable table;
	BookSystem system = new BookSystem();

	
	public User_BookListSearchUI(User_MainUI main) {
		this.main = main;
		this.f=main.f;
		init();
	}

	
	public void init() {
		main.switching(User_MainUI.BOOKLIST);	
		
		String[] header = new String[] {"도서번호","이름","저자","출판사","가격"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		bookViewPanel = new JPanel();
		bookViewPanel.setBackground(Color.WHITE);
		bookViewPanel.setBounds(133, 10, 531, 341);
		f.getContentPane().add(bookViewPanel);
		bookViewPanel.setLayout(null);
		
		lblNewLabel = new JLabel("검색");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 10, 79, 30);
		bookViewPanel.add(lblNewLabel);
		
		search_tf = new JTextField();
		search_tf.setBackground(Color.WHITE);
		search_tf.setBounds(161, 10, 232, 30);
		bookViewPanel.add(search_tf);
		search_tf.setColumns(10);
		
		tablePanel = new JPanel();
		tablePanel.setBounds(47, 78, 431, 223);
		bookViewPanel.add(tablePanel);		

		table = new JTable(model);
		table.setBounds(31, 319, 743, 300);

		table.setBounds(47, 78, 431, 223);
		table.setRowHeight(30);
		table.setPreferredScrollableViewportSize(new Dimension(400,400));
		tablePanel.add(new JScrollPane(table));
		tablePanel.setOpaque(false);		
		
		/** 폰트설정 **/
		lblNewLabel.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());
		table.setFont(Commons.getFont());
		
		/** 테이블 모양 헤더 변경 **/
		JTableHeader head = table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));
		
		/** 테이블 검색 기능 **/
		search_tf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String search = search_tf.getText();
				TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		
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
}
