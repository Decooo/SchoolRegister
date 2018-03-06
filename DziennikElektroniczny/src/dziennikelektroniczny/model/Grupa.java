/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jakub
 */
public class Grupa {

    private IntegerProperty id_grupy;
    private StringProperty nazwa;

    public Grupa() {
        this.id_grupy = new SimpleIntegerProperty();
        this.nazwa = new SimpleStringProperty();
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public void setNazwa(String value) {
        nazwa.set(value);
    }

    public StringProperty nazwaProperty() {
        return nazwa;
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

    public String getId_grupyString(){
        return String.valueOf(id_grupy.get());
    } 
}
