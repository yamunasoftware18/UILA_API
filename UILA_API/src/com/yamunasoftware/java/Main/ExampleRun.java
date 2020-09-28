package com.yamunasoftware.java.Main;

import java.util.Scanner;
import java.util.Stack;

import com.yamunasoftware.java.Main.Neuron;
import com.yamunasoftware.java.Tools.*;

//Example of classification between cats and dogs!
public class ExampleRun extends Neuron {
	//Runner Variables:
	private static String init;
    private static boolean start = true;
    private static double trainTimes = 1;
    private static double boxPadding = 0;
    
    //Data Arrays (Customize, ONLY DOUBLES!), Make them correspond to each other!:
    
    //DOG set:
    private static Double dogX[] = {10.9, 15.8, 201.2, 2000.256, 1200.34, 6000.8, 2000000000.0}; //x1
    private static Double dogY[] = {2.1, 5.6, 149.0, 5.4, 7.895, 2000000.2, 666666667.0};  //y1
    
    //CAT set:
    private static Double catX[] = {10.9, 15.8, 201.2, 2000.256, 1200.34, 6000.8};  //x2
    private static Double catY[] = {2.1, 5.6, 149.0, 5.4, 7.895, 2000000.2};  //y2
    
    //Identifiers Set:
    private static String identifiers[] = {"CAT", "DOG"};
	  
	  //Objects:
	  private static Scanner s = new Scanner(System.in); //Scanner Object
	  private static Stack<String> stack = new Stack<String>(); //Stack Object

	  private static Trainer t = new Trainer() { //Trainer Object
		  //Training Neural Network Commands:
		  @Override
		  public void train() throws Exception {
		    //Dog Training:
		    removeOutliers(dogX, dogY);
		    findBoxValues(dogX, dogY, boxPadding, identifiers[1]);	
			
		    //Cat Training:
	      removeOutliers(catX, catY);
	      findBoxValues(catX, catY, boxPadding, identifiers[0]);
	      
	      trainTimes++;
		  }  	  
	  };
	  
	  //Constructor: 
	  public ExampleRun() {	 
		//Example Code...
	  }
	  
	  //Main Method:
	  public static void main(String[] args) throws Exception {
	    ask();
	  }
	  
	  //User Interface Function:
	  public static void ask() throws Exception {
		  if (start == true) { //Error Traps:
			System.out.println("Welcome to the Universal Integer Learning Algorithm API!");
			start = false;
		  }
		  
		   //Try Block:  
			try {
			  System.out.println("What would you like to do? (train, run, exit)");	
			  init = s.nextLine();	
			}
			
			catch (Exception e) {
			  System.out.println("Error...Restarting API...");	
			  ask();
			}
			
			//Input Cases:
			if (init.equalsIgnoreCase("train")) {
			 if (trainTimes == 1) {	
			  t.train();	
			  ask();
			 }
			 
			 else {
			  System.out.println("Error. Already Trained.");
			  ask();
			 }
			}
			
		    else if (init.equalsIgnoreCase("exit")) {
		      stack.clear();  //Clears Stack and Exits.
			  System.exit(0);	
			}
			
		    else if (init.equalsIgnoreCase("run")) {
		     if (trainTimes >= 2) { 	
		      stack.clear();
		      test();
		     }
		     
		     else {
		      System.out.println("Error...Restarting API...");	
			  ask();	 
		     } 
		    }
			
		    else {
		      System.out.println("Error...Restarting API...");	
		  	  ask();	
		    }	  
	  }
	  
	  //Test Method:
	  public static void test() throws Exception {
		//Variables:
		double X = 0.0, Y = 0.0, classif;  
		  
		//Asks for User Input:
		try {  
		  System.out.println("Enter X: ");	
		  X = s.nextDouble();  
		  
		  System.out.println("Enter Y: ");
		  Y = s.nextDouble();
		}
		
		catch (Exception e) {
		  System.out.println("Error...Restarting API...");	
		  test();	
		}
		  
		//Gets classification:
		classif = classification(X, Y, identifiers); 
		
		//Identifies class (in terminal):
		if (classif == 0.0) {
		  System.out.println("Its a CAT!");
		  s.nextLine();
		  ask();
		}
		
		else if (classif == 1.0) {
		  System.out.println("Its a DOG!");	
		  s.nextLine();
		  ask();
		}
		
		else {
		  System.out.println("Its a NEITHER!");	
		  s.nextLine();
		  ask();
		}
	  }
}