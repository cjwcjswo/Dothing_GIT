package dothing.web.android.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dothing.web.service.ChatService;
import dothing.web.socket.handler.WebSocketHandler;

/**
 * 접속된 클라이언트를 스레드로 관리하기 위한 class
 */
public class ServerThread extends Thread {
	public BufferedReader br;
	public PrintWriter pw;
	String errandsNum, threadId;
	ChatService chatService = new ChatService();
	Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
		this.start();
	}

	@Override
	public void run() {
		try {
			// 클라이언트가 보내오는 데이터 읽기
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			// 접속된 클라이언트에게 데이터 보내기.
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			String initStr = br.readLine(); // 대화명읽기
			if(initStr == null) return;
			String initArr[] = initStr.split(":"); // 상대방 id : 채팅방 번호
			threadId = initArr[0]; errandsNum = initArr[1];
			List<ServerThread> list = AndroidChatServer.socketMap.get(errandsNum);
			if (list == null) { // 채팅방이 아직 안만들어졌을 경우
				AndroidChatServer.socketMap.put(errandsNum, new ArrayList<ServerThread>());
			}
			List<ServerThread> realList = AndroidChatServer.socketMap.get(errandsNum);
			realList.add(this);
			System.out.println(errandsNum + "방의 사용자: " + realList);
			String data = "";
			while ((data = br.readLine()) != null) {
				String dataArr[] = data.split(":");
				if (dataArr[0].equals("EXIT") && threadId.equals(dataArr[1])) { // 방을 나간다는 메세지가 왔을 경우
					throw new Exception("방 나감");
				} else {
					String sendMessage = errandsNum + "#/separator/#" + dataArr[0] + "#/separator/#" + dataArr[1]
							+ "#/separator/#" + dataArr[2];
					for (ServerThread st : realList) {
						WebSocketHandler.sendWebsocketMessage(sendMessage);
						WebSocketHandler.sendAlert("알림:" + dataArr[3] + ":" + errandsNum + ":" + dataArr[1] + ":"
								+ dataArr[4] + ":" + dataArr[0]);
						st.pw.println(data);
					}
					chatService.write(sendMessage.split("#/separator/#"));
				}
			}
		} catch (Exception e) { // 예외가 발생 했을 경우 채팅방을 나간다.
			AndroidChatServer.socketMap.get(errandsNum).remove(this);
			e.printStackTrace();
		}
	}
}// ServerThread끝