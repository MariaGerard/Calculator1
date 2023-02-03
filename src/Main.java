import java.util.Scanner;

class Calculator{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Input:");
        String expression = input.nextLine();
        String result1 = result.calc(expression);
        System.out.println("Output:\n " + result1);
    }
}
public class Main{
    public static String calc(String input){
        boolean romanOrArabic = false;
        String exception = "throws exception";
        Main romanVerif = new Main();
        Main arabicToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3){
            return exception;
        }
        Integer number1 = 0;
        Integer number2 = 0;
        try {
            number1 = Integer.valueOf(inputSplit[0]);
            number2 = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                number1 = romanVerif.romanToArabic(inputSplit[0]);
                number2 = romanVerif.romanToArabic(inputSplit[2]);
                romanOrArabic = true;
            } catch (NumberFormatException ex) {
                return exception;
            }
        }

        if ((number1 < 1) || (number1 > 10) || (number2 < 1) || (number2 > 10)){
            return exception;
        }

        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = number1 + number2;
            case "-" -> result = number1 - number2;
            case "*" -> result = number1 * number2;
            case "/" -> result = number1 / number2;
            default -> {
                return exception;
            }
        }
        String output;

        if (romanOrArabic){
            if(result < 1){
                return exception;
            } else {
                output = arabicToRoman.arabicToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }
        return output;
    }

    Integer romanToArabic(String romanInput){
        int result = 0;
        int[] arabic = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabic.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arabic[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }
        return result;
    }

    String arabicToRome(int arabicInput){
        String result = "";
        int value = 0;
        int[] arabic = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabic.length; i++){
            value = arabicInput / arabic[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabicInput = arabicInput % arabic[i];
        }
        return result;
    }
}
