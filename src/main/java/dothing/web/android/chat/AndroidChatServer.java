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
	    		 System.out.println("Client���� �����...");
	    		 socket = server.accept();
	    		 System.out.println(socket.getInetAddress()+" ���ӵǾ����ϴ�.");
	    		 ServerThread st = new ServerThread();
	    		 st.start();
	    	 
	    	 }
	    	 
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**
	 * ���ӵ� Ŭ���̾�Ʈ�� �������  �����ϱ� ���� class
	 * */
	class ServerThread extends Thread{
		BufferedReader br;
		PrintWriter pw;
		String errandsNum;
		@Override
		public void run() {
			try{
				//Ŭ���̾�Ʈ�� �������� ������ �б�
				br = new BufferedReader(
				 new InputStreamReader(socket.getInputStream(), "UTF-8"));
				
				//���ӵ� Ŭ���̾�Ʈ���� ������ ������.
				pw = new PrintWriter(socket.getOutputStream(), true);
				
				//��ȭ���б�
				errandsNum= br.readLine();
				List<ServerThread> list = socketMap.get(errandsNum);
				if(list == null){
					socketMap.put(errandsNum, new ArrayList<ServerThread>());
				}
				List<ServerThread> realList = socketMap.get(errandsNum);
				realList.add(this);
		        System.out.println(errandsNum +"���� �����: " + realList);
		        String data="";
		        while((data=br.readLine())!=null){
		        	System.out.println(data + " ����!");
		        	for(ServerThread st : realList){
		        		st.pw.println(data);
		        	}
		        }
			}catch (Exception e) {
				System.out.println(errandsNum + "�� ����");
				socketMap.get(errandsNum).remove(this);
			}
		}
	}//ServerThread��
}
