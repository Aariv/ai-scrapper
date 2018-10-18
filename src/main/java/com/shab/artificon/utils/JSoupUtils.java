package com.shab.artificon.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.shab.artificon.model.ArtificHelp;

@Component
public class JSoupUtils {

	public static void main(String[] args) throws IOException {
		System.out.println(installPrinterUrlToHelp("https://support.office.com/en-us/article/outlook-not-responding-stuck-at-processing-stopped-working-freezes-or-hangs-5c313d04-64af-4441-82d2-44e5a43eee5a"));
	}

	public ArtificHelp convertUrlToHelp(String url) {
		// https://support.office.com/en-us/article/download-and-install-or-reinstall-office-365-or-office-2019-on-a-pc-or-mac-4414eaaf-0478-48be-9c42-23adc4716658
		ArtificHelp artificHelp = new ArtificHelp();
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			artificHelp.setName(doc.title());
			StringBuilder stringBuilder = new StringBuilder();
			Elements titles = doc.getElementsByClass("supTabControlTabContent");
			for (Element title : titles) {
				// System.out.println(title.text());
				Elements li = title.select("li");
				int stepsCount = 1;
				for (int i = 0; i <= 2; i++) {
					Element element = li.get(i);
					Elements paragraph = element.select("p");
					for (Element element2 : paragraph) {
						String step1 = element2.text();
						stringBuilder.append("STEP " + stepsCount + ": " + step1);
						stringBuilder.append("");
						System.out.println("STEP " + stepsCount + ": " + step1);
						stepsCount++;
						break;
					}
				}
				artificHelp.setSteps(stringBuilder.toString());
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return artificHelp;

	}
	
	public static ArtificHelp installPrinterUrlToHelp(String url) {
		// https://support.office.com/en-us/article/download-and-install-or-reinstall-office-365-or-office-2019-on-a-pc-or-mac-4414eaaf-0478-48be-9c42-23adc4716658
		ArtificHelp artificHelp = new ArtificHelp();
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			artificHelp.setName(doc.title());
			Elements titles = doc.getElementsByClass("ocpSection");
			for (Element title : titles) {
				System.out.println(title.text());
				Elements h1Tites = title.select("p");
				for (Element element : h1Tites) {
					System.out.println(element.text());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return artificHelp;

	}
}