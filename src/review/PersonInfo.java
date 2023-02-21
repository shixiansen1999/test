package review;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author bx
 * @create 2022-11-09 13:12
 */
class PersonInfo implements Serializable {
    private String name;
    private transient String id;
    private String address;
    private String phone;

    public PersonInfo() {
    }

    public PersonInfo(String name, String id, String address, String phone) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo that = (PersonInfo) o;
        return name.equals(that.name) &&
                id.equals(that.id) &&
                address.equals(that.address) &&
                phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, address, phone);
    }

}