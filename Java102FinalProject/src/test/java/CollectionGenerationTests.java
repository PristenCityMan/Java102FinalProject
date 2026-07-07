import org.junit.jupiter.api.Test;
import ru.aston.homework_05.generators.BaseCollectionGenerator;
import ru.aston.homework_05.generators.CollectionGeneratorClient;
import ru.aston.homework_05.generators.FileCollector;
import ru.aston.homework_05.generators.RandomCollector;
import ru.aston.homework_05.models.User;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CollectionGenerationTests {
    @Test
    void when_validFileCollector_thenReturnNonEmptyCollection() {
        String fileName = "src/main/resources/users.json";
        BaseCollectionGenerator<User> placeholder = new FileCollector<>(fileName);
        CollectionGeneratorClient<User> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Collection<User> collection = collectionGeneratorClient.get();
        assertFalse(collection.isEmpty());
    }

    @Test
    void when_noFileCollector_thenReturnEmptyCollection() {
        String fileName = "";
        BaseCollectionGenerator<User> placeholder = new FileCollector<>(fileName);
        CollectionGeneratorClient<User> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Collection<User> collection = collectionGeneratorClient.get();
        assertNull(collection);
    }

    @Test
    void when_RandomCollector_thenReturnNonEmptyCollection() {
        Integer size = 100500;
        BaseCollectionGenerator<User> placeholder = new RandomCollector(size);
        CollectionGeneratorClient<User> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Collection<User> collection = collectionGeneratorClient.get();
        assertEquals(size, collection.size());
    }
}
