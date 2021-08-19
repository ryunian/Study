package Parse.Json.JACKSON;

import Parse.Members;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class jsonToBean {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(".\\data\\members.json");
        Members res =  mapper.readValue(file, Members.class);
        System.out.println(res);


        File file2 = new File(".\\data\\data.json");
        JsonNode node = mapper.readTree(file2).get("members");
        List<Members> list = mapper.readValue(node.traverse(), List.class);
        System.out.println(list);

    }
}
