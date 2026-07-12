package ru.aston.homework_05.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.homework_05.models.BaseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCollector<T extends BaseClass> extends BaseCollectionGenerator<T> {
    private final String filename;
    private final Class<T> _class;

    public FileCollector(String filename, Class<T> elementClass) {
        this.filename = filename;
        this._class = elementClass;
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
            return objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, _class));
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException("json файл не найден");
        } catch (IOException e) {
            throw new IOException("Ошибка чтения json файла");
        }
    }
}
