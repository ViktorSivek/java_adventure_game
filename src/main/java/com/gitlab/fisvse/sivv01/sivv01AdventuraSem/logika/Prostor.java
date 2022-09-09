
package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Viktor Sívek Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2020/2021
 */
public class Prostor{
    
    private String nazev;
    private String popis;
    private Set<Prostor> vychody;
    private List<Vec> veciVProstoru;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis){
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veciVProstoru = new ArrayList<>();
    }
    
    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi){
        vychody.add(vedlejsi);
    }
    
    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */ 
    public boolean equals(Object o){
       if (this == o) {
            return true;
        }
        
       if (!(o instanceof Prostor)) {
            return false;
        }
        
       Prostor druhy = (Prostor) o;
       return (java.util.Objects.equals(this.nazev, druhy.nazev));
    }
    
    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
    
    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }
    
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        StringBuilder vracenyText = new StringBuilder("východy:");
        for (Prostor sousedni : vychody) {
            vracenyText.append(" , ").append(sousedni.getNazev());
        }
        return vracenyText.toString();
    }
    
    /**
     * Vrací textový řetězec, který popisuje věci v prostoru
     *
     * @return Popis věcí - věcí v prostoru
     */
    private String popisVeci() {
        StringBuilder vracenyText = new StringBuilder("věci:");
        for (Vec neco : veciVProstoru) {
            vracenyText.append(" , ").append(neco.getNazev());
        }
        return vracenyText.toString();
    }
    
    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi " + popis + ".\n"
                + popisVychodu() + ".\n"
                + popisVeci();
    }
    
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }
    
    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Vkládá věc do ArrayListu veciVProstoru
     *
     * @param co chci vlozit
     *
     */
    public void vlozVec(Vec co){
        veciVProstoru.add(co);
    }
    
    /**
     * Odebírá věc z ArrayListu veciVProstoru
     *
     * @param nazevVeci chci odebrat
     *
     */
    public Vec odeberVec(String nazevVeci){
        Vec hledana = null;
        for (Vec vecZeSeznamu : veciVProstoru){
            if (vecZeSeznamu.getNazev().equals(nazevVeci)){
                hledana = vecZeSeznamu;
                veciVProstoru.remove(hledana);
                break;
            }
        }
        return hledana;
    }
    
    /**
     * Odebírá věc z ArrayListu veciVProstoru
     *
     * @param vec co chci odebrat
     *
     */
    public void odeberVec (Vec vec) {
        veciVProstoru.remove(vec);
    }
    
    /**
     * Zjišťuje zda se věc nacází v ArrayListu veciVProstoru
     *
     * @param nazevVeci u které chci zjistit zda je v prostoru
     *
     */
    public boolean jeVecVProstoru(String nazevVeci){
        boolean nalezena = false;
        for (Vec vecZeSeznamu : veciVProstoru){
            if (vecZeSeznamu.getNazev().equals(nazevVeci)){
                nalezena = true;
                break;
            }
        }
        return nalezena;
    }

    /**
     * Mění odkaz na název prostoru na String
     *
     */
    public String toString() {
        return getNazev();
    }

    /**
     * Metoda vypíše věci v prostoru
     *
     */
    public List<Vec> getVeciVProstoru() {
        return veciVProstoru;
    }
}
