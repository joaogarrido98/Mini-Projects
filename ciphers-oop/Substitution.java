public abstract class Substitution implements Cipher {
    /**
     * 
     * @param c character to be encrypted
     */
    public abstract char encrypt(char c);

    /**
     * 
     * @param c character to be decrypted
     */
    public abstract char decrypt(char c);

    /**
     *
     * @param plaintext message to be encrypted
     * @return encrypted message
     */
    public String encrypt(String plaintext) {
        String encrypted = "";
        // go to each character of the plaintext and call the encrypt method on it
        for (int i = 0; i < plaintext.length(); i++) {
            encrypted += encrypt(plaintext.charAt(i));
        }
        return encrypted;
    }

    /**
     *
     * @param cryptotext message to be decrypted
     * @return decrypted message
     */
    public String decrypt(String cryptotext) {
        String decrypted = "";
        // go to each character of the plaintext and call the decrypt method on it
        for (int i = 0; i < cryptotext.length(); i++) {
            decrypted += decrypt(cryptotext.charAt(i));
        }
        return decrypted;
    }
}