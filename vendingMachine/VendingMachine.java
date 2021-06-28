import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class VendingMachine {
    private final Press supplier;
    private final double locationFactor;
    private final int size;
    private double cassette;
    private Map<String, Queue<Book>> shelf;

    /**
     * Method to return cassette value
     *
     * @return double value with cassette
     */
    public double getCassette() {
        return this.cassette;
    }

    /**
     * Constructor method for the VendingMachine class
     *
     * @param supplier       Press object
     * @param locationFactor double value for price
     * @param size           int value
     */
    public VendingMachine(Press supplier, double locationFactor, int size) {
        this.supplier = supplier;
        this.locationFactor = locationFactor;
        this.size = size;
        this.shelf = this.supplier.getShelf();
    }

    /**
     * Method for adding money to the cassette
     *
     * @param coin double value of the coin inserted
     */
    public void insertCoin(double coin) {
        // if not a valid coin throw an exception
        if (coin == 0.01 || coin == 0.02 || coin == 0.05 || coin == 0.1 || coin == 0.2 || coin == 0.5 || coin == 1 || coin == 2) {
            this.cassette += coin;
        } else {
            throw new IllegalArgumentException(
                    "The given coin is not accepted \nAccepted values are 0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1, 2");
        }
    }

    /**
     * Method to get cassette value and empty it after
     *
     * @return double containing the value of the cassette
     */
    public double returnCoins() {
        double originalValue = this.cassette;
        this.cassette = 0;
        return originalValue;
    }

    /**
     * Method to buy a book
     *
     * @param bookID String value with the id of the book
     * @return Book object
     */
    public Book buyBook(String bookID) {
        Book sold;
        // get a Book object so that we can get pages value
        Book book = this.supplier.getBook(bookID);
        // possible books to be able to check if book requested can be sold
        List<String> possibleBooks = supplier.getCatalogue();
        // if book requested exists vending machine sells it
        if (possibleBooks.contains(bookID)) {
            double price = book.getPages() * locationFactor;
            if (this.cassette >= price) {
                LinkedList<Book> queue = (LinkedList<Book>) this.shelf.get(bookID);
                sold = queue.getFirst();
                queue.removeFirst();
                if (queue.isEmpty()) {
                    this.supplier.request(bookID, this.size);
                }
                this.cassette = this.cassette - price;
            } else {
                // if money inserted is less than book's price show error message
                throw new CassetteException("Price of the books exceeds money given");
            }
        } else {
            // if book requested doesn't exist show error message
            throw new IllegalArgumentException("The book requested does not exist");
        }
        return sold;
    }
}
