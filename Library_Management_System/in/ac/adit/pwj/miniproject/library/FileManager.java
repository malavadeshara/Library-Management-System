package in.ac.adit.pwj.miniproject.library;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {
    public static void saveUserDataToFile(ArrayList<User> users) {
        try (FileWriter studentWriter = new FileWriter("RegisteredStudents.txt", false);
             FileWriter facultyWriter = new FileWriter("RegisteredFaculty.txt", false)) {
             
            for (User user : users) {
                if (user instanceof Student) {
                    Student S = (Student) user;
                    studentWriter.write(S.getName() + "," + S.getEnrollmentNo() + "," + S.getDepartment() + "\n");
                } else if (user instanceof Faculty) {
                    Faculty F = (Faculty) user;
                    facultyWriter.write(F.getName() + "," + F.getFacultyID() + "," + F.getDepartment() + "\n");
                } else {
                    System.out.println("Invalid user type");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while saving user data: " + e.getMessage());
        }
    }

    public static ArrayList<User> loadUserDataToArrayList() {
        ArrayList<User> tempList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader (new FileReader("RegisteredStudents.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] words = line.split(",");
                Student S = new Student(words[0], words[1], words[2]);
                tempList.add(S);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading data : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while loading data : " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("RegisteredFaculty.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] words = line.split(",");
                Faculty F = new Faculty(words[0], words[1], words[2]);
                tempList.add(F);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading data : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while loading data : " + e.getMessage());
        }
        return tempList;
    }

    public static void saveBooksToFile(HashMap<String, Book> books) throws IOException {
        try (FileWriter writer = new FileWriter("BookRecord.txt")) {
            for(String key : books.keySet()) {
                writer.write(key+","+books.get(key).getAuthor()+","+books.get(key).getCopies()+"\n");
            }
        }
    }

    public static HashMap<String, Book> loadBooksFromFile() throws IOException, ClassNotFoundException {
        HashMap<String, Book> tempHashMap = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader("BookRecord.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] Words = line.split(",");
                Book B = new Book(Words[0], Words[1], Integer.parseInt(Words[2]));
                tempHashMap.put(Words[0], B);
            }
        }
        return tempHashMap;
    }

    public static void saveBorrowedBookData(HashMap<User, String> borrowedBookData) {
        try (FileWriter writer = new FileWriter("BorrowedBooksData.txt")) {
            for (User user : borrowedBookData.keySet()) {
                if (user instanceof Student) {
                    Student s = (Student) user;
                    writer.write(s.getUserType() + "," + s.getName() + "," + s.getEnrollmentNo() + "," + s.getDepartment() + "," + borrowedBookData.get(s) + "\n");
                } else {
                    Faculty f = (Faculty) user;
                    writer.write(f.getUserType() + f.getName() + "," + f.getFacultyID() + "," + f.getDepartment() + "," + borrowedBookData.get(f) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }
    
    public static HashMap<User, String> loadBorrowedBookData() {
        HashMap<User, String> tempHashMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader (new FileReader("BorrowedBooksData.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] words = line.split(",");
                if(words[0].equals("S")) {
                    User S = new Student(words[1], words[2], words[3]);
                    tempHashMap.put(S, words[4]);
                }
                else {
                    User F = new Faculty(words[1], words[2], words[3]);
                    tempHashMap.put(F, words[4]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading data : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while loading data : " + e.getMessage());
        }
        return tempHashMap;
    }
}