package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

/**
 *  Třída PrikazVyhod implementuje pro hru příkaz vyhod.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Viktor Sívek z předlohy: Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2020/2021
 */
public class PrikazVyhod implements IPrikaz {

    private static final String NAZEV = "vyhod";
    private HerniPlan plan;

    /**
    *  Konstruktor třídy
    *  
    *  @param plan plán
    */ 
    public PrikazVyhod(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "vyhod". Zkouší vyhodit zadanou věc. Pokud věc je v inventáři,
     *  vyhodí ji z inventáře. Pokud věc je v inventáři, vypíše se chybové hlášení.
     *
     *@param parametry co chci vyhodit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš zadat co vyhodit";
        } else {
            String jmeno = parametry[0];
            Vec vec = this.plan.getInventar().odeberVec(jmeno);
            if (vec == null) {
                return "Nemůžeš vyhodit z batohu něco, co tam nemáš";
            } else {
                this.plan.getAktualniProstor().vlozVec(vec);
                plan.getInventar().odeberVec(jmeno);
                return "Vyhodil si " + jmeno;
            }
        }
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
