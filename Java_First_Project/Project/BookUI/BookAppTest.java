package BookUI;

import java.awt.EventQueue;

public class BookAppTest {

	//Main
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginUI window = new LoginUI();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
}
