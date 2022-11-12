// A program to simulate traffic lights

package question2;

import java.util.Timer; // A class to help time a block of code (tasks) 
import java.util.TimerTask;  // A class to help create tasks that will be timed
import java.util.ArrayList; // A class for creating a ArrayLists
import java.util.Random; // A class that will help in generating random numbers


// A class of cars
public class car{
//	Properties of a car
	String model;
	String color;
	String plateNumber;
//	Constructor of class car 
	public car(String modelName,String car_color, String plateNo){
		model = modelName;  // initializing the car's model with the value passed to the constructor
		color = car_color;	// initializing the car's color with the value passed to the constructor
		plateNumber = plateNo; //initializing the car's plate number with the value passed to the 
		// constructor
	}
	
//	Main method of class car
	public static void main(String[] args){
//		Creating an ArrayList that will be store instances of class car (will serve as cars queue)
		ArrayList<Object>carQueue= new ArrayList<Object>();
//		Creating an ArrayList that will be store plate numbers of all cars as their unique identifier
		ArrayList<String>carNames= new ArrayList<String>();
//		Creating an instance of class TrafficLights that will be executed by another thread, and 
//		passing to it the the list of all cars and the list of plate numbers of all cars 
		TrafficLights simulateTrafficLight = new TrafficLights(carQueue, carNames);
//		Creating a new thread that will simulate Traffic lights, and passing to it an instance it will 
//		execute
		Thread simulateTrafficLightThread = new Thread(simulateTrafficLight);
//		Creating an instance of class stopProgram that will be executed by another thread, and passing 
//		to it the the list of all cars 
		stopProgram stopProgramObject = new stopProgram(carQueue);
//		Creating a new thread that will stop the program once the car queue is empty and passing to it an
//		 instance it will execute
		Thread endProgramThread = new Thread(stopProgramObject);
		simulateTrafficLightThread.start(); // Starting the thread to simulate traffic lights
		endProgramThread.start(); // Starting the thread to stop the program once the car queue is empty
		
//		Creating an ArrayList that will store models of all cars that will be randomly generated 
		ArrayList<String>carModels= new ArrayList<String>();
//		Adding different car models to the ArrayList
		carModels.add("Toyota Corolla");
		carModels.add("Volkswagen Gol");
		carModels.add("Toyota Hilux");
		carModels.add("Toyota Vios");
		carModels.add("Fiat Uno");
		
//		Creating an ArrayList that will store colors of all cars that will be randomly generated 
		ArrayList<String>carColor= new ArrayList<String>();
//		Adding different colors to the ArrayList
		carColor.add("Red");
		carColor.add("Pink");
		carColor.add("Black");
		carColor.add("Grey");
		carColor.add("White");

//		Creating a timer for the task of generating cars
		Timer timer = new Timer();
//		Creating the task of generating cars
		TimerTask task = new TimerTask(){
			int carCounter = 1;	// Creating a counter for the generated cars
		    Random randIndex = new Random(); // Creating an object of class Random
		    
//		    Defining the task to be timed
			@Override
            public void run() {
//				The condition will check if the maximum number of cars to be generated have not yet 
//				reached (100 in this case) before generating another one
				if(carCounter <= 100) {
//					Generating a random integer in range of 0 and the size of the ArrayList minus one 
					int randomIndex = randIndex.nextInt(carColor.size());
//					Storing a color on the index corresponding to the randomly generated integer above
//					to be used as the color of the randomly generated car
				    String randomColor = carColor.get(randomIndex);
//					Storing a model on the index corresponding to the randomly generated integer above
//					to be used as the model of the randomly generated car
				    String randomModel = carModels.get(randomIndex);
//				    Generating the plate number of the randomly generated car by concatenating the 
//				    value of the counter to the code "RAE" commonly used on Plate numbers in Rwanda 
				    String carPlateNumber = "RAE"+ String.valueOf(carCounter);
//				    Creating a car instance and passing arguments to the car constructor
				    car myCar = new car(randomModel,randomColor,carPlateNumber);
				    carQueue.add(myCar); // Adding the created car instance to the carQueue ArrayList
//					Adding the plate number of the created car to the ArrayList
				    carNames.add(myCar.plateNumber); 
//				    Printing the plate number of the generated car
	                System.out.println(myCar.plateNumber+ " is generated");
	                carCounter++; // incrementing the car counter
				}else {
					timer.cancel();  // if the car counter exceeds 100 the timer will stop and so will
//					the car generation
				}
            }
		};
		
//      Scheduling the task of generating cars to be executed every second with zero delay 
		timer.scheduleAtFixedRate(task,0,1000);
	}
}





