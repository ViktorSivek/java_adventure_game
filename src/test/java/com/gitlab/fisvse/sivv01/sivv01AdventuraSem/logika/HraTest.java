
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author   Viktor Sívek z předlohy: Jarmila Pavlíčková
 * @version  pro školní rok 2020/2021
 */
public class HraTest{
    
    private Hra hra1;
    

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp(){
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown(){
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry(){
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi trziste");
        assertEquals(true, hra1.konecHry());
        assertEquals("trziste", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber lopata");
        hra1.zpracujPrikaz("seber bagr");
        assertEquals(true, hra1.getHerniPlan().getInventar().obsahujeVec("lopata"));
        hra1.zpracujPrikaz("jdi vesnice");
        assertEquals(true, hra1.konecHry());
        assertEquals("vesnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi rozcesti");
        assertEquals(true, hra1.konecHry());
        assertEquals("rozcesti", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi pole");
        assertEquals(true, hra1.konecHry());
        assertEquals("pole", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi zbrojnice");
        assertEquals(true, hra1.konecHry());
        assertEquals("zbrojnice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber zbran");
        hra1.zpracujPrikaz("seber samopal");
        assertEquals(true, hra1.getHerniPlan().getInventar().obsahujeVec("zbran"));
        assertEquals(true, hra1.getHerniPlan().getInventar().obsahujeVec("lopata"));
        hra1.zpracujPrikaz("jdi pole");
        assertEquals(true, hra1.konecHry());
        assertEquals("pole", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi rozcesti");
        assertEquals(true, hra1.konecHry());
        assertEquals("rozcesti", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi les");
        assertEquals(true, hra1.konecHry());
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi jeskyne");
        assertEquals(true, hra1.konecHry());
        assertEquals("jeskyne", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber klic");
        hra1.zpracujPrikaz("seber kostlivec");
        assertEquals(true, hra1.getHerniPlan().getInventar().obsahujeVec("klic"));
        assertEquals(true, hra1.getHerniPlan().getInventar().obsahujeVec("zbran"));
        assertEquals(true, hra1.getHerniPlan().getInventar().obsahujeVec("lopata"));
        hra1.zpracujPrikaz("jdi les");
        assertEquals(true, hra1.konecHry());
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi bojiste");
        assertEquals(true, hra1.konecHry());
        assertEquals("bojiste", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi pokladnice");
        assertEquals(false, hra1.konecHry());
    }
}
