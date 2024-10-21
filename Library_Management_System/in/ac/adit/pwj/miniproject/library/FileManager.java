package in.ac.adit.pwj.miniproject.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {

    public static void saveUserDataToFile(ArrayList<User> users) {
        // Define the file paths for students and faculty
        File studentFile = new File("RegisteredStudents.txt");
        File facultyFile = new File("RegisteredFaculty.txt");

        try {
            // Check if student file exists ?
            if (studentFile.exists()) {
                // Do Nothing.
                // All Is Well!
            } else {
                // Creating "RegisteredStudents.txt" file because file not exists.
                studentFile.createNewFile();
            }

            // Check if faculty file exists
            if (facultyFile.exists()) {
                // Do Nothing.
                // All Is Well!
            } else {
                // Creating "RegisteredFaculty.txt" file because file not exists.
                facultyFile.createNewFile();
            }

            // Try with resources to ensure proper file handling and closing
            try (FileWriter studentWriter = new FileWriter(studentFile, false);
                 FileWriter facultyWriter = new FileWriter(facultyFile, false)) {

                // Iterate over the users list and write to the respective files
                for (User user : users) {
                    if (user instanceof Student) {
                        Student S = (Student) user;
                        studentWriter.write(S.getName() + "," + S.getEnrollmentNo() + "," + S.getDepartment() + "\n");
                    } else if (user instanceof Faculty) {
                        Faculty F = (Faculty) user;
                        facultyWriter.write(F.getName() + "," + F.getFacultyID() + "," + F.getDepartment() + "\n");
                    } else {
                        // Invalid User Type
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error while saving user data: " + e.getMessage());
        }
    }

    public static ArrayList<User> loadUserDataToArrayList() {
        ArrayList<User> tempList = new ArrayList<>();
        
        // Check if "RegisteredStudents.txt" exists before trying to read it
        File studentFile = new File("RegisteredStudents.txt");
        if (studentFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(studentFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(",");
                    Student S = new Student(words[0], words[1], words[2]);
                    tempList.add(S);
                }
            } catch (IOException e) {
                System.out.println("Error while loading student data: " + e.getMessage());
            }
        }

        // Check if "RegisteredFaculty.txt" exists before trying to read it
        File facultyFile = new File("RegisteredFaculty.txt");
        if (facultyFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(facultyFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(",");
                    Faculty F = new Faculty(words[0], words[1], words[2]);
                    tempList.add(F);
                }
            } catch (IOException e) {
                System.out.println("Error while loading faculty data: " + e.getMessage());
            }
        }

        return tempList;
    }

    public static void saveBooksToFile(HashMap<String, Book> books) throws IOException {
        // Define the file path for the book records
        File bookFile = new File("BookRecord.txt");

        // Check if the file exists
        if (bookFile.exists()) {
            // Do Nothing.
            // All Is Well!
        } else {
            // Creating "BookRecord.txt" file because file not exists.
            bookFile.createNewFile();
        }

        // Try with resources to write the book data into the file
        try (FileWriter writer = new FileWriter(bookFile, false)) {
            for (String key : books.keySet()) {
                Book book = books.get(key);
                writer.write(key + "," + book.getAuthor() + "," + book.getCopies() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error while saving book data: " + e.getMessage());
            throw e; // Rethrow the exception if needed
        }
    }

    public static HashMap<String, Book> loadBooksFromFile() throws IOException, ClassNotFoundException {
        HashMap<String, Book> tempHashMap = new HashMap<>();
        
        // Define the file path for the book records
        File bookFile = new File("BookRecord.txt");

        // Check if the file exists
        if (bookFile.exists()) {
            // Try with resources to read the book data from the file
            try (BufferedReader br = new BufferedReader(new FileReader(bookFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(",");
                    Book B = new Book(words[0], words[1], Integer.parseInt(words[2]));
                    tempHashMap.put(words[0], B); // Store the book in the HashMap
                }
            } catch (IOException e) {
                System.out.println("Error while loading book data: " + e.getMessage());
                throw e; // Rethrow the exception if needed
            }
        }

        return tempHashMap;
    }

    public static void saveBorrowedBookData(HashMap<User, String> borrowedBookData) {
        // Define the file path for the borrowed book records
        File borrowedBooksFile = new File("BorrowedBooksData.txt");

        // Check if the file exists
        if (borrowedBooksFile.exists()) {
            // Do Nothing.
            // All Is Well!
        } else {
            // Creating "BorrowedBooksData.txt" file because file not exists.
            try {
                borrowedBooksFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating the file: " + e.getMessage());
                return;
            }
        }

        // Try with resources to write the borrowed book data into the file
        try (FileWriter writer = new FileWriter(borrowedBooksFile, false)) {
            for (User user : borrowedBookData.keySet()) {
                if (user instanceof Student) {
                    Student s = (Student) user;
                    writer.write(s.getUserType() + "," + s.getName() + "," + s.getEnrollmentNo() + "," + s.getDepartment() + "," + borrowedBookData.get((User)s) + "\n");
                } else if (user instanceof Faculty) {
                    Faculty f = (Faculty) user;
                    writer.write(f.getUserType() + "," + f.getName() + "," + f.getFacultyID() + "," + f.getDepartment() + "," + borrowedBookData.get((User)f) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while saving borrowed book data: " + e.getMessage());
        }
    }
    
    public static HashMap<User, String> loadBorrowedBookData() {
        HashMap<User, String> tempHashMap = new HashMap<>();
        File borrowedBooksFile = new File("BorrowedBooksData.txt");

        // Check if the file exists
        if (!borrowedBooksFile.exists()) {
            return tempHashMap; // Return an empty HashMap if the file does not exist
        }

        // Try with resources to load the borrowed book data from the file
        try (BufferedReader br = new BufferedReader(new FileReader(borrowedBooksFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                if (words[0].equals("S")) {
                    User S = new Student(words[1], words[2], words[3]);
                    tempHashMap.put(S, words[4]); // Store the borrowed book data for Student
                } else {
                    User F = new Faculty(words[1], words[2], words[3]);
                    tempHashMap.put(F, words[4]); // Store the borrowed book data for Faculty
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading borrowed book data: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while loading borrowed book data: " + e.getMessage());
        }

        return tempHashMap;
    }
}