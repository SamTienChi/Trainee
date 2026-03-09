package validation;
import java.util.Scanner;

public class InputValidator {
    public static int getPositiveInt(Scanner sc, String message){
        int number;

        while(true){
            try{
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine());

                if(number <= 0){
                    System.out.println("Number must be greater than 0!");
                    continue;
                }

                return number;

            }catch(NumberFormatException e){
                System.out.println("Invalid number! Please enter again.");
            }
        }
    }
}
