/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Nemanja
 */
public interface Operation {
    public static final int LOGIN = 1;
    
    public static final int SAVE_CLAN = 2;
    public static final int VRATI_JEDNOG_CLANA = 3;
    public static final int VRATI_SVЕ_CLANOVE = 4;
    public static final int VRATI_CLANOVE_PO_KRITERIJUMU = 5;
    public static final int OBRISI_CLANA = 6;
    public static final int IZMENI_CLANA = 7;
    
    public static final int SAVE_NOSNJA = 8;
    public static final int VRATI_JEDNU_NOSNJU = 9;
    public static final int VRATI_SVЕ_NOSNJE = 10;
    public static final int VRATI_NOSNJE_PO_KRITERIJUMU = 11;
    public static final int OBRISI_NOSNJU = 12;
    public static final int IZMENI_NOSNJU = 13;
    
    public static final int SAVE_OTPREMNICA = 14;
    public static final int VRATI_JEDNU_OTPREMNICE = 15;
    public static final int VRATI_SVЕ_OTPREMNICE = 16;
    public static final int VRATI_OTPREMNICE_PO_KRITERIJUMU = 17;
    public static final int OBRISI_OTPREMNICU = 18;
    public static final int IZMENI_OTPREMNICU = 19;
}
