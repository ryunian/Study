package Parse.Json.JsonSimple;

import Parse.Json.Members;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class jsonToBean {
    public static void main(String[] args) throws Exception {
        File file = new File(".\\data\\data.json");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        List<Members> members = (List<Members>) jsonObject.get("members");

        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i) + "\n");
        }
    }
}
