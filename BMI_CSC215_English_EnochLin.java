import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
/****************************************************************
 *
 * File: [BMI_CSC215_English_EnochLin.java]
 * By: [Enoch Lin]
 * Date: [10/4/23]
 *
 * Description: [BMI Calculator English Version, takes lbs and inches]
 *
 *
 ****************************************************************/

public class BMI_CSC215_English_EnochLin {
    private static String name; //Making it available through multiple methods
    private static int heightFeet; //Making it available through multiple methods
    private static int heightInInches; //Making it available through multiple methods

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Inputting New Scanner
// Creating the welcome message for the English Version
        System.out.println("-".repeat(90)); //Repeat method to save time
        System.out.println("-- Welcome to: ");
        System.out.printf("%-10s %62s", "--", "BODY MASS INDEX (BMI) Computation, CSC 215, English Version");
        System.out.println();
        System.out.printf("%-10s %70s", "--", "by Enoch Lin");
        System.out.println();
        System.out.println("-".repeat(90));
        System.out.println();


        captureInput("Please enter your full name: "); //Calling captureinput method
        float lowWeight = getLowWeight(scanner);
        float highWeight = getHighWeight(scanner);

        results(name, lowWeight, highWeight, heightFeet, heightInInches); //Calling the results method

        Goodbye(args); //Calling the ending message method

        scanner.close();
    }


    public static void captureInput(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
//Capturing user inputs with this method.
        name = input.nextLine();

        System.out.print("Please enter your height in feet and inches for " + name + ": ");
         heightFeet = input.nextInt(); //Capturing the height in feet first
         heightInInches = input.nextInt(); //Capturing the height in inches as well, but in a way where it can take both
        input.nextLine(); // Line buffer - Allows for further input for weight
        System.out.print("Please enter weight in pounds for " + name + ": ");
        float weightInPounds = input.nextFloat();

        double bmi = calculation(heightFeet * 12 + heightInInches, weightInPounds); //Conversion because we used to variables to capture our height, so we can make calculate the BMI precisely.
        String weightStatus = getWeightStatus(bmi);

        System.out.println();

        System.out.println("-- SUMMARY REPORT for " + name.toUpperCase());
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm:ss a");
        String formattedDate = currentDate.format(formatter);
        System.out.println("-- Date and Time: " + formattedDate);
        System.out.println("-- BMI: " + bmi + " (or " + Math.round(bmi) + " if rounded)");
        System.out.println("-- Weight Status: " + weightStatus);

        System.out.println();
// Our outputs for the information stored.


    }

    public static double calculation(double heightInInches, float weightInInches) {
        Scanner scanner = new Scanner(System.in);
        return (weightInInches / (heightInInches * heightInInches)) * 703.0; // Formula and calculation for English version

    }

    public static String getWeightStatus(double bmi) {
        if (bmi < 18.5) { //Processing lower weight than expected
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.99) { //Processing expected weight
            return "Healthy Weight";
        } else if (bmi >= 25 && bmi < 29.99) { //Processing above expected weight
            return "Overweight";
        } else { //Processing well above expected weight
            return "Obesity";
        }
    }
    public static float getLowWeight(Scanner input) {
        //Capturing user's Low Weight
        System.out.print("Please enter a LOW weight in pounds for " + name + ": ");
        return input.nextFloat();
    }

    public static float getHighWeight(Scanner input) {
        // Capturing user's High Weight
        System.out.print("Please enter a HIGH weight in pounds for " + name + ": ");
        return input.nextFloat();
    }

    public static void results(String name, float lowWeight, float highWeight, int heightFeet, int heightInInches) {
        //Creating the BMI table with this method

        System.out.println("-----------------------------------------------------");
        System.out.println("|  WEIGHT    | BMI          |  WEIGHT STATUS         |");
        System.out.println(" --------------------------------------------------- ");

        ArrayList <String> weightRange = new ArrayList<>();

        for (float weight = lowWeight; weight <= highWeight; weight += 5.5) {
            double bmi = calculation((heightFeet * 12) + heightInInches, weight);
            String weightStatus = getWeightStatus(bmi); //Second calculation for the table

            weightRange.add(String.format("|  %-9.1f %-1s %-12.2f %-2s %-18s    |", weight, "|", bmi, "|", weightStatus));

        }
        if (!weightRange.isEmpty()) {
            //Highlighting the (LOW) and (HIGH)
            weightRange.set(0, weightRange.get(0) + " \u001B[33m(LOW)\u001B[0m");
            weightRange.set(weightRange.size() - 1, weightRange.get(weightRange.size() - 1) + " \u001B[33m(HIGH)\u001B[0m");

        }


        for (String entry : weightRange) {
            System.out.println(entry);
        }



    }
    public static void Goodbye(String []args) {
        // Ending Message method.
        System.out.println("-".repeat(53));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("The SFSU Mashouf Wellness Center is at 755 Font Blvd.");
        System.out.println();
        System.out.println();
        System.out.println("-".repeat(100));
        System.out.println("--" + " Thank you for using my program, " + name + "!");
        if (name.equalsIgnoreCase("Otto Minion")) {
            System.out.println("--" + " Poopaye!!!");
        } else if (name.equalsIgnoreCase("Minnie Mouse")) {
            System.out.println("--" + " Ear-esistible!!!");
        } else {
            System.out.println("Stay Healthy!!!");
        }
        System.out.println("-".repeat(100));
    }
}
