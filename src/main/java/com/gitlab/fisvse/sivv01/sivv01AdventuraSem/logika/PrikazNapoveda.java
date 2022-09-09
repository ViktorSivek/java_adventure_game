
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Viktor Sívek z předlohy: Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2020/2021
 *  
 */
public class PrikazNapoveda implements IPrikaz{
    
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *  které je možné ve hře použít,
    *  aby je nápověda mohla zobrazit uživateli. 
    */  
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
                "Tvým úkolem je zabít příšeru a získat poklad.\n" +
                "Pohybujete se kliknutím na východ.\n" +
                "Inventář se ovládá klikáním na předměty.\n" +
                "Pro zjištění trasy napiš mapa.\n" +
               "\n" +
                "▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁\n" +
               "Můžeš zadat tyto příkazy:\n" +
               platnePrikazy.vratNazvyPrikazu() +
                "\n" +
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
