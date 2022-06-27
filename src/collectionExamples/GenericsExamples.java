package collectionExamples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Instructor;

public class GenericsExamples {
    public static void main(String[] args) {
        // in origine non c'erano le generics, alle liste venivano passati Object
        // i problemi pricipali sono in lettura
        List li = new ArrayList();
        li.add(new Integer(5));
        li.add("ciccio");
        li.add(new Instructor("1", "2", LocalDate.of(2000, 1, 1), "ye", new ArrayList<>()));
        Object x = li.get(0);
        // x da li alla posizione 0 non ci faccio niente a parte i metodi di
        // Object. Sono obbligato a fare un downcasting pericoloso
        // devo sapere cosa c'è nell'elemento scelto per chiamarci sopra le funzioni
        // appropriate, se ci sbagliamo crasha il programma
        if (li.get(0) instanceof String) {
            String forse = (String) li.get(0); // devo obbligare il compilatore a fare sta porcata
        }

        List<String> ls = new ArrayList<>();
        // List<> è una List, <String> è un controllo che fa il compilatore. A livello
        // di virtual machine non c'è nessuna differenza, le generics non esistono
        // ls.add(new Integer(4));
        ls = li; // questo è permesso per compatibilità con vecchi progetti
        String maAncheNo = ls.get(0); // il compilatore non mi fa neanche fare il cast,
                                      // ma dentro non c'è una stringa
        useStringList(li); // le generics possono portare a crash sull'altare della compatibility
    }

    public static void useStringList(List<String> list) {
        if (list.size() > 0) {
            System.out.println(list.get(0).length());
        }
    }
}
