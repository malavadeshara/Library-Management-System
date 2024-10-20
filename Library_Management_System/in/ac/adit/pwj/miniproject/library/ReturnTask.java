package in.ac.adit.pwj.miniproject.library;

public class ReturnTask implements Runnable {
    private Library.Inventory inventory;
    private String bookTitle;
    private User user;

    public ReturnTask(Library.Inventory inventory, String bookTitle, User user) {
        this.inventory = inventory;
        this.bookTitle = bookTitle;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            inventory.returnBook(bookTitle, user);
            System.out.println(Thread.currentThread().getName() + " returned " + bookTitle);
        } finally {
            //do nothing...!
        }
    }
}
