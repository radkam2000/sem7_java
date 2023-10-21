package org.example;

public class Student {
    public String name;

    public String surname;

    public Integer index;

    public String email;

    public Student() {
    }

    public Student(String name, String surname, Integer index, String email) {
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.email = email;
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

