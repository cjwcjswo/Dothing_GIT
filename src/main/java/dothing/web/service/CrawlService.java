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

	public Map<String, List<CrawlDataDTO>> parseMainPage(String pageName, String productName){
		System.out.println("parseMainPage!!");
		
		//Ediya coffee
		//이름, 이미지경로, 링크
		System.out.println("pdtName : " + productName);
		Map<String, List<CrawlDataDTO>> map = new HashMap<>();
		try {
			///Ediya
			String [] ediyaAddrArr = {
					"http://www.ediya.com/board/listing/brd/product_coffee",
					"http://www.ediya.com/board/listing/brd/product_coffee/page/2"/*,
					"http://www.ediya.com/board/listing/brd/product_coffee/page/3"*/
			};
			List<CrawlDataDTO> list = new ArrayList<>();
			for(String ediyaAddr : ediyaAddrArr){
				Document doc = Jsoup.connect(ediyaAddr).get();
				Elements elements = doc.select("ul.pdlist.clearfix li");
				for(Element e : elements){
					//이름
					String name = e.select(" a[href] dl dd").text();
					//이미지 경로
					String img = "http://www.ediya.com" + e.select("a[href] dl dt img[src]").attr("src");
					//링크
					String link = "http://www.ediya.com" + e.select(" a[href]").attr("href");
					if(productName == null){
						CrawlDataDTO crawlDataDTO = new CrawlDataDTO();
						crawlDataDTO.setName(name);
						crawlDataDTO.setImg(img);
						crawlDataDTO.setLink(link);
						
						list.add(crawlDataDTO);
					}else if(name.contains(productName.toUpperCase())){
						CrawlDataDTO crawlDataDTO = new CrawlDataDTO();
						crawlDataDTO.setName(name);
						crawlDataDTO.setImg(img);
						crawlDataDTO.setLink(link);
						
						list.add(crawlDataDTO);
					}
				}
			}
			map.put(pageName, list);
			///Ediya
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return map;
	}
}
