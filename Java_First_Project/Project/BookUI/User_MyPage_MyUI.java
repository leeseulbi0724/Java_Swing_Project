package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import BookSystem.BookSystem;
import BookVO.BoardVO;
import BookVO.BookVO;
import Commons.Commons;

public class User_MyPage_MyUI extends JFrame implements MouseListener, ActionListener{
	User_MyPageUI main;
	JButton btn_delete;
	ArrayList<Object> list = new ArrayList<Object>();
	String[] colName = {"분류", "제목", "작성날짜"};
	DefaultTableModel model= new DefaultTableModel(colName, 0);
	JTable board_table = new JTable(model);
	Object[] row = new Object[5];
	String id;
	BookSystem system;
	int rowrow;
	BoardVO vo;
	ArrayList<BoardVO> boardlist;
	BookVO book;
	
	JPopupMenu popupMenu;
	JMenuItem view, update;
	JCheckBox checkbox_review, checkbox_board;
	
	public User_MyPage_MyUI(User_MyPageUI main) {
		super();		
		this.system = main.system;
		this.main = main;
		this.id = main.user_name;
		init();
	}	
	
	
	public void init() {
		main.switching(User_MyPageUI.My);
		
		JScrollPane scrollPane = new JScrollPane();					
		
		JPanel center_panel = new JPanel();
		main.content_panel.add(center_panel);
		center_panel.setLayout(new BorderLayout(0, 0));
		center_panel.setBounds(12, 35, 507, 268);
				
		center_panel.add(scrollPane, BorderLayout.CENTER);
		
		JScrollPane board_pane = new JScrollPane(board_table);
		scrollPane.setViewportView(board_pane);		
		board_pane.setEnabled(false);
		
		checkbox_board = new JCheckBox("게시판");
		checkbox_board.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//게시판 테이블 보이기
					boardData();
					model.setColumnIdentifiers(colName);
					board_table.setModel(model);
					board_table.setRowHeight(20);
					sort();
				}
			}
		});
		checkbox_board.setBounds(70, 6, 70, 23);
		main.content_panel.add(checkbox_board);
		
		
		checkbox_review = new JCheckBox("리뷰");
		checkbox_review.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					//리뷰 테이블 보이기
					reviewData();
					model.setColumnIdentifiers(colName);
					board_table.setModel(model);
					board_table.setRowHeight(20);
					sort();
				}
			}
		});
		checkbox_review.setSelected(true);
		checkbox_review.setBounds(12, 6, 54, 23);
		main.content_panel.add(checkbox_review);
		
		//체크박스 여러개 중 하나만 선택되도록 설정
		ButtonGroup bg = new ButtonGroup();
		bg.add(checkbox_board);		bg.add(checkbox_review);
		
		
		JButton btn_delete = new JButton("삭제");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btn_delete)) {
					//게시판에 체크되어 있으면 게시판삭제메소드로, 리뷰면 리뷰삭제메소드로
					if(checkbox_board.isSelected()) {
						deleteBoardData();
					} else if (checkbox_review.isSelected()) {
						deleteReviewData();
					}
				}
			}
		});
		btn_delete.setBounds(141, 6, 80, 23);
		main.content_panel.add(btn_delete);
		btn_delete.setBackground(Color.WHITE);
		
		
		JTableHeader head = board_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
		
		  popupMenu = new JPopupMenu();		  
		  view = new JMenuItem("내용보기");
		  view.addActionListener(this);
          popupMenu.add(view);          
          update = new JMenuItem("수정하기");
          update.addActionListener(this);
          popupMenu.add(update);          
          board_table.setComponentPopupMenu(popupMenu);         
          
          
          board_table.addMouseListener(this);
	
          /** 폰트설정 **/
          board_table.setFont(Commons.getFont());
          checkbox_board.setFont(Commons.getFont());
          checkbox_review.setFont(Commons.getFont());
          btn_delete.setFont(Commons.getFont());
          view.setFont(Commons.getFont());
          update.setFont(Commons.getFont());    
          
          sort();

          
	}//init
	
	public void sort() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = board_table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}

	
	//table에 출력되는 데이터 생성 - 일단은 게시판(카테고리, 제목, 내용, 날짜) 갖고오기
	public void boardData() {
		model.setNumRows(0);
		boardlist = system.All_Myboard(main.user_name);
		for(BoardVO board : boardlist) {
			row[0] = board.getCategory();
			row[1] = board.getTitle();
			row[2] = board.getDate();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
	}
	
	//table에 출력되는 데이터 생성 - 일단은 리뷰(카테고리, 제목, 내용, 날짜) 갖고오기
	public void reviewData() {
		model.setNumRows(0);
		boardlist = system.All_Myreview(main.user_name);
		for(BoardVO board : boardlist) {
			row[0] = board.getCategory();
			row[1] = board.getTitle();
			row[2] = board.getDate();
			
			model.addRow(row);
		}
		model.fireTableDataChanged();
		sort();
	}

	//table에서 삭제하는 이벤트 - 게시판 글 삭제 메소드
	public void deleteBoardData() {
		ArrayList<BoardVO> boardlist = system.All_Myboard(main.user_name);
		
		if(rowrow == -1) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("삭제할 게시글을 선택해주세요. "));
		}else {
			int confirm = JOptionPane.showConfirmDialog(null, Commons.getMsg("정말로 삭제하시겠습니까?"));
			if (confirm == 0) {
				int result = system.MyDelete_Board(boardlist.get(board_table.getSelectedRow()).getBid());
				if (result != 0) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("게시글이 삭제되었습니다."));
					init();
				}				
			}			
		}
	}
	
	
	//table에서 삭제하는 이벤트 - 리뷰 삭제 메소드
	public void deleteReviewData() {
		ArrayList<BoardVO> boardlist = system.All_Myreview(main.user_name);		
		if (rowrow == -1) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("삭제할 리뷰를 선택해주세요. "));
		} else {
			int confirm = JOptionPane.showConfirmDialog(null, Commons.getMsg("정말로 삭제하시겠습니까?"));
			if (confirm == 0) {
				int result = system.MyDelete_Review(boardlist.get(board_table.getSelectedRow()).getBid());
				if (result != 0) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("리뷰가 삭제되었습니다."));
					init();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//선택한 행 가져오는 액션 이벤트
		rowrow = board_table.getSelectedRow();	
		TableModel data = board_table.getModel();
		 String name = (String)data.getValueAt(rowrow, 1);
		 book = new BookVO();
		 book.setBookname(name);		
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
		JMenuItem menu = (JMenuItem) e.getSource();
		// 내용보기
		if (menu == view) {
			if(checkbox_board.isSelected()) {
				String bid = boardlist.get(board_table.getSelectedRow()).getBid();
				BoardVO vo = system.board_result(bid);	
				User_Board_ContentUI ui = new User_Board_ContentUI(vo, id, main.frame, system);
				ui.setVisible(true);				
			} if(checkbox_review.isSelected()) {				
				User_BookReviewUI review = new User_BookReviewUI(main.frame, book.getBookname(), system);
				review.setVisible(true);				
			}
		} else if (menu == update) {
			if(checkbox_board.isSelected()) {	
				String bid = boardlist.get(board_table.getSelectedRow()).getBid();
				String category = "수정";
				BoardVO vo = system.board_result(bid);	
				User_WriteUI ui = new User_WriteUI(bid, category, vo, main.main, main.frame);
				ui.setVisible(true);				
			} else if (checkbox_review.isSelected()) {
				String rid = boardlist.get(board_table.getSelectedRow()).getBid();
				String category = "수정";
				User_ReviewWriteUI review = new User_ReviewWriteUI(rid, category, main.frame, book.getBookname(), id, system);
				review.setVisible(true);				
			}
		}
		
	}
		
		
}//outer class
