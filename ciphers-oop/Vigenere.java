public class Vigenere extends Substitution {
    // class variable that will allow methods to use certain values.
    private String identity;
    private int[] ciphers;
    private static int position = 0;

    /**
     * Default constructor, returns identity as A so that every character shifts to
     * itself
     */
    public Vigenere() {
        identity = "A";
        // create a caesar cipher shift array
        createArray(identity);
    }

    /**
     * Overload constructor, returns identity as given by user so that every
     * character shifts Character - keyCharacter times
     * 
     * @param key -> String given by user to be the identity
     */
    public Vigenere(String key) {
        if (key.isBlank()) {
            identity = "A";
        } else {
            identity = key;
        }
        // create a caesar cipher shift array
        createArray(identity);
    }

    /**
     * This method encrypts a character using caesar depending on the shift in an
     * array of caesar ciphers
     * 
     * @param c -> Char that will be encrypted.
     * @return c -> encrypted char.
     */
    public char encrypt(char c) {
        // Create a Caesar object with the shift being the cipher array position
        Caesar cs = new Caesar(ciphers[position]);
        c = cs.encrypt(c);
        // increment position in the cipher array.
        position++;
        // if position is bigger than the number of cipher go to the start.
        if (position > ciphers.length - 1) {
            position = 0;
        }
        return c;
    }

    /**
     * This method decrypts a character using caesar depending on the shift in an
     * array of caesar ciphers
     * 
     * @param c -> Char that will be encrypted.
     * @return c -> encrypted char.
     */
    public char decrypt(char c) {
        // Create a Caesar object with the shift being the cipher array position
        Caesar cs = new Caesar(ciphers[position]);
        c = cs.decrypt(c);
        // increment position in the cipher array.
        position++;
        // if position is bigger than the number of cipher go to the start.
        if (position > ciphers.length - 1) {
            position = 0;
        }
        return c;
    }

    /**
     * This method creates an array with the length between the character in the
     * message and A.
     * 
     * @param identity -> String that holds the message.
     */
    private void createArray(String identity) {
        ciphers = new int[identity.length()];
        for (int i = 0; i < identity.length(); i++) {
            ciphers[i] = identity.charAt(i) - 'A';
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // check if args is bigger or smaller than 3 and give out error message
        // according to it.
        if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
        } else if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
        } else {
            String choice = args[0];
            String definition = args[1];
            String message = args[2];
            // check if user wants to encrypt or decrypt
            if (choice.equals("encrypt") || choice.equals("decrypt")) {
                // Create new Vigenere object
                Cipher vigo = new Vigenere(definition);
                if (choice.equals("encrypt")) {
                    // Call substitution class encrypt method
                    System.out.println(vigo.encrypt(message));
                } else {
                    // Call substitution class decrypt method
                    System.out.println(vigo.decrypt(message));
                }
            } else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            }
        }
    }
}