package calculator;

import java.io.*;
import java.util.ArrayList;


public class SumCalculator {

    public ArrayList<Double> readValues(String filename) throws IOException {
        ArrayList<Double> values = new ArrayList<>();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (stream == null)
                throw new FileNotFoundException("");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    double value = Double.parseDouble(line);
                    values.add(value);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Error in " + lineNumber + ": '" + line + "' is not a double.");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File '" + filename + "' not found.");
        }

        return values;
    }

    public double sumOfValues(String filename) throws IOException {
        ArrayList<Double> values = readValues(filename);

        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }

        return sum;
    }

    public static void main(String[] args) {
        SumCalculator calculator = new SumCalculator();
        String filename = "calculator/data.txt";

        try {
            double sum = calculator.sumOfValues(filename);
            System.out.println("File sum: " + sum);
        } catch (NumberFormatException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}