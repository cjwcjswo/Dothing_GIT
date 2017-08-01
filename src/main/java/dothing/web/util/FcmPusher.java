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
 * @brief FCM서버로 스마트폰으로 푸쉬메세지를 보내기 위한 클래스
 */
@Component
public class FcmPusher {

	/**
	 * FCM 서비스를 이용하기 위한 고유한 인증 키
	 */
	public final static String AUTH_KEY_FCM = "AAAAklIvs2s:APA91bFYwR6NzONMIT-g7MP5pAOyGKRrjKrk_jWnbkvn2CTmGz22tpDVVsCk_P2sLolbhZXmCwvLo_aLramx15iyxa7Po1_jBJLWGQHNuYaf-oqr9cNEeduPF0OuAOlRhD54OkjFEbIz";
	/**
	 * FCM의 URL주소
	 */
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	/**
	 * @brief DoThing 서버에서 FCM서버로 푸쉬메세지를 날리기 위한 메소드
	 * @param List<String> userDeiceIdKey:  유저들의 스마트폰 토큰 값
	 * @param String title: 푸쉬메세지의 제목
	 * @param String body: 푸쉬메세지의 내용
	 * @param String action: 시작되는 액티비티의 종류(DETAIL_ACTIVITY, CHAT_ACTIVITY ...) (null일 경우 기본 액티비티)
	 * @param Map<String, String> params: intent 시작될 때 불러 올 extras 값들
	 */
	public void pushFCMNotification(List<String> userDeviceIdKey, String title, String body, String action,
			Map<String, String> params) throws Exception {
		
		String authKey = AUTH_KEY_FCM;
		String FMCurl = API_URL_FCM;

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		//HTTP 헤더값 설정
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		//FCM 서버에 보내기 위한 JSON 형태의 데이터들
		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();

		info.put("body", body); // 푸쉬메세지의 body
		info.put("title", title); // 푸쉬메세지의 title
		
		if (params != null) { // 보내고 싶은 params들을 반복문을 통해 추가시켜줌
			Iterator<String> iter = params.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String value = params.get(key);
				info.put(key, value);
			}
		}
		
		if (action != null) info.put("click", action); // 기본 액티비티가 아닌 특정 액티비티에서 실행시킬 경우
		json.put("data", info);
		json.put("registration_ids", userDeviceIdKey); // 유저들의 토큰 값들(여러명의 유저에게 전송)
		
		System.out.println(json);
		
		// 메세지 전송
		try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")) {
			wr.write(json.toString()); 
			wr.flush();
		} catch (Exception e) {}

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) { // 오류 발생 시
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		// 서버에게 온 메세지를 확인
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();
	}

}
