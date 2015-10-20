package Parsing_Own;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	public static String parse(String line, String ifExistsKey, String key) throws FileNotFoundException {
		// String regexKey = key + "(=.)(\\w+)(.)";
		String regexKey = key + "\\=\\\"?([.\\w\\s\\-]+)\\\"?";
		String regexIfExistsKey = ifExistsKey;

		Pattern r = Pattern.compile(regexKey);
		Pattern r1 = Pattern.compile(regexIfExistsKey);

		Matcher m = r.matcher(line);
		Matcher m1 = r1.matcher(line);

		String value = null;

		if (m1.find() && m.find()) {
			{
				value = m.group(1);
				return value;
			}

		}
		return value;

	}
}
