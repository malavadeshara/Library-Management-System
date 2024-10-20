package in.ac.adit.pwj.miniproject.library;

import java.io.IOException;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Library.Inventory inventory = library.new Inventory();

        // Add some books to the library
        inventory.addBook("Java Programming", "Author A", 3);
        inventory.addBook("Data Structures", "Author B", 2);

        // Add some users (students and faculty)
        Student student = new Student("Malav", "S123", "Computer Science");
        Faculty faculty = new Faculty("Dr. Smith", "F456", "Mathematics");

        library.addUser(student);
        library.addUser(faculty);

        // Simulate borrowing books with threading
        Thread borrowThread1 = new Thread(new BorrowTask(inventory, "Java Programming", student));
        Thread borrowThread2 = new Thread(new BorrowTask(inventory, "Data Structures", faculty));
        borrowThread1.start();
        borrowThread2.start();

        // Simulate returning books
        Thread returnThread1 = new Thread(new ReturnTask(inventory, "Java Programming", student));
        returnThread1.start();

        try {
            borrowThread1.join();
            borrowThread2.join();
            returnThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Save updated data to file
        FileManager.saveUserDataToFile(library.getUsers());
        try {
            FileManager.saveBooksToFile(library.getBooks());
        } catch (IOException e) {
            System.out.println("Error while saving book data: " + e.getMessage());
        }

        FileManager.saveBorrowedBookData(library.BorrowedBookData);

        // Print the users and books after borrowing and returning
        System.out.println("Registered Users:");
        for (User user : library.getUsers()) {
            System.out.println(user.getName());
        }

        System.out.println("\nBooks in Inventory:");
        for (String title : library.getBooks().keySet()) {
            Book book = library.getBooks().get(title);
            System.out.println(title + " by " + book.getAuthor() + " - " + book.getCopies() + " copies");
        }
    }
}
