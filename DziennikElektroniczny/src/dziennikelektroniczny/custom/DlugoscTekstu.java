/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.custom;

/**
 *
 * @author Jakub
 */
public class DlugoscTekstu {

    public static Boolean czyJest11Znakow(String text) {
        return text.length() == 11;
    }

    public static Boolean czyNieJestPusty(String text) {
        return text.length() > 0;
    }

}
