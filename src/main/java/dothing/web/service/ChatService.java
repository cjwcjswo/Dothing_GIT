package dothing.web.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private String path = "C:\\Edu\\dothing_chat";//저장 경로
	FileWriter fw = null;
	BufferedReader br = null;
	
	public void write(String [] msg){
		File file = new File(path + "/" + msg[0] + ".txt");
		try {
			String translatedMsg = msg[2].replaceAll("\n", "\r\n");
			fw = new FileWriter(file, true);
			fw.write(msg[1] + "#/separator/#" + translatedMsg + "/#separator#/" + msg[3] + "#startendtag#\r\n");
			
			
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
	
	public List<String> getContent(String errandsNum){
		
		List<String> list = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(new File(path + "/" + errandsNum + ".txt")));
			
			int r;
			String result = "";
			while((r = br.read()) != -1){
				result += (char) r;
			}
			
			String sentenceArr []  = result.split("#startendtag#");
			for(int i=0; i<sentenceArr.length-1; i++){
				list.add(sentenceArr[i]);
			}
			System.out.println("list's size : " + list.size());
			for(String xx : list){
				System.out.println(xx);
			}
			
			
		} catch (Exception e) {
		
		}
		
		
		return list;
		
	}
	
	
}
