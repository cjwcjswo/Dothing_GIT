package dothing.web.properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class ErrandsHashProperties {
	public int saveHashtag(List<String> hashtag, String path) throws FileNotFoundException, IOException{
		Properties properties = new Properties();
		properties.load(new FileReader("C:\\CJW\\DoThingProject\\hash\\errandsHash.properties"));
		for(String hash:hashtag){
			if(properties.containsKey(hash)){
				System.out.println(hash + " 이미 존재되서 하나 올리겠씀니다");
				properties.put(hash,
						(Integer.parseInt((String)properties.get(hash)) + 1) + "");
			} else{
				properties.put(hash, "1");
				System.out.println(hash + " 풋 됨!!");
			}
		}
		System.out.println(properties);
		properties.store(new FileWriter("C:\\CJW\\DoThingProject\\hash\\errandsHash.properties",false), "");
		return 1;
	}
	
	public void loadHashtag() throws FileNotFoundException, IOException{
		Properties properties = new Properties();
		properties.load(new FileReader("C:\\CJW\\DoThingProject\\hash\\errandsHash.properties"));
		Iterator<Object> iter = properties.keySet().iterator();
		while(iter.hasNext()){
			
		}
	}
}
