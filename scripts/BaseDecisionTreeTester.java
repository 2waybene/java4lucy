/**
 * Basic code for assignment 2 - F2020
 * @author Peiyu Lu
 * id:150665940
 */

//package luxx5940_a2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BaseDecisionTreeTester {

    /*
     *  Build a decision tree
     *  feature 0 is gender (0: female 1:male)
     *  feature 1 is age
     *  feature 2 is sibling count 
     *  class 0 is not survived
     *  class 1 is survived
     *  read the train file
     */
	
	public static void main(String[] args ) {
    BaseDecisionTree.nClasses = 2;
    BaseDecisionTree.dim = 3;
    
    /*
     * is man?
     */
    BaseDecisionTree s1 = new BaseDecisionTree(0, 0.99);
    //System.out.println(s1);
    BaseDecisionTree s2 = new BaseDecisionTree(); // leaf
    s2.setProb(1, 85); //  survived at 36% probability


    /*
     *  is age < 9.5
     */
    BaseDecisionTree s3 = new BaseDecisionTree(1, 9.9);//
    BaseDecisionTree s4 = new BaseDecisionTree(); // leaf
    s4.setProb(0, 61); //  not survived at  61%
    /*
     * is Siblings>2.5
     */
    BaseDecisionTree s5 = new BaseDecisionTree(2,2.9); // leaf
    BaseDecisionTree s6 = new BaseDecisionTree(); // leaf
    //s6.setProb(0, 15); // not survived at 2%
    BaseDecisionTree s7 = new BaseDecisionTree(); // leaf
    //s7.setProb(1, 25); // survived at 20%


    s1.setSmallerBranch(s2);
    s1.setGreaterBranch(s3);
    s3.setSmallerBranch(s4);
    s3.setGreaterBranch(s5);
    s5.setSmallerBranch(s6);
    s5.setGreaterBranch(s7);
    
    /*
     * this part is just for test your code. we simulated the tree and you can see 
     * the right answers if you want o check them with yours. "1" means survived and "0"
     * means not survived.
     */
//    s1.CalcProb("input.txt");
//    double[] test1 = {1,2,4};
//    System.out.println("Man, 2 years old with 4 siblings = " + s1.getDecision(test1));//answer is:0
//    double[] test2 = {1,10,5};
//    System.out.println("Man 10 years old with 5 siblings = " + s1.getDecision(test2));//answer is:1
//    double[] test3 = {0,2,0};
//    System.out.println("Woman 2 years old = " + s1.getDecision(test3));//answer is 1
//    double[] test4 = {1,20,0};
//    System.out.println("Woman 40 years old = " + s1.getDecision(test4));//answer is:1
//    double[] test5 = {1,34,1};
//    System.out.println("Man, 10 years old with 1 siblings = " + s1.getDecision(test5));//answer is:0

    String line;
    String[] lineArr;
    int tempClass;
    int tempFeature;
    BufferedReader reader;
    try {
        reader = new BufferedReader(new FileReader("input.txt"));
        while((line = reader.readLine()) != null) {
        	s1.CalcProb("input.txt");
        	
        }
    }catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println(s1.CalcProb("input.txt")[0]);
//
    int[] classFreq = s3.CalcProb("gender");
    System.out.println("survived: " + classFreq[1]);
	System.out.println("not survived: " + classFreq[0] );
	
    int[] classFreq1 = s3.CalcProb("age");
    System.out.println("survived: " + classFreq1[1]);
	System.out.println("not survived: " + classFreq1[0]);
	
    int[] classFreq2 = s3.CalcProb("sibling");
    System.out.println("survived: " + classFreq2[1]);
	System.out.println("not survived: " + classFreq2[0]);
    
    int[] result = s1.CalcAccuracy("output.txt");
    System.out.println(result[0]);
    
    int[] result1 = s6.CalcAccuracy("output.txt");
    System.out.println(result1[0]);
    
    int[] result2 = s7.CalcAccuracy("output.txt");
    System.out.println(result2[0]);
//
//    s1.print();
    
//    int[] classFreq4 = s1.CalcAccuracy("gender");
//    System.out.println("survived: " + classFreq4[1]);
//    int[] classFreq5 = s1.CalcAccuracy("age");
//    System.out.println("survived: " + classFreq5[1]);
//    int[] classFreq6 = s1.CalcAccuracy("sibling");
//    System.out.println("survived: " + classFreq6[1]);
    /* Please comment out this part after you calculate the probabilities from 
     * input data. These inputs are based on output data and the answer for them is
     * in front of each one. you can test your decision tree with these.*/

    ///*
    double[] test1 = {0,39,1};
    System.out.println("Man, 2 years old with 4 siblings = " + s1.getDecision(test1));//answer is 1
    double[] test2 = {1,24,2};
    System.out.println("Man 10 years old with 5 siblings = " + s1.getDecision(test2));//answer is 0
    double[] test3 = {0,2,4};
    System.out.println("Woman 2 years old = " + s1.getDecision(test3));//answer is 0
    double[] test4 = {0, 40,4};
    System.out.println("Woman 40 years old = " + s1.getDecision(test4));//answer is 0
    double[] test5 = {1, 10, 1};
    System.out.println("Man, 10 years old with 1 siblings = " + s1.getDecision(test5));//answer is 0
     //*/
	}
}
