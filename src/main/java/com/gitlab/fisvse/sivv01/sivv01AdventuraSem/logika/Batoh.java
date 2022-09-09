
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.main.Pozorovatel;
import com.gitlab.fisvse.sivv01.sivv01AdventuraSem.main.PredmetPozorovani;

import java.util.*;

/**
 *  Class Batoh - eviduje seznam věci v inventáři.
 *  Používá se při braní věcí
 *  Každá nová věc se musí do seznamu přidat metodou addBatoh.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Viktor Sívek z předlohy: Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2020/2021
 */
public class Batoh implements PredmetPozorovani {

    static final int BatohMax = 3;
    private final Map<String,Vec> inventory;
    private Set<Pozorovatel> seznamPozorovatelu = new HashSet<>();

    /**
     * Konstruktor
     */
    public Batoh(){
        inventory = new HashMap<>();
    }

    /**
     * Zjišťuje zda batoh není plný (nepřesáhl zadanou velikost)
     *
     */
    public boolean neniPlny() {
        if ( inventory.size() < BatohMax ) {
            return true;
        }
        return false;
    }
    
    /**
     * Vkládá novou věc.
     *
     *@param  vec co chci přidat
     */
    public void addBatoh(Vec vec) {
        inventory.put(vec.getNazev(), vec );
        this.upozorniPozorovatele();
    }

    /**
     * Zjišťuje zda batoh obsahuje věc
     *
     *@param  nazev věc co chci zjistit
     */
    public boolean obsahujeVec(String nazev) {
        return inventory.containsKey(nazev); //pokud je klíč obsažen v mapě, vrací true
    }
    
    /**
     * Odebírá věc.
     *
     *@param  nazev co chci odebrat
     */
    public Vec odeberVec(String nazev) {
        Vec odebranaVec = inventory.remove(nazev);  //v mapě se zrusí odpovídající klíč s hodnotou
        this.upozorniPozorovatele();
        return odebranaVec;
    }

    /**
     * Vypíše obsah inventáře jako String
     *
     */
    public String toString(){
        if(inventory.size() == 0) { //počet prvků uložených v mapě (inventari) je nula
            return " Inventar je prazdny.";
        }
        StringBuilder result = new StringBuilder();
        for(String s : inventory.keySet()) { //procházení mapy; vrací množinu obsahující klíče - předměty, které jsou v inv
            result.append(s).append(", ");
        }
        return result.toString();
    }

    /**
     * Vrací inventář
     *
     *  @return     odkaz na herní plán
     */
    public Map<String, Vec> getInventory() {
        return inventory;
    }

    @Override
    public void registruj(Pozorovatel pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    private void upozorniPozorovatele() {
        for(Pozorovatel pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
    }
}
