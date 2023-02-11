package com.company;

public class Sklejane {
    double[][] macierz_alf;
    double[] macierz_y;
    double[] x;
    double[] y;
    double[] wyniki;

    public Sklejane(double... args) {
        this.x = new double[args.length / 2];
        this.y = new double[(args.length / 2)];
        this.macierz_y=new double[x.length+2];
        this.wyniki=new double[x.length+2];
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                this.x[temp1] = args[i];
                temp1++;
            }
            if (i % 2 == 0 && i != 0) {
                this.x[temp1] = args[i];
                temp1++;
            }
            if (i % 2 != 0) {
                this.y[temp2] = args[i];
                temp2++;
            }


        }
        this.macierz_alf = new double[x.length + 2][x.length + 2];
    }
    void daj_y(double a,double b){
        int i;
    for( i=0;i<x.length;i++){macierz_y[i]=y[i];}
    macierz_y[i]=a;
    macierz_y[i+1]=b;

    }
    double pow(double x, int n) {
        double wynik = x;
        if (n == 0) return 1.0;
        if(n<0) return 0.0;
        for (int i = 0; i < n - 1; i++) {
            wynik *= x;
        }
        return wynik;
    }

    //liczenie macierzy alf
    void Alfa_matrix() {
        for (int i = 0; i < x.length + 2; i++) {
            for (int z = 0; z < x.length + 2; z++) {
                macierz_alf[i][z] = 0;
            }
        }
        for (int i = 0; i < x.length; i++) {
            if (i == 0) {
                for (int z = 0; z < x.length + 2; z++) {
                    if (z <= 3) {
                        macierz_alf[i][z] = pow(x[i], z);
                    } else {
                        macierz_alf[i][z] = 0;
                    }
                }

            } else {
                int tmp = 1;
                for (int z = 0; z < x.length + 2; z++) {
                    if (z <= 3) {
                        macierz_alf[i][z] = pow(x[i], z);
                    } else {

                        if (z < 3 + i) {
                            macierz_alf[i][z] = pow(x[i] - x[tmp], 3);
                            tmp++;
                        }


                    }
                }
            }



        }
    }

    void Alfa_derivative() {
        int tmp=1;
        for (int z = 0; z < x.length+2; z++) {
            if (z <= 3) {
                macierz_alf[x.length][z] = z * pow(x[0],z-1);
                macierz_alf[x.length+1][z]= z* pow(x[x.length-1],z-1);
            } else {
              macierz_alf[x.length+1][z]= 3* pow(x[x.length-1]-x[tmp],2);
              tmp++;
            }
        }
    }

    void wypisz() {
        for (int i = 0; i < x.length + 2; i++) {
            for (int z = 0; z < x.length + 2; z++) {
                System.out.print(macierz_alf[i][z] + " ");
            }
            System.out.println();
        }
    }
    public void Gauss(double[][] A, double[] B)
    {
        int N = B.length;
        for (int k = 0; k < N; k++)
        {

            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[k][i]) > Math.abs(A[max][i]))
                    max = i;

            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            double t = B[k];
            B[k] = B[max];
            B[max] = t;


            for (int i = k + 1; i < N; i++)
            {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }


        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
       for(int z=0;z< solution.length;z++){wyniki[z]=solution[z];}
    }

    void wypisz_wyniki(){for(int i=0;i< wyniki.length;i++)System.out.print(wyniki[i]+" ");}



    public void oblicz_wynik(double g)
    {
        int tmp=0;

        for(int i=0;i<x.length;i++){if(g>x[i])tmp++;}
        double odpowiedz=wyniki[0];
        for(int i=1;i< 4;i++){
                odpowiedz+=wyniki[i]*pow(g,i);}
        for(int z=0;z<tmp-1;z++){
            odpowiedz=odpowiedz+wyniki[4+z]*pow(g-x[z+1],3);
        }
        System.out.println(odpowiedz);
    }
    void licz_wynik(double x){




    }
}
