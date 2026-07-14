import org.junit.jupiter.api.Test;
import ru.aston.homework_05.generators.BaseCollectionGenerator;
import ru.aston.homework_05.generators.CollectionGeneratorClient;
import ru.aston.homework_05.generators.FileCollector;
import ru.aston.homework_05.generators.RandomCollector;
import ru.aston.homework_05.models.BaseClass;
import ru.aston.homework_05.models.BaseClassImpl;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionGenerationTests {
    @Test
    void whenDeserializeUsers_givenValidJsonFile_thenCollectionItemNameNotEmpty() {
        String fileName = "src/test/resources/users.json";
        int size = 5;
        BaseCollectionGenerator<User> placeholder = new FileCollector<>(fileName, User.class, size);
        CollectionGeneratorClient<User> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Collection<User> collection = collectionGeneratorClient.get();
        String name = collection.stream()
                .map(User::getName)
                .findAny()
                .orElse(null);
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @Test
    void whenDeserializingToList_noFileCollector_thenReturnEmptyCollection() {
        String fileName = "";
        int size = 5;
        BaseCollectionGenerator<BaseClass> placeholder = new FileCollector<>(fileName, BaseClass.class, size);
        CollectionGeneratorClient<BaseClass> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Collection<BaseClass> collection = collectionGeneratorClient.get();
        assertTrue(collection.isEmpty());
    }

    @Test
    void whenDeserializeWorkspaces_givenValidJsonFile_thenCollectionItemNameNotEmpty() {
        String fileName = "src/test/resources/workspaces.json";
        int size = 5;
        BaseCollectionGenerator<WorkSpace> placeholder = new FileCollector<>(fileName, WorkSpace.class, size);
        CollectionGeneratorClient<WorkSpace> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Collection<WorkSpace> collection = collectionGeneratorClient.get();
        String name = collection.stream()
                .map(WorkSpace::getName)
                .findAny()
                .orElse(null);
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @Test
    void when_RandomCollector_thenReturnNonEmptyCollection() {
        int size = 100500;
        BaseCollectionGenerator<BaseClass> placeholder = new RandomCollector<>(size, BaseClassImpl.class.getSimpleName());
        CollectionGeneratorClient<BaseClass> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Collection<BaseClass> collection = collectionGeneratorClient.get();
        assertEquals(size, collection.size());
    }
}
