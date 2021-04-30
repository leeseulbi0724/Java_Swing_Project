package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BookSystem.BookSystem;
import BookVO.BoardVO;
import Commons.Commons;



public class User_BookReviewUI extends JDialog{
	
	//Field
	User_BookRecommendUI main;
	User_BookListSearchUI mainB;
	String bookname;
	JTable review_table;
	BookSystem system;
	
	String category;

	//Constructor
	public User_BookReviewUI(Window parent, String bookname, BookSystem system) {		
		super(parent, "책 리뷰", ModalityType.APPLICATION_MODAL);
		this.system = system;
		this.bookname = bookname;
		ReviewCreate();
	}	
	
	//Method
	public void init() {		
		ReviewCreate();
	}
	
	public void ReviewCreate() {
		setBounds(0, 188, 600, 250);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);		
		
		Object[] header = {"작성자","별점","내용"};		
		DefaultTableModel model = new DefaultTableModel(header, 0);
		review_table = new JTable(model);
		JScrollPane review_pane = new JScrollPane(review_table);	
		JScrollPane scrollPane = new JScrollPane();		
		review_pane.setEnabled(false);
		scrollPane.setViewportView(review_pane);	
		scrollPane.setBackground(Color.WHITE);
		review_table.setShowVerticalLines(false);
		review_table.setShowHorizontalLines(false);
		review_table.setBackground(new Color(255,192,203));
		
		add(BorderLayout.CENTER, scrollPane);
		
		/** 폰트 **/
		review_table.setFont(Commons.getFont());		
		
		/** 테이블 모양 헤더 변경 **/
		JTableHeader head = review_table.getTableHeader();
		head.setBackground(Color.WHITE);		
		
		/** 테이블 리뷰 정보 불러오기 **/
		ArrayList<BoardVO> list  = system.All_Review(bookname);		
		if (list.size() !=  0) {
			Object row[];
			for (BoardVO vo : list) {
				row = new Object[3];
				row[0] = vo.getId();
				row[2] = vo.getContent();
				if (vo.getScore() == 0) {
					row[1] = "☆☆☆☆☆";
				} else if (vo.getScore() == 1) {
					row[1] = "★☆☆☆☆";
				} else if (vo.getScore() == 2) {
					row[1] = "★★☆☆☆";
				} else if (vo.getScore() == 3) {
					row[1] = "★★★☆☆";
				} else if (vo.getScore() == 4) {
					row[1] = "★★★★☆";
				} else if (vo.getScore() == 5) {
					row[1] = "★★★★★";
				} 			
				model.addRow(row);
			}		
		} else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("등록된 리뷰가 없습니다."));
		}
		
		
		/** 컬럼 길이조절, 가운데정렬 **/
		review_table.getColumnModel().getColumn(0).setPreferredWidth(10);
		review_table.getColumnModel().getColumn(1).setPreferredWidth(10);
		review_table.getColumnModel().getColumn(2).setPreferredWidth(200);
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = review_table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}		

}

}
