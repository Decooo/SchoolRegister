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
public class Ocena {

    private IntegerProperty id_oceny;
    private StringProperty opis_oceny;
    private IntegerProperty nr_albumu;
    private IntegerProperty id_przedmiotu;
    private StringProperty ocena;

    public Ocena() {
        this.id_oceny = new SimpleIntegerProperty();
        this.opis_oceny = new SimpleStringProperty();
        this.nr_albumu = new SimpleIntegerProperty();
        this.id_przedmiotu = new SimpleIntegerProperty();
        this.ocena = new SimpleStringProperty();
    }

    public int getId_oceny() {
        return id_oceny.get();
    }

    public void setId_oceny(int value) {
        id_oceny.set(value);
    }

    public IntegerProperty id_ocenyProperty() {
        return id_oceny;
    }

    public int getNr_albumu() {
        return nr_albumu.get();
    }

    public void setNr_albumu(int value) {
        nr_albumu.set(value);
    }

    public IntegerProperty nr_albumuProperty() {
        return nr_albumu;
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

    public String getOcena() {
        return ocena.get();
    }

    public void setOcena(String value) {
        ocena.set(value);
    }

    public StringProperty ocenaProperty() {
        return ocena;
    }

    public String getOpis_oceny() {
        return opis_oceny.get();
    }

    public void setOpis_oceny(String value) {
        opis_oceny.set(value);
    }

    public StringProperty opis_ocenyProperty() {
        return opis_oceny;
    }

}
