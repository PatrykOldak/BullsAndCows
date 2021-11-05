import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        Scanner sc = new Scanner(System.in);
        int userLength = sc.nextInt();
        while (userLength > 36) {
            System.out.println("Error: can't generate a secret number with a length of "
                    + userLength + " because there aren't enough unique digits.");
            userLength = sc.nextInt();
        }
        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbols = sc.nextInt();
        String secretCode = randomGenerator(userLength, possibleSymbols);
        boolean isWon = false;
        int turns = 1;
        System.out.println("Okay, let's start a game!");
        while(!isWon) {
            System.out.println("Turn " + turns + ":");
            String answer = sc.next();
            int bulls = 0;
            int cows = 0;
            for (int i = 0; i < secretCode.length(); i++) {
                for (int j = 0; j < answer.length(); j++) {
                    if (secretCode.charAt(i) == answer.charAt(j) && i == j) {
                        bulls++;
                    } else if (secretCode.charAt(i) == answer.charAt(j)) {
                        cows++;
                    }
                }
            }
            if (cows == 0 && bulls == 0) {
                System.out.println("Cannot find number of bulls or number of cows or None after the input");
            } else if (bulls == 0) {
                if (cows == 1) {
                    System.out.println("Grade: " + cows + " cow");
                } else if (cows > 1) {
                    System.out.println("Grade: " + cows + " cows");
                }
            } else if (cows == 1 && bulls == 1) {
                System.out.println("Grade: " + bulls + " bull and " + cows + " cow");
            } else if (bulls == 1 && cows > 1) {
                System.out.println("Grade: " + bulls + " bull and " + cows + " cows");
            } else if (bulls == userLength) {
                System.out.println("Grade: " + bulls + " bulls");
                System.out.println("Congratulations! You guessed the secret code.");
                isWon = true;
            } else {
                System.out.println("Grade: " + bulls + " bulls and " + cows + " cows");
            }
            turns++;
        }



    }
    public static String randomGenerator(int length, int symbols) {
        List<Character> randomList = Stream.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z').collect(Collectors.toList());
        List<Character> sublist = randomList.subList(0, symbols);
        Collections.shuffle(sublist);
        StringBuilder result = new StringBuilder();
        for (char ch : sublist) {
            result.append(ch);
        }
        System.out.print("The secret is prepared: ");

        for (int i = 0; i < length; i++) {
            System.out.print("*");
        }
        if (symbols < 10) {
            System.out.println(" (0-" + randomList.get(symbols - 1) + ").");
        } else if (symbols == 10) {
            System.out.println(" (0-9, a.");
        } else {
            System.out.println(" (0-9, a-" + randomList.get(symbols - 1) + ").");
        }

        return result.toString();
    }
}
