package FileReaders;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * This class contains the methods used to read a XML file.
 */


public class XMLReader {

    /**
     * Returns the attributes specified in the name argument inside the XML file in the argument fileName.
     * <br> For the moment, it can only return the content of "weapon" elements as we did not create the XML files for other type of data.
     * @param fileName the name of the XML file to read
     * @param name the name of the searched attribute
     * @return the attributes looked for, except in case of error
     */
    public static Element readXML(String fileName, String name) {

        // Instantiate the Factory
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {

            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(new File(fileName));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("weapon");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    if (name.equals(element.getAttribute("name"))){
                       return element;
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}