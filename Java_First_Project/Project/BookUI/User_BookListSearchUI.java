package BookUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BookDAO.BookListDAO;

public class User_BookListSearchUI {

	JFrame f;
	JPanel bookViewPanel;
	JLabel lblNewLabel;
	JTextField search_tf;
	User_MainUI main;
	BookListDAO booklist;
	JPanel tablePanel;
	JTable table;

	
	public User_BookListSearchUI(User_MainUI main) {
		this.main = main;
		this.f=main.f;
		init();
	}

	
	public void init() {
		booklist = new BookListDAO();
		String[][] data = booklist.getBookList();
		String[] header = new String[] {"ISBN","이름","저자","출판사"};
		
		bookViewPanel = new JPanel();
		bookViewPanel.setBackground(Color.WHITE);
		bookViewPanel.setBounds(0, 109, 804, 431);
		f.getContentPane().add(bookViewPanel);
		bookViewPanel.setLayout(null);
		
		lblNewLabel = new JLabel("\uAC80\uC0C9");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(85, 10, 172, 39);
		bookViewPanel.add(lblNewLabel);
		
		search_tf = new JTextField();
		search_tf.setBackground(Color.WHITE);
		search_tf.setBounds(269, 10, 347, 39);
		bookViewPanel.add(search_tf);
		search_tf.setColumns(10);
		
		tablePanel = new JPanel();
		tablePanel.setBounds(0, 88, 804, 343);
		bookViewPanel.add(tablePanel);
		
		table = new JTable(data,header);
		table.setBounds(31, 319, 743, 300);
		table.setRowHeight(30);
		table.setPreferredScrollableViewportSize(new Dimension(700,250));
		tablePanel.add(new JScrollPane(table));
		tablePanel.setOpaque(false);
		
		//테이블헤더 모양변경
		JTableHeader head = table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));
		
		//테이블 검색기능
		search_tf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String search = search_tf.getText();
				TableRowSorter<TableModel> trs = new TableRowSorter<TableModel>(table.getModel());
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		
		}
}
