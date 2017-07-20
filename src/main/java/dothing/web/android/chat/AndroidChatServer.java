package dothing.web.android.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidChatServer {
	Map<String, List<ServerThread>> socketMap = new HashMap<>();
	ServerSocket server;
	Socket socket;
	public AndroidChatServer(){
		try{
	    	 server = new ServerSocket(8888);
	    	 while(true){
	    		 System.out.println("Client접속 대기중...");
	    		 socket = server.accept();
	    		 System.out.println(socket.getInetAddress()+" 접속되었습니다.");
	    		 ServerThread st = new ServerThread();
	    		 st.start();
	    	 
	    	 }
	    	 
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * 접속된 클라이언트를 스레드로  관리하기 위한 class
	 * */
	class ServerThread extends Thread{
		BufferedReader br;
		PrintWriter pw;
		String errandsNum;
		@Override
		public void run() {
			try{
				//클라이언트가 보내오는 데이터 읽기
				br = new BufferedReader(
				 new InputStreamReader(socket.getInputStream(), "UTF-8"));
				
				//접속된 클라이언트에게 데이터 보내기.
				pw = new PrintWriter(socket.getOutputStream(), true);
				
				//대화명읽기
				errandsNum= br.readLine();
				List<ServerThread> list = socketMap.get(errandsNum);
				if(list == null){
					socketMap.put(errandsNum, new ArrayList<ServerThread>());
				}
				List<ServerThread> realList = socketMap.get(errandsNum);
				realList.add(this);
		        System.out.println(errandsNum +"방의 사용자: " + realList);
		        String data="";
		        while((data=br.readLine())!=null){
		        	System.out.println(data + " 받음!");
		        	for(ServerThread st : realList){
		        		st.pw.println(data);
		        	}
		        }
			}catch (Exception e) {
				System.out.println(errandsNum + "방 나감");
				socketMap.get(errandsNum).remove(this);
			}
		}
	}//ServerThread끝
}
