
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

/**
 * Trida Vec - popisuje jednotlivé věci hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Věc" reprezentuje jednu věc ve scénáři hry.
 *
 * @author Viktor Sívek z předlohy: Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2020/2021
 */
public class Vec{
    
    private String nazev;
    private boolean prenositelnost;

    /**
     * Vytvoření věci se zadaným názvem a přenostitelností
     *
     * @param nazev věci
     * @param prenositelnost věci
     */
    public Vec(String nazev, boolean prenositelnost){
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    /**
     * Metoda vrací název věci
     *
     */
    public String getNazev(){
        return nazev;
    }

    /**
     * Metoda zjišťuje přenositelnost
     *
     */
    public boolean jePrenosna(){
        return prenositelnost;
    }
}
