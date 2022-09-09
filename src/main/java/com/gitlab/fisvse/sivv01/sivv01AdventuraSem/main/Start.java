
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.main;

import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika.Hra;
import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika.IHra;
import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.uiText.TextoveRozhrani;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*******************************************************************************
 * Třída {@code Start} je hlavní třídou projektu,
 * který ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Start extends Application {
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        if(args.length>0 && args[0].equals("text")) {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
            Platform.exit();
        } else {
            launch();
        }
    }

    /**
     * Metoda pro stuštění FXML souboru (grafické rozhraní hry).
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

