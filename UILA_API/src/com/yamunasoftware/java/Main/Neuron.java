package com.yamunasoftware.java.Main;

import com.yamunasoftware.java.Tools.*;

@SuppressWarnings("unused")
public class Neuron extends Trainer { 
  //API USER: Use to run any of the above methods:
  public void classify(double xData, double yData) throws Exception {}
	
	//Constructor: Gets Methods from Super class:
  public Neuron() {
	  super();  
  }
  
  //Allows User to Classify Just One Class:
  public static boolean getClass(double dataX, double dataY, String identifier) throws Exception {
  	//Boolean Value:
  	boolean classification = false; //Default Value
  	
    //Box Variables:
  	//Top Left:
  	double[] TL = new double[2];
  	//Bottom Left:
  	double[] BL = new double [2];
  	//Top Right:
    double[] TR = new double[2];
  	//Bottom Right
  	double[] BR = new double[2]; 
  	
  	TL[0] = HandleData.openData(identifier + "tlX.txt"); //TL X
		TL[1] = HandleData.openData(identifier + "tlY.txt"); //TL Y
		
		BL[0] = HandleData.openData(identifier + "blX.txt"); //BL X
		BL[1] = HandleData.openData(identifier + "blY.txt"); //BL Y
		
		TR[0] = HandleData.openData(identifier + "trX.txt"); //TR X
		TR[1] = HandleData.openData(identifier + "trY.txt"); //TR Y
		
		BR[0] = HandleData.openData(identifier + "brX.txt"); //BR X
		BR[1] = HandleData.openData(identifier + "brY.txt"); //BR Y	
		
		if (dataX >= BL[0] && dataX <= TR[0] && dataY >= BL[1] && dataY <= TR[1]) {
		  classification = true;	
		}
		
		else {
		  classification = false;	
		}
		
		//Returns Value:
		return classification;
  }
  
  //Allows User to Classify the Test Data:
  public static double classification(double dataX, double dataY, String identifiers[]) throws Exception {
  	//Classification Indexes:
	  boolean classificationIndex[] = new boolean[identifiers.length]; 
	  int startTurns = identifiers.length, turns = 0;
	  Double timesClassifiedIndex[] = {}; //Default Value
	  int timesClassified = 0; //Default Value
	  
	  //Confidences:
	  Double confidences[] = {};
	  Double boxConfidences[] = {};
	  
	  //Final Classification:
	  double finalClassIndex = 0.0, finalClassValue = 0.0; 
	
	  //Box Variables:
	  //Top Left:
	  double[] TL = new double[2];
	  //Bottom Left:
	  double[] BL = new double [2];
	  //Top Right:
    double[] TR = new double[2];
	  //Bottom Right
	  double[] BR = new double[2]; 
	
	  //Loops through and classifies:
	  mainLoop: while (turns < startTurns) {
		  TL[0] = HandleData.openData(identifiers[turns] + "tlX.txt"); //TL X
		  TL[1] = HandleData.openData(identifiers[turns] + "tlY.txt"); //TL Y
		
		  BL[0] = HandleData.openData(identifiers[turns] + "blX.txt"); //BL X
		  BL[1] = HandleData.openData(identifiers[turns] + "blY.txt"); //BL Y
		
		  TR[0] = HandleData.openData(identifiers[turns] + "trX.txt"); //TR X
		  TR[1] = HandleData.openData(identifiers[turns] + "trY.txt"); //TR Y
		
		  BR[0] = HandleData.openData(identifiers[turns] + "brX.txt"); //BR X
		  BR[1] = HandleData.openData(identifiers[turns] + "brY.txt"); //BR Y	
		
		  //Gets the Class:
		  classificationIndex[turns] = getClass(dataX, dataY, identifiers[turns]);
		
		  turns++;
	  }
	
	  //Resets Turn Value:
	  turns = 0;
	
	  //Checks for Multiple Classifications:
	  secondLoop: while (turns < startTurns) {
      if (classificationIndex[turns]) {  
    	  timesClassifiedIndex = populate(timesClassifiedIndex, turns); 
    	  timesClassified++;
      }		
		
	    turns++;	
	  }
	
	  //Checks for times Classified:
	  if (timesClassified != 1) {
	    //Resets turn values:
	    turns = 0;
	    startTurns = timesClassifiedIndex.length;
		
	    //Calculates Confidences:
	    calculateLoop: while (turns < startTurns) {
		    double indexOf = timesClassifiedIndex[turns];
		    int stringIndex = (int)(indexOf);
		  
		    //Gets the Distances:
		    double indexConfidence = getDistanceToMidpoint(dataX, dataY, identifiers[stringIndex]);
		    double indexBoxConfidence = getDistanceToBox(dataX, dataY, identifiers[stringIndex]);
		    
		    //Populates the Arrays:
		    confidences = populate(confidences, indexConfidence);
		    boxConfidences = populate(boxConfidences, indexBoxConfidence);
		
		    turns++;
	    } 	
	  
	    //Resets turn Value:
	    turns = 0;
	  
	    //Finds Lowest Dist Value:
	    distLoop: while (turns < startTurns) {
		    if (turns > 0) {
		      double currentClassValue = confidences[turns];
		  
		      //Sets New Values, If Smaller:
		      if (currentClassValue < finalClassValue) {
			      finalClassValue = currentClassValue;
			      finalClassIndex = turns;
		      }
		      
		      else if (currentClassValue == finalClassValue) {
		      	//Gets Box Confidences:
		      	double currentBoxClass = boxConfidences[turns];
		      	double finalBoxClass = boxConfidences[(int)(finalClassIndex)];
		      	
		      	//Checks the Case and Sets New Values, If Smaller:
		      	if (currentBoxClass <= finalBoxClass) {
		      		finalClassValue = currentClassValue;
				      finalClassIndex = turns;
		      	}
		      }
		    } 
		
		    else {
		      finalClassValue = confidences[turns];
		      finalClassIndex = turns;	
		    }
		
		    turns++;
	    } 
	  
	    //Return the Final Index:
	    return finalClassIndex;
	  }
	
	  else { 
	  	//Returns the Classification:
	  	return timesClassifiedIndex[0];
	  }
  }
}