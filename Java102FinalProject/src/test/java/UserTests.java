import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.aston.homework_05.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {
    @Test
    void when_givenValidUserEmail_thenUserHasAnEmail() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        User user = new User("Joe Doe", email);
        assertEquals(email, user.getEmail());
    }
}
