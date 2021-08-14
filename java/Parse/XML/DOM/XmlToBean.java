package Parse.XML.DOM;

import Parse.Members;

import java.io.File;
import java.util.List;

public class XmlToBean {
    public static void main(String[] args) {
        File file = new File(".\\data\\members.xml");
        XmlDomDAO dao = new XmlDomDAO();
        List<Members> list = dao.getList(file);

        for (Members members : list) {
            System.out.println(members);
        }
    }
}