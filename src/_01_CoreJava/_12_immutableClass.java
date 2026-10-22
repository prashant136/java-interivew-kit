package _01_CoreJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class _12_immutableClass {
    void main() {

// 👉Question: There is a list of Object. can we add string other data Structure.
//        List<Object> list = new ArrayList<>();
//        list.add(1);
//        list.add("maitra");
//        list.add(new HashMap<>());
//        System.out.println(list);
    }
}


// ---------------------------------------------------------------------------


/// Immutable Class - It is class where the state of Object can't be changed once it is created.
/// Once the fields of Object are assigned some values, it can't be updated.
/// It is read only class.
/// Rules of Immutability in a class -
/// 1. No setter methods inside the class.
/// 2. Fields must be private
/// 3. Initialization of fields using constructor
/// 4. Fields must be final (class ka koi internal method hi fields ki values ko change kr de)
/// 5. Class must be final (mutable child class iske fields ko change karne ki koshish kare) (Mutable chaild class will make the parent class also as mutable)
/// 6. Whenever mutable field, we should return copy of the object instead of returning actual obejct.


final class Player {
    private final String name;
    private final int age;
    private final String team;
    private final Ranking ranking;

    public Player(String name, int age, String team, Ranking ranking) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.ranking = new Ranking(ranking.getBattingRank(), ranking.getBowlingRank());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTeam() {
        return team;
    }

// ❌ big red flag
//    public Ranking getRanking() {
//        return ranking;     // ❌[interviewer] return mutable object directly will make your class mutable.
//    }

// ✅ [interviewer] instead of returning original object directly, create copy of the object then return the copy.
    public Ranking getRanking() {
        return new Ranking(ranking.getBattingRank(), ranking.getBowlingRank());
    }

    public void print() {
        System.out.println("Name: " + name + ", Age: " + age + ", Team: " + team + ", Bating: " + ranking.getBattingRank() + ", Bowling: "  + ranking.getBowlingRank());
    }

    /// internal method fields ko change krne ki koshish
    // public void switchTeam(String newTeam) {
    //    this.team = newTeam;
    // }
}

// mutable child class jo parent class ko mutable krne k try karega.
//class MutablePlayer extends Player {
//    public MutablePlayer(String name, int age, String team) {
//        super(name, age, team);
//    }
//}

class Ranking {
    int battingRank;
    int bowlingRank;

    public Ranking(int battingRank, int bowlingRank) {
        this.battingRank = battingRank;
        this.bowlingRank = bowlingRank;
    }

    public int getBattingRank() {
        return battingRank;
    }

    public void setBattingRank(int battingRank) {
        this.battingRank = battingRank;
    }

    public int getBowlingRank() {
        return bowlingRank;
    }

    public void setBowlingRank(int bowlingRank) {
        this.bowlingRank = bowlingRank;
    }
}

class ImmutableClass {
    void main() {
        Player p1 = new Player("Rohit Sharma", 38, "MI", new Ranking(1, 100));
        p1.print();

        Ranking rank = p1.getRanking();
        rank.setBattingRank(2);     // update nhi hoga (rank object original object nhi copy h)

        p1.print();
    }
}