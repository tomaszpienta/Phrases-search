package Sercherv2;

import Sercherv2.Szukacz.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sercherv2 extends Application{
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        VBox Pion = new VBox(2);
        Pion.setPadding(new Insets(3));
        Label Odczytywanie = new Label();
        Odczytywanie.setText("Przeszukiwany plik: ");
        Pion.getChildren().add(Odczytywanie);
        TextField odczyt = new TextField();
        Pion.getChildren().add(odczyt);
        
        
        Label Zapisywanie = new Label();
        Zapisywanie.setText("Zapis do pliku: ");
        Pion.getChildren().add(Zapisywanie);
        TextArea zapisz = new TextArea();
        zapisz.setWrapText(true);
        Pion.getChildren().add(zapisz);

        
        HBox Poziom = new HBox(5);
        Label szukaj = new Label("Szukaj Frazy: ");
        TextField Szukaj = new TextField();
        Szukaj.setMaxWidth(235);
        Poziom.getChildren().add(Szukaj);
        Poziom.getChildren().add(szukaj);
        Pion.getChildren().add(Poziom);
        Label wynik = new Label("Wynik: ");
        Pion.getChildren().add(wynik);
        TextArea Wynik = new TextArea();
        Wynik.setEditable(true);
        Pion.getChildren().add(Wynik);
        HBox ButtonHBox = new HBox(5);
        Button ButtonSzukaj = new Button("Szukaj: ");
        ButtonSzukaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println(odczyt.getText());
                System.out.println(zapisz.getText());
                System.out.println(Szukaj.getText());
                
                try (Scanner in = new Scanner(new File(odczyt.getText()))){      

                    FileWriter zapis1;
                    zapis1 = new FileWriter(zapisz.getText());
                    zapis1.append("");
                    zapis1.close();    

                    FileWriter zapis;
                    List<String> list;
                    String Fraza1;
                
                        
                    zapis = new FileWriter(new File(zapisz.getText()), true);
                    list = new ArrayList<>();
                    Scanner jedna = new Scanner(Szukaj.getText());
                    Fraza1 = jedna.nextLine();
                    System.out.println("Sszukana fraza to:" + Szukaj.getText());
                    while(in.hasNext()){
                        list.add(in.nextLine());
                    }
                    in.close(); 
                    
                    for (String s2 : list){        
                        if (s2.contains(Fraza1)) {                    
                            zapis.write(s2 + "\n");   
                            zapis.write(Wynik.getText());
                            
                            Wynik.setText(s2);
                            System.out.print("WYNIK: "+Wynik.getText()+ "\n");                          
                        }
                    } 
                    zapis.close();
                    System.in.read();
                }
                catch (IOException ex)
                {
                    System.err.println("Error" + ex);
                }
                
            }
        });
        
        Button ButtonZakoncz = new Button("Koniec");
        ButtonZakoncz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        
        ButtonHBox.getChildren().addAll(ButtonSzukaj, ButtonZakoncz);
        Pion.getChildren().add(ButtonHBox);

        Scene scene = new Scene(Pion, 350, 300);
        
        primaryStage.setTitle("Szukajka");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
