package BookSystem;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public void connect(String ip, int port) throws Exception{
		//서버 연결
		Socket s = new Socket(ip, port);
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		
		//new ClientThread(ois,taContent,lstUser,lCount).start();
		new ClientThread(this).start();		
		
	}//end connect method
}
