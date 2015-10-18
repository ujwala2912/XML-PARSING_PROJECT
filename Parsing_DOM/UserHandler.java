package Parsing_DOM;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserHandler {
	// puts the id and OwnerUserId into a map

	public static Map<String, String> handler(Document document) {
		NodeList nodelist = document.getElementsByTagName("row");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node nNode = nodelist.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				map.put(eElement.getAttribute("Id"), eElement.getAttribute("DisplayName"));
			}
		}
		return map;
	}
}
