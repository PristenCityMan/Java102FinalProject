import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.aston.homework_05.validators.ValidationException;
import ru.aston.homework_05.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTests {
    @Test
    void when_givenValidUserEmail_thenUserHasAnEmail() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        User user = new User("Joe Doe", email);
        assertEquals(email, user.getEmail());
    }

    @Test
    void when_givenEmptyUserName_thenThrowValidationException() {
        assertThrows(ValidationException.class, () -> User.Builder.builder().build());
    }

    @Test
    void when_givenInvalidUserEmail_thenThrowValidationException() {
        assertThrows(ValidationException.class, () -> User.Builder.builder().addEmail("email").build());
    }
}
