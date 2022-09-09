
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Viktor Sívek Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2020/2021
 */
class PrikazSeber implements IPrikaz {
    
    private static final String NAZEV = "seber";
    private HerniPlan plan;

    /**
    *  Konstruktor třídy
    *  
    *  @param plan plán
    */  
    public PrikazSeber(HerniPlan plan){
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "seber". Zkouší sebrat zadanou věc. Pokud se věc v prostoru
     *  nachází, sebere věc do inventáře. Pokud se věc v prostoru nenachází
     *  vypíše se chybové hlášení.
     *
     *@param parametry co chci sebrat
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Napiš název předmětu.";
        }

        String coSebrat = parametry[0];

        Prostor kdeJsem = plan.getAktualniProstor();
        Vec coSbiram = kdeJsem.odeberVec(coSebrat);

        if (coSbiram == null) {
            return "To tu není!";
        }
        else {
            if(coSbiram.jePrenosna()){
                if (plan.getInventar().neniPlny()) {
                    kdeJsem.odeberVec(coSbiram);
                    plan.getInventar().addBatoh(coSbiram);
                    return "\n" + coSebrat + " jsi vlozil do batohu ";
                }
                else {
                    kdeJsem.vlozVec(coSbiram);
                    return "\nChyba: V batohu neni misto!!Musis neco polozit.";
                }
            }
            else{
                kdeJsem.vlozVec(coSbiram);
                return "\nVec " + coSebrat +" si nemuzes odnest v batohu.";
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

