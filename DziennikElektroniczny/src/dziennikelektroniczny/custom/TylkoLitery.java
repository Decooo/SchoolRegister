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
public class TylkoLitery {
    
    public static Boolean czySameLitery(String text){
        return text.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
    }
    
}
