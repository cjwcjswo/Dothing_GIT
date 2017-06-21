package dothing.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import dothing.web.dto.CrawlDataDTO;

@Service
public class CrawlService {

	public Map<String, List<CrawlDataDTO>> parseMainPage(String pageName){
		System.out.println("parseMainPage!!");
		
		//Ediya coffee
		//�̸�, �̹������, ��ũ
		Map<String, List<CrawlDataDTO>> map = new HashMap<>();
		
		try {
			
			///Ediya
			Document doc = Jsoup.connect("http://www.ediya.com/board/listing/brd/product_coffee").get();
			Elements elements = doc.select("ul.pdlist.clearfix li");
			List<CrawlDataDTO> list = new ArrayList<>();
			for(Element e : elements){
				//�̹��� ���
				String img = "http://www.ediya.com" + e.select("a[href] dl dt img[src]").attr("src");
				//��ũ
				String link = "http://www.ediya.com" + e.select(" a[href]").attr("href");
				//�̸�
				String name = e.select(" a[href] dl dd").text();
				
				CrawlDataDTO crawlDataDTO = new CrawlDataDTO();
				crawlDataDTO.setName(name);
				crawlDataDTO.setImg(img);
				crawlDataDTO.setLink(link);
				
				list.add(crawlDataDTO);
			}
			map.put(pageName, list);
			///Ediya
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return map;
	}
}
