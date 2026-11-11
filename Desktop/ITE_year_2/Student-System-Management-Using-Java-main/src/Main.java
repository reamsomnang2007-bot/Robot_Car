import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        // student system management
        Scanner scanner = new Scanner(System.in);
        Student.loadFromFile();
        int choice;
        do{
            menu();
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1 -> {
                    Student.addStudent();
                }
                case 2 -> {
                    Student.listAllStudent();
                }
                case 3 -> {
                    Student.searchStudent();
                }
                case 4 -> {
                    Student.editStudent();
                }
                case 5 -> {
                    Student.sortStudent();
                }
                case 6 -> {
                    Student.deleteStudent();
                }
                case 7 -> {
                    Student.saveStudent();
                }
                case 8 -> {
                    System.out.println("Thank for your using. Good bye!");
                }
                default -> System.out.println("Invalid choice. Please enter only number (1-6)!");
            }
        }while(choice != 8);
        scanner.close();

    }
    static void menu(){
        System.out.println("----------<{ Student System Management }>----------");
        System.out.println("[1]. Add Student");
        System.out.println("[2]. List All Students");
        System.out.println("[3]. Search Student");
        System.out.println("[4]. Edit Student");
        System.out.println("[5]. Sort List of Student");
        System.out.println("[6]. Delete Student");
        System.out.println("[7]. Save Student");
        System.out.println("[8]. Exit program");
        System.out.println("---------------------------------------------------");
    }
}