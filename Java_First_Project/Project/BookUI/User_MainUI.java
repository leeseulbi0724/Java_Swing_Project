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
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import BookDAO.MemberDAO;
import BookVO.MemberVO;
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
	String name;	
	// book1,book2 구별
	JTextArea Rankarea;
	JScrollPane scrollPane;
	public boolean flag;
	
	public static final int HOME = 0;
	public static final int BOOK = 1;
	public static final int BOOKLIST = 3;
	public static final int BOARD = 4;
	public static final int MYPAGE = 5;	
	
	MemberDAO dao = new MemberDAO();
	
	//Constructor	
	public User_MainUI(LoginUI main, String name) {
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
		content_panel.setBounds(12, 10, 768, 374);
		content_panel.setBackground(new Color(255, 240, 245));
		content_panel.setLayout(null);
		mainPanel.add(content_panel);
		
//		JLabel recommendIconLabel = new JLabel();
//		recommendIconLabel.setIcon(new ImageIcon("images/bestSeller.PNG"));
//		recommendIconLabel.setBounds(135, 10, 70, 70);
//		content_panel.add(recommendIconLabel);				
		
		book1_btn = new JButton();
		book1_btn.setBackground(new Color(255, 240, 245));
		book1_btn.setBorderPainted(false);
		book1_btn.setIcon(new ImageIcon("images/book1img.jpg"));
		book1_btn.setBounds(52, 118, 112, 172);
		content_panel.add(book1_btn);
		
		book2_btn = new JButton();
		book2_btn.setBackground(new Color(255, 240, 245));
		book2_btn.setBorderPainted(false);
		book2_btn.setIcon(new ImageIcon("images/book2img.jpg"));
		book2_btn.setBounds(188, 118, 112, 172);
		content_panel.add(book2_btn);		

		bookList_btn = new JButton("도 서 전 체 리 스 트");
		bookList_btn.setBackground(Color.PINK);
		bookList_btn.setBounds(285, 334, 201, 30);
		bookList_btn.setBorder(null);
//		bookList_btn.setFocusPainted(false);
		content_panel.add(bookList_btn);
		
		recommendText_Panel = new JPanel();
		recommendText_Panel.setLayout(new BorderLayout(0, 0));
		recommendText_Panel.setBackground(Color.WHITE);
		recommendText_Panel.setBounds(105, 78, 136, 30);
		content_panel.add(recommendText_Panel);
		
		JLabel recommendTextLabel = new JLabel("이달의 추천도서");
		recommendTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recommendText_Panel.add(recommendTextLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(517, 78, 136, 30);
		panel.setBackground(Color.WHITE);
		content_panel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));		
		
		JLabel lblNewLabel = new JLabel("이달의 구매왕");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);		
		
		Rankarea = new JTextArea();
		Rankarea.setEditable(false);
		Rankarea.setTabSize(6);
		
		scrollPane = new JScrollPane(Rankarea);
		scrollPane.setBounds(454, 118, 256, 80);
		scrollPane.setVisible(true);
		content_panel.add(scrollPane);

		
		
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
		recommendTextLabel.setFont(Commons.getFont());
		id_label.setFont(Commons.getFont());
		lblNewLabel.setFont(Commons.getFont());		
		
		user_order_rank();
		
	}//init
	
	public void user_order_rank() {
		ArrayList<MemberVO> list = dao.getRank();		
		for (MemberVO m : list) {
			Rankarea.append("\t [ "+m.getRno() + "등 ] " + m.getId() + "님 " + m.getCount() + "권 구매\n");
		}
	}


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
