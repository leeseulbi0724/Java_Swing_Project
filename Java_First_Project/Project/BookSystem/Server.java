package BookSystem;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Server {
	//Field
		ServerSocket server;	
		static ArrayList<ObjectOutputStream> list = new ArrayList<ObjectOutputStream>();
		static Vector<String> users = new Vector<String>();
		
		//Constructor
		public Server() {
			try {
				server = new ServerSocket(9000);
				System.out.println("서버 실행중");
				
				while(true) {
					Socket s = server.accept();
					ServerThread st = new ServerThread(s);
					st.start();
					
					list.add(st.oos);
					System.out.println("클라이언트 접속 완료");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//접속한 모든 클라이언트에게 메시지 전송-동기화 적용
		synchronized static public void broadcast() {
			try {	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
