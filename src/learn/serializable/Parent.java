package learn.serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import learn.serializable.Children;

public class Parent {
    private String name;
    @JsonBackReference
    private Children children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
