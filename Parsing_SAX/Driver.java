package Parsing_SAX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Driver {

	public static void main(String[] args) throws SAXException, IOException {
		XMLReader postReader = XMLReaderFactory.createXMLReader();
		XMLReader UsersReader = XMLReaderFactory.createXMLReader();

		postReader.setContentHandler(new HandlerPosts());
		UsersReader.setContentHandler(new HandlerUser());

		postReader.parse("F:\\posts.xml");
		UsersReader.parse("F:\\users.xml");

		Sorting sorting = new Sorting();
		CountOccurences count = new CountOccurences();

		// counts the occurrences of OwnerUserId
		HashMap<String, Integer> stringsCountQuestions = count.countOccurrences(HandlerPosts.numberOfQuestions);
		HashMap<String, Integer> stringsCountAnswers = count.countOccurrences(HandlerPosts.numberOfAnswers);

		// Sorting the map by occurrences and returns the last 10 keys which are
		// the top most UserIds asking questions/answers
		List<String> top10QuestionUsers = sorting.sortingByValue(stringsCountQuestions);
		List<String> top10AnswerUsers = sorting.sortingByValue(stringsCountAnswers);

		List<String> questionUsers = new ArrayList<String>();
		List<String> answerUsers = new ArrayList<String>();

		for (String s : top10QuestionUsers) {
			questionUsers.add(HandlerUser.map.get(s));
		}
		System.out.println("The top 10 users(using SAX) who ask questions are : " + questionUsers);

		for (String s : top10AnswerUsers) {
			answerUsers.add(HandlerUser.map.get(s));
		}
		System.out.println("The top 10 users(using SAX) who answer are : " + answerUsers);

	}

}
