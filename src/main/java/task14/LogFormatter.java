package task14;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogFormatter {

    public static void formatToHtml(List<String> logs, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("<!DOCTYPE html>\n<html>\n<head>\n");
            writer.write("<title>Форматированные логи</title>\n");
            writer.write("<style>\n");
            writer.write("table { width: 100%; border-collapse: collapse; }\n");
            writer.write("th, td { border: 1px solid #ddd; padding: 8px; }\n");
            writer.write("th { background-color: #f2f2f2; }\n");
            writer.write("tr.error { background-color: #ffdddd; }\n");
            writer.write("tr.warning { background-color: #ffffcc; }\n");
            writer.write("tr.info { background-color: #d4edda; }\n");
            writer.write("</style>\n</head>\n<body>\n");

            writer.write("<h2>Протокол работы системы</h2>\n");
            writer.write("<table>\n<thead>\n<tr>\n");
            writer.write("<th>Время</th>\n<th>Уровень</th>\n<th>Сообщение</th>\n");
            writer.write("</tr>\n</thead>\n<tbody>\n");

            for (String log : logs) {
                // Парсинг строки лога формата "[время] УРОВЕНЬ: сообщение"
                String[] parts = log.split("]\\s+", 3);
                if (parts.length < 3) continue;

                String time = parts[0].substring(1);
                String level = parts[1].replace(":", "");
                String message = parts[2];

                String rowClass = "";
                switch (level.toUpperCase()) {
                    case "ERROR": rowClass = "error"; break;
                    case "WARNING": rowClass = "warning"; break;
                    case "INFO": rowClass = "info"; break;
                }

                writer.write(String.format("<tr class='%s'>\n", rowClass));
                writer.write(String.format("<td>%s</td>\n", time));
                writer.write(String.format("<td>%s</td>\n", level));
                writer.write(String.format("<td>%s</td>\n", message));
                writer.write("</tr>\n");
            }

            writer.write("</tbody>\n</table>\n</body>\n</html>");
        }
    }

    public static void main(String[] args) {
        List<String> testLogs = List.of(
                "[2023-10-05 12:34:56] INFO: Запуск системы",
                "[2023-10-05 12:35:01] WARNING: Низкий уровень памяти",
                "[2023-10-05 12:35:07] ERROR: Сбой подключения к БД",
                "[2023-10-05 12:35:15] INFO: Переподключение успешно"
        );

        try {
            formatToHtml(testLogs, "logs.html");
            System.out.println("HTML-файл успешно создан!");
        } catch (IOException e) {
            System.err.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}
