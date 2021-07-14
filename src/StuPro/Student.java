package StuPro;

public class Student implements Comparable<Student>{
    int id;
    String nickname;
    String clan;
    int points;
    
    public Student(int id, String nickname, String clan, int points) {
        super();
        this.id = id;
        this.nickname = nickname;
        this.clan = clan;
        this.points = points;
    }

    @Override
    public String toString() {
        return "id=" + id + " nickname=" + nickname + " clan=" + clan + " points=" + points; 
    }

    @Override
    public int compareTo(Student o) {
        if ( this.id < o.id ) {
            return -1;
        } else if ( this.id > o.id ){
            return 1;
        }
        return 0;
    }
}
