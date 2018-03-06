package dziennikelektroniczny.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jakub
 */
public class Student {

    private IntegerProperty nr_albumu;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty pesel;
    private IntegerProperty id_grupy;

    public Student() {
        this.nr_albumu = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.pesel = new SimpleStringProperty();
        this.id_grupy = new SimpleIntegerProperty();
    }

    public int getNr_albumu() {
        return nr_albumu.get();
    }

    public String getNr_albumuString(){
        return String.valueOf(nr_albumu.get());
    } 
    
    public void setNr_albumu(int nr_albumu) {
        this.nr_albumu.set(nr_albumu);
    }

    public IntegerProperty nr_albumuProperty() {
        return nr_albumu;
    }

    public String getImie() {
        return imie.get();
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel.get();
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public int getId_grupy() {
        return id_grupy.get();
    }
    public String getId_grupyString(){
        return String.valueOf(id_grupy.get());
    } 

    public void setId_grupy(int id_grupy) {
        this.id_grupy.set(id_grupy);
    }

    public IntegerProperty id_grupyProperty() {
        return id_grupy;
    }

}
