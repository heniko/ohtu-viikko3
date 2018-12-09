/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Niko
 */
public class Nollaa implements Komento {

    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;
    int peruutettava;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        peruutettava = sovellus.tulos();
        sovellus.miinus(peruutettava);

        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
        nollaa.disableProperty().set(sovellus.tulos() == 0);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(peruutettava);
        tuloskentta.setText("" + sovellus.tulos());
        nollaa.disableProperty().set(sovellus.tulos() == 0);
        undo.disableProperty().set(true);
    }

}
