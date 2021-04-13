package BookUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class User_BookPurchaseUI {
	
	//Field
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_BookPurchaseUI window = new User_BookPurchaseUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//constructor
	public User_BookPurchaseUI() {
		initialize();
	}

	//Method
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
