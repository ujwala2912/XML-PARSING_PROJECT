package Parsing_SAX;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Driver {

	public static void main(String[] args) throws SAXException, IOException {
		XMLReader postReader = XMLReaderFactory.createXMLReader();
		XMLReader usersReader = XMLReaderFactory.createXMLReader();

		postReader.setContentHandler(new HandlerPosts());
		usersReader.setContentHandler(new HandlerUser());

		postReader.parse("F:\\posts.xml");
		usersReader.parse("F:\\users.xml");

		Sorting sorting = new Sorting();
		CountOccurences count = new CountOccurences();

		// counts the occurrences of OwnerUserId
		HashMap<String, Integer> stringsCountQuestions = count.countOccurrences(HandlerPosts.numberOfQuestions);
		HashMap<String, Integer> stringsCountAnswers = count.countOccurrences(HandlerPosts.numberOfAnswers);

		// Sorting the map by occurrences and returns the last 10 keys which are
		// the top most UserIds asking questions/answers
		List<String> top10QuestionUsers = sorting.sortingByValue(stringsCountQuestions);
		List<String> top10AnswerUsers = sorting.sortingByValue(stringsCountAnswers);

		int i = 1;
		System.out.println("The top 10 users(using SAX) who ask questions are : ");
		for (String s : top10QuestionUsers) {

			System.out.println(i + ". " + HandlerUser.map.get(s));
			i++;

		}

		int j = 1;
		System.out.println("\nThe top 10 users(using SAX) who answer are : ");
		for (String s : top10AnswerUsers) {
			System.out.println(j + ". " + HandlerUser.map.get(s));
			j++;
		}
	}

}
