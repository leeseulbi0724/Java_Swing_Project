package BookUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BookDAO.BookDAO;
import BookSystem.BookSystem;
import BookVO.BookVO;
import Commons.Commons;

public class User_BookRecommendUI implements ActionListener {
	
		////Field
	
		JPanel bookPanel;
		User_MainUI main;
		JFrame f;
		JButton lookReview_btn,putIn_btn,lookReview_btn2;
		JTextField bookNumber_tf,bookName_tf,bookAuthor_tf,publisher_tf,issueDay_tf,price_tf;	
		JLabel bookNumberLabel,bookNameLabel,bookAuthorLabel,publisherLabel,priceLabel,issueDayLabel,bookimgLabel;
		BookDAO dao;
		BookVO book;
		String name;
		//book1, book2 구별
		public boolean flag;
		//User_BookReviewUI에서 book1, book2 구별
		static boolean tag;
		BookSystem system;
		
		
		
		//Constructor
		
		public User_BookRecommendUI(User_MainUI main,boolean flag){
			this.name = main.name;
			this.system = main.system;
			this.main = main;
			this.f= main.f;
			this.flag=flag;
			init();
		}	
		
		public User_BookRecommendUI() {
			
		}
		
	
		//Method
	
		public void init(){
			
			//패널 콘텐츠 생성
			book();
			
			/** 폰트 설정 **/
			bookNumberLabel.setFont(Commons.getFont());
			bookNameLabel.setFont(Commons.getFont());
			bookAuthorLabel.setFont(Commons.getFont());
			publisherLabel.setFont(Commons.getFont());
			priceLabel.setFont(Commons.getFont());
			issueDayLabel.setFont(Commons.getFont());
			lookReview_btn.setFont(Commons.getFont());
			putIn_btn.setFont(Commons.getFont());
			bookNumber_tf.setFont(Commons.getFont());
			bookName_tf.setFont(Commons.getFont());
			bookAuthor_tf.setFont(Commons.getFont());
			publisher_tf.setFont(Commons.getFont());
			price_tf.setFont(Commons.getFont());
			issueDay_tf.setFont(Commons.getFont());
		}
	
	
		//버튼 액션
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==lookReview_btn) { //리뷰버튼
				flag = false;
				User_BookReviewUI review = new User_BookReviewUI(f, bookName_tf.getText(),system);		
				review.setVisible(true);
			}else if(obj==lookReview_btn2) {
				flag = true;
				User_BookReviewUI review = new User_BookReviewUI(f, bookName_tf.getText(),system);
				review.setVisible(true);
			}
			else if(obj==putIn_btn) { //장바구니버튼
				User_BookBasketUI basket1 = new User_BookBasketUI(f, book, name, system);
				basket1.setVisible(true);
			}
		}
		
	
		public JPanel book() {
		
			dao = new BookDAO();
			//BookDAO에 있는 메소드로 추천도서 db 데이터 받아오는 것
			String[][] bookInfo = dao.recommendList(flag);
			
			main.switching(User_MainUI.BOOK);		
			
			bookPanel = new JPanel();
			bookPanel.setBackground(Color.WHITE);
			bookPanel.setBounds(133, 20, 531, 330);
			
			bookPanel.setVisible(false);
			bookPanel.setLayout(null);
		
			bookimgLabel = new JLabel();
			
			if(main.flag==false) {
			bookimgLabel.setIcon(new ImageIcon("images/book1_s.jpg"));
			tag = false;
			}else if(main.flag==true){
			bookimgLabel.setIcon(new ImageIcon("images/book2_s.jpg"));
			tag = true;
			}
			
			bookimgLabel.setBounds(20, 0, 150, 250);
			bookPanel.add(bookimgLabel);
			
			bookNumberLabel = new JLabel("도서번호");
			bookNumberLabel.setBounds(180, 10, 69, 30);
			bookPanel.add(bookNumberLabel);
			
			bookNameLabel = new JLabel("도서명");
			bookNameLabel.setBounds(180, 50, 69, 30);
			bookPanel.add(bookNameLabel);
			
			bookAuthorLabel = new JLabel("저자");
			bookAuthorLabel.setBounds(180, 90, 69, 30);
			bookPanel.add(bookAuthorLabel);
			
			publisherLabel = new JLabel("출판사");
			publisherLabel.setBounds(180, 130, 69, 30);
			bookPanel.add(publisherLabel);
			
			priceLabel = new JLabel("가격");
			priceLabel.setBounds(180, 170, 69, 30);
			bookPanel.add(priceLabel);
			
			issueDayLabel = new JLabel("발행일");
			issueDayLabel.setBounds(180, 210, 69, 30);
			bookPanel.add(issueDayLabel);
			
			if(main.flag==false) {
			lookReview_btn = new JButton("리뷰보기");
			lookReview_btn.setForeground(Color.WHITE);
			lookReview_btn.setBackground(Color.PINK);
			lookReview_btn.setBounds(276, 268, 95, 47);
			bookPanel.add(lookReview_btn);
			}else if(main.flag==true) {
			lookReview_btn = new JButton("리뷰보기");
			lookReview_btn.setForeground(Color.WHITE);
			lookReview_btn.setBackground(Color.PINK);
			lookReview_btn.setBounds(276, 268, 95, 47);
			bookPanel.add(lookReview_btn);
			}
			putIn_btn = new JButton("장바구니");
			putIn_btn.setForeground(Color.WHITE);
			putIn_btn.setBackground(Color.PINK);
			putIn_btn.setBounds(383, 268, 95, 47);
			bookPanel.add(putIn_btn);
			
			bookNumber_tf = new JTextField(bookInfo[0][0]);
			bookNumber_tf.setBounds(276, 10, 192, 30);
			bookPanel.add(bookNumber_tf);
			bookNumber_tf.setColumns(10);
			bookNumber_tf.setEnabled(false);
				
			bookName_tf = new JTextField(bookInfo[0][1]);
			bookName_tf.setColumns(10);
			bookName_tf.setBounds(276, 50, 192, 30);
			bookPanel.add(bookName_tf);
			bookName_tf.setEnabled(false);
			
			bookAuthor_tf = new JTextField(bookInfo[0][2]);
			bookAuthor_tf.setColumns(10);
			bookAuthor_tf.setBounds(276, 90, 192, 30);
			bookPanel.add(bookAuthor_tf);
			bookAuthor_tf.setEnabled(false);
			
			publisher_tf = new JTextField(bookInfo[0][3]);
			publisher_tf.setColumns(10);
			publisher_tf.setBounds(276, 130, 192, 30);
			bookPanel.add(publisher_tf);
			publisher_tf.setEnabled(false);
			
			price_tf = new JTextField(bookInfo[0][4]);
			price_tf.setColumns(10);
			price_tf.setBounds(276, 170, 192, 30);
			bookPanel.add(price_tf);
			price_tf.setEnabled(false);
			
			issueDay_tf = new JTextField(bookInfo[0][5]);
			issueDay_tf.setColumns(10);
			issueDay_tf.setBounds(276, 210, 192, 30);
			bookPanel.add(issueDay_tf);
			issueDay_tf.setEnabled(false);
			
			lookReview_btn.addActionListener(this);
			putIn_btn.addActionListener(this);
			
			bookPanel.setVisible(true);
			main.mainPanel.add(bookPanel);
			
			book = new BookVO();
			book.setBookname(bookName_tf.getText());
			book.setPrice(Integer.parseInt(price_tf.getText()));
			
			return bookPanel;
		}
			
		
		
}
