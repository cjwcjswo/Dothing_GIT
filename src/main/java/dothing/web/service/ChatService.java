package dothing.web.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private String path = "C:\\Edu\\dothing_chat";//저장 경로
	FileWriter fw = null;
	//BufferedWriter bw = null;
	
	public void write(String [] msg){
		File file = new File(path + "/" + msg[0] + ".txt");
		try {
			String translatedMsg = msg[2].replaceAll("\n", "\r\n");
			fw = new FileWriter(file, true);
			fw.write(msg[1] + "#/separator/#" + translatedMsg + "#/separator/#" + msg[3] + "\r\n");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("wrtie!!!!");
		System.out.println("eno : " + msg[0]);
		System.out.println("sender : "+ msg[1]);
		System.out.println("msg : "+ msg[2]);
		System.out.println("date : "+ msg[3]);
	}
	
	
}
