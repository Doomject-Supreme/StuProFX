package StuPro;

import java.util.Comparator;

public class StudentPointsComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        return o2.points - o1.points;
        
        
        
    }

}
