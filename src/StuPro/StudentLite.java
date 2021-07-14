package StuPro;

public class StudentLite {
    public String nickname;
    public int points;
    
    public StudentLite(String nickname, int points) {
        super();
        this.nickname = nickname;
        this.points = points;
    }

    @Override
    public String toString() {
        return("nickname=" + nickname + " points=" + points); 
    }

}
