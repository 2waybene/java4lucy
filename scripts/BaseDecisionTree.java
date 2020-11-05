/**
  * Basic code for assignment 2 - F2020
  * @author Sadeghi F, Safaa Bedawi
*/

package assignment2;

public class BaseDecisionTree {
	private int featureIndex; // feature index on which
    private double threshold; // threshold is applied

    public static int dim; // input feature vector dimension
    public static int nClasses; // number of classes

    private int[] classFreq; // number of time instance of this class has reached this node
    private int total; // number of trained samples having reached this node

    private BaseDecisionTree smallerBranch; // if feature value < threshold
    private BaseDecisionTree greaterBranch; // if feature value >= threshold
    private BaseDecisionTree parent; // a reference to the parent node

    /**
     *  constructor for a leaf
     */
    public BaseDecisionTree() {

        this.featureIndex = -1;
        this.threshold = 0.0;

        classFreq = new int[nClasses];
    }

    /**
     *  constructor for a node
     */
    public BaseDecisionTree(int featureIndex, double threshold) {

        this.featureIndex = featureIndex;
        this.threshold = threshold;

        classFreq = new int[nClasses];
    }

    
    /**
     * get the class associated with this decision tree
     */
 
    public int getDecision(double[] vector) {
      // write your code here.
       	  return 0;
    }
    
    /**
     *  return the class having highest probability
     */
    public int getMaxClass() {
    //  write your code here.
        return 0;
    }

    /**
    *returns the number of samples that hit this node
    */
    
    public int getTotal() {
        return total;
    }

    /**
    *return the highest probability
    */ 
    public double getMaxProb() {
   
   //    write your code here.
   
  	  return 0;
    }
    
    /**
     * manually set the probability of class c (in %)
     * to use instead of updateProbCount
     */
    
   public void setProb(int c, int percentage) {

        classFreq[c] = percentage;
        total = 100;
    }

   /**
   In this method you should calculate the probability for different input features 
   *(gender, age and siblings) and use them to build your tree. This function takes an 
   *input file “input.txt”. The file is provided in the zip folder.
   *For each feature (column) calculate the probability of survived/non survived(last column). 
   *For example for feature 1 (male/female) we have female with 27% survived, count
   how many samples of female that has survived/total number of females.
   */
 
   public int[] CalcProb(String inputFeatureWLabel) {  	
   /*
    *       write your code here.
   */
	int[] myList = {1, 2, 3, 4};
	return myList;   
    }


    /*
     * adds a new branch on the left
     */
    public void setSmallerBranch(BaseDecisionTree ds) {

        smallerBranch = ds;
        ds.parent = this;
    }

    /*
     *  adds a new branch on the right
     */
    public void setGreaterBranch(BaseDecisionTree ds) {   	
    /*
     *       write your code here.
     */
    }

    /*
     *  returns the parent of this node
     */
    public BaseDecisionTree getParent() {

        return parent;
    }

    /*
     *  returns the left child of this node
     */
    public BaseDecisionTree getSmallerBranch() {

        return smallerBranch;
    }

    /*
     * returns the right child of this node
     */
    public BaseDecisionTree getGreaterBranch() {

        return greaterBranch;
    }

    /*
     * checks if it is a leaf
     */
    public boolean isExternal() {

        return featureIndex == -1;
    }
    
    /**
     * 1.Calculate the accuracy of your results.
     * After building your tree and testing with sample data in the tester class.
     *You should try it with real test data (output.txt) and compare your results with actual test results (last column in the file).
     *The accuracy will be your right decisions divided by the number of samples in output data.
     */
  
    public int[] CalcAccuracy(String inputFeatureWLabel) {

    //   write your code here.
	int[] myList = {1, 2, 3, 4}; 
	return myList;
    }







 /**
     *  In this function you should 
     *  be able to print all the nodes of decision tree in a pre-Order Traversal.
     */
    
    public void print() {

       // write your code here.       
 
    }
    
    /**
     *  toString
     */
    public String toString() {

        StringBuilder out = new StringBuilder("(" + featureIndex + " of " + dim + " ," + threshold + " , [" + classFreq[0]);
        for (int i = 1; i < nClasses; i++) {
            out.append(",").append(classFreq[i]);
        }
        out.append("] of ").append(total).append(")");

        return out.toString();
    }
    
    
   
    
    


}
