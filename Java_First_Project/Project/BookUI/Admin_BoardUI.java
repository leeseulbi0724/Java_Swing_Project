package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BookDAO.BoardDAO;
import BookSystem.BookSystem;
import BookVO.BoardVO;
import Commons.Commons;

public class Admin_BoardUI implements ActionListener, MouseListener {
	JFrame frame;
	Admin_MainUI main;
	User_MainUI ui;
	JScrollPane scrollPane;
	ArrayList<BoardVO> blist;
	BookSystem system = new BookSystem();
	BoardDAO bdao = new BoardDAO();
	JTable board_table;
	String name = "관리자";
	User_Board_ContentUI user;
	
	public Admin_BoardUI(Admin_MainUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}
	
	
	public void init() {
		main.switching(Admin_MainUI.Board);

		JLabel label = new JLabel("게 시 판");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		main.content_panel.add(label, BorderLayout.NORTH);
		label.setBackground(SystemColor.activeCaption);
		label.setFont(Commons.getFont());
		board_data();
	}
	
	public void board_data() {
		blist = bdao.getBoardSelect();
		String [] colNames = new String [] {"번호","제목","내용","작성자"};
		Object[][] rowDatas = new Object[blist.size()][colNames.length];
		 for (int i = 0; i < blist.size(); i++) {
	            rowDatas[i] = new Object[] {
	                blist.get(i).getRownum(),
	                blist.get(i).getTitle(),
	                blist.get(i).getContent(),
	                blist.get(i).getId()
	              
	            };
	        }
		 
		 board_table = new JTable();
		 board_table.setModel(new DefaultTableModel(rowDatas,colNames) {
	            boolean[] columnEditables = new boolean[] {
	                false, false, false, true, false
	            };
	        });
		 
		 board_table.getColumnModel().getColumn(0).setResizable(false);
	     board_table.getColumnModel().getColumn(0).setPreferredWidth(50);
	     board_table.getColumnModel().getColumn(1).setResizable(false);
	     board_table.getColumnModel().getColumn(1).setPreferredWidth(90);
	     board_table.getColumnModel().getColumn(2).setResizable(false);
	     board_table.getColumnModel().getColumn(2).setPreferredWidth(270);
	     board_table.getColumnModel().getColumn(3).setResizable(false);
	     board_table.getColumnModel().getColumn(3).setPreferredWidth(50);
	     
	     JScrollPane board_pane = new JScrollPane(board_table);
		 board_pane.setEnabled(false);
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setViewportView(board_pane);
		 main.content_panel.add(scrollPane, BorderLayout.CENTER);
		    
		 /** 테이블 색 설정 **/
		 JTableHeader head = board_table.getTableHeader();
		 head.setBackground(new Color(173, 216, 230));
		 head.setForeground(new Color(255,255,255));		
		 board_table.setFont(Commons.getFont());
		 board_table.addMouseListener(this);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1 ) { // 한번 클릭
			  //선택한 셀의 행 번호계산 
//			  int row = board_table.getSelectedRow();		  
			  //테이블의 모델객체 얻어오기
//			  TableModel data = board_table.getModel();		
			
			String bid = blist.get(board_table.getSelectedRow()).getBid();
			BoardVO vo = system.board_result(bid);	
			User_Board_ContentUI ui = new User_Board_ContentUI(vo, name, frame);
			ui.setVisible(true);
		}			
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}