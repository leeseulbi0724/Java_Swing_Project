package BookUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class User_BookRecommendUI implements ActionListener {
	
		//Field
	
		JPanel bookPanel;
		User_MainUI main;
		JFrame f;
		JButton lookReview_btn, putIn_btn;
		JTextField bookNumber_tf,bookName_tf,bookAuthor_tf,publisher_tf,issueDay_tf,price_tf;	
		
		//Constructor
		
		public User_BookRecommendUI(User_MainUI main) {
			this.main = main;
			this.f= main.f;
			init();
		}	
		//Method
	
		public void init() {
			
			main.switching(User_MainUI.BOOK);		
			
			bookPanel = new JPanel();
			bookPanel.setBackground(Color.WHITE);
			bookPanel.setBounds(133, 10, 531, 341);
			bookPanel.setVisible(false);
			bookPanel.setLayout(null);
			
			JLabel book1imgLabel = new JLabel("");
			book1imgLabel.setIcon(new ImageIcon("images/book1.jfif"));
			
			
			book1imgLabel.setIcon(new ImageIcon("images/book1.jfif"));
			book1imgLabel.setBounds(0, -10, 111, 155);
			bookPanel.add(book1imgLabel);
			
			JLabel bookNumberLabel = new JLabel("도서번호");
			bookNumberLabel.setBounds(180, 10, 69, 30);
			bookPanel.add(bookNumberLabel);
			
			JLabel bookNameLabel = new JLabel("도서명");
			bookNameLabel.setBounds(180, 50, 69, 30);
			bookPanel.add(bookNameLabel);
			
			JLabel bookAuthorLabel = new JLabel("저자");
			bookAuthorLabel.setBounds(180, 90, 69, 30);
			bookPanel.add(bookAuthorLabel);
			
			JLabel publisherLabel = new JLabel("출판사");
			publisherLabel.setBounds(180, 130, 69, 30);
			bookPanel.add(publisherLabel);
			
			JLabel issueDayLabel = new JLabel("발행일");
			issueDayLabel.setBounds(180, 170, 69, 30);
			bookPanel.add(issueDayLabel);
			
			JLabel priceLabel = new JLabel("가격");
			priceLabel.setBounds(180, 210, 69, 30);
			bookPanel.add(priceLabel);
			
			lookReview_btn = new JButton("리뷰보기");
			lookReview_btn.setForeground(Color.WHITE);
			lookReview_btn.setBackground(Color.PINK);
			lookReview_btn.setBounds(276, 268, 95, 47);
			bookPanel.add(lookReview_btn);
			
			putIn_btn = new JButton("장바구니");
			putIn_btn.setForeground(Color.WHITE);
			putIn_btn.setBackground(Color.PINK);
			putIn_btn.setBounds(383, 268, 95, 47);
			bookPanel.add(putIn_btn);
			
			bookNumber_tf = new JTextField();
			bookNumber_tf.setBounds(276, 10, 192, 30);
			bookPanel.add(bookNumber_tf);
			bookNumber_tf.setColumns(10);
			bookNumber_tf.setEnabled(false);
			
			bookName_tf = new JTextField();
			bookName_tf.setColumns(10);
			bookName_tf.setBounds(276, 50, 192, 30);
			bookPanel.add(bookName_tf);
			bookName_tf.setEnabled(false);
			
			bookAuthor_tf = new JTextField();
			bookAuthor_tf.setColumns(10);
			bookAuthor_tf.setBounds(276, 90, 192, 30);
			bookPanel.add(bookAuthor_tf);
			bookAuthor_tf.setEnabled(false);
			
			publisher_tf = new JTextField();
			publisher_tf.setColumns(10);
			publisher_tf.setBounds(276, 130, 192, 30);
			bookPanel.add(publisher_tf);
			publisher_tf.setEnabled(false);
			
			issueDay_tf = new JTextField();
			issueDay_tf.setColumns(10);
			issueDay_tf.setBounds(276, 170, 192, 30);
			bookPanel.add(issueDay_tf);
			issueDay_tf.setEnabled(false);
			
			price_tf = new JTextField();
			price_tf.setColumns(10);
			price_tf.setBounds(276, 210, 192, 30);
			bookPanel.add(price_tf);
			price_tf.setEnabled(false);
			
			lookReview_btn.addActionListener(this);
			putIn_btn.addActionListener(this);
			
			bookPanel.setVisible(true);
			main.mainPanel.add(bookPanel);
			
			
		}
	
		//버튼 액션
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==lookReview_btn) { //리뷰버튼
				User_BookReviewUI review = new User_BookReviewUI(f);
				review.setVisible(true);
			}else if(obj==putIn_btn) { //장바구니버튼
				User_BookBasketUI basket1 = new User_BookBasketUI(f);
				basket1.setVisible(true);
			}
		}
	
	
}
