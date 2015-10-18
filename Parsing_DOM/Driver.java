package Parsing_DOM;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import Parsing_SAX.CountOccurences;
import Parsing_SAX.Sorting;

public class Driver {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		File postXmlFile = new File("F:\\posts.xml");
		File userXmlFile = new File("F:\\users.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(postXmlFile);
		Document document1 = documentBuilder.parse(userXmlFile);
		document.getDocumentElement().normalize();
		Posthandler.getOwnerUserIds(document);

		Sorting sorting = new Sorting();
		CountOccurences count = new CountOccurences();

		// counts the occurrences of OwnerUserId
		HashMap<String, Integer> stringsCountQuestions = count.countOccurrences(Posthandler.numberOfQuestions);
		HashMap<String, Integer> stringsCountAnswers = count.countOccurrences(Posthandler.numberOfAnswers);

		// Sorting the map by occurrences and returns the last 10 keys which are
		// the top most UserIds asking questions/answers
		List<String> top10QuestionUsers = sorting.sortingByValue(stringsCountQuestions);
		List<String> top10AnswerUsers = sorting.sortingByValue(stringsCountAnswers);

		// gets a map with id's and owner user id
		Map<String, String> map = UserHandler.handler(document1);

		LinkedList<String> questionUsers = new LinkedList<String>();
		LinkedList<String> answerUsers = new LinkedList<String>();

		for (String s : top10QuestionUsers) {
			questionUsers.add(map.get(s));
		}
		System.out.println("The top 10 users(using DOM) who ask questions are : " + questionUsers);

		for (String s : top10AnswerUsers) {
			answerUsers.add(map.get(s));
		}
		System.out.println("The top 10 users(using DOM) who answer are : " + answerUsers);
	}

}
