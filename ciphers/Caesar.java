/**
 * This class let's us encrypt strings according to the caesar cypher.
 * Using shifts and the alphabet.
 *
 * @author João Garrido
 */
public class Caesar {
    /**
     * string of the alphabet so that I can use with for loops to match with the characters
     */
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * main method of the class, search's for parameters given
     * in the console input and performs a simple if for how many
     * parameters it has.
     *
     * @param args String[]
     *             Prints to the screen the decrypted message;
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
        } else if (args.length > 2) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
        } else {
            int shift = Integer.parseInt(args[0]);
            String message = args[1];
            System.out.println(rotate(shift, message));
        }
    }

    /**
     * This method changes the letter given in the input for another in the alphabet
     * the number of times the value of shift says. if shift is 1 'a' -> 'b'
     *
     * @param shift  int number of shifts we have to make in the character to encrypt
     * @param letter char character from the user to encrypt
     * @return new char shifted in the alphabet
     */
    public static char rotate(int shift, char letter) {
        int length = alphabet.length();
        char encrypted = ' ';
        char lowerLetter = Character.toLowerCase(letter);
        if (shift > 26) {
            shift = shift % 26;
        } else if (shift < 0) {
            shift = (shift % 26) + 26;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(letter)) {
                encrypted = letter;
                break;
            } else {
                if (lowerLetter == alphabet.charAt(i)) {
                    if (i + shift >= length) {
                        if (Character.isUpperCase(letter)) {
                            encrypted = alphabet.charAt(i + shift - length);
                            encrypted = Character.toUpperCase(encrypted);
                        } else {
                            encrypted = alphabet.charAt(i + shift - length);
                        }
                    } else if (i + shift < 0) {
                        if (Character.isUpperCase(letter)) {
                            encrypted = alphabet.charAt(i + shift + length);
                            encrypted = Character.toUpperCase(encrypted);
                        } else {
                            encrypted = alphabet.charAt(i + shift + length);
                        }
                    } else {
                        if (Character.isUpperCase(letter)) {
                            encrypted = alphabet.charAt(i + shift);
                            encrypted = Character.toUpperCase(encrypted);
                        } else {
                            encrypted = alphabet.charAt(i + shift);
                        }
                    }
                    break;
                }
            }
        }
        return encrypted;
    }


    /**
     * This method changes each character of the text given in the input
     * for another in the alphabet the number of times the value of shift says.
     * if shift is 1 "abc" -> "bcd".
     *
     * @param shift int number of shifts we have to make in the message to encrypt
     * @param text  String message from the user to encrypt
     * @return new String shifted in the alphabet
     */
    public static String rotate(int shift, String text) {
        String encrypted = "";
        char altered;
        int length = alphabet.length();
        if (shift > 26) {
            shift = shift % 26;
        } else if (shift < 0) {
            shift = (shift % 26) + 26;
        }
        for (int i = 0; i < text.length(); i++) {
            char lowerCh = Character.toLowerCase(text.charAt(i));
            for (int j = 0; j < alphabet.length(); j++) {
                if (!Character.isLetter(text.charAt(i))) {
                    encrypted += text.charAt(i);
                    break;
                } else if (lowerCh == alphabet.charAt(j)) {
                    if (j + shift >= alphabet.length()) {
                        if (Character.isUpperCase(text.charAt(i))) {
                            altered = alphabet.charAt(j + shift - length);
                            encrypted += Character.toUpperCase(altered);
                        } else {
                            encrypted += alphabet.charAt(j + shift - length);
                        }
                    } else if (j + shift < 0) {
                        if (Character.isUpperCase(text.charAt(i))) {
                            altered = alphabet.charAt(j + shift + length);
                            encrypted += Character.toUpperCase(altered);
                        } else {
                            encrypted += alphabet.charAt(j + shift + length);
                        }
                    } else {
                        if (Character.isUpperCase(text.charAt(i))) {
                            altered = alphabet.charAt(j + shift);
                            encrypted += Character.toUpperCase(altered);
                        } else {
                            encrypted += alphabet.charAt(j + shift);
                        }
                    }
                    break;
                }
            }
        }
        return encrypted;
    }
}
