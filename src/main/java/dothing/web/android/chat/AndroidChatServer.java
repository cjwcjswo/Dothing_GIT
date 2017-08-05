package dothing.web.android.chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 안드로이드 채팅 서버
 */
public class AndroidChatServer {
	
	public static Map<String, List<ServerThread>> socketMap = new HashMap<>();
	ServerSocket server;
	Socket socket;
	public AndroidChatServer(){
		try{
	    	 server = new ServerSocket(8888);
	    	 while(true){
	    		 System.out.println("Client접속 대기중...");
	    		 socket = server.accept();
	    		 System.out.println(socket.getInetAddress()+" 접속되었습니다.");
	    		 ServerThread st = new ServerThread(socket);
	    		 }
	    	 
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
