public class Book {
    private final String title;
    private final String author;
    private final String content;
    private final int edition;

    /**
     * Constructor method of the class Book
     * 
     * @param t String contains book's title
     * @param a String contains book's author
     * @param c String contains book's content
     * @param e int contains book's edition
     */
    public Book(String t, String a, String c, int e) {
        this.title = t;
        this.author = a;
        this.content = c;
        this.edition = e;
    }

    /**
     * Method to get the book's title
     * 
     * @return String the book's title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Method to get the book's author
     * 
     * @return String the book's author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Method to get the book's content
     * 
     * @return String the book's content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Method to get the book's edition
     * 
     * @return int the book's edition
     */
    public int getEdition() {
        return this.edition;
    }

    /**
     * Method for getting the book's number of pages
     * 
     * @return int book's number of Pages
     */
    public int getPages() {
        int numberOfPages;
        int numberOfCharacters = this.content.length();
        numberOfPages = numberOfCharacters / 800;
        // if number of pages has decimal numbers we round it up.
        if (numberOfPages % 2 != 0) {
            // cast to int the value of round number
            numberOfPages = (int) Math.ceil(numberOfPages);
        }
        return numberOfPages;
    }

    /**
     * Method for getting a String with the book's information.
     * 
     * @return String with book's information
     */
    public String toString() {
        return "Title: " + this.title + "\n" + "Author: " + this.author + "\n" + "Edition: " + this.edition + "\n"
                + "Pages: " + getPages();
    }
}