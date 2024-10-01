package com.qa.utils;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class TestUtils {
public static final Duration WAIT =Duration.ofSeconds(30);
public static HashMap<String, String> parseStringXML(InputStream file) throws Exception {
	//class: returns the Class object associated with this class
	//getClassLoader(): returns the ClassLoader object associated with this Class.
	//getResourceAsStream: method returns an input stream for reading the specified resource.
   // InputStream is = TestUtils.class.getClassLoader().getResourceAsStream(file);
    HashMap<String, String> stringMap = new HashMap<>();
    //Get Document Builder
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    //Build Document
    Document document = builder.parse(file);

    //Normalize the XML Structure; It's just too important !!
    document.getDocumentElement().normalize();

    //Here comes the root node
    Element root = document.getDocumentElement();

    //Get all elements
    NodeList nList = document.getElementsByTagName("string");

    for (int temp = 0; temp < nList.getLength(); temp++) {
        Node node = nList.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            // Store each element key value in map
            stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
        }
    }
    //initialize this in base test class
    return stringMap;
}

public String getDateTime() 
{  DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
   Date date = new Date();
   System.out.println(dateformat.format(date));
   return dateformat.format(date);

	
   }

}


