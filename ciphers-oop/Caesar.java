public class Caesar extends MonoAlphaSubstitution {
    // Class variables, shift will be given value in the Constructor method.
    private int shift;
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Default constructor, returns shift as 0.
     */
    public Caesar() {
        shift = 0;
    }

    /**
     * Overload constructor, returns shift as the one given by user.
     *
     * @param key int that becomes the value of shift.
     */
    public Caesar(int key) {
        shift = key;
    }

    /**
     * * This method encrypts a character according to a given shift and alphabet.
     *
     * @param c -> Char that will be encrypted.
     * @return c -> encrypted char.
     */
    public char encrypt(char c) {
        // Get new shift to be able to work with string ends, solves index problems.
        if (shift > 26) {
            shift = shift % 26;
        } else if (shift < 0) {
            shift = (shift % 26) + 26;
        }
        char l = Character.toLowerCase(c);
        // if is not a letter return the original character
        if (Character.isLetter(l)) {
            for (int i = 0; i < alphabet.length(); i++) {
                // if letter in the alphabet is equal to the given on then return the letter in
                // the alphabet of position - shift.
                if (l == alphabet.charAt(i)) {
                    // i + shift is bigger than 26 return alphabet place of i + shift - 26
                    if (shift + i >= 26) {
                        if (Character.isLowerCase(c)) {
                            c = alphabet.charAt(i + shift - 26);
                        } else {
                            c = Character.toUpperCase(alphabet.charAt(i + shift - 26));
                        }
                        // i + shift is smallet than 0 return alphabet place of i + shift + 26
                    } else if (shift + i < 0) {
                        if (Character.isLowerCase(c)) {
                            c = alphabet.charAt(i + shift + 26);
                        } else {
                            c = Character.toUpperCase(alphabet.charAt(i + shift + 26));
                        }
                    } else {
                        if (Character.isLowerCase(c)) {
                            c = alphabet.charAt(i + shift);
                        } else {
                            c = Character.toUpperCase(alphabet.charAt(i + shift));
                        }
                    }
                    break;
                }
            }
        }
        return c;
    }

    /**
     * * This method decrypts a character according to a given shift and alphabet.
     *
     * @param c -> Char that will be decrypted.
     * @return c -> decrypted char.
     */
    public char decrypt(char c) {
        // Get new shift to be able to work with string ends, solves index problems.
        if (shift > 26) {
            shift = shift % 26;
        } else if (shift < 0) {
            shift = (shift % 26) + 26;
        }
        char l = Character.toLowerCase(c);
        // if is not a letter return the original character
        if (Character.isLetter(l)) {
            // Go through the alphabet.
            for (int i = 0; i < alphabet.length(); i++) {
                // if letter in the alphabet is equal to the given on then return the letter in
                // the alphabet of position - shift.
                if (l == alphabet.charAt(i)) {
                    // i - shift is smallet than 0 return alphabet place of i - shift + 26
                    if (i - shift < 0) {
                        if (Character.isLowerCase(c)) {
                            c = alphabet.charAt(i - shift + 26);
                        } else {
                            c = Character.toUpperCase(alphabet.charAt(i - shift + 26));
                        }
                        // i - shift is bigger than 26 return alphabet place of i + shift - 26
                    } else if (i - shift >= 26) {
                        if (Character.isLowerCase(c)) {
                            c = alphabet.charAt(i + shift - 26);
                        } else {
                            c = Character.toUpperCase(alphabet.charAt(i + shift - 26));
                        }
                    } else {
                        if (Character.isLowerCase(c)) {
                            c = alphabet.charAt(i - shift);
                        } else {
                            c = Character.toUpperCase(alphabet.charAt(i - shift));
                        }
                    }
                    break;
                }
            }
        }
        return c;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // check if args is bigger or smaller than 3 and give out error message
        // according to it.
        if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
        } else if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
        } else {
            String choice = args[0];
            int key = Integer.parseInt(args[1]);
            String message = args[2];
            // check if user wants to encrypt or decrypt.
            if (choice.equals("encrypt") || choice.equals("decrypt")) {
                // Create new Caesar object.
                Cipher cs = new Caesar(key);
                if (choice.equals("encrypt")) {
                    // call the substitution class encrypt method
                    System.out.println(cs.encrypt(message));
                } else {
                    // call the substitution class decrypt method
                    System.out.println(cs.decrypt(message));
                }
            } else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            }
        }
    }
}
