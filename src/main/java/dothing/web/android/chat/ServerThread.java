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
 * ���ӵ� Ŭ���̾�Ʈ�� ������� �����ϱ� ���� class
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
			// Ŭ���̾�Ʈ�� �������� ������ �б�
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			// ���ӵ� Ŭ���̾�Ʈ���� ������ ������.
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			String initStr = br.readLine(); // ��ȭ���б�
			if(initStr == null) return;
			String initArr[] = initStr.split(":"); // ���� id : ä�ù� ��ȣ
			threadId = initArr[0]; errandsNum = initArr[1];
			List<ServerThread> list = AndroidChatServer.socketMap.get(errandsNum);
			if (list == null) { // ä�ù��� ���� �ȸ�������� ���
				AndroidChatServer.socketMap.put(errandsNum, new ArrayList<ServerThread>());
			}
			List<ServerThread> realList = AndroidChatServer.socketMap.get(errandsNum);
			realList.add(this);
			System.out.println(errandsNum + "���� �����: " + realList);
			String data = "";
			while ((data = br.readLine()) != null) {
				String dataArr[] = data.split(":");
				if (dataArr[0].equals("EXIT") && threadId.equals(dataArr[1])) { // ���� �����ٴ� �޼����� ���� ���
					throw new Exception("�� ����");
				} else {
					String sendMessage = errandsNum + "#/separator/#" + dataArr[0] + "#/separator/#" + dataArr[1]
							+ "#/separator/#" + dataArr[2];
					for (ServerThread st : realList) {
						WebSocketHandler.sendWebsocketMessage(sendMessage);
						WebSocketHandler.sendAlert("�˸�:" + dataArr[3] + ":" + errandsNum + ":" + dataArr[1] + ":"
								+ dataArr[4] + ":" + dataArr[0]);
						st.pw.println(data);
					}
					chatService.write(sendMessage.split("#/separator/#"));
				}
			}
		} catch (Exception e) { // ���ܰ� �߻� ���� ��� ä�ù��� ������.
			AndroidChatServer.socketMap.get(errandsNum).remove(this);
			e.printStackTrace();
		}
	}
}// ServerThread��