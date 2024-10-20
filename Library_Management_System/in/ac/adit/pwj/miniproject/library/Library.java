package in.ac.adit.pwj.miniproject.library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    protected ArrayList<User> users = new ArrayList<>();
    protected HashMap<String, Book> books = new HashMap<>();
    protected HashMap<User, String> BorrowedBookData = new HashMap<>();

    Library() {
        users = FileManager.loadUserDataToArrayList();
        BorrowedBookData = FileManager.loadBorrowedBookData();
        try {
            books = FileManager.loadBooksFromFile();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Inner class for managing book inventory
    public class Inventory {
        public void addBook(String title, String author, int copies) {
            if (books.containsKey(title)) {
                Book B = books.get(title);
                B.increaseCopies();
                books.put(title, B);
            }
            else {
                books.put(title, new Book(title, author, copies));
            }
            try {
                FileManager.saveBooksToFile(books); // Save updated books after adding
            } catch (IOException e) {
                System.out.println("Error while saving book data: " + e.getMessage());
            }
        }
    
        public void removeBook(String title) {
            books.remove(title);
            try {
                FileManager.saveBooksToFile(books); // Save updated books after removing
            } catch (IOException e) {
                System.out.println("Error while saving book data: " + e.getMessage());
            }
        }

        public boolean isAvailable(String title) {
            return books.containsKey(title) && books.get(title).getCopies() > 0;
        }

        public void borrowBook(String title, User user) throws BookNotAvailableException {
            if (!BorrowedBookData.containsKey(user)) {
                if (!isAvailable(title)) {
                    throw new BookNotAvailableException("Book " + title + " is not available.");
                }
                else {
                    books.get(title).decreaseCopies();
                    BorrowedBookData.put(user, title);
                }
            }
            else {
                // User has already borrowed a book...!
            }
        }

        public void returnBook(String title, User user) {
            if (books.containsKey(title)) {
                books.get(title).increaseCopies();
                BorrowedBookData.remove(user, title);
            }
        }
    }

    public void addUser(User user) {
        if (!users.contains(user)) { // Now the list is not reset on every call
            users.add(user);
            FileManager.saveUserDataToFile(users); // Save only after adding a new user
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public HashMap<String, Book> getBooks() {
        return books;
    }
}