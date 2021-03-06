/*                                                              Jonathan Webber
    Data Structures Homework 2, part 1. Develop a program which will allow you 
to read the student records from an input .txt file. As you read watch student 
record, create an object for that record. Have your object calculate the 
percentage test score (you may assume that each test was 100 points). Have your
object store the %score and letter grade for each student (use school standards
to assign the letter grades and store the %score as an integer).
   Also create an ArrayList for this academic class. As you read each student 
record, create their object and store their object and store each student into
the ArrayList for this academic class.

    Answers to 1-7 on the homework sheet are answered below.
*/

package ds_02_students;

import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.*;

public class DS_02_Students {
    public static void main(String[] args) throws Exception {
        // 1) read in students info from .txt file and save into an ArrayList
        ArrayList<Student> Academic_Class = readIn();
        
        // 2) print out a table of the Students in the ArrayList
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade");
        for(int i = 0; i<Academic_Class.size(); i++){
            System.out.println(Academic_Class.get(i).getStuID()+"\t\t"+
                    Academic_Class.get(i).getStuName()+"\t"+
                    Academic_Class.get(i).getScore1()+"\t"+
                    Academic_Class.get(i).getScore2()+"\t"+
                    Academic_Class.get(i).getScore3()+"\t"+
                    Academic_Class.get(i).getScoreAvg()+"\t\t"+
                    Academic_Class.get(i).getLetterGrade());
        }//close for loop
        System.out.println();
                
        // 3) delete two students with given IDs from the ArrayList
        DeleteStudent(Academic_Class, "42P4");
        DeleteStudent(Academic_Class, "45A3");
        
        // 4) print out a table of the Students in the ArrayList after deletion
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade");
        for(int i = 0; i<Academic_Class.size(); i++){
            System.out.println(Academic_Class.get(i).getStuID()+"\t\t"+
                    Academic_Class.get(i).getStuName()+"\t"+
                    Academic_Class.get(i).getScore1()+"\t"+
                    Academic_Class.get(i).getScore2()+"\t"+
                    Academic_Class.get(i).getScore3()+"\t"+
                    Academic_Class.get(i).getScoreAvg()+"\t\t"+
                    Academic_Class.get(i).getLetterGrade());
        }//close for loop
        System.out.println();
        
        // 5) add students to the ArrayList
        AddStudent(Academic_Class, new Student("67T4", "Clouse, B", 80, 75, 98));
        AddStudent(Academic_Class, new Student("45P5", "Garrison, J", 75, 78, 72));
        AddStudent(Academic_Class, new Student("89P0", "Singer, A", 85, 89, 99));
        
        // 6) sort the array based on largest to smallest average score
        SortLarge(Academic_Class);
        
        // 7) print out the array after it has been sorted on average score
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade");
        for(int i = 0; i<Academic_Class.size(); i++){
            System.out.println(Academic_Class.get(i).getStuID()+"\t\t"+
                    Academic_Class.get(i).getStuName()+"\t"+
                    Academic_Class.get(i).getScore1()+"\t"+
                    Academic_Class.get(i).getScore2()+"\t"+
                    Academic_Class.get(i).getScore3()+"\t"+
                    Academic_Class.get(i).getScoreAvg()+"\t\t"+
                    Academic_Class.get(i).getLetterGrade());
        }//close for loop       
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
                        (int) Double.parseDouble(wordsArray[4])));
                
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
        
        //
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
abstract class Classroom implements Comparable {
    public abstract int getScoreAvg();
    //no need for a constructor, so just a compareTo for score averages
    public int compareTo(Object o) {
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
    //constructor for Student
    public Student (String ID, String Name, int s1, int s2, int s3){
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
    @Override
    public int getScoreAvg() {
        return scoreAvg;
    }
}//close Students
