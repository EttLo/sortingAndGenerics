package collectionExamples;

import java.util.Comparator;

import model.Instructor;

public class InstructorComparatorBySpecNumber implements Comparator<Instructor> {

    @Override
    public int compare(Instructor o1, Instructor o2) {
        return o1.getSpecialization().size() - o2.getSpecialization().size();
    }

}
