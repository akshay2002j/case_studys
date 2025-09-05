package org.example.entity;

public class Contact implements Comparable<Contact> {
    String name;
    String phone;

    public Contact( String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                " name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.name);
    }
}
