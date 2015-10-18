package Parsing_SAX;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerPosts extends DefaultHandler {

	public static ArrayList<String> numberOfQuestions = new ArrayList<String>();
	public static ArrayList<String> numberOfAnswers = new ArrayList<String>();

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {

		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (localName.equals("row") && attributes.getValue("OwnerUserId") != null) {
			if (attributes.getValue("PostTypeId").equals("1")) {
				numberOfQuestions.add(attributes.getValue("OwnerUserId"));
			} else if (attributes.getValue("PostTypeId").equals("2") && attributes.getValue("OwnerUserId") != null) {
				numberOfAnswers.add(attributes.getValue("OwnerUserId"));
			}

		}

		super.startElement(uri, localName, qName, attributes);

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}

}
