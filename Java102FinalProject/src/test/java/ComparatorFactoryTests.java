import org.junit.jupiter.api.Test;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.models.WorkSpace;
import ru.aston.homework_05.sort.ComparatorFactory;
import ru.aston.homework_05.sort.comparators.EmailComparator;
import ru.aston.homework_05.sort.comparators.PasswordComparator;
import ru.aston.homework_05.sort.comparators.UserNameComparator;
import ru.aston.homework_05.sort.comparators.WorkSpaceNameComparator;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorFactoryTests {
    @Test
    void test_FactoryShouldReturnCorrectComparatorForUser() {
        Comparator<User> c = ComparatorFactory.classFieldsSort(User.class, 1);
        assertTrue(c instanceof UserNameComparator);

        c = ComparatorFactory.classFieldsSort(User.class, 2);
        assertTrue(c instanceof EmailComparator);

        c = ComparatorFactory.classFieldsSort(User.class, 3);
        assertTrue(c instanceof PasswordComparator);
    }
    @Test
    void test_FactoryShouldThrowExceptionForInvalidField() {
        assertThrows(IllegalArgumentException.class, () -> ComparatorFactory.classFieldsSort(User.class, 0));
        assertThrows(IllegalArgumentException.class, () -> ComparatorFactory.classFieldsSort(User.class, 4));
        assertThrows(IllegalArgumentException.class, () -> ComparatorFactory.classFieldsSort(User.class, 10));
    }
    @Test
    void test_UserNameComparatorShouldCompareByName() {
        User user1 = new User.Builder().addName("Liza").build();
        User user2 = new User.Builder().addName("Rob").build();
        UserNameComparator c = new UserNameComparator();
        assertTrue(c.compare(user1, user2) < 0);
        assertTrue(c.compare(user1, user2) > 0);
        assertEquals(0, c.compare(user1, user2));
    }
    @Test
    void test_WorkSpaceNameComparatorShouldCompareByName() {
        WorkSpace w1 = new WorkSpace("Lizas", 1, 1);
        WorkSpace w2 = new WorkSpace("Robs", 2,2);
        WorkSpaceNameComparator c = new WorkSpaceNameComparator();
        assertTrue(c.compare(w1, w2) < 0);
        assertTrue(c.compare(w1, w2) > 0);
        assertEquals(0, c.compare(w1, w2));
    }
}
