package studentgradesystem.models;

public class Student {
    private String name;
    private int id;
    private double[] grades;


    public Student(String name, int id, double[] grades) {
        this.name = name;
        this.id = id;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }
     
    public int getId() {
        return id;
    }

    public double[] getGrades() {
        return grades;
    }
    
}


