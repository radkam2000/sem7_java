package org.example;


import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.CollationElementIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            String blogUrl = "https://cs.pollub.pl";
            Document doc = Jsoup.connect(blogUrl).get();
            Element aElementPracownicy = doc.select("a[title=Pracownicy]").first();
            String link = aElementPracownicy.attr("abs:href");
            Document docPrac = Jsoup.connect(link).get();
            Elements zaklady = docPrac.select("article");
            Elements zakladyNazwy = zaklady.select("h3");

            Map<String, List<String>> zakladyPracownicy_zad1 = new HashMap<String, List<String>>();
            Map<String, List<String>> zakladyPracownicy_zad2 = new HashMap<String, List<String>>();
            zakladyNazwy.forEach(element -> {
                if (element.text().startsWith("Zakład")) {
                    List<String> pr = new ArrayList<>();
                    List<String> pr2 = new ArrayList<>();
                    element.nextElementSibling().children().forEach(element1 -> {
                        if (!element1.text().equals(""))
                            pr.add(element1.text().replace(",", "").replace("dr ", "").replace("inż. ", "").replace("hab. ", "").replace("mgr ", "").replace(" prof. uczelni", ""));
                        if (element1.text().startsWith("dr inż.") || element1.text().startsWith("dr") && !element1.text().equals(""))
                            pr2.add(element1.text());
                    });
                    Collections.sort(pr);
                    Collections.sort(pr2);
                    zakladyPracownicy_zad1.put(element.text(), pr);
                    zakladyPracownicy_zad2.put(element.text(), pr2);

                }
            });
            zakladyPracownicy_zad1.forEach((key, value) -> {
                System.out.println(key);
                value.forEach(elem -> {
                    System.out.println(elem);
                });
                System.out.println();
            });

            zakladyPracownicy_zad2.forEach((key, value) -> {
                System.out.println(key);
                value.forEach(elem -> {
                    System.out.println(elem);
                });
                System.out.println();
            });

            String dziekanskieUrl = "https://weii.pollub.pl/wyszukiwarka?query=godziny+dzieka%C5%84skie";
            String rektorskieUrl = "https://weii.pollub.pl/wyszukiwarka?query=godziny+rektorskie";

            Document docDziek = Jsoup.connect(dziekanskieUrl).get();
            Document docRekt = Jsoup.connect(rektorskieUrl).get();

            List<Godziny> godziny = new ArrayList<Godziny>();

            Elements naglowkiDziek = docDziek.select("h4");
            Elements naglowkiRekt = docRekt.select("h4");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            naglowkiRekt.forEach(element -> {
                try {
                    godziny.add(new Godziny(element.select("a").attr("href"), formatter.parse(element.select("small").text())));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
            naglowkiDziek.forEach(element -> {
                try {
                    godziny.add(new Godziny(element.select("a").attr("href"), formatter.parse(element.select("small").text())));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });

            Comparator<Godziny> comparator = new Comparator<Godziny>() {
                @Override
                public int compare(Godziny g1, Godziny g2) {
                    return g1.date.compareTo(g2.date);
                }
            };

            Collections.sort(godziny, comparator);
            godziny.forEach(elem -> {
                System.out.println("link: " + elem.link + " data: " + formatter.format(elem.date));
            });
        } catch (HttpStatusException ex) {

        }


    }
}
