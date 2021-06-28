
public class MonoAlphaSubstitution extends Substitution {
    // class variable that will allow methods to use the translation table.
    // set at the instantiate moment.
    private String mapping;

    /**
     * Default constructor, returns translation table as the alphabet.
     */
    public MonoAlphaSubstitution() {
        mapping = "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz";
    }

    /**
     * Overload constructor, returns translation table as the one given by user.
     *
     * @param translation String that becomes the translation table.
     */
    public MonoAlphaSubstitution(String translation) {
        // if translation given is nothing then mapping .
        // is the alphabet else is the string given.
        if (translation.isBlank()) {
            mapping = "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz";
        } else {
            mapping = translation;
        }
    }

    /**
     * * This method encrypts a character according to a given mapping.
     *
     * @param c -> Char that will be encrypted.
     * @return c -> encrypted char.
     */
    public char encrypt(char c) {
        // for loop that goes over translation table 2 in 2.
        // if char c is equal to char in the table then char becomes the value of that
        // index + 1.
        for (int i = 0; i < mapping.length(); i += 2) {
            if (c == mapping.charAt(i)) {
                c = mapping.charAt(i + 1);
                break;
            }
        }
        return c;
    }

    /**
     * This method decrypts a character according to a given mapping.
     *
     * @param c -> Char that will be decrypted.
     * @return c -> decrypted char.
     */
    public char decrypt(char c) {
        // for loop that goes over translation table 2 in 2.
        // if char c is equal to char in the table then char becomes the value of that
        // index - 1.
        for (int i = 1; i < mapping.length(); i += 2) {
            if (c == mapping.charAt(i)) {
                c = mapping.charAt(i - 1);
                break;
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
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        } else if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
        } else {
            String choice = args[0];
            String translation = args[1];
            String message = args[2];
            // check if user wants to encrypt or decrypt
            if (choice.equals("encrypt") || choice.equals("decrypt")) {
                // Create new MonoAlphaSubstitution object
                Cipher mono = new MonoAlphaSubstitution(translation);
                if (choice.equals("encrypt")) {
                    // Call substitution class encrypt method using polymorphism.
                    System.out.println(mono.encrypt(message));
                } else {
                    // Call substitution class decrypt method using polymorphism.
                    System.out.println(mono.decrypt(message));
                }
            } else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            }
        }
    }
}
