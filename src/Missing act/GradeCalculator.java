import java.io.*;
import java.util.Scanner;

public class GradeCalculator {
    public static final double MIN_GRADE = 50;
    public static final String FILE_DIR = "target/records";
    public static final int MAX_SUBJECTS = 64;
    public static final int NUM_OF_TERMS = 3;

    public static void main(String[] args) {
        Scanner mk = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = mk.nextLine();

        String[] subjects = new String[MAX_SUBJECTS];
        double[][] grades = new double[MAX_SUBJECTS][NUM_OF_TERMS];
        String[] terms = {"Prelim", "Midterm", "Final"};
        int subjectCount = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append(String.format("%-15s%-10s%-10s%-10s%s\n", "SUBJECT", "PRELIM", "MIDTERM", "FINAL", "FINAL RATING"));
        sb.append("-".repeat(55)).append("\n");

        while (subjectCount < MAX_SUBJECTS) {
            System.out.print("Enter subject: ");
            subjects[subjectCount] = mk.nextLine();
            sb.append(String.format("%-15s", subjects[subjectCount]));

            for (int j = 0; j < NUM_OF_TERMS; j++) {
                System.out.print("\t" + terms[j] + ": ");
                try {
                    grades[subjectCount][j] = Double.parseDouble(mk.nextLine());
                    if (grades[subjectCount][j] < MIN_GRADE) {
                        throw new Exception("Error! No grades lower than 50.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\tError! Invalid number.");
                    j--;
                    continue;
                } catch (Exception e) {
                    System.out.println("\t" + e.getMessage());
                    j--;
                    continue;
                }
                sb.append(String.format("%-10.2f", grades[subjectCount][j]));
            }

            double finalRating = calculateFinalRating(grades[subjectCount]);
            sb.append(String.format("%.2f\n", finalRating));

            System.out.print("Add another subject? (y/n): ");
            if (mk.nextLine().equalsIgnoreCase("n")) break;
            subjectCount++;
        }

        System.out.println(sb);
        writeToFile(name, sb.toString());
    }

    public static double calculateFinalRating(double[] termGrades) {
        return termGrades[0] * 0.3 + termGrades[1] * 0.3 + termGrades[2] * 0.4;
    }

    public static void writeToFile(String fileName, String data) {
        File folder = new File(FILE_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, fileName);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(data);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}