package collectionExamples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        ArrayList<Feline> felines = new ArrayList<>();
        // handleCatList(felines);

        horribleCycle(new LinkedList<String>());
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

    // Felini e figli
    public static void covariantHandleFelineList(ArrayList<? extends Feline> list) { // Covariante: leggere ma non
                                                                                     // aggiungere
        // list.add(new Tiger);
        // in questo modo segnali al compilatore che gli passi liste di felini o figlie.
        // Facendo questo il compilatore ti vieta di aggiungere cose alla lista, però
        // posso usare i suoi elementi
        for (Feline f : list) {
            f.play();
        }
    }

    // Gatti e madri
    public static void handleCatList(ArrayList<? super Cat> list) { // Controvariante: aggiungere ma non leggere
        if (list.size() < 3) {
            list.add(new Cat());
        }
        // Obj x = list.get(0);
    }

    public static void horribleCycle(List<String> ls) {
        System.out.println(ls.iterator().getClass().getName());
        for (int i = 0; i < ls.size(); i++) {
            System.out.println(ls.get(i));
        }
        // Horrible perchè è O(n) nel caso delle ArrayList, ma O(n^2) per le LinkedList
        // (.get(n)) in un'ArrayList parte da 0 per arrivare a n per trovare quel dato
        // get è un metodo che funziona in due modi diversi tra ArrayList e LinkedList

        for (String s : ls) {
            System.out.println(s);
        }
        for (Iterator<String> it = ls.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
        // In questo modo si riesce ad avere O(n) anche per le LinkedList, visto che usa
        // un iteratore per scorrere la lista
    }

}

// interfacce semantiche sono come annotazioni alle classi, non contengono
// metodi ma marcano che una classe ha certe proprietà, come Set indetifica che
// non ci siano duplicati all'interno ma non ha metodi propri

// ArrayList & LinkedList
// ArrayList tiene i dati in liste con posti sequenziali
// LinkedList tiene i dati in posti non sequenziali, ma ogni dato ha un
// puntatore a quello successivo (o precedente)
// in entrambi i casi posso fare random access, cioè andare a leggere il dato in
// una posizione che non dipende dal dato precedente
// ArryList
// sono molto efficenti a fare un random access O(1), perchè non si ha
// necessità di scorrere l'array per trovare il dato alla posizione certcata,
// Perchè in un'arraylist i dati hanno tutti la stessa posizione e sono uno dopo
// l'altro in memoria. Posizione dell'array + dimensione del dato * posizione
// cercata trovo il dato alla posizione cercata
// Questo diventa un punto depole nel caso di modificare l'arraylist
// (rimuovere/aggiungere in mezzo all'array) perchè devo spostare avanti o
// indietro gli altri dati
// LinkedList
// invece sono O(1) per inserimento o rimozione di dati (a patto di avere già la
// posizione al cui si vuole inserire). Per inserire in mezzo basta modificare
// un puntatore e farlo puntare al nuovo dato.
// il punto debole delle LinkedList è invece fare il random access, dovendosi
// scorrere tutto l'array fino alla posizione per ottenere il dato che sto
// cercando
// ==> Scegli bene la lista in base all'utilizzo che se ne vuole fare

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
