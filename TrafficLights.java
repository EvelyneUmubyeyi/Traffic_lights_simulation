// This file contains TrafficLights class that will be used to create traffic lights 

package question2;

import java.util.ArrayList; // A class for creating a ArrayLists

// Class TrafficLights which implements the runnable interface
public class TrafficLights implements Runnable {
	
//	Properties of the TrafficLights instance
	ArrayList<Object>carsQueue= new ArrayList<Object>(); // ArrayList of all cars
	ArrayList<String>carsName= new ArrayList<String>(); // ArrayList of all cars' plate numbers

//	Constructor of the TrafficLights class 
	public TrafficLights(ArrayList<Object>carQueue,ArrayList<String>carName){
		//Initializing carsQueue ArrayList with the cars list passed from the main thread 
		carsQueue = carQueue; 
		//Initializing carsName ArrayList with the cars' plate numbers list passed from the main thread 
		carsName = carName;
	}
	
//	Class of traffic lights
	public class TrafficLight{
		int duration; // Property of traffic lights determining how long the light will last
//		constructor of the TrafficLight class
		public TrafficLight(int time) {
			duration = time; //initializing the light's duration with value passed to the constructor
		}	
	}

// Run method will be called exactly when the simulateTrafficLightThread is started
	@Override
	public void run() {
//		Creating the green traffic light instance and passing its duration to the constructor
		TrafficLights.TrafficLight green = new TrafficLights.TrafficLight(30);
//		Creating the yellow traffic light instance and passing its duration to the constructor
		TrafficLights.TrafficLight yellow = new TrafficLights.TrafficLight(10);
//		Creating the red traffic light instance and passing its duration to the constructor
		TrafficLights.TrafficLight red = new TrafficLights.TrafficLight(20);
		
//		creating an ArrayList to store all the traffic light instances
		ArrayList<Object>lightsList= new ArrayList<Object>();
//		Adding all Traffic lights to the ArrayList 
		lightsList.add(green);
		lightsList.add(yellow);
		lightsList.add(red);
		
// Creating an endless loop as there will always be a traffic light on through out the program
		while(true) {
//			Looping through all the Traffic lights stored in the lightsList ArrayList
			for(int lightIndex =0; lightIndex < lightsList.size();lightIndex++) {
				
//				Condition to check if the light under iteration is green
				if (lightsList.get(lightIndex) == green) {
//					Loop to iterate a number of times equal to the number of seconds the light will be 
//					on, the counter will decrement each iteration
					for (int counter = green.duration; counter>=1;counter--) {
//						Printing the name of the current light and the counter's value
						System.out.println("Green light! " + counter + " ");
//						Condition to check if the cars queue is empty before removing a car 
						if (carsQueue.isEmpty() != true) {
//							Storing the plate number of the first car in the queue to print it after it 
//							has been removed
							String removedCar = carsName.remove(0);
//							Removing the front car in the queue 
							carsQueue.remove(0);
//							Printing the removed car's plate number and the remaining cars in the queue
							System.out.println(removedCar+ " is removed "+ carsQueue.size() + 
									" cars remaining on queue!!");
//							Printing dash lines to easily visualize the outputs of each iteration
							System.out.println("------------------------------------------------------");
//						This will be executed if the queue is empty and there's no car to remove
						}else {
//							Printing dash lines to easily visualize the outputs of each iteration
							System.out.println("------------------------------------------------------");
						}
//						Delaying the thread for a second as a car should be removed after a second under 
//						green light and enclosing it in try and except to handle the exception for 
//						when the thread is interrupted
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
//					Condition to check if the light under iteration is yellow
				}else if (lightsList.get(lightIndex) == yellow) {
//					Loop to iterate a number of times equal to the number of seconds the light will be 
//					on, the counter will decrement each iteration
					for (int counter = yellow.duration; counter>=1;counter--) {
//						Printing the name of the current light and the counter's value
						System.out.println("Yellow light! " + counter + " ");
//						Condition to check if the cars queue is empty before removing a car, and if it 
//						has been two seconds since a car was removed as a car is removed every 2 seconds 
						if (carsQueue.isEmpty() != true && counter%2!=0) {
//							Storing the plate number of the first car in the queue to print it after it 
//							has been removed
							String removedCar = carsName.remove(0);
//							Removing the front car in the queue 
							carsQueue.remove(0);
//							Printing the removed car's plate number and the remaining cars in the queue
							System.out.println(removedCar+ " is removed "+carsQueue.size() + 
									" cars remaining on queue!!");
//							Printing dash lines to easily visualize the outputs of each iteration
							System.out.println("------------------------------------------------------");
//							This will be executed if the queue is empty and there's no car to remove
						}else {
//							Printing dash lines to easily visualize the outputs of each iteration
							System.out.println("------------------------------------------------------");
						}
//						Delaying the thread for a second before the next iteration
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}					
					}
//					This will be executed if the light is red
				}else {
//					Loop to iterate a number of times equal to the number of seconds the light will be 
//					on, the counter will decrement each iteration
					for (int counter = red.duration; counter>=1;counter--) {
//						Printing the name of the current light and the counter's value
						System.out.println("Red light! " + counter + " ");
//						Printing the remaining cars in the car queue
						System.out.println(carsQueue.size() + "cars remaining on queue!!");
//						Printing dash lines to easily visualize the outputs of each iteration
						System.out.println("------------------------------------------------------");
//						Delaying the thread for a second before the next iteration
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}
				}
			}
			
		}
		

	}
}