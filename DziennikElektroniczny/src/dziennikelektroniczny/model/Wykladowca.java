package dziennikelektroniczny.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Wykladowca {

    private IntegerProperty id_wykladowcy;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty email;
    private StringProperty typ_specjalizacji;

    public Wykladowca() {
        this.id_wykladowcy = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.typ_specjalizacji = new SimpleStringProperty();
    }

    public int getId_wykladowcy() {
        return id_wykladowcy.get();
    }
    
     public String getId_wykladowcyString(){
        return String.valueOf(id_wykladowcy.get());
    }

    public void setId_wykladowcy(int value) {
        id_wykladowcy.set(value);
    }

    public IntegerProperty id_wykladowcyProperty() {
        return id_wykladowcy;
    }

    public String getImie() {
        return imie.get();
    }

    public void setImie(String value) {
        imie.set(value);
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public void setNazwisko(String value) {
        nazwisko.set(value);
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getTyp_specjalizacji() {
        return typ_specjalizacji.get();
    }
    
    public String getTyp_specjalizacjiString(){
        return String.valueOf(typ_specjalizacji.get());
    }

    public void setTyp_specjalizacji(String value) {
        typ_specjalizacji.set(value);
    }

    public StringProperty typ_specjalizacjiProperty() {
        return typ_specjalizacji;
    }

}
