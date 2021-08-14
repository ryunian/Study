package Parse.XML.DOM;

import Parse.Members;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlDomDAO {
    private List<Members> list;

    public List<Members> getList(File file) {
        connect(file);
        return list;
    }

    private void connect(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            list = new ArrayList<>();
            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("members");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Members member = getMember(node);

                if (member != null) {
                    list.add(member);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Members getMember(Node node) {
        String name = null;
        String age = null;
        String secretIdentity = null;
        List<String> powers = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            if (child.getNodeName().equals("name")) {
                name = child.getTextContent();

            } else if (child.getNodeName().equals("age")) {
                age = child.getTextContent();

            } else if (child.getNodeName().equals("secretIdentity")) {
                secretIdentity = child.getTextContent();

            } else if (child.getNodeName().equals("powers")) {
                powers.add(child.getTextContent());
            }
        }
        if (name != null && age != null && secretIdentity != null && !powers.isEmpty()) {
            return new Members(name, Integer.parseInt(age), secretIdentity, powers);
        }
        return null;
    }

}

