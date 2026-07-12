import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import net.datafaker.Faker;
import ru.aston.homework_05.generators.BaseCollectionGenerator;
import ru.aston.homework_05.generators.CollectionGeneratorClient;
import ru.aston.homework_05.models.User;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ConsoleGenerationTests {
    @Mock
    BaseCollectionGenerator<User> placeholder;
    Faker faker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.faker = new Faker();
    }

    @Test
    void when_CollectorName_thenReturnCollectionWithName() {
        String name = faker.name().fullName();
        CollectionGeneratorClient<User> collectionGeneratorClient = new CollectionGeneratorClient<>(placeholder);
        Mockito.when(collectionGeneratorClient.get()).thenReturn(List.of(User.Builder.builder().addName(name).build()));
        Collection<User> collection = collectionGeneratorClient.get();
        String actualName = collection.stream()
                .map(User::getName)
                .findFirst()
                .orElse(null);
        assertEquals(name, actualName);
    }
}
