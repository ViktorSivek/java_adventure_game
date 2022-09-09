package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

/**
 *  Třída PrikazInventar implementuje pro hru příkaz inventar.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Viktor Sívek z předlohy: Jarmila Pavlickova
 *@version    pro školní rok 2020/2021
 */
public class PrikazInventar implements IPrikaz {

    private static final String NAZEV = "inventar";
    private HerniPlan plan;

    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém mám inventář 
    */ 
    public PrikazInventar(HerniPlan plan){
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "inventar". Zkouší se zda držím nějaké věci. Pokud držím
     *  věc tak se vypíše. Pokud věc nedržím vypíše se chybové hlášení.
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        String result;
        if (parametry.length > 0) {
            // pokud se zadá druhý parametr
            return "Tento prikaz je bez parametru.";
        } else {
            result = plan.getInventar().toString();
        }
        return  "▛▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▜"  + "\n"
                +"  " + result + "\n" +
                "▙▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▟"; //vypíše obsah inv
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
