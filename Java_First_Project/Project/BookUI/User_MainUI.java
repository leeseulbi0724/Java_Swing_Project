package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Commons.Commons;

public class User_MainUI implements ActionListener{

	//Field
	
	LoginUI main;
	JFrame f;
	JPanel mainPanel,topPanel;	
	//탑패널 버튼
	JButton logout_btn,myPage_btn,board_btn,home_btn;	
	//메인패널 버튼
	JButton book1_btn,book2_btn,bookList_btn;	
	//홈 화면 눌렀을 때 초기화되었기 때문에 다시 넣어줘야함
	JLabel recommendIconLabel;
	
	
	
	public static final int HOME = 0;
	public static final int BOOK = 1;
	public static final int BOOKLIST = 3;
	public static final int BOARD = 4;
	public static final int MYPAGE = 5;	
	
	
	//Constructor	
	public User_MainUI(LoginUI main) {
		this.main = main;
		this.f = main.frame;
		init();
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
		
		board_btn = new JButton("게시판");
		board_btn.setBackground(Color.WHITE);
		board_btn.setForeground(Color.PINK);
		board_btn.setBorder(null);
		board_btn.setBounds(430, 10, 105, 36);
		topPanel.add(board_btn);
		
		myPage_btn = new JButton("마이페이지");
		myPage_btn.setForeground(Color.PINK);
		myPage_btn.setBackground(Color.WHITE);
		myPage_btn.setBorder(null);
		myPage_btn.setBounds(547, 10, 113, 36);
		topPanel.add(myPage_btn);
		
		logout_btn = new JButton("로그아웃");
		logout_btn.setForeground(Color.PINK);
		logout_btn.setBackground(Color.WHITE);
		logout_btn.setBorder(null);
		logout_btn.setBounds(672, 10, 105, 36);
		topPanel.add(logout_btn);
	
		home_btn = new JButton("");
		home_btn.setIcon(new ImageIcon("images/pngwing.com.png"));
		home_btn.setBackground(Color.WHITE);
		home_btn.setBorderPainted(false);
		home_btn.setBounds(12, 10, 105, 36);
		topPanel.add(home_btn);
		
		
		/*
		 * 메인 패널
		 */
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 109, 804, 431);
		f.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
				
		recommendIconLabel = new JLabel("");
		recommendIconLabel.setIcon(new ImageIcon("images/recommendIcon.PNG"));
		recommendIconLabel.setBounds(282, 10, 209, 59);
		mainPanel.add(recommendIconLabel);		
		
		book1_btn = new JButton("");
		book1_btn.setBackground(Color.WHITE);
		book1_btn.setIcon(new ImageIcon("images/book1.jfif"));
		book1_btn.setBounds(214, 92, 114, 150);
		mainPanel.add(book1_btn);
		
		book2_btn = new JButton("");
		book2_btn.setIcon(new ImageIcon("images/javaimg.jfif"));
		book2_btn.setBackground(Color.WHITE);
		book2_btn.setBounds(443, 92, 114, 150);
		mainPanel.add(book2_btn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(170, 304, 434, 2);
		mainPanel.add(separator);
		
		bookList_btn = new JButton("도서조회");
		bookList_btn.setForeground(Color.WHITE);
		bookList_btn.setBackground(Color.PINK);
		bookList_btn.setBounds(257, 337, 263, 47);
		mainPanel.add(bookList_btn);
		
		/* 버튼 */
		home_btn.addActionListener(this);
		book1_btn.addActionListener(this);
		book2_btn.addActionListener(this);
		bookList_btn.addActionListener(this);
		board_btn.addActionListener(this);
		myPage_btn.addActionListener(this);
		logout_btn.addActionListener(this);
		
		
		/** 폰트 **/
		myPage_btn.setFont(Commons.getFont());
		logout_btn.setFont(Commons.getFont());
		board_btn.setFont(Commons.getFont());
		bookList_btn.setFont(Commons.getFont());
		
	}//init


	public void switching(int num) {
		mainPanel.removeAll();
		mainPanel.setVisible(false);
		switch(num) {
			case HOME :
				mainPanel.setVisible(true);
				//리셋되는 버튼들 새로 생성하는것
				createContent();
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
				new User_BookRecommendUI(User_MainUI.this);
			}else if(obj==book2_btn) {		// BOOK2 클릭시
				new User_BookRecommendUI(User_MainUI.this);
			}else if(obj==bookList_btn) {	// 도서목록 버튼
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
			}
		}
		
		//스위치에서 홈버튼 누르면 버튼 새로 생성해야하는거 좀 지저분해서 여따 옮겨봤어요 다시 옮기셔도 돼요 ^^
		public void createContent() {
			recommendIconLabel.setBounds(282, 10, 209, 59);
			mainPanel.add(recommendIconLabel);		
			book1_btn.setBounds(214, 92, 114, 150);
			mainPanel.add(book1_btn);
			book2_btn.setBounds(443, 92, 114, 150);
			mainPanel.add(book2_btn);
			bookList_btn.setBounds(257, 337, 263, 47);
			mainPanel.add(bookList_btn);
		}
	
}
