package ru.aston.homework_05.generators;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCollector<T> extends BaseCollectionGenerator<T> {
    private final String filename;

    public FileCollector(String filename) {
        this.filename = filename;
    }

    @Override
    public List<T> generate() {
        try {
            return readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<T> readFile() throws IOException {
        File file = new File(filename);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException("json файл не найден");
        } catch (IOException e) {
            throw new IOException("Ошибка чтения json файла");
        }
    }
}
