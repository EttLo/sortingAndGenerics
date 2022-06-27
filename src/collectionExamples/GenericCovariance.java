package collectionExamples;

import java.util.ArrayList;

public class GenericCovariance {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        handleFeline(cat1);
        Cat[] cats = new Cat[2];
        ArrayList<Cat> cal = new ArrayList<>();
        // handleFelineArray(cats);
        // problema, un array di gatti è diverso da un'array di felini. Funziona perchè
        // è spesso utile che si possa passare un'array di figlie dove richiesto
        // un'array di madri per polimorfismo. Problema quando si cerca di aggiungere
        // una tigre ad un'array di gatti
        // Gli array sono covarianti, come il polimorfismo regolare
        // mantenuto perchè il programma crasha immediatamente, così si può risolvere
        // subito. D'altro lato è utile per polimorfismo
        // handleFelineList(cal);
        // con le arraylist non è permesso. perchè il crash non è immediato perchè le
        // generics sono trucchi del compilatore, per la vm posso mettere una tigre in
        // una list perchè si aspetta un Object. Questo significa che crasha quando
        // chiamo sulla tigre che ho aggiunto un metodo che ha solo il gatto
        // Per evitare questo le generics non sono covarianti

    }

    public static void handleFeline(Feline f) {
        System.out.println(f);
    }

    public static void handleFelineArray(Feline[] f) {
        f[0] = new Tiger();
    }

    public static void handleFelineList(ArrayList<Feline> list) {
        list.add(new Tiger());
    }

    public static void covariantHandleFelineList(ArrayList<? extends Feline> list) {
        // list.add(new Tiger);
        // in questo modo segnali al compilatore che gli passi liste di felini o figlie.
        // Facendo questo il compilatore ti vieta di aggiungere cose alla lista, però
        // posso usare i suoi elementi
        for (Feline f : list) {
            f.play();
        }
    }
}

class Feline {
    void play() {
    }
}

class Cat extends Feline {

}

class Persian extends Cat {

}

class Tiger extends Feline {

}
