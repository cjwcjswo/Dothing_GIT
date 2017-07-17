package dothing.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class FcmPusher {


    // Method to send Notifications from server to client end.

    public final static String AUTH_KEY_FCM = "AAAAklIvs2s:APA91bFYwR6NzONMIT-g7MP5pAOyGKRrjKrk_jWnbkvn2CTmGz22tpDVVsCk_P2sLolbhZXmCwvLo_aLramx15iyxa7Po1_jBJLWGQHNuYaf-oqr9cNEeduPF0OuAOlRhD54OkjFEbIz";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    // userDeviceIdKey is the device id you will query from your database

    public void pushFCMNotification(List<String> userDeviceIdKey, String title, String body)
            throws Exception {
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");
  
        JSONObject json = new JSONObject();
        JSONObject info = new JSONObject();
        info.put("body", body); // Notification body
        info.put("title", title);
        json.put("data", info);
        
        json.put("registration_ids", userDeviceIdKey); // deviceID
        System.out.println(json);
        try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){
//혹시나 한글 깨짐이 발생하면 
//try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다.

            wr.write(json.toString());
            wr.flush();
        }catch(Exception e){
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }

}
