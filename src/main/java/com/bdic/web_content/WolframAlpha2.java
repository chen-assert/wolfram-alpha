package com.bdic.web_content;

import com.wolfram.alpha.*;

public class WolframAlpha2 {

	private static String APPID = "99AV5K-HP9JWKG97U";

	public static String query(String question) {
		WAEngine engine = new WAEngine();
		engine.setAppID(WolframAlpha2.APPID);
		engine.addFormat("plaintext");
		WAQuery query = engine.createQuery();
		query.setInput(question);
		try {
			System.out.println("Asking Wolfram Alpha: " + question);
			WAQueryResult queryResult = engine.performQuery(query);
			if (queryResult.isError()) {
				return "<noresults></noresults>";
			} else if (!queryResult.isSuccess()) {
				return "<noresults></noresults>";
			} else {
				StringBuffer sb = new StringBuffer("");
				for (WAPod pod : queryResult.getPods()) {
					sb.append("<section><title>" + pod.getTitle() + "</title><sectioncontents>");
					for (WASubpod subpod : pod.getSubpods()) {
						for (Object element : subpod.getContents()) {
							if (element instanceof WAPlainText) {
								sb.append(((WAPlainText) element).getText());
							}
						}
					}
					sb.append("</sectioncontents></section>");
				}
				return sb.toString();
			}
		} catch (WAException e) {
			return "<noresults></noresults>";
		}
	}
}
