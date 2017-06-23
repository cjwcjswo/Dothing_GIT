package dothing.web.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private String path = "C:\\dothing_chat";//历厘 版肺
	BufferedWriter bw = null;
	BufferedReader br = null;
	
	public void write(String [] msg) throws UnsupportedEncodingException, FileNotFoundException{
		//叼泛配府 积己
		File defaultDirectory = new File(path);
		if(!defaultDirectory.exists()){
			defaultDirectory.mkdir();
		}
		//颇老 积己
		File file = new File(path + "/" + msg[0] + ".txt");

		try {
			String translatedMsg = msg[2].replaceAll("\n", "\r\n");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "ANSI"));
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
			
			
		} catch (Exception e) {
		
		}
		
		
		return list;
		
	}
	
 
   
   
}
