import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;



//package assignment2;

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

        this.featureIndex = -1; //has no more children 
        this.threshold = 0.0;

        classFreq = new int[nClasses]; //class:survise or not. frequency: percentage
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
    	//if Prob1>Prob2:return 1,else return 0
    	//vector = {dim1,dim2,dim3} gender age slibing
    	//vector[dim];
    	/*
    	 * recursion
    	 * base case: 
    	 */
    	if (isExternal()) {
    		return getMaxClass();
    	}
    	else {
    		if (vector[featureIndex]>threshold) {
    			return greaterBranch.getDecision(vector);
    		}
    		else {
    			return smallerBranch.getDecision(vector);
    		}
    	}
    	//vector[featureIndex]
    	
    	
       	  //return 0;
    }
    
    /**
     *  return the class having highest probability
     */
    public int getMaxClass() {
    //  write your code here.
    	if (isExternal()) {  //if it is the leaf
    		if (classFreq[0]>classFreq[1]) {
    			return 0;
    		}
    		else {
    			return 1;
    		}
    	}
    	else {	//if it is node, then compare children node or leaf
    		if (smallerBranch.getMaxProb()>greaterBranch.getMaxProb()) {
    			return 0;//smaller branch has bigger prob
    		}
    		else {
    			return 1;
    		}
    	}
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
    	//if it is the leaf -----isExternal()==-1
    	//total
    	//
    	double result = 0;
    	if (isExternal()) {
    		result = Math.max(classFreq[0]/total,classFreq[1]/total);
    	}
    	else {
    		result = Math.max(smallerBranch.getMaxProb(), greaterBranch.getMaxProb());
    	}
   
  	  return result;
    }
    
    /**
     * manually set the probability of class c (in %)
     * to use instead of updateProbCount
     */
    
   public void setProb(int c, int percentage) { //c=0:not survive, c=1:survive

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
	   String input_line;
	   String[] lineArr;
	   double total_female = 0;
	   double female_survive = 0;
	   double total_age = 0;
	   double age_survive=0;
	   double total_sibling = 0;
	   double sibling_survive = 0;
	   BufferedReader reader;

	   try {
		reader = new BufferedReader(new FileReader("input.txt"));
		input_line=reader.readLine();//get next line of input.txt
		while ((input_line=reader.readLine())!=null) {
			lineArr = input_line.split(",");
			//temp_class = Integer.parseInt(lineArr[input_number]);//lineArr[dim];//which attribute(gender,age,sibling) whole coloum value
			total++;

			if (inputFeatureWLabel.equals("gender")) {		
				if (Integer.parseInt(lineArr[0])==0) {
					total_female++;
					if (Integer.parseInt(lineArr[3])==1){
						female_survive++;
					}
	            }
				double value = Double.valueOf(female_survive)/Double.valueOf(total_female);
				classFreq[1]= (int)Math.round(100*value);
				classFreq[0]= 100-classFreq[1];
			}
			if (inputFeatureWLabel =="age") {
				if (Integer.parseInt(lineArr[1])<9.9 && Integer.parseInt(lineArr[0])==1) {
//					age_survive++;
					total_age++;
					if (Integer.parseInt(lineArr[3])==1) {
						age_survive++;
					}
					
				}

				double age_value = Double.valueOf(age_survive)/Double.valueOf(total_age);
				classFreq[1]= (int)Math.round(100*age_value);
				classFreq[0]= 100-classFreq[1];
			}
			if (inputFeatureWLabel =="sibling") {
				if (Integer.parseInt(lineArr[2])>2.9 && Integer.parseInt(lineArr[1])<9.9 && Integer.parseInt(lineArr[0])==1) {
					total_sibling++;
					if (Integer.parseInt(lineArr[3])==1) {
						sibling_survive++;
					}
					
				}
				double sibling_value = Double.valueOf(sibling_survive)/Double.valueOf(total_sibling);
				classFreq[1]= (int)Math.round(100*sibling_value);
				classFreq[0]= 100-classFreq[1];

			}
		
		}
		

	   } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return classFreq;
	   
	   
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
    	greaterBranch = ds;
    	ds.parent = this;
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
 	   String input_line;
 	   String[] lineArr;
 	   int line_count=0;
 	   double[] tempFeatureArr = new double[dim];
 	   int classCorrect = 0;
 	   //BaseDecisionTree current = null;//current node, usage: current.getDecision(tempFeatureArr)
 	   BufferedReader reader;
 	   
 	   try {
 		   	reader = new BufferedReader(new FileReader(inputFeatureWLabel));
 			input_line=reader.readLine();//get next line of input.txt
 			while ((input_line=reader.readLine())!=null) {
 				lineArr = input_line.split(",");
 				line_count++;//50
 				for (int i =0;i<dim;i++) {
 					tempFeatureArr[i] = Integer.parseInt(lineArr[i]); //save vector value
 				}
 				if (getDecision(tempFeatureArr) == Integer.parseInt(lineArr[dim])){
 					classCorrect++;
 				}
 			}
 	   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 	   }
 	   
 	   
// 	   try {
// 		  //reader = new BufferedReader(new FileReader("output.txt"));
// 		   	reader = new BufferedReader(new FileReader(inputFeatureWLabel));
// 			input_line=reader.readLine();//get next line of input.txt
// 			while ((input_line=reader.readLine())!=null) {
// 				lineArr = input_line.split(",");
// 				//line_count++;//50
// 				for (int i =0;i<dim;i++) {
// 					tempFeatureArr[i] = Integer.parseInt(lineArr[i]); //save vector value
// 				}
// 				
// 				
// 				
// 			}
// 	   }catch (IOException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}
		//return classFreq;
 	   //int[] result = new int[4];
 	   int[] result = new int[] {classCorrect*100/line_count};
 	   return result;
 	   

    }







 /**
     *  In this function you should 
     *  be able to print all the nodes of decision tree in a pre-Order Traversal.
     */
    
    public void print() {

       // write your code here. 
    	//print(parent);
    	System.out.println(toString());//print node
        if(!isExternal()) {	//recursion
            smallerBranch.print();
            greaterBranch.print();
        }   	 	
  	
     }
    
    public void print(BaseDecisionTree parent) {
    	if (parent == null) {
    		return;
    	}
    	Stack<BaseDecisionTree> stack = new Stack<BaseDecisionTree>();
    	stack.push(parent);
    	
    	while(!stack.empty()) {
    		BaseDecisionTree n =stack.pop();
    		System.out.printf("%d",n);
    		
    		if (n.getGreaterBranch() != null) {
    			stack.push(n.getGreaterBranch());
    			print(n.getGreaterBranch());
    		}
    		if (n.getSmallerBranch() != null) {
    			stack.push(n.getSmallerBranch());
    			print(n.getSmallerBranch());
    		}
    		
    	}
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
