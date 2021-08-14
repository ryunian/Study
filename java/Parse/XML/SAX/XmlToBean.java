package Parse.XML.SAX;

import Parse.Members;

import java.io.File;
import java.util.List;

public class XmlToBean {
    public static void main(String[] args) {
        File file = new File(".\\data\\members.xml");
        XmlSaxDAO dao = new XmlSaxDAO();
        List<Members> list = dao.getList(file);
        for (Members member : list) {
            System.out.println(member);
        }
    }
}
