/*                                                              Jonathan Webber
    Data Structures Homework 2, part 4. Rewrite part II of problem 2
using linked lists; store the student records of part III as linked
list (instead of array), store student records in descending order
(according to class % score). Use the given data in part II
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class DS_02_Students3 {
    public static void main(String[] args) throws Exception {
        // 1) read in students info from .txt file and save into an Linked List
        LinkedListManager Academic_Class = readIn();

        // 2) print out a table of the Students in the Linked List
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade\tTotal Hr\tGPA");
        for(int i = 1; i<=Academic_Class.getNodeCount(); i++){
            System.out.printf("%s\t\t%s\t\t%d\t\t%d\t\t%d\t\t%d\t\t\t%s\t\t%d\t\t\t%.3f\n",
                    ((Student)(Academic_Class.getNode(i))).getStuID(),
                    ((Student)(Academic_Class.getNode(i))).getStuName(),
                    ((Student)(Academic_Class.getNode(i))).getScore1(),
                    ((Student)(Academic_Class.getNode(i))).getScore2(),
                    ((Student)(Academic_Class.getNode(i))).getScore3(),
                    ((Student)(Academic_Class.getNode(i))).getScoreAvg(),
                    ((Student)(Academic_Class.getNode(i))).getLetterGrade(),
                    ((Student)(Academic_Class.getNode(i))).getNoHours(),
                    ((Student)(Academic_Class.getNode(i))).getNewGPA());
        }//close for loop
        System.out.println();

        // 3) delete two students with given IDs from the Linked List

        Academic_Class.deleteNode(Academic_Class.findNode("42P4"));
        Academic_Class.deleteNode(Academic_Class.findNode("45A3"));

        // 4) print out a table of the Students in the Linked List after deletion
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade\tTotal Hr\tGPA");
        for(int i = 1; i<=Academic_Class.getNodeCount(); i++){
            System.out.printf("%s\t\t%s\t\t%d\t\t%d\t\t%d\t\t%d\t\t\t%s\t\t%d\t\t\t%.3f\n",
                    ((Student)(Academic_Class.getNode(i))).getStuID(),
                    ((Student)(Academic_Class.getNode(i))).getStuName(),
                    ((Student)(Academic_Class.getNode(i))).getScore1(),
                    ((Student)(Academic_Class.getNode(i))).getScore2(),
                    ((Student)(Academic_Class.getNode(i))).getScore3(),
                    ((Student)(Academic_Class.getNode(i))).getScoreAvg(),
                    ((Student)(Academic_Class.getNode(i))).getLetterGrade(),
                    ((Student)(Academic_Class.getNode(i))).getNoHours(),
                    ((Student)(Academic_Class.getNode(i))).getNewGPA());
        }//close for loop
        System.out.println();

        // 5) add students to the Linked List
        Academic_Class.addNode(new Student("67T4", "Clouse, B", 80, 75, 98, 102, (float)3.65));
        Academic_Class.addNode(new Student("45P5", "Garrison, J", 75, 78, 72, 39, (float)1.85));
        Academic_Class.addNode(new Student("89P0", "Singer, A", 85, 89, 99, 130, (float)3.87));

        // 6) sort the array based on largest to smallest average score (class%)
        //creates a new linked list to put the nodes in order into the new one
        LinkedListManager orderedAcademic_Class = new LinkedListManager<>();
        for(int i = 1; i<=Academic_Class.getNodeCount(); i++){
            orderedAcademic_Class.addInOrder(Academic_Class.getNode(i));
        }

        // 7) print out the array after it has been sorted on average score
        System.out.println("Student ID\tStudent Name\tTest 1\tTest2\tTest3\tAvg Score\tGrade\tTotal Hr\tGPA");
        for(int i = 1; i<=Academic_Class.getNodeCount(); i++){
            System.out.printf("%s\t\t%s\t\t%d\t\t%d\t\t%d\t\t%d\t\t\t%s\t\t%d\t\t\t%.3f\n",
                    ((Student)(orderedAcademic_Class.getNode(i))).getStuID(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getStuName(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getScore1(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getScore2(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getScore3(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getScoreAvg(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getLetterGrade(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getNoHours(),
                    ((Student)(orderedAcademic_Class.getNode(i))).getNewGPA());
        }//close for loop
    }//closes main

    //creates a method to read in from a tab delimited .txt and returns an ArrayList
    private static LinkedListManager readIn(){
        //try to work with a file
        try{
            //will hold lineJustFetched after it has been split "\t"
            String[] wordsArray;

            //will hold finished Students in a Linked list
            LinkedListManager students = new LinkedListManager();

            //create a buffer reader to read each line
            BufferedReader buffer = new BufferedReader(new FileReader("Students.txt"));

            //will temporarily holds line being looked at
            String lineJustFetched = buffer.readLine();

            //while loop splitting line fed on "\t", storing into wordsArray, and
            //then putting into a new Student object and lastly the students1 linked list
            while(lineJustFetched != null){
                wordsArray = lineJustFetched.split("\t");

                students.addNode(new Student(
                        wordsArray[0], wordsArray[1],
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

            //returns the Linked List of Student Objects
            return students;
        }//close try
        //if something goes wrong with connecting to the file...
        catch(Exception e){
            System.out.println("Something has gone wrong accessing the file.");
            return null;
        }//close catch
    }//close readIn
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
class Student extends Classroom implements Comparable {
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
}//close Student

//creation of linkedListManager
class LinkedListManager<T extends Comparable>{
    private static class myNode<T>{
        protected T nodeData;
        protected myNode<T> next;

        //my node constructor
        public myNode(T x){
            nodeData = x;
            next = null; //creates pointer to next node (null)
        }//end of constructor

        public T getData(){
            return nodeData;
        }
    }//end of myNode

    protected myNode<T> firstNode;
    protected myNode<T> lastNode;
    protected int nodeCount;

    //creation of constructor
    public LinkedListManager(){
        firstNode = null;
        lastNode = null;
        nodeCount = 0;
    }//end of constructor

    //add a node to the list
    public int addNode(T x){
        addNodeFront(x);
        return nodeCount;
    }

    //add a node to the front of the linked list
    public void addNodeFront(T x){
        myNode<T> newNode = new myNode(x);

        //linked list is empty
        if(firstNode==null){
            firstNode = newNode;
            lastNode = newNode;
            nodeCount++;
            return;
        }
        //linked list has something in it
        else{
            newNode.next = firstNode;
            firstNode = newNode;
            nodeCount++;
            return;
        }

    }//end of addNode method

    //adds a new node to the list in order with compareTo
    public void addInOrder(T x){
        myNode<T> newNode = new myNode(x);
        myNode<T> currentNode;
        myNode<T> nextNode;


        //linked list is empty
        if(nodeCount == 0){
            firstNode = newNode;
            lastNode = newNode;
            nodeCount++;
            return;
        }

        //if data is in the linked list, check x against the first data point
        if (x.compareTo(firstNode.nodeData) == 1) {
            newNode.next = firstNode;
            firstNode = newNode;
            nodeCount++;
            return;
        }

        //or check against the last data point
        if (x.compareTo(lastNode.nodeData) == -1) {

            lastNode.next = newNode;
            lastNode = newNode;
            nodeCount++;
            return;
        }

        //all other cases of x being inbetween first and last data point
        currentNode = firstNode;
        nextNode = firstNode.next;
        while(x.compareTo(nextNode.nodeData) != 1){
            currentNode = nextNode;
            nextNode = nextNode.next;
        }
        //x is now greater compared to the next node bu less than the current, so...
        currentNode.next = newNode;
        newNode.next = nextNode;
        nodeCount++;
        return;
    }//end of addInOrder

    //creation of deleteNode method for student ID
    public myNode<T> deleteNode(int position) {
        myNode<T> start = firstNode;

        //if entered number is negative...
        if (position < 0) {
            System.out.println("Could not delete node in that position.");
            return firstNode;
        }

        //if the linked list is empty...
        if (firstNode == null) {
            System.out.println("List is currently empty.");
            return firstNode;
        }
        //if at the front of the linked list, make new node the front
        if(position == 0){
            myNode<T> temp = firstNode.next;
            firstNode = firstNode.next;
            temp.next = null;
            return temp;
        }
        else {
            //temporarily store firstNode and create count
            myNode<T> previous = firstNode;
            int count = 1;

            while(count<position-1){
                previous = previous.next;
                count++;
            }

            myNode current = previous.next;
            previous.next = current.next;
            current.next = null;
            nodeCount--;
            return current;
        }//end of else
    }//end of deleteNode method

    //return place in  after finding the specified spot
    public int findNode(String stuID){
        myNode current = firstNode;
        int count = 1;

        //if there is data in the linked list...
        while(current != null) {

            //cast first node to student
            if (((Student) (current.getData())).getStuID().equals(stuID)){
                return count;
            }
            else{
                current = current.next;
                count++;
            }
        }//end while loop

        //if no data is in the linked list
        System.out.println("Could not find data in list");
        return -1;
    }//end findNode method

    //generic get node count
    public int getNodeCount(){
        return nodeCount;
    }

    //returns node at given position
    public T getNode(int position){
        if(position<0 || position>nodeCount){
            System.out.println("Error in getNode("+position+") while list holds "+nodeCount);
        }

        int itCount = 1; //iterator count
        myNode<T> curNode;
        curNode= firstNode; //create a node and link it to first node

        //go through list until we hit the node number we want
        while(itCount<position){
            curNode = curNode.next;
            itCount++;
        }
        return curNode.nodeData;
    }//end of getNode method
}//end of linkListManager class