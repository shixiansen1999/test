package learn.serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

//@JsonIgnoreProperties({"name","age"})
public class ComplexStudent {
    //@JsonIgnore
    private String name;
    private int age;
    Map<String, Double> scores;
    private ContactInfo contactInfo;

    /*public ComplexStudent() {
    }*/

    @JsonCreator
    public ComplexStudent(@JsonProperty("name") String name, @JsonProperty("age") int age) {
        this.name = name;
        this.age = age;
    }

    //@JsonIgnore
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

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "ComplexStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", scores=" + scores +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
