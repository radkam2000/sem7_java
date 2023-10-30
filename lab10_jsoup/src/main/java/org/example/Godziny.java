package org.example;

import java.util.Date;

public class Godziny {
    public String link;
    public Date date;

    public Godziny(String link, Date date) {
        this.link = link;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Godziny{" +
                "link='" + link + '\'' +
                ", date=" + date +
                '}';
    }
}
