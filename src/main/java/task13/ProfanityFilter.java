package task13;

import java.util.regex.Pattern;

public class ProfanityFilter {
    private static final Pattern[] BAD_WORDS = {
            Pattern.compile("\\bсекс\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE),
            Pattern.compile("\\bнаркотики\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE),
            Pattern.compile("\\bC\\+\\+\\b", Pattern.CASE_INSENSITIVE)
    };

    public static boolean containsProfanity(String text) {
        for (Pattern pattern : BAD_WORDS) {
            if (pattern.matcher(text).find()) {
                return true;
            }
        }
        return false;
    }

    public static String censorProfanity(String text) {
        String result = text;
        for (Pattern pattern : BAD_WORDS) {
            result = pattern.matcher(result).replaceAll("****");
        }
        return result;
    }

    public static void main(String[] args) {
        test("Этот текст о сексе и наркотиках содержит C++", true);
        test("СЕКС, Наркотики и c++ тоже блокируются", true);
        test("Безопасный текст про Java и Python", false);
        test("Сексопатология - это наука, но C++ рулит!", true);
    }

    private static void test(String text, boolean expected) {
        boolean detected = containsProfanity(text);
        System.out.printf("Тест: \"%s\"%nРезультат: %s (ожидалось: %s)%n%n",
                text, detected, expected);

        if (detected) {
            System.out.println("Цензурированный текст: " + censorProfanity(text));
        }
    }
}