package ohtu;

import java.util.ArrayList;

public class Course {

    private int[] exercises;
    private String name;
    private String fullName;
    private ArrayList<Submission> subs;

    public void addSubmission(Submission sub) {
        subs.add(sub);
    }

    public void initSubs() {
        this.subs = new ArrayList<>();
    }
    
    public int[] getExersises() {
        return exercises;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public void setExersises(int[] exersises) {
        this.exercises = exersises;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int countTotalExercises() {
        int i = 0;
        for (int exercise : exercises) {
            i += exercise;
        }
        return i;
    }

    @Override
    public String toString() {
        if (subs.isEmpty()) {
            return "";
        }
        int ttlHours = 0;
        int ttlExcDone = 0;
        String s = fullName + "\n\n";
        for (Submission sub : subs) {
            ttlExcDone += sub.getExercises().length;
            ttlHours += sub.getHours();
            s += "viikko " + sub.getWeek() + ":\n ";
            s += "tehtyjä tehtäviä " + sub.getExercises().length + "/" + exercises[sub.getWeek()] + " aikaa kului " + sub.getHours() + " tehdyt tehtävät: " + sub.exercisesDone() + "\n";
        }
        s += "\nyhteensä: " + ttlExcDone + "/" + countTotalExercises() + " tehtävää " + ttlHours + " tuntia";
        return s;
    }
}
