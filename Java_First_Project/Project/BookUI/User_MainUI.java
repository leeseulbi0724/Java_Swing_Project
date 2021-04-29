package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BookDAO.BookDAO;
import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_MainUI implements ActionListener{

	/////Field	
	LoginUI main;
	JFrame f;
	JPanel topPanel;
	JPanel mainPanel;
	JPanel recommendText_Panel;
	JPanel content_panel;
	//탑패널 버튼
	JButton logout_btn,myPage_btn,board_btn,home_btn;	
	//메인패널 버튼
	JButton book1_btn,book2_btn,bookList_btn;	
	//홍보 도서 버튼
	JButton Label_01,Label_02,Label_03;
	String name;	
	// book1,book2 구별
	JTextArea Rankarea;
	JScrollPane scrollPane;
	public boolean flag;
	
	BookSystem system;
	
	DefaultTableModel model;
	JTable rank_table;
	
	public static final int HOME = 0;
	public static final int BOOK = 1;
	public static final int BOOKLIST = 3;
	public static final int BOARD = 4;
	public static final int MYPAGE = 5;
	private static final Object[][]  Object = null;	
	

	
	//Constructor	
	public User_MainUI(LoginUI main, String name) {
		this.system = main.system;
		this.main = main;
		this.f = main.frame;
		this.name = name;
		init();
		
	}
	
	public User_MainUI() {
	
	}

	
	//Method
	public void init() {
		
		/*
		 * 탑패널
		 */
		topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		topPanel.setBounds(0, 0, 804, 110);
		f.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		JLabel id_label = new JLabel();
		id_label.setBounds(120, 0, 187, 64);
		id_label.setText(name+"님 환영합니다");
		id_label.setForeground(Color.pink);
		id_label.setBackground(Color.WHITE);
		topPanel.add(id_label);
		
		board_btn = new RoundedButton("게시판");
		board_btn.setBorder(null);
		board_btn.setFocusPainted(false);
		board_btn.setBounds(430, 10, 105, 36);
		topPanel.add(board_btn);
		
		myPage_btn = new RoundedButton("마이페이지");
		myPage_btn.setForeground(Color.PINK);
		myPage_btn.setBackground(Color.WHITE);
		myPage_btn.setBorder(null);
		myPage_btn.setFocusPainted(false);
		myPage_btn.setBounds(547, 10, 105, 36);
		topPanel.add(myPage_btn);
		
		logout_btn = new RoundedButton("로그아웃");
		logout_btn.setForeground(Color.PINK);
		logout_btn.setBackground(Color.WHITE);
		logout_btn.setBorder(null);
		logout_btn.setFocusPainted(false);
		logout_btn.setBounds(672, 10, 105, 36);
		topPanel.add(logout_btn);
	
		home_btn = new JButton("");
		home_btn.setIcon(new ImageIcon("images/homeBtn.png"));
		home_btn.setPressedIcon(new ImageIcon("images/home_Btn2.png"));
		home_btn.setBackground(Color.WHITE);
		home_btn.setBorderPainted(false);
		home_btn.addActionListener(this);
		home_btn.setFocusPainted(false);
		home_btn.setBounds(12, 20, 105, 40);
		topPanel.add(home_btn);
		
		
		/*
		 * 메인 패널
		 */
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 240, 245));
		mainPanel.setBounds(0, 109, 804, 491);
		f.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		content_panel = new JPanel();
		content_panel.setBounds(0, 10, 804, 386);
		content_panel.setBackground(new Color(255, 240, 245));
		content_panel.setLayout(null);
		mainPanel.add(content_panel);				
		
		JPanel recommend_panel = new JPanel();
		recommend_panel.setBounds(94, 100, 256, 235);
		recommend_panel.setBackground(Color.WHITE);
		recommend_panel.setBorder(new TitledBorder(new LineBorder(new Color(240, 128, 128),3)));
		content_panel.add(recommend_panel);
		recommend_panel.setLayout(null);
		
		book1_btn = new JButton();
		book1_btn.setBackground(Color.WHITE);
		book1_btn.setBorderPainted(false);
		book1_btn.setBorder(null);
		book1_btn.setIcon(new ImageIcon("images/book1_s.jpg"));
		book1_btn.setBounds(12, 53, 112, 172);
		recommend_panel.add(book1_btn);
		
		book2_btn = new JButton();
		book2_btn.setBackground(Color.WHITE);
		book2_btn.setBorderPainted(false);
		book2_btn.setBorder(null);
		book2_btn.setIcon(new ImageIcon("images/book2_s.jpg"));
		book2_btn.setBounds(132, 53, 112, 172);
		recommend_panel.add(book2_btn);		

		bookList_btn = new JButton("도 서 전 체 리 스 트");
		bookList_btn.setBackground(new Color(255, 228, 225));
		bookList_btn.setBounds(287, 346, 201, 20);
//		bookList_btn.setBorder(null);
		bookList_btn.setFocusPainted(false);
		content_panel.add(bookList_btn);
		
		recommendText_Panel = new JPanel();
		recommendText_Panel.setLayout(new BorderLayout(0, 0));
		recommendText_Panel.setBackground(Color.WHITE);
		recommendText_Panel.setBounds(60, 10, 136, 30);
		recommend_panel.add(recommendText_Panel);
		
		JLabel recommendTextLabel = new JLabel("이달의 추천도서");
		recommendTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recommendTextLabel.setForeground(new Color(240, 128, 128));
		recommendText_Panel.add(recommendTextLabel);
		
		JPanel bookrank_panel = new JPanel();
		bookrank_panel.setBounds(414, 100, 322, 235);
		bookrank_panel.setBackground(Color.WHITE);
		bookrank_panel.setBorder(new TitledBorder(new LineBorder(new Color(240, 128, 128),3)));
		content_panel.add(bookrank_panel);
		bookrank_panel.setLayout(null);
		
		JLabel rank_Label = new JLabel("이달의 베스트셀러");
		rank_Label.setHorizontalAlignment(SwingConstants.CENTER);
		rank_Label.setForeground(new Color(240, 128, 128));
		rank_Label.setBounds(66, 24, 180, 15);
		bookrank_panel.add(rank_Label);
		
		String[] colName = {"NO", "제목"};
		model = new DefaultTableModel(colName, 0);				
		rank_table = new JTable(model);
		rank_table.setEnabled(false);
		rank_table.setShowVerticalLines(false);
		rank_table.setShowHorizontalLines(false);
		scrollPane = new JScrollPane(rank_table);
		scrollPane.setBounds(35, 48, 256, 175);
		scrollPane.setVisible(true);
		bookrank_panel.add(scrollPane);	
		
		/** 가운데정렬, 헤더 색깔, 컬럼 길이 조절 **/
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = rank_table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		JTableHeader head = rank_table.getTableHeader();
		head.setBackground(new Color(255,192,203));
		head.setForeground(new Color(255,255,255));		
		rank_table.getColumnModel().getColumn(0).setPreferredWidth(1);
		rank_table.getColumnModel().getColumn(1).setPreferredWidth(180);
		
		
		ImageIcon icon01 = new ImageIcon("images/Label01.PNG");
		int h = icon01.getIconHeight();
		int w = icon01.getIconWidth();
//		ImageIcon imageSetSize(icon01, w, h) {
//			
//		}
		
		//
		Label_01 = new JButton();
		Label_01.setBounds(607, 0, 185, 90);
		Label_01.setIcon(icon01);
		Label_01.setBackground(new Color(255, 240, 245));
		Label_01.setBorderPainted(false);
		content_panel.add(Label_01);	
		Label_02 = new JButton();
		Label_02.setBounds(424, 0, 185, 90);
		Label_02.setIcon(new ImageIcon("images/Label02.PNG"));
		Label_02.setBackground(new Color(255, 240, 245));
		Label_02.setBorderPainted(false);
		content_panel.add(Label_02);
		Label_03 = new JButton();
		Label_03.setBounds(241, 0, 185, 90);
		Label_03.setIcon(new ImageIcon("images/Label03.PNG"));
		Label_03.setBackground(new Color(255, 240, 245));
		Label_03.setBorderPainted(false);
		content_panel.add(Label_03);
		JLabel Label_04 = new JLabel();
		Label_04.setBounds(0, 0, 201, 79);
		Label_04.setIcon(new ImageIcon("images/BOOk_S.PNG"));
		content_panel.add(Label_04);		
		
	
		/* 버튼 */
		home_btn.addActionListener(this);
		book1_btn.addActionListener(this);
		book2_btn.addActionListener(this);
		bookList_btn.addActionListener(this);
		board_btn.addActionListener(this);
		myPage_btn.addActionListener(this);
		logout_btn.addActionListener(this);
		Label_01.addActionListener(this);
		Label_02.addActionListener(this);
		Label_03.addActionListener(this);
		
		
		/** 폰트 **/
		myPage_btn.setFont(Commons.getFont());
		logout_btn.setFont(Commons.getFont());
		board_btn.setFont(Commons.getFont());
		bookList_btn.setFont(Commons.getFont());
		recommendTextLabel.setFont(Commons.getFont(15,"BOLD"));
		id_label.setFont(Commons.getFont());
		bookrank_panel.setFont(Commons.getFont());
		rank_Label.setFont(Commons.getFont(15,"BOLD"));
		
		user_order_rank();
		
	}//init
	
	private ImageIcon imageSetsize(ImageIcon icon01, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public void user_order_rank() {
		model.setRowCount(0);
		ArrayList<BookVO> list = system.getRank();		
		Object row[];
		for (BookVO b : list) {
			row = new Object[3];
			row[0] = b.getBno()+"등";
			row[1] = b.getBookname();
			
			model.addRow(row);			
		}
		model.fireTableDataChanged();
	}


	public void switching(int num) {
		mainPanel.removeAll();
		mainPanel.setVisible(false);
		switch(num) {
			case HOME :
				mainPanel.setVisible(true);
				//리셋되는 버튼들 새로 생성하는것
				createContent();
				user_order_rank();
			case BOARD :
				mainPanel.setVisible(true);
			case MYPAGE :
				mainPanel.setVisible(true);
			case BOOK :			
				mainPanel.setVisible(true);
			case BOOKLIST :
				mainPanel.setVisible(true);
		}
	}	
	
	
	/* 버튼액션 */
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj.equals(home_btn)) {		//홈 버튼
				switching(HOME);
			}else if(obj==book1_btn) {		// BOOK1 클릭시
				flag = false;
				new User_BookRecommendUI(User_MainUI.this,flag);
			}else if(obj==book2_btn) {
				flag = true;
				new User_BookRecommendUI(User_MainUI.this,flag);
			}
			else if(obj==bookList_btn) {	// 도서목록 버튼
				new User_BookListSearchUI(User_MainUI.this);
			}else if(obj.equals(board_btn)) {	//게시판 버튼 (O)
				new User_BoardUI(User_MainUI.this);			
			}else if(obj.equals(myPage_btn)) {		//마이페이지 버튼 (O)
				new User_MyPageUI(User_MainUI.this);			
			}else if(obj.equals(logout_btn)) {		//로그아웃 버튼 (O)
				int result = JOptionPane.showConfirmDialog(null,"정말로 로그아웃 하시겠습니까?");	
				if(result == 0) {
					mainPanel.setVisible(false);
					topPanel.setVisible(false);
					main.login_panel.setVisible(true);
					new LoginUI();
				}
			}else if(obj.equals(Label_01)) {
				try {
					String link = "https://book.naver.com/bookdb/book_detail.nhn?bid=13410363";
					URI uri = new URI(link);
			        java.awt.Desktop.getDesktop().browse(uri);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}else if(obj.equals(Label_02)) {
				try {
					String link = "https://book.naver.com/bookdb/book_detail.nhn?bid=10962071";
					URI uri = new URI(link);
			        java.awt.Desktop.getDesktop().browse(uri);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}else if(obj.equals(Label_03)) {
				try {
					String link = "https://book.naver.com/bookdb/book_detail.nhn?bid=9053751";
					URI uri = new URI(link);
			        java.awt.Desktop.getDesktop().browse(uri);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		//스위치에서 홈버튼 누르면 버튼 새로 생성해야하는거 좀 지저분해서 여따 옮겨봤어요 다시 옮기셔도 돼요 ^^
				public void createContent() {
					content_panel.setBounds(12, 10, 768, 374);
					mainPanel.add(content_panel);
				}
		
		/** 버튼 디자인 바꾸기 **/
		class RoundedButton extends JButton {
		      public RoundedButton() { super(); decorate(); } 
		      public RoundedButton(String text) { super(text); decorate(); } 
		      public RoundedButton(Action action) { super(action); decorate(); } 
		      public RoundedButton(Icon icon) { super(icon); decorate(); } 
		      public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
		      protected void decorate() { setBorderPainted(false); setOpaque(false); }
		      @Override 
		      protected void paintComponent(Graphics g) {
		         Color c=new Color(255, 240, 245); //배경색 결정
		         Color o=new Color(0,0,0); //글자색 결정
		         int width = getWidth(); 
		         int height = getHeight(); 
		         Graphics2D graphics = (Graphics2D) g; 
		         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		         if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
		         else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
		         else { graphics.setColor(c); } 
		         graphics.fillRoundRect(0, 0, width, height, 10, 10); 
		         FontMetrics fontMetrics = graphics.getFontMetrics(); 
		         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		         int textX = (width - stringBounds.width) / 2; 
		         int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
		         graphics.setColor(o); 
		         graphics.setFont(getFont()); 
		         graphics.drawString(getText(), textX, textY); 
		         graphics.dispose(); 
		         super.paintComponent(g); 
		         }
		      }
	
}
