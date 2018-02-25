/*                                                              Jonathan Webber
    Data Structures Homework 2, part 3. Create  a StudentClassManager that
utilizes generic objects. Use the ArrayList class for storing objects in your
StudentClassManager class.
    Answers to 1-7 on the homework sheet are answered below. These 1-7 after
for the part 3 assignment, however.
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class DS_02_Students2 {
    public static void main(String[] args) throws Exception {
        // 1) read in students info from .txt file and save into an ArrayList
        ArrayList<Student> Academic_Class = readIn();

        // 2) print out a table of the Students in the ArrayList
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade\tTotal Hr\tGPA");
        for(int i = 0; i<Academic_Class.size(); i++){
            System.out.printf("%s\t\t%s\t%d\t%d\t%d\t%d\t\t%s\t%d\t\t%.3f\n",
                    Academic_Class.get(i).getStuID(),
                    Academic_Class.get(i).getStuName(),
                    Academic_Class.get(i).getScore1(),
                    Academic_Class.get(i).getScore2(),
                    Academic_Class.get(i).getScore3(),
                    Academic_Class.get(i).getScoreAvg(),
                    Academic_Class.get(i).getLetterGrade(),
                    Academic_Class.get(i).getNoHours(),
                    Academic_Class.get(i).getNewGPA());
        }//close for loop
        System.out.println();

        // 3) delete two students with given IDs from the ArrayList
        DeleteStudent(Academic_Class, "42P4");
        DeleteStudent(Academic_Class, "45A3");

        // 4) print out a table of the Students in the ArrayList after deletion
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade\tTotal Hr\tGPA");
        for(int i = 0; i<Academic_Class.size(); i++){
            System.out.printf("%s\t\t%s\t%d\t%d\t%d\t%d\t\t%s\t%d\t\t%.3f\n",
                    Academic_Class.get(i).getStuID(),
                    Academic_Class.get(i).getStuName(),
                    Academic_Class.get(i).getScore1(),
                    Academic_Class.get(i).getScore2(),
                    Academic_Class.get(i).getScore3(),
                    Academic_Class.get(i).getScoreAvg(),
                    Academic_Class.get(i).getLetterGrade(),
                    Academic_Class.get(i).getNoHours(),
                    Academic_Class.get(i).getNewGPA());
        }//close for loop
        System.out.println();

        // 5) add students to the ArrayList
        AddStudent(Academic_Class, new Student("67T4", "Clouse, B", 80, 75, 98, 102, (float)3.65));
        AddStudent(Academic_Class, new Student("45P5", "Garrison, J", 75, 78, 72, 39, (float)1.85));
        AddStudent(Academic_Class, new Student("89P0", "Singer, A", 85, 89, 99, 130, (float)3.87));

        // 6) sort the array based on largest to smallest average score
        SortLarge(Academic_Class);

        // 7) print out the array after it has been sorted on average score
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade\tTotal Hr\tGPA");
        for(int i = 0; i<Academic_Class.size(); i++){
            System.out.printf("%s\t\t%s\t%d\t%d\t%d\t%d\t\t%s\t%d\t\t%.3f\n",
                    Academic_Class.get(i).getStuID(),
                    Academic_Class.get(i).getStuName(),
                    Academic_Class.get(i).getScore1(),
                    Academic_Class.get(i).getScore2(),
                    Academic_Class.get(i).getScore3(),
                    Academic_Class.get(i).getScoreAvg(),
                    Academic_Class.get(i).getLetterGrade(),
                    Academic_Class.get(i).getNoHours(),
                    Academic_Class.get(i).getNewGPA());
        }//close for loop

        //create an arraylist of doubles using StudentClassManager and fill with doubles
        StudentClassManager<Double> doubles = new StudentClassManager<>();
        doubles.setValue(2.3); doubles.setValue(1.5); doubles.setValue(15.6); doubles.setValue(0.9); doubles.setValue(11.2);
        doubles.setValue(8.9); doubles.setValue(12.4); doubles.setValue(51.2); doubles.setValue(-3.5);

        //sort the doubles
        doubles.sortManage();

        //print out the doubles in order from smallest to largest
        for(int i = 0; i<doubles.acount; i++){
            System.out.print(doubles.getValue(i)+" ");
        }
    }//closes main

    //creates a method to read in from a tab delimited .txt and returns an ArrayList
    private static ArrayList<Student> readIn(){
        //try to work with a file
        try{
            //will hold lineJustFetched after it has been split "\t"
            String[] wordsArray;
            //will hold finished Students in ArrayList
            ArrayList<Student> students = new ArrayList<>();

            //create a buffer reader to read each line
            BufferedReader buffer = new BufferedReader(new FileReader("Students.txt"));

            //will temporarily holds line being looked at
            String lineJustFetched = buffer.readLine();

            //while loop splitting line fed on "\t", storing into wordsArray, and
            //then putting into a new Student object and lastly the students ArrayList
            while(lineJustFetched != null){
                wordsArray = lineJustFetched.split("\t");

                //create new student based on wordsArray at certain indexes
                students.add(new Student(wordsArray[0], wordsArray[1],
                        (int) Double.parseDouble(wordsArray[2]),
                        (int) Double.parseDouble(wordsArray[3]),
                        (int) Double.parseDouble(wordsArray[4]),
                        (int) Double.parseDouble(wordsArray[5]),
                        Float.parseFloat(wordsArray[6])));

                //move to next line in .txt file
                lineJustFetched = buffer.readLine();
            }//close while loop

            //close the buffer
            buffer.close();

            //returns the ArrayList of Student Objects
            return students;
        }//close try
        //if something goes wrong with connecting to the file...
        catch(Exception e){
            System.out.println("Something has gone wrong accessing the file.");
            return null;
        }//close catch
    }//close readIn

    //adds a student given student object to a given arraylist
    private static void AddStudent(ArrayList<Student> Academic_Class, Student Obj){
        Academic_Class.add(Obj);
    }

    //deletes a student with given ID from the given arraylist
    private static void DeleteStudent(ArrayList<Student> Academic_Class, String StudentID){
        for(int i = 0; i<Academic_Class.size(); i++){
            if(Academic_Class.get(i).stuID.equals(StudentID)){
                Academic_Class.remove(i);
            }
        }
    }

    //sorts the arraylist by percentage (scoreAvg)
    private static void SortLarge(ArrayList<Student> Academic_Class){
        //creating temporary variables to store when arranging ArrayList
        Student xSave;
        Student ySave;
        int isw = 1;

        //while loop for sorting
        while(isw == 1){
            isw = 0;
            for(int i = 0; i<Academic_Class.size()-1; i++){
                switch(Academic_Class.get(i).compareTo(Academic_Class.get(i+1))){
                    case 1: //in correct order
                        break;
                    case -1: //objects out of order
                        xSave = Academic_Class.get(i);
                        ySave = Academic_Class.get(i+1);
                        Academic_Class.remove(i);
                        Academic_Class.add(i, ySave);
                        Academic_Class.remove(i+1);
                        Academic_Class.add(i+1, xSave);
                        isw = 1;
                        break;
                    default:
                }//close switch
            }//close for loop
        }//close while loop
    }//close SortLarge
}//end of class DS_02_Students (main)

//creation of Classroom
abstract class Classroom<T extends Comparable<T>> {
    public abstract int getScoreAvg();
    //no need for a constructor, so just a compareTo for score averages
    public int compareTo(Object o){
        if(getScoreAvg()>((Student)o).getScoreAvg()){
            return 1;
        }
        else if (getScoreAvg()<((Student)o).getScoreAvg()){
            return -1;
        }
        else{
            return 0;
        }
    }//close compareTo
}//close Classroom

//creation of class Student
class Student extends Classroom{
    protected String stuID;
    protected String stuName;
    protected String letterGrade;
    protected int score1;
    protected int score2;
    protected int score3;
    protected int scoreAvg;
    protected int noHours;
    protected String classification;
    protected float newGPA;
    //constructor for Student
    public Student (String ID, String Name, int s1, int s2, int s3, int hours, float gradePoint){
        this.stuID = ID;
        this.stuName = Name;
        this.score1 = s1;
        this.score2 = s2;
        this.score3 = s3;
        this.scoreAvg = Math.round((score1+score2+score3)/3);
        if (scoreAvg >= 90) {this.letterGrade = "A";}
        else if (scoreAvg >= 80 && scoreAvg <=89) {this.letterGrade = "B";}
        else if (scoreAvg >= 70 && scoreAvg <=79) {this.letterGrade = "C"; }
        else if (scoreAvg >= 60 && scoreAvg <=69) {this.letterGrade = "D";}
        else {this.letterGrade = "F";}
        this.noHours = hours;
        if (noHours>=0 && noHours<=30) {this.classification = "FR";}
        else if (noHours>=31 && noHours<=60) {this.classification = "SO";}
        else if (noHours>=61 && noHours<=90) {this.classification = "JR";}
        else if (noHours>=91) {this.classification = "SR";}
        int tempLetterInt;
        if(letterGrade.equals("A")){tempLetterInt = 4;}
        else if(letterGrade.equals("B")){tempLetterInt = 3;}
        else if(letterGrade.equals("C")){tempLetterInt = 2;}
        else if(letterGrade.equals("D")){tempLetterInt = 1;}
        else {tempLetterInt = 0;}
        this.newGPA = ((gradePoint*noHours)+tempLetterInt*2)/(noHours+2);
    }
    public String getStuID() {
        return stuID;
    }
    public String getStuName() {
        return stuName;
    }
    public String getLetterGrade() {
        return letterGrade;
    }
    public int getScore1() {
        return score1;
    }
    public int getScore2() {
        return score2;
    }
    public int getScore3() {
        return score3;
    }
    public int getNoHours() {
        return noHours;
    }
    public float getNewGPA() {
        return newGPA;
    }
    public int getScoreAvg() {
        return scoreAvg;
    }

}//close Students

//creation of class StudentClassManager
class StudentClassManager<T extends Comparable>{
    protected ArrayList<T> things = new ArrayList<>();
    protected int acount;

    //creates generic constructor
    public StudentClassManager(){
        acount = 0;
    }

    //place T x into array at current acount
    public int setValue(T x){
        things.add(acount,x);
        return ++acount;
    }

    //gets T from the array at the i index
    public T getValue(int i){
        if(i<=acount){return things.get(i);}
        else return things.get(0);
    }
    //get the size of the array (.size() basically)
    public int getAcount(){
        return acount;
    }

    //loop for sorting smallest to largest
    public void sortManage(){
        //creating temporary variables to store when arranging ArrayList
        T xSave, ySave, a, b ;
        int isw = 1;

        //while loop for sorting small to large
        while(isw == 1){
            isw = 0;
            for(int i = 0; i<things.size()-1; i++){
                switch(things.get(i).compareTo(things.get(i+1))){
                    case -1: //in correct order
                        break;
                    case 1: //objects out of order
                        xSave = things.get(i);
                        ySave = things.get(i+1);
                        things.remove(i);
                        things.add(i, ySave);
                        things.remove(i+1);
                        things.add(i+1, xSave);
                        isw = 1;
                        break;
                    default:
                }//close switch
            }//close for loop
        }//close while loop
    }
}