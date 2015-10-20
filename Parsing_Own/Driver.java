package Parsing_Own;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Parsing_SAX.CountOccurences;
import Parsing_SAX.Sorting;

public class Driver {
	public static ArrayList<String> numberOfQuestions = new ArrayList<String>();
	public static ArrayList<String> numberOfAnswers = new ArrayList<String>();
	public static Map<String, String> map = new HashMap<String, String>();

	public static void main(String[] args) throws IOException {

		BufferedReader postsInput = new BufferedReader(new FileReader(new File("F:\\posts.xml")));
		BufferedReader usersInput = new BufferedReader(new FileReader(new File("F:\\users.xml")));

		String line = "";
		String question = "";
		String answer = "";

		while (postsInput.ready()) {
			line = postsInput.readLine();
			question = Parser.parse(line, "PostTypeId=\"1\"", "OwnerUserId");
			if (question != null) {
				numberOfQuestions.add(question);
			}
			answer = (Parser.parse(line, "PostTypeId=\"2\"", "OwnerUserId"));
			if (answer != null) {
				numberOfAnswers.add(answer);
			}
		}

		Sorting sorting = new Sorting();
		CountOccurences count = new CountOccurences();

		// counts the occurrences of OwnerUserId
		HashMap<String, Integer> stringsCountQuestions = count.countOccurrences(numberOfQuestions);
		HashMap<String, Integer> stringsCountAnswers = count.countOccurrences(numberOfAnswers);

		// Sorting the map by occurrences and returns the last 10 keys which are
		// the top most UserIds asking questions/answers
		List<String> top10QuestionUsers = sorting.sortingByValue(stringsCountQuestions);
		List<String> top10AnswerUsers = sorting.sortingByValue(stringsCountAnswers);

		// gets a map with id's and owner user id
		while (usersInput.ready()) {
			line = usersInput.readLine();
			map.put(Parser.parse(line, "DisplayName", "Id"), Parser.parse(line, "Id", "DisplayName"));
		}

		int i = 1;
		System.out.println("The top 10 users(using my parser) who ask questions are : ");
		for (String s : top10QuestionUsers) {

			System.out.println(i + ". " + map.get(s));
			i++;

		}

		int j = 1;
		System.out.println("\nThe top 10 users(using my parser) who answer are : ");
		for (String s : top10AnswerUsers) {
			System.out.println(j + ". " + map.get(s));
			j++;
		}

	}

}
