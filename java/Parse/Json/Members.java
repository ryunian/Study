package Parse.Json;

import java.util.ArrayList;
import java.util.List;

public class Members {
    private String name;
    private int age;
    private String secretIdentity;
    private List<String> powers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }

    @Override
    public String toString() {
        return "Members{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", secretIdentity='" + secretIdentity + '\'' +
                ", powers=" + powers +
                '}';
    }
}
