package task12;

import java.util.Arrays;
import java.util.Random;

public class MinFinder {

    public static void main(String[] args) {
        int[] largeArray = generateLargeArray(1_000_000);

        long startTime = System.nanoTime();
        int result1 = min(largeArray);
        long endTime = System.nanoTime();
        System.out.println("Время с утверждениями: " + (endTime - startTime) / 1e6 + " мс");

        startTime = System.nanoTime();
        int result2 = min(largeArray);
        endTime = System.nanoTime();
        System.out.println("Время без утверждений: " + (endTime - startTime) / 1e6 + " мс");
    }

    public static int min(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        int min = Arrays.stream(values).min().getAsInt();
        assert isMinValid(values, min) : "Некорректный минимум: " + min;
        return min;
    }

    private static boolean isMinValid(int[] values, int min) {
        return Arrays.stream(values).allMatch(v -> v >= min);
    }

    private static int[] generateLargeArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}