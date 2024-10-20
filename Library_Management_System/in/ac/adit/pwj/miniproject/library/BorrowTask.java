package in.ac.adit.pwj.miniproject.library;

public class BorrowTask implements Runnable {
    private Library.Inventory inventory;
    private String bookTitle;
    private User user;

    public BorrowTask(Library.Inventory inventory, String bookTitle, User user) {
        this.inventory = inventory;
        this.bookTitle = bookTitle;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            inventory.borrowBook(bookTitle, user);
            System.out.println(Thread.currentThread().getName() + " borrowed " + bookTitle);
        } catch (BookNotAvailableException e) {
            System.out.println(Thread.currentThread().getName() + ": " + e.getMessage());
        }
    }
}