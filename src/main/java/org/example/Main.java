package org.example;

import org.example.domain.DataBase;
import org.example.ui.Ventana;

import javax.xml.crypto.Data;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        (new Ventana()).load();

        DataBase.getAll();
    }
}