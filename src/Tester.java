/*
PROGRAMMER: Cesar Borroto
PANTHER ID: 6041526
PROGRAMMING LANGUAGE: Java
ASSIGNMENT: Project 6
DESCRIPTION: Program to design a parking lot using stacks and queue.
*/

import java.util.*;
public class Tester {
    public static void main(String []args){

        Stack<Integer> carsOnStreet  = new Stack<>();
        Stack<Integer> carsOnLot  = new Stack<>();
        Queue<Integer> carsWaiting  = new LinkedList<>();
        int decal = 0;
        int license=1;
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 10; i++)
        {
            int int_random1 = (int) ((Math.random() * (70000 - 11111)) + 11111);
            carsOnLot.push(int_random1);
        }
        System.out.print("Cars on Lot: ");PrintStack(carsOnLot);
        System.out.print("\nCars on Street: ");PrintStack(carsOnStreet);
        System.out.print("\nCars waiting: ");PrintQueue(carsWaiting);

        do{
            System.out.println("\n***************************** MAIN MENU ************************************" +
                                "\nEnter a positive license plate number of 5 digits to park your car." +
                                "\nEnter a negative license plate number of 5 digits to retrieve your car." +
                                "\nEnter zero to finish.");
            license = sc.nextInt();

            if(license!=0){

                int int_random = (int) ((Math.random() * (99999 - 11111)) + 11111);

                if (11111 < int_random && int_random < 70000){//to randomly reject decals grater than 70000
                    System.out.println("\nValid permit.");

                    if(license>0){//if user enters positive license
                        if(carsOnLot.size() < 15){
                            carsOnLot.push(license);//push license into stack
                            System.out.println("\nPlease proceed to park your car.");
                            System.out.println();
                            System.out.print("Cars on Lot: ");PrintStack(carsOnLot);
                            System.out.print("\nCars on Street: ");PrintStack(carsOnStreet);
                            System.out.print("\nCars waiting: ");PrintQueue(carsWaiting);
                        }
                        else{
                            System.out.println("\nParking Lot full, you must wait for a parking spot to become available.");
                            carsWaiting.add(license);//add license to cars waiting
                            System.out.println("Number 3");
                            System.out.print("Cars on Lot: ");PrintStack(carsOnLot);
                            System.out.print("\nCars on Street: ");PrintStack(carsOnStreet);
                            System.out.print("\nCars waiting: ");PrintQueue(carsWaiting);
                        }
                    }
                    else{//user entered negative license

                        if(carsOnLot.contains(Math.abs(license))){

                            System.out.println("\nYour car is here. License: "+Math.abs(license));

                            while (carsOnLot.empty()!=true){//while car lot is not empty
                                int currentCarOnLot = carsOnLot.pop();

                                if(currentCarOnLot!=Math.abs(license)){//if current car is not a match
                                    carsOnStreet.push(currentCarOnLot);//send it to street temporarily
                                    System.out.println("\nSending cars to street one by one until we get to your car...\n");
                                    System.out.print("Cars on Lot: ");PrintStack(carsOnLot);
                                    System.out.print("\nCars on Street: ");PrintStack(carsOnStreet);
                                    System.out.print("\nCars waiting: ");PrintQueue(carsWaiting);
                                }
                                else{
                                    System.out.println("\n\nFound your car with license "+currentCarOnLot);
                                    System.out.println("\nYour car has been retrieved from parking lot.\n");
                                    System.out.print("Cars on Lot: ");PrintStack(carsOnLot);
                                    System.out.print("\nCars on Street: ");PrintStack(carsOnStreet);
                                    System.out.print("\nCars waiting: ");PrintQueue(carsWaiting);
                                    break;
                                }

                            }
                            while (carsOnStreet.empty()!=true){//while cars on the street are not empty
                                carsOnLot.push(carsOnStreet.pop());//send them to lot

                            }
                            System.out.println("\nSending cars from street back to the lot...\n");
                            System.out.print("Cars on Lot: ");PrintStack(carsOnLot);//check
                            System.out.print("\nCars on Street: ");PrintStack(carsOnStreet);
                            System.out.print("\nCars waiting: ");PrintQueue(carsWaiting);

                            System.out.println("\nChecking if Parking Lot has any space for cars waiting to park, if there is, they will be transferred now...\n");

                            while(carsOnLot.size() < 15){
                                carsOnLot.push(carsWaiting.remove());
                            }
                            System.out.print("Cars on Lot: ");PrintStack(carsOnLot);
                            System.out.print("\nCars on Street: ");PrintStack(carsOnStreet);
                            System.out.print("\nCars waiting: ");PrintQueue(carsWaiting);
                        }
                        else{
                            System.out.println("\nLicense Plate Number not found, your car might not be parked here.");
                        }
                    }
                }
                else{
                    System.out.println("Im sorry, but you have an invalid permit.");
                }
            }
            else{
                System.out.println("You entered zero. Returning to Main Menu");//I decided not to close the program and instead just return to main menu since thats what the machine would do.
                license=1;
            }
        }while(license!=0);
    }

    public static void PrintStack(Stack<Integer> s)
    {
        // If stack is empty
        if (s.empty())
            return;
        // Extract top of the stack
        int x = s.peek();
        s.pop();
        System.out.print(x + " ");
        PrintStack(s);
        s.push(x);
    }
    public static void PrintQueue(Queue<Integer> carsWaiting){
        for(int elem:carsWaiting){
            System.out.print(elem+" ");
        }
        System.out.println();

    }
}
