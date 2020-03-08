/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclehireapp;

import au.edu.swin.vehicle.Vehicle;
import au.edu.swin.vehicle.VehicleType;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luke
 */
public class VehicleHireApp {

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// Create the vehicle types
        VehicleType sedan = new VehicleType("SEDAN", "A standard sedan", 4);
        VehicleType limo6 = new VehicleType("LIMO6", "A six seater limo", 6);
        VehicleType limo8 = new VehicleType("LIMO8", "An eight seater limo", 8);
// Create the vehicles
        ArrayList<Vehicle> vehicles = new ArrayList();
        vehicles.add(new Vehicle("Ed's Holden Caprice", "Silver", sedan, 2002));
        vehicles.add(new Vehicle("John's Mercedes C200", "Black", sedan, 2005));
        vehicles.add(new Vehicle("Guy's Volvo 244 DL", "Blue", sedan, 1976));
        vehicles.add(new Vehicle("Sasco's Ford Limo", "White", limo6, 2014));
        vehicles.add(new Vehicle("Peter's Ford Limo", "White", limo6, 2004));
        vehicles.add(new Vehicle("Robert's Ford Limo", "White", limo8, 2003));
        System.out.println("\n\nList of vehicles in system:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
        String typeCode = "NuLl"; //Here is our variable to be changed
        typeCode = chooseCarMenu();
        System.out.println("\n\nList of vehicle of type " + typeCode);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType().getCode().equals(typeCode)) {
                System.out.println(vehicle);
            }
        }

    }

    enum VehicleEnum {
        DEFAULT,
        SEDAN,
        LIMO6,
        LIMO8
    }

    public static String chooseCarMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String chosenType;
        VehicleEnum vEnum = VehicleEnum.DEFAULT;
        chosenType = "test";

        System.out.println("Here are the following cars avaiable \n");
        System.out.println(" 1: SEDAN \n 2: LIMO6 \n 3: LIMO8 \n 4: Exit \n");

        System.out.println("Please select one of the above options(1-4): ");

        String userInput = "null";
        int i = 0;
        try {
            userInput = br.readLine();
            i = Integer.parseInt(userInput);
            if (i >= 1 && i < 4) {
                vEnum = VehicleEnum.values()[i];
            }
            else if(i == 4){
                System.out.println("You have chosen to EXIT by choosing 4");
                vEnum = VehicleEnum.values()[0];
            }
            else {
                System.out.println("I did not understand your input");
                vEnum = VehicleEnum.values()[0];
            }

        } catch (IOException ex) {
            Logger.getLogger(VehicleHireApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        //int i = System.in.read();
       // System.out.println("String: {} Integer: {} Type: {}".format(userInput, i, vEnum));

        //System.out.println("Test: {}".format(vEnum.toString()));
        
        return vEnum.toString();
    }

}
