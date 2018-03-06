/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Jakub
 */
public class PrzedmiotyGrupy {

    private IntegerProperty id_przedmioty_grupy;
    private IntegerProperty id_grupy;
    private IntegerProperty id_przedmiotu;
    private IntegerProperty id_wykladowcy;

    public PrzedmiotyGrupy() {
        this.id_przedmioty_grupy = new SimpleIntegerProperty();
        this.id_grupy = new SimpleIntegerProperty();
        this.id_przedmiotu = new SimpleIntegerProperty();
        this.id_wykladowcy = new SimpleIntegerProperty();
    }

    public int getId_przedmioty_grupy() {
        return id_przedmioty_grupy.get();
    }

    public void setId_przedmioty_grupy(int value) {
        id_przedmioty_grupy.set(value);
    }

    public IntegerProperty id_przedmioty_grupyProperty() {
        return id_przedmioty_grupy;
    }
    
    public String getId_przedmiotuString(){
        return String.valueOf(id_przedmiotu.get());
    } 

    public int getId_grupy() {
        return id_grupy.get();
    }

    public void setId_grupy(int value) {
        id_grupy.set(value);
    }

    public IntegerProperty id_grupyProperty() {
        return id_grupy;
    }

    public int getId_przedmiotu() {
        return id_przedmiotu.get();
    }

    public void setId_przedmiotu(int value) {
        id_przedmiotu.set(value);
    }

    public IntegerProperty id_przedmiotuProperty() {
        return id_przedmiotu;
    }

    public int getId_wykladowcy() {
        return id_wykladowcy.get();
    }

    public void setId_wykladowcy(int value) {
        id_wykladowcy.set(value);
    }

    public IntegerProperty id_wykladowcyProperty() {
        return id_wykladowcy;
    }

}
