package com.gitlab.fisvse.sivv01.sivv01AdventuraSem.main;

/**
 * Trida PredmetPozorovani - vytváří interface PredmetPozorovani
 *
 * Tato třída je součástí jednoduché textové hry s základním grafickým rozhraním.
 *
 * @author Viktor Sívek, Filip Vencovský, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2021/2022
 */
public interface PredmetPozorovani {
    /**
     * pomocí metody se registrují pozorovatelé k odběru změn
     * @param pozorovatel
     */
    void registruj(Pozorovatel pozorovatel);
}
