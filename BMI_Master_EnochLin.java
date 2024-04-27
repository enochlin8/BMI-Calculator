import java.util.Scanner;
import java.util.regex.Pattern;
/****************************************************************
 *
 * File: [BMI_Master_EnochLin.java]
 * By: [Enoch Lin]
 * Date: [10/4/2023]
 *
 * Description: [This program is meant to call on the English and Metric versions of the BMI calculators I created.]
 *
 *
 ****************************************************************/
public class BMI_Master_EnochLin {
    public static void main(String [] args) {
        //Menu to call on either English or Metric BMI Calculators
        Scanner scanner = new Scanner (System.in);
        String choice;

        Pattern engPattern = Pattern.compile(".*eng.*", Pattern.CASE_INSENSITIVE); //Recognizes "eng" from user input
        Pattern metPattern = Pattern.compile(".*met.*", Pattern.CASE_INSENSITIVE); //Recognizes "met" from user input

        do { //Start of menu
            System.out.println("My CSC 215 BMI Calculator Projects:");
            System.out.println("   1. BMI, English");
            System.out.println("   2. BMI, Metric");
            System.out.println();
            System.out.println();
            System.out.println("[ USER MANUEL ] Enter an exclamation mark ! to end");
            System.out.print("Please enter the version you want to try: "); //User's input
            if (scanner.hasNext()) {
                choice = scanner.next().toLowerCase(); //Regardless of upper or lower case
            } else {
                choice = "!";
            }
            if (choice.equals("!")) { //If user decides to stop choosing calculators.
                System.out.println("Exiting");
                break;
            } else if (choice.contains("eng") || choice.contains("lish")) { //If User inputs either ENG or LISH it will choose the English calculator
                BMI_CSC215_English_EnochLin.main(new String []{}); //Calls on the English Version
            } else if (choice.contains("met") || choice.contains("tric")) { //If User inputs either MET or TRIC it will choose the Metric calculator
                BMI_CSC215_Metric_EnochLin.main(new String []{}); //Calls on the Metric Version
            } else { //If they don't input a correct answer.
                System.out.println("Invalid choice. Please choose English or Metric");
            } //If they want to choose another version.
            System.out.println("Press Enter to return to the main menu");

        } while (true);
        scanner.close();
}
}
