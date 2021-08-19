package Parse.XML.SAX;

import Parse.Members;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlSaxDAO {
    private List<Members> list;

    public List<Members> getList(File file) {
        list = new ArrayList<>();
        connection(file);
        return list;
    }

    private void connection(File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, new SaxHandler());

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class SaxHandler extends DefaultHandler {
        private StringBuilder sb = new StringBuilder();
        private Members member;
        private List<String> tmp;
        private boolean flag;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("members")) {
                flag = true;
                member = new Members();
                tmp = new ArrayList<>();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (!flag) return;
            if (qName.equals("members")) {
                member.setPowers(tmp);
                list.add(member);
                flag = false;
                member = null;
                tmp = null;

            } else if (qName.equals("name")) {
                member.setName(sb.toString());

            } else if (qName.equals("age")) {
                member.setAge(Integer.parseInt(sb.toString()));

            } else if (qName.equals("secretIdentity")) {
                member.setSecretIdentity(sb.toString());

            } else if (qName.equals("powers")) {
                tmp.add(sb.toString());
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            sb.setLength(0);
            sb.append(ch, start, length);
        }
    }
}
