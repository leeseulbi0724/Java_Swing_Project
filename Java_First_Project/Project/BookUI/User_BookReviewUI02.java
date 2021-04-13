package BookUI;

import java.awt.Color;
import java.awt.Window;
import java.awt.Dialog.ModalityType;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class User_BookReviewUI02 extends JDialog{
	
	//Field
	User_MainUI main;
	LoginUI login;
	
	//Constructor
	public User_BookReviewUI02(Window parent) {
		super(parent, "Ã¥ ¸®ºä", ModalityType.APPLICATION_MODAL);
		init();
		login = new LoginUI();
	}
	
	//Method

	public void init() {
		
		setBounds(0, 188, 491, 250);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setResizable(false);
		
		JLabel book2img = new JLabel();
		book2img.setBackground(Color.WHITE);
		book2img.setIcon(new ImageIcon("images/javaimg.jfif"));
		book2img.setBounds(12, 10, 103, 151);
		add(book2img);
		
		JLabel starLabel2 = new JLabel("\uBCC4\uC810");
		starLabel2.setBounds(150, 26, 115, 42);
		add(starLabel2);
		
		JTextArea reviewContent_ta2 = new JTextArea();
		reviewContent_ta2.setBackground(new Color(255, 240, 245));
		reviewContent_ta2.setBounds(160, 79, 267, 82);
		add(reviewContent_ta2);
		reviewContent_ta2.setEnabled(false);
		
		JButton buy_btn2 = new JButton("\uAD6C\uB9E4\uD558\uAE30");
		buy_btn2.setForeground(Color.WHITE);
		buy_btn2.setBackground(Color.PINK);
		buy_btn2.setBounds(188, 171, 180, 30);
		add(buy_btn2);
	}
}
