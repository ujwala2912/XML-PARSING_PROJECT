package Parsing_DOM;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Posthandler {

	public static ArrayList<String> numberOfQuestions = new ArrayList<String>();
	public static ArrayList<String> numberOfAnswers = new ArrayList<String>();

	public static void  getOwnerUserIds(Document document) {

		NodeList nodelist = document.getElementsByTagName("row");
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node nNode = nodelist.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if (eElement.getAttribute("PostTypeId").equals("1") && (eElement.hasAttribute("OwnerUserId"))) {
					numberOfQuestions.add(eElement.getAttribute("OwnerUserId"));

				} else if (eElement.getAttribute("PostTypeId").equals("2") && (eElement.hasAttribute("OwnerUserId"))) {
					numberOfAnswers.add(eElement.getAttribute("OwnerUserId"));
				}
			}
		}
		// System.out.println("Total number of Questions : " +
		// numberOfQuestions.size());
		// System.out.println("Total number of Answers : " +
		// numberOfAnswers.size());
	}

}
