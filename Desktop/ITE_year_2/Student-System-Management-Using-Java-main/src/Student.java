import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Student {
    int id = 0;
    String name = "";
    char gender = ' ';
    int age = 0;
    String course = "";
    String classRoom = "";
    String contact = "";

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    // read char
    static char readChar(){
        while(true){
            String text = scanner.nextLine().trim();
            if(!text.isEmpty()){
                return Character.toUpperCase(text.charAt(0));
            }
            System.out.println("Please enter (M/F) : ");
        }
    }
    // check duplicate id
    static boolean isDuplicate(int id){
        for(Student student : students){
            if(student.id == id){
                return true;
            }
        }
        return false;
    }


    // input information
    public void inputStudent(){
        System.out.print("ID : ");
        this.id = scanner.nextInt();
        while(isDuplicate(this.id)){
            System.out.println("This ID already exits. Student can't added!");
            System.out.print("ID : ");
            this.id = scanner.nextInt();
        }
        scanner.nextLine();
        System.out.print("Name : ");
        this.name = scanner.nextLine();
        System.out.print("Gender (M/F) : ");
        this.gender = readChar();
        System.out.print("Age : ");
        this.age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Course : ");
        this.course = scanner.nextLine();
        System.out.print("Classroom : ");
        this.classRoom = scanner.nextLine();
        System.out.print("Contact : ");
        this.contact = scanner.nextLine();
    }

    // add student
    static void addStudent(){
        System.out.println("----------<{ Add Student >}----------");
        Student student = new Student();
        student.inputStudent();
        students.add(student);
        System.out.println();
        System.out.println("Add student successfully!\n");
        System.out.println("-------------------------------------");
    }

    // header for display list all student
    static void header(){
        System.out.printf("%-7s %-20s %-11s %-8s %-18s %-14s %-12s%n", "ID", "Name", "Gender", "Age", "Course", "Classroom", "Contact");
    }

    // display all information for all student
    public void display(){
        System.out.printf("%-7d %-20s %-11c %-8d %-18s %-14s %-12s%n", this.id, this.name, this.gender, this.age, this.course, this.classRoom, this.contact);
    }

    // display all student that have
    static void listAllStudent(){
        System.out.println("----------{ List All Student }>----------");
        if(students.isEmpty()){
            System.out.println("No student yet!");
        }
        else{
            header();
            for(Student student : students){
                student.display();
            }
            System.out.println("------------------------------------------");
        }
    }

    // menu of search function
    static void searchMenu(){
        System.out.println("----------<{ Search Student }>----------");
        System.out.println("[1]. Search by ID");
        System.out.println("[2]. Search by Name");
        System.out.println("----------------------------------------");
    }

    // search student by id and by name
    static void searchStudent(){
        boolean found = false;
        searchMenu();
        System.out.print("Enter your choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1 -> {
                System.out.println("----------<{ Search by ID }>----------");
                System.out.print("Enter ID to search : ");
                int id = scanner.nextInt();
                for(Student student : students){
                    if(student.id == id){
                        found = true;
                        Student.header();
                        student.display();
                    }
                }
                if(!found){
                    System.out.println("ID : " + id + " not found!");
                }
            }
            case 2 -> {
                System.out.println("----------<{ Search by Name }>----------");
                System.out.print("Enter Name to search : ");
                String name = scanner.nextLine();
                for(Student student : students){
                    if(student.name.equals(name)){
                        found = true;
                        Student.header();
                        student.display();
                    }
                }
                if(!found){
                    System.out.println("Name : " + name + " not found!");
                }
            }
            default -> System.out.println("Invalid choice. Please enter only 1-2!");

        }
        System.out.println("----------------------------------------");
    }

    // edit menu
    static void editMenu(){
        System.out.println("----------<{ Edit Student }>----------");
        System.out.println("[1]. Edit by ID");
        System.out.println("[2]. Edit by Name");
        System.out.println("--------------------------------------");
    }

    // form edit
    static void menuForEdit(){
        System.out.println("-----<{ Menu For Edit }>-----");
        System.out.println("[1]. Edit ID");
        System.out.println("[2]. Edit Name");
        System.out.println("[3]. Edit Gender");
        System.out.println("[4]. Edit Age");
        System.out.println("[5]. Edit Course");
        System.out.println("[6]. Edit Classroom");
        System.out.println("[7]. Edit Contact");
        System.out.println("-----------------------------");
    }

    // edit function of student
    static void editStudent(){
        boolean found = false;
        editMenu();
        System.out.print("Enter your choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){

            // edit by id
            case 1 -> {
                System.out.println("------------<{ Edit by ID }>----------");
                System.out.print("Enter ID to edit : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                for(Student student : students){
                    if(student.id == id){
                        found = true;
                        header();
                        student.display();
                        menuForEdit();
                        System.out.print("Enter your choice : ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch(choice){
                            case 1 -> {
                                System.out.print("Enter new ID : ");
                                student.id = scanner.nextInt();
                                scanner.nextLine();
                            }
                            case 2 -> {
                                System.out.print("Enter new Name : ");
                                student.name = scanner.nextLine();
                            }
                            case 3 -> {
                                System.out.print("Enter new Gender : ");
                                student.gender = scanner.next().toUpperCase().charAt(0);
                                while(student.gender != 'M' && student.gender != 'F'){
                                    System.out.println("Please enter only (M = Male or F = Female). Please try again!");
                                    System.out.print("Gender (M/F) : ");
                                    student.gender = scanner.next().toUpperCase().charAt(0);
                                }
                            }
                            case 4 -> {
                                System.out.print("Enter new Age : ");
                                student.age = scanner.nextInt();
                                scanner.nextLine();
                            }
                            case 5 -> {
                                System.out.print("Enter new Course : ");
                                student.course = scanner.nextLine();
                            }
                            case 6 -> {
                                System.out.print("Enter new Classroom : ");
                                student.classRoom = scanner.nextLine();
                            }
                            case 7 -> {
                                System.out.print("Enter new Contact : ");
                                student.contact = scanner.nextLine();
                            }
                            default -> System.out.println("Invalid choice. Please enter only number 1-7!");
                        }
                    }
                }
                if(!found){
                    System.out.println("ID : " + id + " not found!");
                }
                else{
                    System.out.println();
                    System.out.println("Edit successfully!\n");
                }
            }

            // edit by name
            case 2 -> {
                System.out.println("------------<{ Edit by Name }>----------");
                System.out.print("Enter Name to edit : ");
                String name = scanner.nextLine();
                for(Student student : students){
                    if(student.name.equals(name)){
                        found = true;
                        header();
                        student.display();
                        menuForEdit();
                        System.out.print("Enter your choice : ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch(choice){
                            case 1 -> {
                                System.out.print("Enter new ID : ");
                                student.id = scanner.nextInt();
                                scanner.nextLine();
                            }
                            case 2 -> {
                                System.out.print("Enter new Name : ");
                                student.name = scanner.nextLine();
                            }
                            case 3 -> {
                                System.out.print("Enter new Gender : ");
                                student.gender = scanner.next().toUpperCase().charAt(0);
                                while(student.gender != 'M' && student.gender != 'F'){
                                    System.out.println("Please enter only (M = Male or F = Female). Please try again!");
                                    System.out.print("Gender (M/F) : ");
                                    student.gender = scanner.next().toUpperCase().charAt(0);
                                }
                            }
                            case 4 -> {
                                System.out.print("Enter new Age : ");
                                student.age = scanner.nextInt();
                                scanner.nextLine();
                            }
                            case 5 -> {
                                System.out.print("Enter new Course : ");
                                student.course = scanner.nextLine();
                            }
                            case 6 -> {
                                System.out.print("Enter new Classroom : ");
                                student.classRoom = scanner.nextLine();
                            }
                            case 7 -> {
                                System.out.print("Enter new Contact : ");
                                student.contact = scanner.nextLine();
                            }
                            default -> System.out.println("Invalid choice. Please enter only number 1-7!");
                        }
                    }
                }
                if(!found){
                    System.out.println("Name : " + name + " not found!");
                }
                else{
                    System.out.println();
                    System.out.println("Edit successfully!\n");
                }
            }
            default -> System.out.println("Invalid choice. Please enter only number 1-2!");
        }
        System.out.println("--------------------------------------");
    }

    // delete menu
    static void deleteMenu(){
        System.out.println("----------<{ Delete Student }>----------");
        System.out.println("[1]. Delete by ID");
        System.out.println("[2]. Delete by Name");
        System.out.println("----------------------------------------");
    }

    // delete function
    static void deleteStudent(){
        boolean found = false;
        deleteMenu();
        System.out.print("Enter your choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Student.listAllStudent();
        switch(choice){
            case 1 -> {
                System.out.println("-----<{ Delete by ID }>-----");
                System.out.print("Enter ID to Delete : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                found = students.removeIf(student -> student.id == id);
                if(!found){
                    System.out.println("ID -> " + id + " not found!");
                }
                else{
                    System.out.println();
                    System.out.println("Delete student successfully!\n");
                }
            }
            case 2 -> {
                System.out.println("-----<{ Delete by Name }>-----");
                System.out.print("Enter Name to Delete : ");
                String name = scanner.nextLine();
                found = students.removeIf(student -> student.name.equals(name));
                if(!found){
                    System.out.println("Name -> " + name + " not found!");
                }
                else{
                    System.out.println();
                    System.out.println("Delete student successfully!\n");
                }
            }
            default -> System.out.println("Invalid choice. Please enter only number 1-2!");
        }
        System.out.println("----------------------------------------");
    }
    static void saveStudent(){
        System.out.println("----------<{ Save Student }>----------");
        String filePath = "Student-Management-System.txt";
        if(students.isEmpty()){
            System.out.println("Not Student yet!");
        }
        else{
            try(FileWriter writer = new FileWriter(filePath)) {
                writer.write(String.format("%-7s %-20s %-11s %-8s %-18s %-14s %-12s%n", "ID", "Name", "Gender", "Age", "Course", "Classroom", "Contact"));
                for (Student student : students) {
                    writer.write(String.format("%-7d %-20s %-11c %-8d %-18s %-14s %-12s%n", student.id, student.name, student.gender, student.age, student.course, student.classRoom, student.contact));
                }
                System.out.println();
                System.out.println("Save student successfully!\n");
            }
            catch(IOException e){
                System.out.println("Something went wrong!");
            }
        }
        System.out.println("--------------------------------------");
    }
    static void loadFromFile(){
        String filePath = "Student-Management-System.txt";
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            reader.readLine();
            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()){
                    continue;
                }
                line = String.format("%-96s", line);

                Student student = new Student();
                student.id = Integer.parseInt(line.substring(0, 7).trim());
                student.name = line.substring(8, 28).trim();
                student.gender = line.substring(29, 40).trim().charAt(0);
                student.age = Integer.parseInt(line.substring(41, 49).trim());
                student.course = line.substring(50, 68).trim();
                student.classRoom = line.substring(69, 83).trim();
                student.contact = line.substring(84, 96).trim();

                students.add(student);

            }
        }
        catch(FileNotFoundException e){
            System.out.println("Could not found file!");
        }
        catch(IOException | NumberFormatException e){
            System.out.println("Could load file : " + e.getMessage());
        }
    }

    // sort student
    static void sortMenu(){
        System.out.println("----------<{ Sort List of Student }>----------");
        System.out.println("[1]. Sort by ID");
        System.out.println("[2]. Sort by Name");
        System.out.println("----------------------------------------------");
    }
    static void sortStudent(){
        if(students.isEmpty()){
            System.out.println("Not Student yet!");
            return;
        }
        sortMenu();
        System.out.print("Enter your choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1 -> {
                students.sort((studentA, studentB) -> Integer.compare(studentA.id, studentB.id));
                Student.listAllStudent();
            }
            case 2 -> {
                students.sort((studentA, studentB) -> studentA.name.compareToIgnoreCase(studentB.name));
                Student.listAllStudent();
            }
            default -> System.out.println("Invalid choice. Please enter only number 1-2!");
        }
        System.out.println("----------------------------------------------");
    }
}
