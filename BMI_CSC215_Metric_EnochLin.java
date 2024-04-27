import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/****************************************************************
 *
 * File: [BMI_CSC215_Metric_EnochLin.java]
 * By: [Enoch Lin]
 * Date: [10/4/23]
 *
 * Description: [BMI Calculator Metric Version, takes kg and meters ]
 *
 *
 ****************************************************************/
public class BMI_CSC215_Metric_EnochLin {
    private static String name; //Making it available through multiple methods
    private static int heightCm; //Making it available through multiple methods

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Inputting the scanner into the main method
        //Creating welcome message for the Metric Calculator
        System.out.println("-".repeat(90));
        System.out.println("-- Welcome to: ");
        System.out.printf("%-10s %62s", "--", "BODY MASS INDEX (BMI) Computation, CSC 215, Metric Version");
        System.out.println();
        System.out.printf("%-10s %70s", "--", "by Enoch Lin");
        System.out.println();
        System.out.println("-".repeat(90));
        System.out.println();

        captureInput("Please enter your full name: "); //Calling the CaptureInput method to the main
        float lowWeightKg = getLowWeight(scanner);
        float highWeightKg = getHighWeight(scanner);

        results(name, lowWeightKg, highWeightKg, heightCm); //Calling results from the results method

        Goodbye(args); //Calling the final end message method.
        scanner.close();
    }
    public static void captureInput(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        //Capturing user inputs in this method
        name = input.nextLine();

        System.out.print("Please enter your height in centimeters for " + name + ": ");
        heightCm = input.nextInt();
        input.nextLine(); //Line buffer - Allows for further input, newline
        System.out.print("Please enter weight in kilograms for " + name + ": ");
        float weightInKg = input.nextFloat();

        double bmi = calculation(heightCm, weightInKg);
        String weightStatus = getWeightStatus(bmi);

        System.out.println();

        System.out.println("-- SUMMARY REPORT for " + name.toUpperCase());
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm:ss a"); //Date formatting
        String formattedDate = currentDate.format(formatter);
        System.out.println("-- Date and Time: " + formattedDate);
        System.out.println("-- BMI: " + bmi + " (or " + Math.round(bmi) + " if rounded)"); //Rounding bmi for output
        System.out.println("-- Weight Status: " + weightStatus);

        System.out.println();
    }
    public static double calculation(int heightCm, float weightInKg) {
        return weightInKg / ((heightCm / 100.0) * (heightCm / 100.0)); // Formula for Metric BMI
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
        //Capturing user's low weight
       System.out.print("Please enter a LOW weight in kilograms for " + name + ": ");
       return input.nextFloat();
   }

   public static float getHighWeight(Scanner input) {
        //Capturing user's high weight
       System.out.print("Please enter a HIGH weight in kilograms for " + name + ": ");
       return input.nextFloat();
   }

   public static void results(String name, float lowWeightKg, float highWeightKg, int heightCm) {
        //Creating the BMI table with this method
       System.out.println("-----------------------------------------------------");
       System.out.println("|  WEIGHT  | BMI     |  WEIGHT STATUS                |");
       System.out.println(" --------------------------------------------------- ");

       ArrayList<String> weightRange = new ArrayList<>();

       for (float weight = lowWeightKg; weight <= highWeightKg; weight += 2.5) {
           double bmi = calculation(heightCm, weight);
           String weightStatus = getWeightStatus(bmi);

           weightRange.add(String.format("| %-9.1f %-1s %-12.2f %-2s %-18s  |", weight, "|", bmi, "|", weightStatus));
       }

       if (!weightRange.isEmpty()) {
           //Highlighting (LOW) and (HIGH)
           weightRange.set(0, weightRange.get(0) + " \u001B[33m(LOW)\u001B[0m");
           weightRange.set(weightRange.size() - 1,weightRange.get(weightRange.size() - 1) + " \u001B[33m(HIGH)\u001B[0m");
       }
       for (String entry : weightRange) {
           System.out.println(entry);
       }
   }
   public static void Goodbye(String []args) {
        //Ending message method
       System.out.println("-".repeat(53));
       System.out.println();
       System.out.println();
       System.out.println("The SFSU Mashouf Wellness Center is at 755 Font Blvd.");
       System.out.println();
       System.out.println();
       System.out.println("-".repeat(100));
       System.out.println("--" + " Thank you for using my program, " + name + "!");
       if (name.equalsIgnoreCase("Baymax Hamada")) {
           System.out.println("--" + " Sayōnara!!!");
       } else if (name.equalsIgnoreCase("Goofy Dog")) {
           System.out.println("--" + " Woof Woof!!!");
       } else {
           System.out.println("Stay healthy!!!");
       }
       System.out.println("-".repeat(100));
   }
}