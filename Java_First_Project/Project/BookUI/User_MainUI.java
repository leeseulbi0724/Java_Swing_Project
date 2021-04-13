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
	JPanel book1Panel,book2Panel,mainPanel,topPanel,bookViewPanel;	
	//탑패널 버튼
	JButton logout_btn,myPage_btn,board_btn,home_btn;	
	//메인패널 버튼
	JButton book1_btn,book2_btn,bookList_btn;	
	//첫번째 추천도서 버튼
	JButton lookReview_btn, putIn_btn;
	//두번쨰 추천도서 버튼
	JButton lookReview_btn2,putIn_btn2;	
	//장바구니
	JTextField bsBookName_tf,bsBookPrice_tf;	
	//book1
	JTextField bookNumber_tf,bookName_tf,bookAuthor_tf,publisher_tf,issueDay_tf,price_tf;	
	//book2
	JTextField bookNumber_tf2,bookName_tf2,bookAuthor_tf2,publisher_tf2,issueDay_tf2,price_tf2;
	//홈 화면 눌렀을 때 초기화되었기 때문에 다시 넣어줘야함
	JLabel recommendIconLabel;
	
	
	
	public static final int HOME = 0;
	public static final int BOOK1 = 1;
	public static final int BOOK2 = 2;
	public static final int BOOKLIST = 3;
	public static final int BOARD = 4;
	public static final int MYPAGE = 5;	
	public static final int book1 = 6;
	public static final int book2 = 7;
	
	//Constructor	
	public User_MainUI(LoginUI main) {
		//메인 프레임을 가져와서 여기서 쓰게함
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
		
		bookList_btn = new JButton("\uB3C4\uC11C\uC870\uD68C");
		bookList_btn.setForeground(Color.WHITE);
		bookList_btn.setBackground(Color.PINK);
		bookList_btn.setBounds(257, 337, 263, 47);
		mainPanel.add(bookList_btn);
		
		/*
		 * 첫번째 추천도서
		 */
		book1Panel = new JPanel();
		book1Panel.setBackground(Color.WHITE);
		book1Panel.setBounds(0, 109, 804, 431);
		f.getContentPane().add(book1Panel);
		book1Panel.setLayout(null);
		book1Panel.setVisible(false);
		
		JLabel book1imgLabel = new JLabel("");
		book1imgLabel.setIcon(new ImageIcon("images/book1.jfif"));
		book1imgLabel.setBounds(75, 54, 111, 155);
		book1Panel.add(book1imgLabel);
		
		JLabel bookNumberLabel = new JLabel("도서번호");
		bookNumberLabel.setBounds(431, 54, 111, 38);
		book1Panel.add(bookNumberLabel);
		
		JLabel bookNameLabel = new JLabel("도서명");
		bookNameLabel.setBounds(431, 102, 111, 38);
		book1Panel.add(bookNameLabel);
		
		JLabel bookAuthorLabel = new JLabel("저자");
		bookAuthorLabel.setBounds(431, 152, 111, 38);
		book1Panel.add(bookAuthorLabel);
		
		JLabel publisherLabel = new JLabel("출판사");
		publisherLabel.setBounds(431, 200, 111, 38);
		book1Panel.add(publisherLabel);
		
		JLabel issueDayLabel = new JLabel("발행일");
		issueDayLabel.setBounds(431, 248, 111, 38);
		book1Panel.add(issueDayLabel);
		
		JLabel priceLabel = new JLabel("가격");
		priceLabel.setBounds(431, 296, 111, 38);
		book1Panel.add(priceLabel);
		
		JLabel bookContentLabel = new JLabel("도서내용");
		bookContentLabel.setBounds(75, 219, 111, 38);
		book1Panel.add(bookContentLabel);
		
		JTextArea bookContent_ta = new JTextArea();
		bookContent_ta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		bookContent_ta.setBounds(30, 271, 295, 119);
		bookContent_ta.setEnabled(false);
		book1Panel.add(bookContent_ta);
		
		lookReview_btn = new JButton("리뷰보기");
		lookReview_btn.setForeground(Color.WHITE);
		lookReview_btn.setBackground(Color.PINK);
		lookReview_btn.setBounds(424, 344, 160, 46);
		book1Panel.add(lookReview_btn);
		
		putIn_btn = new JButton("장바구니 담기");
		putIn_btn.setForeground(Color.WHITE);
		putIn_btn.setBackground(Color.PINK);
		putIn_btn.setBounds(596, 344, 160, 46);
		book1Panel.add(putIn_btn);
		
		bookNumber_tf = new JTextField();
		bookNumber_tf.setBounds(526, 54, 228, 38);
		book1Panel.add(bookNumber_tf);
		bookNumber_tf.setColumns(10);
		bookNumber_tf.setEnabled(false);
		
		bookName_tf = new JTextField();
		bookName_tf.setColumns(10);
		bookName_tf.setBounds(526, 102, 228, 38);
		book1Panel.add(bookName_tf);
		bookName_tf.setEnabled(false);
		
		bookAuthor_tf = new JTextField();
		bookAuthor_tf.setColumns(10);
		bookAuthor_tf.setBounds(528, 150, 228, 38);
		book1Panel.add(bookAuthor_tf);
		bookAuthor_tf.setEnabled(false);
		
		publisher_tf = new JTextField();
		publisher_tf.setColumns(10);
		publisher_tf.setBounds(528, 200, 228, 38);
		book1Panel.add(publisher_tf);
		publisher_tf.setEnabled(false);
		
		issueDay_tf = new JTextField();
		issueDay_tf.setColumns(10);
		issueDay_tf.setBounds(526, 248, 228, 38);
		book1Panel.add(issueDay_tf);
		issueDay_tf.setEnabled(false);
		
		price_tf = new JTextField();
		price_tf.setColumns(10);
		price_tf.setBounds(528, 296, 228, 38);
		book1Panel.add(price_tf);
		price_tf.setEnabled(false);
		
		/*
		 * 두번째 추천도서
		 */
		book2Panel = new JPanel();
		book2Panel.setBackground(Color.WHITE);
		book2Panel.setBounds(0, 109, 804, 431);
		f.getContentPane().add(book2Panel);
		book2Panel.setLayout(null);
		book2Panel.setVisible(false);
		
		JLabel book1imgLabel2 = new JLabel("");
		book1imgLabel2.setIcon(new ImageIcon("images/javaimg.jfif"));
		book1imgLabel2.setBounds(75, 54, 111, 155);
		book2Panel.add(book1imgLabel2);
		
		JLabel bookNumberLabel2 = new JLabel("\uB3C4\uC11C\uBC88\uD638");
		bookNumberLabel2.setBounds(431, 54, 111, 38);
		book2Panel.add(bookNumberLabel2);
		
		JLabel bookNameLabel2 = new JLabel("\uB3C4\uC11C\uBA85");
		bookNameLabel2.setBounds(431, 102, 111, 38);
		book2Panel.add(bookNameLabel2);
		
		JLabel bookAuthorLabel2 = new JLabel("\uC800\uC790");
		bookAuthorLabel2.setBounds(431, 152, 111, 38);
		book2Panel.add(bookAuthorLabel2);
		
		JLabel publisherLabel2 = new JLabel("\uCD9C\uD310\uC0AC");
		publisherLabel2.setBounds(431, 200, 111, 38);
		book2Panel.add(publisherLabel2);
		
		JLabel issueDayLabel2 = new JLabel("\uBC1C\uD589\uC77C");
		issueDayLabel2.setBounds(431, 248, 111, 38);
		book2Panel.add(issueDayLabel2);
		
		JLabel priceLabel2 = new JLabel("\uAC00\uACA9");
		priceLabel2.setBounds(431, 296, 111, 38);
		book2Panel.add(priceLabel2);
		
		JLabel bookContentLabel2 = new JLabel("\uB3C4\uC11C\uB0B4\uC6A9");
		bookContentLabel2.setBounds(75, 219, 111, 38);
		book2Panel.add(bookContentLabel2);
		
		JTextArea bookContent_ta2 = new JTextArea();
		bookContent_ta2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		bookContent_ta2.setBounds(30, 271, 295, 119);
		bookContent_ta2.setEnabled(false);
		book2Panel.add(bookContent_ta2);
		
		lookReview_btn2 = new JButton("\uB9AC\uBDF0\uBCF4\uAE30");
		lookReview_btn2.setForeground(Color.WHITE);
		lookReview_btn2.setBackground(Color.PINK);
		lookReview_btn2.setBounds(424, 344, 160, 46);
		book2Panel.add(lookReview_btn2);
		
		
		putIn_btn2 = new JButton("\uC7A5\uBC14\uAD6C\uB2C8 \uB2F4\uAE30");
		putIn_btn2.setForeground(Color.WHITE);
		putIn_btn2.setBackground(Color.PINK);
		putIn_btn2.setBounds(596, 344, 160, 46);
		book2Panel.add(putIn_btn2);
		
		bookNumber_tf2 = new JTextField();
		bookNumber_tf2.setBounds(526, 54, 228, 38);
		book2Panel.add(bookNumber_tf2);
		bookNumber_tf2.setColumns(10);
		bookNumber_tf2.setEnabled(false);
		
		bookName_tf2 = new JTextField();
		bookName_tf2.setColumns(10);
		bookName_tf2.setBounds(526, 102, 228, 38);
		book2Panel.add(bookName_tf2);
		bookName_tf2.setEnabled(false);
		
		bookAuthor_tf2 = new JTextField();
		bookAuthor_tf2.setColumns(10);
		bookAuthor_tf2.setBounds(528, 150, 228, 38);
		book2Panel.add(bookAuthor_tf2);
		bookAuthor_tf2.setEnabled(false);
		
		publisher_tf2 = new JTextField();
		publisher_tf2.setColumns(10);
		publisher_tf2.setBounds(528, 200, 228, 38);
		book2Panel.add(publisher_tf2);
		publisher_tf2.setEnabled(false);
		
		issueDay_tf2 = new JTextField();
		issueDay_tf2.setColumns(10);
		issueDay_tf2.setBounds(526, 248, 228, 38);
		book2Panel.add(issueDay_tf2);
		issueDay_tf2.setEnabled(false);
		
		price_tf2 = new JTextField();
		price_tf2.setColumns(10);
		price_tf2.setBounds(528, 296, 228, 38);
		book2Panel.add(price_tf2);
		price_tf2.setEnabled(false);
	
		
		/* 버튼 */
		home_btn.addActionListener(this);
		book1_btn.addActionListener(this);
		book2_btn.addActionListener(this);
		bookList_btn.addActionListener(this);
		lookReview_btn.addActionListener(this);
		putIn_btn.addActionListener(this);
		lookReview_btn2.addActionListener(this);
		putIn_btn2.addActionListener(this);
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
				recommendIconLabel.setBounds(282, 10, 209, 59);
				mainPanel.add(recommendIconLabel);		
				book1_btn.setBounds(214, 92, 114, 150);
				mainPanel.add(book1_btn);
				book2_btn.setBounds(443, 92, 114, 150);
				mainPanel.add(book2_btn);
				bookList_btn.setBounds(257, 337, 263, 47);
				mainPanel.add(bookList_btn);				
			case BOARD :
				mainPanel.setVisible(true);
			case MYPAGE :
				mainPanel.setVisible(true);
			case book1 :			
				book1Panel.setVisible(false);
			case book2 :
				book2Panel.setVisible(false);
		}
	}	
	
	
	/* 버튼액션 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(home_btn)) {		//홈 버튼
			switching(HOME);
		}else if(obj==book1_btn) {		// BOOK1 클릭시
			mainPanel.setVisible(false);
			book1Panel.setVisible(true);
		}else if(obj==book2_btn) {		// BOOK2 클릭시
			mainPanel.setVisible(false);
			book2Panel.setVisible(true);
		}else if(obj==bookList_btn) {	// 도서목록 버튼
			mainPanel.setVisible(false);
			new User_BookListSearchUI(User_MainUI.this);
		}else if(obj==lookReview_btn) {		//리뷰 버튼
			User_BookReviewUI review = new User_BookReviewUI(f);
			review.setVisible(true);
		}else if(obj==putIn_btn) {		//장바구니?
			User_BookBasketUI basket1 = new User_BookBasketUI(f);
			basket1.setVisible(true);
		}else if(obj==lookReview_btn2) {	//리뷰 버튼
			User_BookReviewUI review2 = new User_BookReviewUI(f);
			review2.setVisible(true);
		}else if(obj==putIn_btn2) {		//장바구니
			User_BookBasketUI basket2 = new User_BookBasketUI(f);
			basket2.setVisible(true);	
		}else if(obj.equals(board_btn)) {	//게시판 버튼 (O)
			new User_BoardUI(User_MainUI.this);			
		}else if(obj.equals(myPage_btn)) {		//마이페이지 버튼 (O)
			new User_MyPageUI(User_MainUI.this);			
		}else if(obj.equals(logout_btn)) {		//로그아웃 버튼 (O)
			int result = JOptionPane.showConfirmDialog(null,"정말로 로그아웃 하시겠습니까?");	
			if(result == 0) {
				book1Panel.setVisible(false);
				book2Panel.setVisible(false);
				mainPanel.setVisible(false);
				topPanel.setVisible(false);
				main.login_panel.setVisible(true);
				new LoginUI();
			}
		}
	}
}
