package com.myApp.JobManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobWorker {
	
	private static final String USER_AGENT =  
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private Document htmlDocument;
	private Logger log = LoggerFactory.getLogger(JobWorker.class);
	
	public JobWorker(){
		
	}
	
	public List<String> crawlUrl(String url){
		List<String> urlOnThePage = new ArrayList<>();
		
			Connection conn = Jsoup.connect(url).userAgent(USER_AGENT);
			try {
				htmlDocument = conn.get();
				System.out.println("::----------------------::");
				System.out.println(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Elements linkOnPage = htmlDocument.select("a[href]");
			
			for (Element link : linkOnPage) {
				urlOnThePage.add(link.absUrl("href"));
			}
			return urlOnThePage;
	}
	
	public List<String> crawlUrlForImage(String url){
		List<String> imageUrlOnThePage = new ArrayList<>();
		
		Connection conn = Jsoup.connect(url).userAgent(USER_AGENT);
		try {
			htmlDocument = conn.get();
		} catch (Exception e) {
			log.info("URL = " + url + " not accessable due to "+ e.getMessage());
		}
		
		Elements imagesOnPage = htmlDocument.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		for (Element image : imagesOnPage) {
			imageUrlOnThePage.add(image.attr("abs:src"));
		}
		
		return imageUrlOnThePage;
	}

}
