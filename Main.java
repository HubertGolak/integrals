package com.company;

public class Main {

    public static void main(String[] args) {
	Sklejane a=new Sklejane(-4,-1207,-2,-75,0,1,2,-19,4,-711);
    a.daj_y(1182,-802);
    a.Alfa_matrix();
    a.Alfa_derivative();
     a.wypisz();

    a.Gauss(a.macierz_alf,a.macierz_y);
    a.oblicz_wynik(1);
    }
}
