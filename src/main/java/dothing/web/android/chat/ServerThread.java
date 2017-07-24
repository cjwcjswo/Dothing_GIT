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
	}

	@Override
	public void run() {
		try {
			// Ŭ���̾�Ʈ�� �������� ������ �б�
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

			// ���ӵ� Ŭ���̾�Ʈ���� ������ ������.
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			// ��ȭ���б�
			String initStr = br.readLine();
			String initArr[] = initStr.split(":");
			threadId = initArr[0];
			errandsNum = initArr[1];
			List<ServerThread> list = AndroidChatServer.socketMap.get(errandsNum);
			if (list == null) {
				AndroidChatServer.socketMap.put(errandsNum, new ArrayList<ServerThread>());
			}
			List<ServerThread> realList = AndroidChatServer.socketMap.get(errandsNum);
			realList.add(this);
			System.out.println(errandsNum + "���� �����: " + realList);
			String data = "";
			while ((data = br.readLine()) != null) {
				// System.out.println(data + " ����!");
				String dataArr[] = data.split(":");
				if (dataArr[0].equals("EXIT") && threadId.equals(dataArr[1])) {
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
		} catch (Exception e) {
			System.out.println(errandsNum + "�� ����");
			AndroidChatServer.socketMap.get(errandsNum).remove(this);
			e.printStackTrace();
		}
	}
}// ServerThread��