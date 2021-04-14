package BookUI;

import java.awt.Color;
import java.awt.Window;
import java.awt.Dialog.ModalityType;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class User_BookReviewUI extends JDialog{
	
	//Field
	User_MainUI main;
	LoginUI login;
	
	//Constructor
	public User_BookReviewUI(Window parent) {
		super(parent, "책 리뷰", ModalityType.APPLICATION_MODAL);
		init();
		login = new LoginUI();
	}
	
	//Method

	public void init() {		
		setBounds(0, 188, 491, 250);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel book1img = new JLabel();
		book1img.setBackground(Color.WHITE);
		book1img.setIcon(new ImageIcon("images/book1.jfif"));
		book1img.setBounds(12, 10, 103, 151);
		add(book1img);
		
		JLabel starLabel = new JLabel("별점");
		starLabel.setBounds(150, 26, 115, 42);
		add(starLabel);
		
		JTextArea reviewContent_ta = new JTextArea();
		reviewContent_ta.setBackground(new Color(255, 240, 245));
		reviewContent_ta.setBounds(160, 79, 267, 82);
		add(reviewContent_ta);
		reviewContent_ta.setEnabled(false);
		
		JButton buy_btn = new JButton("구매하기");
		buy_btn.setForeground(Color.WHITE);
		buy_btn.setBackground(Color.PINK);
		buy_btn.setBounds(188, 171, 180, 30);
		add(buy_btn);
	}
}
