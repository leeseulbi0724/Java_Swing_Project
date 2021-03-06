package BookUI;//

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

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
	BookSystem system;
	JTable board_table;
	String name = "관리자";
	
	public Admin_BoardUI(Admin_MainUI main) {
		this.system = main.system;
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
		blist = system.getBoardSelect();
		String [] colNames = new String [] {"번호","제목","작성자"};
		Object[][] rowDatas = new Object[blist.size()][colNames.length];
		 for (int i = 0; i < blist.size(); i++) {
	            rowDatas[i] = new Object[] {
	                blist.get(i).getRownum(),
	                blist.get(i).getTitle(),
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
	     board_table.getColumnModel().getColumn(2).setResizable(false);
	     board_table.getColumnModel().getColumn(2).setPreferredWidth(90);
	     board_table.getColumnModel().getColumn(1).setResizable(false);
	     board_table.getColumnModel().getColumn(1).setPreferredWidth(270);
	     
		 JScrollPane scrollPane = new JScrollPane(board_table);
		 scrollPane.setViewportView(board_table);
		 main.content_panel.add(scrollPane, BorderLayout.CENTER);
		    
		 /** 테이블 색 설정 **/
		 JTableHeader head = board_table.getTableHeader();
		 head.setBackground(new Color(173, 216, 230));
		 head.setForeground(new Color(255,255,255));		
		 board_table.setFont(Commons.getFont());
		 board_table.addMouseListener(this);
		 
		 DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcmSchedule = board_table.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1 ) {			
			String bid = blist.get(board_table.getSelectedRow()).getBid();
			BoardVO vo = system.board_result(bid);	
			User_Board_ContentUI ui = new User_Board_ContentUI(vo, name, frame, system);
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