
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Viktor Sívek z předlohy: Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2020/2021
 */
public class Hra implements IHra {
    
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    
    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra(){
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVyhod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMapa(platnePrikazy));
    }
    
    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return  "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                "⚑ Vitejte! ⚑\n"  +
                "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                "Prijeli jste do vesnice s mapou pokladu.\n" +
                "Napiste napoveda, pokud si nevite rady.\n" +
                "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Diky, ze jste hrali";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return !konecHry;
    }
    
    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
               parametry[i]= slova[i+1];      
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            String zaverecnyText = overZdaJeKonec();
            String bojisteText = overZdaJeBojiste();
            if (bojisteText != null){
                textKVypsani = bojisteText;
            }
            if (zaverecnyText != null) {
                textKVypsani = zaverecnyText;
            }
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }

    /** 
     * Pokud do metody zpracujPrikaz bude zadán parametr ´bojiste´ tak se spustí tato metoda.
     */
    private String overZdaJeBojiste(){
        if (herniPlan.getAktualniProstor().getNazev().equals("bojiste")){
            if (herniPlan.getInventar().obsahujeVec("zbran")){
                return "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                        "Zabil si priseru\n" +
                        "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁";
            }
            else {
                this.setKonecHry(true);
                return "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                        "Zabila te prisera\n" +
                        "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁";
            }
        }
        return null;
    }
    
    /** 
     * Pokud do metody zpracujPrikaz bude zadán parametr ´pokladnice´ tak se spustí tato metoda.
     */
    private String overZdaJeKonec(){
        if (herniPlan.getAktualniProstor().getNazev().equals("pokladnice")){
            if (herniPlan.getInventar().obsahujeVec("klic") && herniPlan.getInventar().obsahujeVec("lopata")) {
                this.setKonecHry(true);
                return "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                        "Nasel si poklad\n" +
                        "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁";
            }
            else{
                return "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                        "Nasel si poklad, ale nemas klic, nebo lopatu\n" +
                        "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁";
            }
        }
        return null;
    }
    
    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }
    
    /**
     *  Metoda vrátí odkaz na platné příkazy, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní platné příkazy.
     *  
     *  @return     odkaz na herní plán
     */
    public SeznamPrikazu getPlatneprikazy(){
        return platnePrikazy;
    }
}
