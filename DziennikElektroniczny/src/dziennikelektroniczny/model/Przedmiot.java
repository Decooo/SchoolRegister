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
public class Przedmiot {

    private IntegerProperty id_przedmiotu;
    private StringProperty nazwa;
    private StringProperty typ_przedmiotu;

    public Przedmiot() {
        this.id_przedmiotu = new SimpleIntegerProperty();
        this.nazwa = new SimpleStringProperty();
        this.typ_przedmiotu = new SimpleStringProperty();
    }

    public int getId_przedmiotu() {
        return id_przedmiotu.get();
    }

    public String getId_przedmiotuString() {
        return String.valueOf(id_przedmiotu.get());
    }

    public void setId_przedmiotu(int value) {
        id_przedmiotu.set(value);
    }

    public IntegerProperty id_przedmiotuProperty() {
        return id_przedmiotu;
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public String getNazwaString() {
        return String.valueOf(nazwa.get());
    }

    public void setNazwa(String value) {
        nazwa.set(value);
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public String getTyp_przedmiotu() {
        return typ_przedmiotu.get();
    }

    public String getTyp_przedmiotuString() {
        return String.valueOf(typ_przedmiotu.get());
    }

    public void setTyp_przedmiotu(String value) {
        typ_przedmiotu.set(value);
    }

    public StringProperty typ_przedmiotuProperty() {
        return typ_przedmiotu;
    }

}
