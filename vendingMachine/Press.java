import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Press {
    private final int booksPerEdition;
    private final Map<String, Integer> edition = new HashMap<>();// Map that contains the edition of each
                                                                 // Book
    private final Map<String, Queue<Book>> shelf = new HashMap<>();// Map that contains the queue of each Book
    private final String path;// global variable with the path
    private final HashMap<String, String[]> info = new HashMap<>();// global hashmap with title,
    // content and author of the book

    /**
     * Constructor method for Press class
     *
     * @param pathToBookDir   String with the path to the books
     * @param booksPerEdition int book's edition
     * @throws IOException
     */
    public Press(String pathToBookDir, int booksPerEdition) throws IOException {
        this.booksPerEdition = booksPerEdition;
        this.path = pathToBookDir;

        try {
            // get the separator according to the platform
            String sep = File.separator;

            // get all file names (books) from directory
            List<String> files = getCatalogue();

            // for every file in directory get the text inside
            for (String book : files) {
                // initialize variables that will be used to create Book objects
                String author = "";
                String title = "";
                StringBuilder content = new StringBuilder();

                // get all the lines inside of the book
                File file = new File(this.path + sep + book);
                ArrayList<String> bookInfo = (ArrayList<String>) Files.readAllLines(file.toPath());
                StringBuilder bookAsString = new StringBuilder();
                for (String s : bookInfo) {
                    bookAsString.append(s);
                }

                if (bookAsString.toString().contains("Title: ") && bookAsString.toString().contains("Author: ")
                        && bookAsString.toString().contains("*** START OF")) {
                    int count = 0;
                    for (String line : bookInfo) {
                        count++;
                        String substring = line.substring(line.indexOf(':') + 1);
                        if (line.contains("Title:")) {
                            title = substring;
                        }
                        if (line.contains("Author:")) {
                            author = substring;
                        }
                        if (line.contains("*** START OF")) {
                            content = new StringBuilder();
                            List<String> restFile = bookInfo.subList(count, bookInfo.size());
                            for (String s : restFile) {
                                content.append(" ").append(s);
                            }
                        }
                    }

                    // add title, content, author to an array so it can be used again
                    // without getting the files again
                    String[] addToInfo = { title, author, content.toString() };
                    info.put(book, addToInfo);

                    // create a queue of books depending on how many books we have
                    // for each edition
                    LinkedList<Book> queue = (LinkedList<Book>) this.print(book);
                    shelf.put(book, queue);

                    // put edition 1 into variable edition because we printed one edition
                    edition.put(book, 1);
                } else {
                    throw new IOException("Content of file not correct");
                }

            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * This method prints new books of a certain book with a new edition
     *
     * @param bookID String containing the book to be printed
     * @return List containing the new books
     */
    private List<Book> print(String bookID) {
        // get title, content and author of the book
        String[] infos = info.get(bookID);
        int newEdition;
        List<Book> books = new LinkedList<>();
        // if book exists increment edition and create new books with that edition
        if (checkId(bookID)) {
            // get current edition
            if (this.edition.containsKey(bookID)) {
                this.edition.put(bookID, this.edition.get(bookID) + 1);
                newEdition = this.edition.get(bookID);
            } else {
                newEdition = 1;
            }
            // create as many books as books per edition says
            for (int i = 0; i < this.booksPerEdition; i++) {
                Book book = new Book(infos[0], infos[1], infos[2], newEdition);
                books.add(book);
            }
        } else {
            throw new IllegalArgumentException("The book requested does not exist");
        }
        return books;
    }

    /**
     * create List that is filled with the values of String array with the values in
     * the directory
     *
     * @return List with ID's of the books in directory
     */
    public List<String> getCatalogue() {
        File dir = new File(this.path);
        // get files in directory
        String[] files = dir.list();
        return Arrays.asList(files);
    }

    /**
     * Method to get books according to a certain amount
     *
     * @param bookID String containing id of the book
     * @param amount int containing the amount of books
     * @return List with book objects
     */
    public List<Book> request(String bookID, int amount) {
        List<Book> books = new LinkedList<>();
        if (checkId(bookID)) {
            // get a book n amount of times
            for (int i = 0; i < amount; i++) {
                // get the current shelf
                LinkedList<Book> booksQueue = (LinkedList<Book>) this.shelf.get(bookID);
                if (booksQueue.isEmpty()) {
                    // if shelf queue is empty we create a new print
                    List<Book> newEditions = this.print(bookID);
                    // we put every book we printed in the shelf again
                    booksQueue.addAll(newEditions);
                    // update shelf
                    shelf.put(bookID, booksQueue);
                }
                // add to the list the first element in the shelf
                // by taking it out of the shelf
                books.add(booksQueue.getFirst());
                booksQueue.removeFirst();
            }
        } else {
            throw new IllegalArgumentException("The book requested does not exist");
        }
        return books;
    }

    /**
     * This method checks if a certain book exists
     *
     * @param bookID String containing the book to be checked
     * @return boolean according if book exists or not
     */
    private boolean checkId(String bookID) {
        boolean exists = false;
        List<String> possibleBooks = getCatalogue();
        // if catalogue contains the bookID then book ID exists
        if (possibleBooks.contains(bookID)) {
            exists = true;
        }
        return exists;
    }

    /**
     * This method returns the shelf
     *
     * @return Map object with shelf so it can be called in vending machine
     */
    public Map<String, Queue<Book>> getShelf() {
        return this.shelf;
    }

    /**
     * This method returns a Book object so that it can be used in a vending machine
     *
     * @param bookID String containing the book's id
     * @return Book object
     */
    public Book getBook(String bookID) {
        String[] infos = this.info.get(bookID);
        return new Book(infos[0], infos[1], infos[2], 1);
    }
}