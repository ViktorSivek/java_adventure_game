package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.main;

import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.*;

/**
 * Trida Controler - tato třída vytvárí jednoduché grafické rozhraní hry pomocí FXML souboru.
 *
 * Tato třída je součástí jednoduché textové hry s základním grafickým rozhraním.
 *
 * @author Viktor Sívek, Filip Vencovský, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2021/2022
 */
public class Controller implements Pozorovatel {

    @FXML private ImageView hrac;
    @FXML private ListView<Prostor> seznamVychodu;
    @FXML private Button tlacitkoOdesli;
    @FXML private TextArea vystup;
    @FXML private TextField vstup;
    @FXML private ListView<Vec> inventar;
    @FXML private ListView<Vec> zem;

    private IHra hra = new Hra();
    private Map<String, Point2D> souradniceProstoru = new HashMap<>();
    private Map<String, String> nazvySouboruIkonekProstoru = new HashMap<>();
    private Map<String, String> nazvySouboruIkonekVeci = new HashMap<>();

    /**
     * Metoda initialize složí k vložení prostorů a věcí v grafickém rozhraní hry
     *
     */
    @FXML
    private void initialize() {
        hra.getHerniPlan().registruj(this);
        hra.getHerniPlan().getInventar().registruj(this);

        nazvySouboruIkonekProstoru.put("vesnice","vesnice.jpg");
        nazvySouboruIkonekProstoru.put("trziste","trziste.jpg");
        nazvySouboruIkonekProstoru.put("rozcesti","rozcesti.jpg");
        nazvySouboruIkonekProstoru.put("jezero","jezero.jpg");
        nazvySouboruIkonekProstoru.put("pole","pole.jpg");
        nazvySouboruIkonekProstoru.put("zbrojnice","zbrojnice.jpg");
        nazvySouboruIkonekProstoru.put("les","les.jpg");
        nazvySouboruIkonekProstoru.put("jeskyne","jeskyne.jpg");
        nazvySouboruIkonekProstoru.put("bojiste","bojiste.jpg");
        nazvySouboruIkonekProstoru.put("pokladnice","pokladnice.jpg");

        nazvySouboruIkonekVeci.put("lopata", "lopata.jpg");
        nazvySouboruIkonekVeci.put("sekera", "sekera.jpg");
        nazvySouboruIkonekVeci.put("kladivo", "kladivo.jpg");
        nazvySouboruIkonekVeci.put("bagr", "bagr.jpg");
        nazvySouboruIkonekVeci.put("zbran", "zbran.jpg");
        nazvySouboruIkonekVeci.put("delo", "delo.jpg");
        nazvySouboruIkonekVeci.put("klic", "klic.jpg");
        nazvySouboruIkonekVeci.put("kostlivec", "kostlivec.jpg");

        // TODO: doplnit další prostory

        seznamVychodu.setCellFactory(new Callback<ListView<Prostor>, ListCell<Prostor>>() {
            @Override
            public ListCell<Prostor> call(ListView<Prostor> param) {
                return new ListCell<Prostor>() {
                    @Override
                    protected void updateItem(Prostor prostor, boolean bln) {
                        super.updateItem(prostor, bln);
                        if (prostor != null) {

                            String nazevSouboru = nazvySouboruIkonekProstoru.get(prostor.getNazev());

                            ImageView pohled = new ImageView(new Image(nazevSouboru, 250, 200, false, true)); //TODO: načítat názvy z mapy
                            pohled.setPreserveRatio(true);
                            pohled.setFitHeight(70);

                            setText(prostor.getNazev());
                            setGraphic(pohled);

                        } else {
                            setText("");
                            setGraphic(new ImageView());
                        }
                    }
                };
            }
        });

        Callback<ListView<Vec>, ListCell<Vec>> veciCellFactory = new Callback<ListView<Vec>, ListCell<Vec>>() {
            @Override
            public ListCell<Vec> call(ListView<Vec> param) {
                return new ListCell<Vec>() {
                    @Override
                    protected void updateItem(Vec vec, boolean bln) {
                        super.updateItem(vec, bln);
                        if (vec != null) {

                            String nazevSouboru = nazvySouboruIkonekVeci.get(vec.getNazev());

                            ImageView pohled = new ImageView(new Image(nazevSouboru, 250, 200, false, true)); //TODO: načítat názvy z mapy
                            pohled.setPreserveRatio(true);
                            pohled.setFitHeight(70);

                            setText(vec.getNazev());
                            setGraphic(pohled);

                        } else {
                            setText("");
                            setGraphic(new ImageView());
                        }
                    }
                };
            }
        };

        inventar.setCellFactory(veciCellFactory);
        zem.setCellFactory(veciCellFactory);
        vystup.appendText(hra.vratUvitani()+"\n\n");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        });

        souradniceProstoru.put("vesnice", new Point2D(322,14));
        souradniceProstoru.put("trziste", new Point2D(507,14));
        souradniceProstoru.put("rozcesti", new Point2D(327,129));
        souradniceProstoru.put("jezero", new Point2D(503,127));
        souradniceProstoru.put("pole", new Point2D(167,128));
        souradniceProstoru.put("zbrojnice", new Point2D(14,125));
        souradniceProstoru.put("les", new Point2D(329,230));
        souradniceProstoru.put("jeskyne", new Point2D(505,235));
        souradniceProstoru.put("bojiste", new Point2D(321,330));
        souradniceProstoru.put("pokladnice", new Point2D(131,333));

        nacteniMistnosti();
        nacteniInventare();
        nacteniZeme();
    }

    /**
     * Metoda nacteniMistnosti definuje aktuální prostor, východy a polohu prostoru.
     *
     */
    private void nacteniMistnosti() {
        Prostor aktualniProstor = hra.getHerniPlan().getAktualniProstor();
        seznamVychodu.getItems().addAll(aktualniProstor.getVychody());

        hrac.setLayoutX(souradniceProstoru.get(aktualniProstor.getNazev()).getX());
        hrac.setLayoutY(souradniceProstoru.get(aktualniProstor.getNazev()).getY());

    }

    /**
     * Metoda nacteniInventare načítá věci v inventáří.
     *
     */
    private void nacteniInventare() {
        inventar.getItems().clear();
        inventar.getItems().addAll(hra.getHerniPlan().getInventar().getInventory().values());
    }

    /**
     * Metoda nacteniZeme načítá věci v prostoru.
     *
     */
    private void nacteniZeme() {
        zem.getItems().clear();
        zem.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeciVProstoru());
    }

    /**
     * Metoda zpracujVstup zpracuje text vypsaný do konzole.
     *
     */
    public void zpracujVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        zpracujPrikaz(prikaz);
    }

    /**
     * Metoda zpracujPrikaz provede příkaz napsaný do konzole po zpracování vstupu.
     * Pokud hra skončí znemožní další postup hrou.
     *
     */
    private void zpracujPrikaz(String prikaz) {

        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText("> "+prikaz+"\n");
        vystup.appendText(vysledek+"\n\n");
        vstup.clear();

        if(!hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            tlacitkoOdesli.setDisable(true);
            seznamVychodu.setDisable(true);
            inventar.setDisable(true);
            zem.setDisable(true);
        }
    }

    /**
     * Metoda klikSeznamVychodu umožňuje posouvat se mezi prostory pomocí klikání.
     *
     */
    public void klikSeznamVychodu(MouseEvent mouseEvent) {
        Prostor novyProstor = seznamVychodu.getSelectionModel().getSelectedItem();
        if(novyProstor==null) return;
        String prikaz = "jdi "+novyProstor;
        zpracujPrikaz(prikaz);
    }

    /**
     * Metoda klikInventar umožňuje pomocí kliknutí vyhazovat věci na zem.
     *
     */
    public void klikInventar(MouseEvent mouseEvent) {
        Vec vecKZahozeni = inventar.getSelectionModel().getSelectedItem();
        if(vecKZahozeni==null) return;
        String prikaz = "vyhod "+vecKZahozeni.getNazev();
        zpracujPrikaz(prikaz);
    }

    /**
     * Metoda klikZem umožňuje pomocí kliknutí brát věci ze země.
     *
     */
    public void klikZem(MouseEvent mouseEvent) {
        Vec vecKSebrani = zem.getSelectionModel().getSelectedItem();
        if(vecKSebrani==null) return;
        String prikaz = "seber "+vecKSebrani.getNazev();
        zpracujPrikaz(prikaz);
    }

    /**
     * Metoda klikNapoveda umožňuje zobrazit nápomědu v menu.
     *
     */
    public void klikNapoveda() {

        WebView view = new WebView();
        String urlNapovedy = getClass().getResource("/napoveda.html").toString();
        view.getEngine().load(urlNapovedy);

        Stage stage = new Stage();
        stage.setTitle("Nápověda");
        stage.setScene(new Scene(view));
        stage.show();
    }

    /**
     * Metoda klikNovaHra umožňuje spustit novou hru v menu.
     *
     */
    public void klikNovaHra() {
        this.hra = new Hra();
        hra.getHerniPlan().registruj(this);
        hra.getHerniPlan().getInventar().registruj(this);

        this.aktualizuj();

        vystup.clear();
        vystup.appendText(hra.vratUvitani()+"\n\n");

        vstup.setDisable(false);
        tlacitkoOdesli.setDisable(false);
        seznamVychodu.setDisable(false);
        inventar.setDisable(false);
        zem.setDisable(false);
    }

    /**
     * Metoda klikKonec umožňuje ukončit hru v menu.
     *
     */
    public void klikKonec(ActionEvent mouseEvent) {
        System.exit(0);
    }

    /**
     * Metoda aktualizuj vyčistí a znovu aktualizuje východy, inventář a věci na zemi.
     *
     */
    @Override
    public void aktualizuj() {
        seznamVychodu.getItems().clear();
        nacteniMistnosti();
        nacteniInventare();
        nacteniZeme();
    }
}


