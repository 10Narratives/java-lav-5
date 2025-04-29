package task1;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public ArrayList<Double> readValues(String resourceName) throws IOException {
        ArrayList<Double> values = new ArrayList<>();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (stream == null)
                throw new FileNotFoundException("File not found");
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
                    throw new NumberFormatException("Error in line " + lineNumber + ": '" + line + "' is not a double value.");
                }
            }
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
        Main example = new Main();
        try {
            String resourceName = "task1/data.txt";
            System.out.println(example.readValues(resourceName));
            System.out.println(example.sumOfValues(resourceName));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
