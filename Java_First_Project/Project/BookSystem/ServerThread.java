package BookSystem;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	//Field
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	//Constructor
	public ServerThread(Socket s) {
		try {
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//Method
	public void run() {
		try {
			while(true) {			
				Server.broadcast();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
