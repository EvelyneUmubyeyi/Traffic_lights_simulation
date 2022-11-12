// This file contains code to stop the Traffic Lights simulation program when appropriate

package question2;

import java.util.ArrayList; // A class for creating a ArrayLists
import java.util.Timer; // A class to help time a block of code (tasks)
import java.util.TimerTask; // A class to help create tasks that will be timed

//Class stopProgram which implements the runnable interface
public class stopProgram implements Runnable {

//	Properties of the stopProgram instance
	ArrayList<Object>carsQueue= new ArrayList<Object>(); // ArrayList of all cars
	
//	Constructor of the stopProgram class 
	public stopProgram(ArrayList<Object>carQueue){
		//Initializing carsQueue ArrayList with the cars list passed from the main thread 
		carsQueue = carQueue;
	}
	
//	Run method will be called exactly when the endProgramThread is started
	@Override
	public void run() {
//		Creating a timer for the task of counting the number of generated cars
		Timer timer = new Timer();
//		Creating the task of counting the number of generated cars
		TimerTask task = new TimerTask(){
//			initializing a counter for the number of seconds since the program has started which is equal
//			to the number of random cars generated as a car is generated each second
			 int counter = 0;
			 
//			 Defining the task to be timed
			@Override
            public void run() {
				counter++; // Incrementing the counter each second
//				Condition to check whether the cars queue is empty and if the counter has reached 100 
//				meaning that 100 cars would have been generated already and removed from the queue
				if(counter >= 100 && carsQueue.isEmpty() == true) {
					System.out.println("Queue empty!!"); // Print statement before the program is ended
					System.exit(0); // Exiting the program
				}
			}
		};

//      Scheduling the task of counting the number of generated cars to be executed every second with 
//		zero delay 
		timer.scheduleAtFixedRate(task,0,1000);
	}

}
