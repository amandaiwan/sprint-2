
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amanda
 */
public class BestGymEverDemo {
   
    String lineOne;
    String lineTwo;

    boolean personHere = false;
    boolean shouldAppend = true;
    
    LocalDate membershipDate;

    String filePath = "C:\\JAVA17\\Inlämningsuppgift_2\\Customers.txt";
   

    public BestGymEverDemo() throws IOException {
        
        try (PrintWriter w = new PrintWriter(new FileWriter("Visiters.txt", shouldAppend))) {

           Path inFilePath = Paths.get(filePath); //hämtar customers
           Scanner fileScanner = new Scanner(inFilePath); //scannar av filen
           
            String guest = JOptionPane.showInputDialog("Var vänligen och skriv in ditt namn eller ditt personnummer: ");
              
            while (fileScanner.hasNext()) { // medans filen har en token i sin input
                lineOne = fileScanner.nextLine(); // tilldelar första raden i filen i variabeln lineOne
                String[] personnrOchNamn = lineOne.split(",");

                if (fileScanner.hasNext()) //ger ett villkor OM filen har en token i sin input
                    lineTwo = fileScanner.nextLine(); // tilldelar raden i filen i variabeln lineTwo                
                
                String[] medlemsAvgiftDatum = lineTwo.split("-");
                int year = Integer.parseInt(medlemsAvgiftDatum[0]); // ex. 2008
                int month = Integer.parseInt(medlemsAvgiftDatum[1]); // ex. 04
                int day = Integer.parseInt(medlemsAvgiftDatum[2]); // ex. 07

                LocalDate membershipDate = LocalDate.of(year, month, day); 
                LocalDate today = LocalDate.now();
                LocalDate lastYear = today.minusYears(1); 

                if (guest == null || guest.equals("")) {
                    System.exit(0);
                }

                if (personnrOchNamn[0].equalsIgnoreCase(guest) || personnrOchNamn[1].trim().equalsIgnoreCase(guest)) { 
                    personHere = true;
                    if (membershipDate.isAfter(lastYear)) {
                        JOptionPane.showMessageDialog(null, "Du är en nuvarande kund");
                        w.printf("%s %nBesöksdatum: %s %n", lineOne, today);

                    }
                    if (membershipDate.isBefore(lastYear)) {
                        JOptionPane.showMessageDialog(null, "Du är en före detta kund");
                    }
                }
            }

            if (!personHere) {
                JOptionPane.showMessageDialog(null, "Du är inte en kund hos oss.");
            }

            fileScanner.close();

         }catch (Exception e) { //superklassen till alla exceptions
            System.out.println("Något fel inträffa");
        }  
    }
    
    public static void main(String[] args) throws IOException {

        BestGymEverDemo b = new BestGymEverDemo();
    }
}
