import org.junit.jupiter.api.Test;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;
import ru.aston.homework_05.sort.ComparatorStrategy;
import ru.aston.homework_05.sort.comparators.EmailComparator;
import ru.aston.homework_05.sort.comparators.PasswordComparator;
import ru.aston.homework_05.sort.comparators.UserNameComparator;
import ru.aston.homework_05.sort.comparators.WorkSpaceNameComparator;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorStrategyTests {
    @Test
    void test_FactoryShouldReturnCorrectComparatorForUser() {
        Comparator<User> c = ComparatorStrategy.classFieldsSort(User.class, 1);
        assertInstanceOf(UserNameComparator.class, c);

        c = ComparatorStrategy.classFieldsSort(User.class, 2);
        assertInstanceOf(EmailComparator.class, c);

        c = ComparatorStrategy.classFieldsSort(User.class, 3);
        assertInstanceOf(PasswordComparator.class, c);
    }

    @Test
    void test_FactoryShouldThrowExceptionForInvalidField() {
        assertThrows(IllegalArgumentException.class, () -> ComparatorStrategy.classFieldsSort(User.class, 0));
        assertThrows(IllegalArgumentException.class, () -> ComparatorStrategy.classFieldsSort(User.class, 4));
        assertThrows(IllegalArgumentException.class, () -> ComparatorStrategy.classFieldsSort(User.class, 10));
    }

    @Test
    void test_UserNameComparatorShouldCompareByName() {
        User user1 = new User.Builder().addName("Liza").disableValidation().build();
        User user2 = new User.Builder().addName("Rob").disableValidation().build();
        User user3 = new User.Builder().addName("Liza").disableValidation().build();
        UserNameComparator c = new UserNameComparator();
        assertTrue(c.compare(user1, user2) < 0);
        assertTrue(c.compare(user2, user1) > 0);
        assertEquals(0, c.compare(user1, user3));
    }

    @Test
    void test_WorkSpaceNameComparatorShouldCompareByName() {
        WorkSpace w1 = new WorkSpace("Lizas", 1, 1);
        WorkSpace w2 = new WorkSpace("Robs", 2, 2);
        WorkSpace w3 = new WorkSpace("Lizas", 3, 3);
        WorkSpaceNameComparator c = new WorkSpaceNameComparator();
        assertTrue(c.compare(w1, w2) < 0);
        assertTrue(c.compare(w2, w1) > 0);
        assertEquals(0, c.compare(w1, w3));
    }
}
