import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Student Grade Calculator
 * Lets the user enter his prelim,
 * midterm, final grades
 * and the program will compute the
 * final rating and save the grades into file
 */
public class Grades{
    public static final double MIN_GRADE = 50;
    public static final String FILE_DIR = "target/records";
    public static final int NUM_OF_SUBJECTS = 64;
    public static final int NUM_OF_TERMS = 3;

    public static void main(String[] args){
        String name;
        String subject;

        String[] subjects = new String[NUM_OF_SUBJECTS];
        double[][] grades = new double[NUM_OF_SUBJECTS][NUM_OF_TERMS];

        String[] terms = {"Prelim", "Midterm", "Finals"};

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        name = scanner.nextLine();

        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name);
        sb.append("\n").append(String.format(
                        "%-20s%-10s%-10s%-10s%-15s\n",
                        "SUBJECTS", "PRELIM", "MIDTERM", "FINAL", "FINAL RATING"
                )
        );

        for(int i = 0; i < NUM_OF_SUBJECTS; i++){
            System.out.print("Enter subject: ");
            subjects[i] = scanner.nextLine();
            sb.append(String.format("%-20s", subjects[i]));

            for(int j = 0; j < NUM_OF_TERMS; j++){
                System.out.print("\t" + terms[j] + ": ");
                try {
                    grades[i][j] = Double.parseDouble(scanner.nextLine());
                    if(grades[i][j] < MIN_GRADE){
                        throw new Exception("Error! No grades lesser than 50.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\tError! Invalid number.");
                    --j;
                    continue;
                } catch(Exception e){
                    System.out.println("\t" + e.getMessage());
                    --j;
                    continue;
                }

                sb.append(String.format("%-10.2f", grades[i][j]));

            }

            sb.append(String.format("%-15.2f\n", getFinalRating(grades[i])));

            System.out.print("Add another subject (y/n): ");
            char choice = scanner.nextLine().charAt(0);

            if(Character.toLowerCase(choice) != 'y')
                break;
        }

        System.out.println(sb);
        writeToFile(name, sb.toString());
    }

    public static double getFinalRating(double[] termGrades){
        double finalRating = termGrades[0] * .3 + termGrades[1] * .3 + termGrades[2] * .4;
        return finalRating;
    }

    public static void writeToFile(String fileName, String data){
        File folder = new File(FILE_DIR);
        if(!folder.exists()){
            folder.mkdirs();
        }
        File file = new File(folder, fileName);
        try ( FileWriter fw = new FileWriter(file)) {
            fw.write(data);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static void readAllFiles(){
        File folder = new File(FILE_DIR);
        File[] studentGradeFiles = folder.listFiles();
        for (File studentGradeFile : studentGradeFiles) {
            if (studentGradeFile.isFile()) {
                readFile(studentGradeFile);
            }
        }
    }

    public static void readFile(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            System.out.println("--------------------------");
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

