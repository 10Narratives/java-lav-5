package calculator;

import java.io.*;
import java.util.ArrayList;

public class SumCalculatorErrors {

    public static final int SUCCESS = 0;
    public static final int FILE_NOT_FOUND = -1;
    public static final int INVALID_DATA = -2;

    public int readValues(String resourceName, ArrayList<Double> values) {
        // Получаем поток для ресурса
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                return FILE_NOT_FOUND;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

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
                    return INVALID_DATA;
                }
            }
        } catch (IOException e) {
            return -3;
        }

        return SUCCESS;
    }

    public double sumOfValues(String filename, int[] errorCode) {
        ArrayList<Double> values = new ArrayList<>();
        int result = readValues(filename, values);

        if (result != SUCCESS) {
            errorCode[0] = result;
            return 0.0;
        }

        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }

        errorCode[0] = SUCCESS;
        return sum;
    }

    public static void main(String[] args) {
        SumCalculatorErrors example = new SumCalculatorErrors();
        String filename = "calculator/data.txt";
        int[] errorCode = new int[1];

        double sum = example.sumOfValues(filename, errorCode);

        switch (errorCode[0]) {
            case SUCCESS:
                System.out.println("Сумма значений из файла: " + sum);
                break;
            case FILE_NOT_FOUND:
                System.err.println("Ошибка: Файл '" + filename + "' не найден.");
                break;
            case INVALID_DATA:
                System.err.println("Ошибка: В файле содержатся некорректные данные.");
                break;
            default:
                System.err.println("Произошла ошибка при работе с файлом.");
                break;
        }
    }
}