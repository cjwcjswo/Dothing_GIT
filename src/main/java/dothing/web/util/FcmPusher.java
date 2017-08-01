package dothing.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

/**
 * @brief FCM������ ����Ʈ������ Ǫ���޼����� ������ ���� Ŭ����
 */
@Component
public class FcmPusher {

	/**
	 * FCM ���񽺸� �̿��ϱ� ���� ������ ���� Ű
	 */
	public final static String AUTH_KEY_FCM = "AAAAklIvs2s:APA91bFYwR6NzONMIT-g7MP5pAOyGKRrjKrk_jWnbkvn2CTmGz22tpDVVsCk_P2sLolbhZXmCwvLo_aLramx15iyxa7Po1_jBJLWGQHNuYaf-oqr9cNEeduPF0OuAOlRhD54OkjFEbIz";
	/**
	 * FCM�� URL�ּ�
	 */
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	/**
	 * @brief DoThing �������� FCM������ Ǫ���޼����� ������ ���� �޼ҵ�
	 * @param List<String> userDeiceIdKey:  �������� ����Ʈ�� ��ū ��
	 * @param String title: Ǫ���޼����� ����
	 * @param String body: Ǫ���޼����� ����
	 * @param String action: ���۵Ǵ� ��Ƽ��Ƽ�� ����(DETAIL_ACTIVITY, CHAT_ACTIVITY ...) (null�� ��� �⺻ ��Ƽ��Ƽ)
	 * @param Map<String, String> params: intent ���۵� �� �ҷ� �� extras ����
	 */
	public void pushFCMNotification(List<String> userDeviceIdKey, String title, String body, String action,
			Map<String, String> params) throws Exception {
		
		String authKey = AUTH_KEY_FCM;
		String FMCurl = API_URL_FCM;

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		//HTTP ����� ����
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		//FCM ������ ������ ���� JSON ������ �����͵�
		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();

		info.put("body", body); // Ǫ���޼����� body
		info.put("title", title); // Ǫ���޼����� title
		
		if (params != null) { // ������ ���� params���� �ݺ����� ���� �߰�������
			Iterator<String> iter = params.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String value = params.get(key);
				info.put(key, value);
			}
		}
		
		if (action != null) info.put("click", action); // �⺻ ��Ƽ��Ƽ�� �ƴ� Ư�� ��Ƽ��Ƽ���� �����ų ���
		json.put("data", info);
		json.put("registration_ids", userDeviceIdKey); // �������� ��ū ����(�������� �������� ����)
		
		System.out.println(json);
		
		// �޼��� ����
		try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")) {
			wr.write(json.toString()); 
			wr.flush();
		} catch (Exception e) {}

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) { // ���� �߻� ��
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		// �������� �� �޼����� Ȯ��
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();
	}

}
