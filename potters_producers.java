public class sgjgarri_201504029 {
    // create objects of each potter, shelf and packer
    public static void main(String[] args) {
        Shelf shelf = new Shelf();
        Potters ginny = new Potters(shelf, 5, "Harry");
        Potters harry = new Potters(shelf, 6, "Ginny");
        Albus albus = new Albus(shelf);
        // start the potters and packers
        ginny.start();
        harry.start();
        albus.start();
    }
}

class Potters extends Thread {
    private Shelf shelf;
    private int rate;
    private String name;
    private int count = 1;

    /**
     * load name/rate/shelf into variables so we can use later
     * @param sh   Shelf object
     * @param time int value for how long it takes to make a pot
     * @param n    String value to assign a name to the potter
     */
    public Potters(Shelf sh, int time, String n) {
        this.rate = time == 5 ? 500 : 600;
        this.shelf = sh;
        this.name = n;
    }

    // method that makes potter make pots
    public void run() {
        System.out.println(name + " has started");
        // while potter doesn't make 10 pots keep making them
        while (count <= 10) {
            try {
                System.out.println(name + " is making a pot");
                sleep(rate);
            } catch (InterruptedException e) { e.printStackTrace(); }
            // after the "n minutes" put a pot in the shelf
            shelf.insert();
            System.out.println(name + " has put a pot on the shelf");
            count++;
        }
    }
}

class Albus extends Thread {
    private Shelf shelf;
    private int count = 1;

    /**
     * load shelf into variables so we can use later
     * @param sh Shelf object
     */
    public Albus(Shelf sh) {
        this.shelf = sh;
    }

    // method that makes packers pack pots
    public void run() {
        System.out.println("Albus has started");
        // while packer doesn't pack 20 pots keep packing them
        while (count <= 20) {
            try {
                System.out.println("Albus is ready to pack");
                sleep(400);
            } catch (InterruptedException e) { e.printStackTrace(); }
            // after the "4 minutes" put a pot in the shelf
            shelf.remove();
            System.out.println("Albus has packed pot " + count);
            count++;
        }
    }
}

class Shelf {
    private volatile int pots = 0;

    // while shelf is equal to 5 (max storage) wait
    public synchronized void insert() {
        while (pots == 5) {
            System.out.println("Shelf is full (Waiting...)");
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        // if shelf not maxed allow inserting
        pots++;
        System.out.println("Potters inserted a pot and shelf now has " + pots + " pots");
        notifyAll();
    }

    // while shelf equal to 0 (is empty) wait
    public synchronized void remove() {
        while (pots == 0) {
            System.out.println("Shelf is empty (Waiting...)");
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        // if shelf not maxed allow removing
        pots--;
        System.out.println("Potters removed a pot and shelf now has " + pots + " pots");
        notifyAll();
    }
}