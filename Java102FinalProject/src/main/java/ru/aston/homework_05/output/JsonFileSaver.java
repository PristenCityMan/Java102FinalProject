package ru.aston.homework_05.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFileSaver {
    private final ObjectMapper objectMapper;

    public JsonFileSaver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> void saveToFile(String filePath, List<T> newData, Class<T> elementType) {
        if (newData == null || newData.isEmpty()) {
            System.out.println("Нет данных для сохранения.");
            return;
        }
        try {
            Path path = Path.of(filePath);
            List<T> allData = new ArrayList<>();

            if (Files.exists(path)) {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementType);
                List<T> existing = objectMapper.readValue(path.toFile(), listType);
                allData.addAll(existing);
            } else {
                System.out.println("Ошибка: Файл не найден: " + path);
                System.out.println("Будет создан новый файл");
            }
            allData.addAll(newData);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), allData);
            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка: При работе с файлом возникла ошибка: " + e.getMessage());
        }
    }
}
