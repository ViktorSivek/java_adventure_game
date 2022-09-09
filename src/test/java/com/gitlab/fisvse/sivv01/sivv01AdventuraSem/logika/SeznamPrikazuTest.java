
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Viktor Sívek z předlohy: Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class SeznamPrikazuTest{
    
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazSeber prSeber;
    private PrikazVyhod prVyhod;
    private PrikazMapa prMapa;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp(){
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
        prSeber = new PrikazSeber(hra.getHerniPlan());
        prVyhod = new PrikazVyhod(hra.getHerniPlan());
        prMapa = new PrikazMapa(hra.getPlatneprikazy());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prVyhod);
        seznPrikazu.vlozPrikaz(prMapa);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(prSeber, seznPrikazu.vratPrikaz("seber"));
        assertEquals(prVyhod, seznPrikazu.vratPrikaz("vyhod"));
        assertEquals(prMapa, seznPrikazu.vratPrikaz("mapa"));
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
        assertEquals(null, seznPrikazu.vratPrikaz("inventar"));
    }
    
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prVyhod);
        seznPrikazu.vlozPrikaz(prMapa);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("seber"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("vyhod"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("mapa"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("inventar"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prVyhod);
        seznPrikazu.vlozPrikaz(prMapa);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(true, nazvy.contains("seber"));
        assertEquals(true, nazvy.contains("vyhod"));
        assertEquals(true, nazvy.contains("mapa"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("inventar"));
        assertEquals(false, nazvy.contains("Konec"));
    }
}
