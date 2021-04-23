package BookSystem;

import BookUI.BookAppTest;

public class ClientTest {
	
	public static void main(String args[]) throws Exception {
		Client c = new Client();
		c.connect("127.0.0.1",9000);
		BookAppTest main = new BookAppTest();
		
	}
	
	

}
