package ohtu;

public class Submission {

    private int week;
    private int[] exercises;
    private int hours;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public String getCourse() {
        return course;
    }

    public int[] getExercises() {
        return exercises;
    }

    public int getHours() {
        return hours;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    private String exercisesDone() {
        String s = "";
        for (int i = 0; i < exercises.length - 1; i++) {
            s = s + exercises[i] + ", ";
        }
        if (exercises.length > 0) {
            s = s + exercises[exercises.length - 1];
        }
        return s;
    }

    @Override
    public String toString() {
        return course + ", viikko " + week + " tehtyjä tehtäviä yhteensä " + exercises.length + " aikaa kului " + hours + " tehdyt tehtävät: " + exercisesDone();
    }

}
