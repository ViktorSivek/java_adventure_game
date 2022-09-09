
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Viktor Sívek z předlohy: Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2020/2021
 *  
 */
public class PrikazMapa implements IPrikaz{

    private static final String NAZEV = "mapa";
    private SeznamPrikazu platnePrikazy;

    /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je mapa mohla zobrazit uživateli. 
    */    
    public PrikazMapa(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     *  Vrací mapu po zadání příkazu "mapa". Nyní se vypisuje cesta po mapě hry
     *  a věci co má hráč posbírat
     *  
     *  @return mapa
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                "Cesta: vesnice ➤ rozcesti ➤ les ➤ bojiste ➤ pokladnice  \n" +
                "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                "Potrebujes lopatu na vykopani pokladu ➤ trziste .\n" +
                "Do bojiste vstupuj pouze se zbrani ➤ zbrojnice .\n" +
                "Na otevreni pokladu potrbujes klic ➤ jeskyne .\n" +
                "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
