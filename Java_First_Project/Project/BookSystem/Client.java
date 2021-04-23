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
		
		//1. CONNECT : status - 0		
		
		//4. 수신 - 브로드캐스팅하는 모든 메시지	
		//new ClientThread(ois,taContent,lstUser,lCount).start();
		new ClientThread(this).start();		
		
	}//end connect method
}
