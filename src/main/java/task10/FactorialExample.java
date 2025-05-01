package task10;

public class FactorialExample {

    public static void main(String[] args) {
        System.out.println("Результат: " + factorial(3));
    }

    public static int factorial(int n) {
        if (n == 0) {
            // Вывод трассировки стека перед возвратом из базового случая
            Throwable stackTrace = new Throwable("Трассировка стека для factorial(" + n + ")");
            stackTrace.printStackTrace();
            return 1;
        }

        int result = n * factorial(n - 1);

        // Вывод трассировки стека перед возвратом из рекурсивного вызова
        Throwable stackTrace = new Throwable("Трассировка стека для factorial(" + n + ")");
        stackTrace.printStackTrace();

        return result;
    }
}