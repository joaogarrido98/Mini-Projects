/**
 * This class let's us decrypt strings according to the caesar cypher.
 * Using frequency of letters in the english alphabet
 *
 * @author João Garrido
 */
public class Brutus {
    /**
     * string of the alphabet so that I can use with for loops to match with the characters
     */
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * main method of the class, search's for parameters given
     * in the console input and performs a simple if for how many
     * parameters it has. Also decrypts the message.
     *
     * @param args String[] input from the user in the console
     *             Prints to the screen the decrypted message;
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar \"cipher text\"");
        } else if (args.length < 1) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar \"cipher text\"");
        } else {
            String message = args[0];
            String newMessage = message;
            double[] msgFrequency = frequency(message);
            double[] frequencies = new double[26];
            double chi;
            for (int i = 0; i < 26; i++) {
                newMessage = Caesar.rotate(i, newMessage);
                msgFrequency = frequency(newMessage);
                chi = chiSquared(msgFrequency, english);
                frequencies[i] = chi;
            }
            int key = 0;
            double smallest = frequencies[key];
            for (int i = 1; i < frequencies.length; i++) {
                if (frequencies[i] < smallest) {
                    smallest = frequencies[i];
                    key = i;
                }
            }
            System.out.println(Caesar.rotate(key, message));
        }
    }

    /**
     * english alphabet frequency of letters
     */
    public static final double[] english = {
            0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
            0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
            0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };

    /**
     * This method is used to count how many times a character appears in one text
     * example -> in "hello" the count for "h" is 1 and the count for "l" is 2.
     *
     * @param message String used to get the text to get the count
     * @return int[] organized alphabetically -> {4, 1,4 ,9.... etc}
     */
    public static int[] count(String message) {
        int[] letterCounts = new int[26];
        int counter = 0;
        message = message.toLowerCase();
        int len = alphabet.length();
        int msgLen = message.length();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < msgLen; j++) {
                if (alphabet.charAt(i) == message.charAt(j)) {
                    counter++;
                }
            }
            letterCounts[i] = counter;
            counter = 0;
        }
        return letterCounts;
    }

    /**
     * This method is used to get the frequency off a character in a text
     * example -> in "hello" the count for "h" is 1 therefore the frequency is 1/5
     * and the frequency for "l" is 2/5.
     *
     * @param message String used to get the text to get the frequency
     * @return doubles[] organized alphabetically -> {0.1, 0.2, 0.004 , 0.119.... etc}
     */
    public static double[] frequency(String message) {
        double[] letterFreq = new double[26];
        int counter = 0;
        message = message.toLowerCase();
        int len = alphabet.length();
        int msgLen = message.length();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < msgLen; j++) {
                if (alphabet.charAt(i) == message.charAt(j)) {
                    counter++;
                }
            }
            double freq = counter / Double.valueOf(msgLen);
            letterFreq[i] = freq;
            counter = 0;
        }
        return letterFreq;
    }

    /**
     * This method is used to check how similar two arrays are.
     * example -> in "hello" the count for "h" is 1 and the count for "l" is 2.
     *
     * @param frequency   double[] used to get the frequency of the letters in a message
     * @param englishFreq double[] used to get the frequency of each letter in the english language
     * @return sum double chiSquare equation
     */
    public static double chiSquared(double[] frequency, double[] englishFreq) {
        double sum = 0.0;
        double expected = 0.0;
        double observed = 0.0;
        for (int i = 0; i < 26; i++) {
            observed = frequency[i];
            expected = englishFreq[i];
            double delta = observed - expected;
            double similarity = delta * delta / expected;
            sum += similarity;
        }
        return sum;
    }
}
