package task5;

import java.io.*;
import java.util.Scanner;

public class ScannerPrintWriterExample {

    public void processFile(String inputResourceName, String outputFileName) {
        Scanner scanner = null;
        PrintWriter printWriter = null;

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputResourceName);
            if (inputStream == null) {
                throw new FileNotFoundException("Файл '" + inputResourceName + "' не найден в ресурсах.");
            }

            scanner = new Scanner(inputStream);

            String outputPath = "src/main/resources/" + outputFileName;
            printWriter = new PrintWriter(new FileWriter(outputPath));

            while (scanner.hasNext()) {
                String line = scanner.next();
                printWriter.println(line.toUpperCase());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода - " + e.getMessage());
        } finally {
            if (scanner != null) {
                try {
                    scanner.close();
                } catch (Exception e) {
                    System.err.println("Ошибка при закрытии Scanner - " + e.getMessage());
                }
            }

            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    System.err.println("Ошибка при закрытии PrintWriter - " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        ScannerPrintWriterExample example = new ScannerPrintWriterExample();

        String inputResourceName = "task5/input.txt";
        String outputFileName = "task5/output.txt";

        example.processFile(inputResourceName, outputFileName);
    }
}