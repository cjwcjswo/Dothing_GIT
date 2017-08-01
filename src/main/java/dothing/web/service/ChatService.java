package dothing.web.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 서버로부터 채팅 내용을 읽고 쓰는 클래스
 */
@Service
public class ChatService {
	/**
	 * 채팅내역 저장 경로
	 */
	private String path = "C:\\dothing_chat";
	BufferedWriter bw = null;
	BufferedReader br = null;
	
	/**
	 * 채팅메세지를 서버폴더로 저장하기
	 * @param String[] msg: 저장하고자 하는 메세지의 배열(0=아이디, 1=내용, 2=시간)
	 */
	public void write(String [] msg) throws UnsupportedEncodingException, FileNotFoundException{
		
		//디렉토리 생성
		File defaultDirectory = new File(path);
		if(!defaultDirectory.exists()){
			defaultDirectory.mkdir();
		}
		
		//파일 생성
		File file = new File(path + "/" + msg[0] + ".txt");

		try {
			// \r\n형태로 저장해야 줄바꿈을 인식함
			String translatedMsg = msg[2].replaceAll("\n", "\r\n");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
			
			// 파일형태로 저장하기
			bw.write(msg[1] + "#/separator/#" + translatedMsg + "/#separator#/" + msg[3] + "#startendtag#\r\n");
			bw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 해당하는 채팅방으로부터 채팅 내역 가져오기
	 * @param int errandsNum: 해당하는 채팅방 번호
	 * @return List<String>: 채팅 내역
	 */
	public List<String> getContent(String errandsNum){
		List<String> list = new ArrayList<>();
		try {
			//파일로부터 채팅 내역을 가져온다
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path + "/" + errandsNum + ".txt")), "UTF-8"));
			int r;
			String result = ""; // 채팅 내역결과
			while ((r = br.read()) != -1) {
				result += (char) r;
			}

			// #startendtag#를 구분자로 하여 채팅 하나의 끝을 구분한다
			String sentenceArr[] = result.split("#startendtag#");
			for (int i = 0; i < sentenceArr.length - 1; i++) {
				list.add(sentenceArr[i]);
			}

		} catch (Exception e) {

		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
   
}
