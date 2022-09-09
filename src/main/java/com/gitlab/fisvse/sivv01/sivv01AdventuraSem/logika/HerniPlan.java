
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.main.Pozorovatel;
import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.main.PredmetPozorovani;

import java.util.HashSet;
import java.util.Set;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Viktor Sívek z předlohy Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2020/2021
 */
public class HerniPlan implements PredmetPozorovani {
    
    private Prostor aktualniProstor;
    private Batoh inventar;
    private Set<Pozorovatel> seznamPozorovatelu = new HashSet<>();


     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(){
        zalozProstorHry();
    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstorHry() {
        // vytvářejí se jednotlivé prostory
        Prostor vesnice = new Prostor("vesnice","ve vesnici, ta je tvou výchozi pozici");
        Prostor trziste = new Prostor("trziste","na trzisti, zde si muzes koupit lopatu");
        Prostor rozcesti = new Prostor("rozcesti","na rozcesti, kam se jenom vydas?");
        Prostor jezero = new Prostor("jezero","u jezera, tady nic neni");
        Prostor pole = new Prostor("pole","na poli, tady nic neni");
        Prostor zbrojnice = new Prostor("zbrojnice","ve zbrojnici, zde si muzes koupit zbran");
        Prostor les = new Prostor("les", "v lese, tady nic neni");
        Prostor jeskyne= new Prostor("jeskyne", "v jeskyni, zde je klic");
        Prostor bojiste = new Prostor("bojiste", "na bojišti, zde je prisera");
        Prostor pokladnice = new Prostor("pokladnice", "v poklednici, zde je poklad");
        
        // vytvářejí se jednotlivé věci
        Vec lopata = new Vec("lopata",true);
        Vec klic = new Vec("klic",true);
        Vec zbran = new Vec("zbran",true);
        Vec kladivo = new Vec("kladivo",true);
        Vec sekera = new Vec("sekera",true);
        Vec bagr = new Vec("bagr",false);
        Vec delo = new Vec("delo",false);
        Vec kostlivec = new Vec("kostlivec",false);
    
        // přiřazují se průchody mezi prostory (sousedící prostory)
        vesnice.setVychod(trziste);
        vesnice.setVychod(rozcesti);
        trziste.setVychod(vesnice);
        rozcesti.setVychod(vesnice);
        rozcesti.setVychod(jezero);
        rozcesti.setVychod(pole);
        rozcesti.setVychod(les);
        jezero.setVychod(rozcesti);
        pole.setVychod(rozcesti);
        pole.setVychod(zbrojnice);
        zbrojnice.setVychod(pole);
        les.setVychod(rozcesti);
        les.setVychod(jeskyne);
        les.setVychod(bojiste);
        jeskyne.setVychod(les);
        bojiste.setVychod(les);
        bojiste.setVychod(pokladnice);
        pokladnice.setVychod(bojiste);
        
        // přiřazují se věci v prostoru
        trziste.vlozVec(lopata);
        trziste.vlozVec(sekera);
        trziste.vlozVec(kladivo);
        trziste.vlozVec(bagr);
        zbrojnice.vlozVec(zbran);
        zbrojnice.vlozVec(delo);
        jeskyne.vlozVec(klic);
        jeskyne.vlozVec(kostlivec);
        
        
        aktualniProstor = vesnice;

        inventar = new Batoh();

    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor(){
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor){
        aktualniProstor = prostor;
        upozorniPozorovatele();
    }
    
    /**
     *  Metoda vrací inventář a věci co hráč drží
     *
     *@return     invetar
     */
    public Batoh getInventar() {
        return inventar;
    }

    /**
     *  Metoda přidá pozorovatele do seznamu pozorovatelů
     *
     *@param  pozorovatel
     */
    @Override
    public void registruj(Pozorovatel pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    /**
     * metoda projde seznam pozorovatelu a kazdeho upozorni na zmenu
     * volanim metody aktualizuj
     */
    private void upozorniPozorovatele() {
        for(Pozorovatel pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
    }
}