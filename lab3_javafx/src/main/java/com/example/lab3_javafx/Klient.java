package com.example.lab3_javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Klient extends Application {
    static final String KEY = "SZYFR";
    TextArea odbiorWiadomosci;
    TextField wiadomosc;
    TextField nickname;
    BufferedReader czytelnik;
    PrintWriter pisarz;
    Socket gniazdo;


    public static void main(String[] args) throws IOException {
        Platform.runLater(() -> {
            try {
                Klient k1 = new Klient();
                k1.start(new Stage());
                Klient k2 = new Klient();
                k2.start(new Stage());
            } catch (Exception exception) {
            }
        });


    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Czy chcesz się połączyć? y/n");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String toRun = input.readLine();
        if (!toRun.equals("y")) {
            return;
        }
        stage.setTitle("Prosty klient czatu");

        VBox panel = new VBox(5);
        odbiorWiadomosci = new TextArea();
        odbiorWiadomosci.setWrapText(true);
        odbiorWiadomosci.setEditable(false);
        Pane przewijanie = new Pane(odbiorWiadomosci);
        wiadomosc = new TextField();
        nickname = new TextField();
        nickname.setMaxWidth(200);
        wiadomosc.setMaxWidth(300);
        Button przyciskWyslij = new Button("Wyslij");
        przyciskWyslij.setOnAction(new SluchaczPrzycisku());
        panel.getChildren().add(przewijanie);
        panel.getChildren().add(new Text("Nick:"));
        panel.getChildren().add(nickname);
        panel.getChildren().add(new Text("Wiadomość:"));
        panel.getChildren().add(wiadomosc);
        panel.getChildren().add(przyciskWyslij);
        konfiguruj();
        Thread watekOdbiorcy = new Thread(new Odbiorca());
        watekOdbiorcy.start();
        Scene scene = new Scene(panel, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Title");
        stage.show();
    }


    private void konfiguruj() {
        try {
            gniazdo = new Socket("127.0.0.1", 2020);
            InputStreamReader czytelnikStrm = new InputStreamReader(gniazdo.getInputStream());
            czytelnik = new BufferedReader(czytelnikStrm);
            pisarz = new PrintWriter(gniazdo.getOutputStream());
            System.out.println("Zakończono konfiguracje sieci");
        } catch (IOException ex) {
            System.out.println("Konfiguracja sieci nie powiodła się !");
            ex.printStackTrace();

        }
    }

    private class SluchaczPrzycisku implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                pisarz.println(Vigenere.encrypt(nickname.getText() + ": " + wiadomosc.getText(),KEY));
                pisarz.flush();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
            wiadomosc.setText("");
            wiadomosc.requestFocus();
        }
    }

    public class Odbiorca implements Runnable {
        @Override
        public void run() {

            String wiad;
            try {
                while ((wiad = czytelnik.readLine()) != null) {
                    System.out.println("Odczytano: " + Vigenere.decrypt(wiad,KEY));
                    odbiorWiadomosci.appendText(Vigenere.decrypt(wiad,KEY) + "\n");

                }
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}