package learn.serializable;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

public class Children {
    private String name;
    @JsonIdentityReference
    private Parent parent;

    public int[] a;

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
