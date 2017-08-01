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
 * �����κ��� ä�� ������ �а� ���� Ŭ����
 */
@Service
public class ChatService {
	/**
	 * ä�ó��� ���� ���
	 */
	private String path = "C:\\dothing_chat";
	BufferedWriter bw = null;
	BufferedReader br = null;
	
	/**
	 * ä�ø޼����� ���������� �����ϱ�
	 * @param String[] msg: �����ϰ��� �ϴ� �޼����� �迭(0=���̵�, 1=����, 2=�ð�)
	 */
	public void write(String [] msg) throws UnsupportedEncodingException, FileNotFoundException{
		
		//���丮 ����
		File defaultDirectory = new File(path);
		if(!defaultDirectory.exists()){
			defaultDirectory.mkdir();
		}
		
		//���� ����
		File file = new File(path + "/" + msg[0] + ".txt");

		try {
			// \r\n���·� �����ؾ� �ٹٲ��� �ν���
			String translatedMsg = msg[2].replaceAll("\n", "\r\n");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
			
			// �������·� �����ϱ�
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
	 * �ش��ϴ� ä�ù����κ��� ä�� ���� ��������
	 * @param int errandsNum: �ش��ϴ� ä�ù� ��ȣ
	 * @return List<String>: ä�� ����
	 */
	public List<String> getContent(String errandsNum){
		List<String> list = new ArrayList<>();
		try {
			//���Ϸκ��� ä�� ������ �����´�
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path + "/" + errandsNum + ".txt")), "UTF-8"));
			int r;
			String result = ""; // ä�� �������
			while ((r = br.read()) != -1) {
				result += (char) r;
			}

			// #startendtag#�� �����ڷ� �Ͽ� ä�� �ϳ��� ���� �����Ѵ�
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
