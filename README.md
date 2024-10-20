# Library Management System

This project implements a **Library Management System** using Object-Oriented Programming (OOP) in Java. The system includes a `Library` class and a `User` class hierarchy with `Student` and `Faculty` as derived classes. It also demonstrates advanced features like inner classes, threading, and exception handling.

## Features

- Add books to the library.
- Register and manage users (students and faculty).
- Borrow and return books with real-time operations using threads.
- Display user information and book details.
- Save and retrieve data from files.
- Handle scenarios like borrowing books that are not available using custom exceptions.

## Class Structure

### 1. Library Class 

The `Library` class manages the collection of books, user registrations, and the lending/return process.

#### Members

- `ArrayList<User> users`: A list of users registered in the library.
- `HashMap<String, Book> books`: A map of books available in the library.
- `HashMap<User, String> borrowedBookData`: A map of borrowed books with user object and the corresponding book detail.

#### Methods

- `ArrayList<User> getUsers()`: To get the information about all the registered users.
- `HashMap<String, Book> getBooks()`: To get the information about all the available books in the library.

### 2. Inner Class Inventory

The `Inventory` class represents the Inventory of the library.

#### Members

- No private members for Inner class Inventory.

#### Methods

- `void addBook(String title, String author, int copies)`: Function to add books in the inventory of library with corresponding details.
- `void removeBook(String title)`: Function to remove perticular book from the library inventory.
- `isAvailable(String title)`: Function to check whether the perticular book is available in the library inventory or not.
- `borrowBook(String title, User user)`: Function to borrow the book from library.
- `returnBook(String title, User user)`: Function to return the book.
  
### 2. Book Class

The `Book` class represents a book in the library.

#### Members

- `String title`: Title of the book.
- `String author`: The name of the author.
- `int copies`: Number of copies available in the library.

#### Methods

- `String getTitle()`: Retrieves the title of the book.
- `String getAuthor()`: Retrieves the author's name.
- `int getCopies()`: Retrieves the available quantity of the book.
- `void decreaseCopies()` and `void increaseCopies()`: Updates the quantity of the book when the user borrows or returns the book.

### 3. User Class

The `User` class is the base class for library users, with common attributes for students and faculty.

#### Members

- `String name`: The name of the user.

#### Methods

- `String getName()`: Retrieves the user's name.
- `boolean equals(Object obj)`: Overridden default method equals() to compare two User objects. 

### 4. Student Class

The `Student` class inherits from `User`, representing student users.

#### Members
- `String enrollmentNo`: The student's enrollment number.
- `String department`: The department of the student.

#### Methods

- Inherits all methods from `User`.

### 5. Faculty Class

The `Faculty` class inherits from `User`, representing faculty users.

#### Members
- `String facultyId`: The faculty's identification number.
- `String department`: The department of the faculty.
  
#### Methods

- Inherits all methods from `User`.

### 6. FileManager Class

The `FileManager` class handles file I/O operations for saving and loading user data, book records, and borrowed book information.

#### Methods

- `void saveData()`: Saves the current library and user data to files.
- `void loadData()`: Loads library and user data from files.

### 7. BorrowTask and ReturnTask Classes

The `BorrowTask` and `ReturnTask` classes implement `Runnable` to allow multiple users to borrow and return books concurrently using threads.

#### Methods

- `void run()`: Executes the borrow or return operation in a separate thread.

### 8. BookNotAvailableException Class

A custom exception class for handling cases when a book is not available for borrowing.

#### Methods

- `BookNotAvailableException(String message)`: Constructor to display an appropriate message when the exception is thrown.

# Application of OOP Principles

1. **Encapsulation**: 
   - **Definition**: Wrapping data and methods into a single class.
   - **Example**: The `Book` class encapsulates the properties (`title`, `author`, etc.) and methods (`getTitle()`, `getAuthor()`, & `getCopies()`) for managing book data.

2. **Abstraction**: 
   - **Definition**: Hiding complex details and exposing only necessary information.
   - **Example**: The `Library` class abstracts away the internal operations of managing books and users, providing a simple interface for borrowing and returning books.

3. **Inheritance**: 
   - **Definition**: Reusing code by deriving new classes from existing ones.
   - **Example**: `Student` and `Faculty` classes inherit from the `User` class, gaining access to common properties and methods.

4. **Polymorphism**: 
   - **Definition**: The ability of different classes to provide their own implementation of a method.
   - **Example**: Although not explicitly shown in this project, polymorphism could be applied by overriding methods such as `calculateFine()` in different user types if fine calculations were required.

# Future Enhancements

- **Database Integration**: Integrate a database like MySQL to manage users and books for better data persistence.
- **Graphical User Interface (GUI)**: Add a GUI to enhance user interaction.
- **Fine Calculation**: Implement fine calculation for overdue books based on the number of days a book is kept.
