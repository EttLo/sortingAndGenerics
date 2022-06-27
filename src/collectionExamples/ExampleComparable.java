package collectionExamples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import model.Instructor;

public class ExampleComparable {
    public static void main(String[] args) {
        Set<String> ss1 = new TreeSet<>();
        Set<String> sc1 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        ss1.add("pippo");
        ss1.add("ciccio");
        ss1.add("paperino");
        ss1.add("pippo");
        System.out.println(ss1.size());
        for (String element : ss1) {
            System.out.println(element);

        }
        Instructor i1 = new Instructor("ettore", "lopes", LocalDate.of(1998, 9, 17), "heyciao", new ArrayList<>());
        Instructor i2 = new Instructor("alessio", "tutera", LocalDate.of(1995, 5, 20), "ciao2", new ArrayList<>());
        Set<Instructor> ss2 = new TreeSet<>();
        ss2.add(i1);
        ss2.add(i2);
        for (Instructor element : ss2) {
            System.out.println(element);
        }

        ArrayList<Instructor> al1 = new ArrayList<>();
        al1.add(i1);
        al1.add(i2);
        Collections.sort(al1);
        for (Instructor instructor : al1) {
            System.out.println(instructor);
        }
        // quando chiamo sort ho bisogno di un metodo per ordinarli. => implementare
        // l'interfaccia con quel metodo e inserirlo dentro a classe

        // Cosa facciamo però a cambiare le regole dell'ordinamento degli oggetti? Tipo
        // ordinarli per numero di specializzazioni
        Comparator<Instructor> c = new InstructorComparatorBySpecNumber();
        Collections.sort(al1, c);
        // Collections.sort(al1, new InstructorComparatorBySpecNumber());
        for (Instructor instructor : al1) {
            System.out.println(instructor);
        }

        Collections.sort(al1, new Comparator<Instructor>() {
            // new Comparator<Instructor> crea un nuovo oggetto di una nuova classe,
            // creata dal compilatore, che implementi Comparator. Classe interna anonima
            @Override
            public int compare(Instructor o1, Instructor o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Instructor instructor : al1) {
            System.out.println(instructor);
        }

    }
}
