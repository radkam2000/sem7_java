package org.example;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"Name", "Surname", "Index", "Email"})
@JacksonXmlRootElement(localName = "Student")
public class Student {
    @JacksonXmlProperty(localName = "Name")
    public String name;
    @JacksonXmlProperty(localName = "Surname")
    public String surname;
    @JacksonXmlProperty(localName = "Index")
    public Integer index;
    @JacksonXmlProperty(localName = "Email")
    public String email;

    public Student() {
    }

    public Student(String name, String surname, Integer index, String email) {
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", index=" + index +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

