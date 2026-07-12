import org.junit.jupiter.api.Test;
import ru.aston.homework_05.models.User;
import ru.aston.homework_05.sort.MergeSort;
import ru.aston.homework_05.sort.comparators.EmailComparator;
import ru.aston.homework_05.sort.comparators.UserNameComparator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeSortingTests {
    @Test
    void testSortingByName() {
        List<User> users = new ArrayList<>();
        users.add(new User.Builder().addName("Alisa").addEmail("als@gmail.com").build());
        users.add(new User.Builder().addName("Boris").addEmail("brs@gmail.com").build());
        users.add(new User.Builder().addName("Vladislav").addEmail("vlad@gmail.com").build());
        users.add(new User.Builder().addName("Igor").addEmail("igr@gmail.com").build());
        users.add(new User.Builder().addName("Yana").addEmail("yana@gmail.com").build());

        MergeSort.sort(users, new UserNameComparator());
        assertEquals("Alisa", users.get(0).getName());
        assertEquals("Yana", users.get(4).getName());
    }

    @Test
    void testSortingByEmail() {
        List<User> users = new ArrayList<>();
        users.add(new User.Builder().addName("Alisa").addEmail("liza@gmail.com").build());
        users.add(new User.Builder().addName("Boris").addEmail("brs@gmail.com").build());
        users.add(new User.Builder().addName("Vladislav").addEmail("vlad@gmail.com").build());
        users.add(new User.Builder().addName("Igor").addEmail("eeeegor@gmail.com").build());
        users.add(new User.Builder().addName("Yana").addEmail("jana@gmail.com").build());

        MergeSort.sort(users, new EmailComparator());
        assertEquals("brs@gmail.com", users.get(0).getEmail());
        assertEquals("eeeegor@gmail.com", users.get(1).getEmail());
        assertEquals("vlad@gmail.com", users.get(4).getEmail());
    }

    @Test
    void testShouldHandleEmptyList() {
        List<User> empty = new ArrayList<>();
        MergeSort.sort(empty, new UserNameComparator());
        assertTrue(empty.isEmpty(), "Список должен быть пуст");
    }

    @Test
    void testShouldReverseOrder() {
        List<User> users = new ArrayList<>();
        users.add(new User.Builder().addName("Yana").addEmail("jana@gmail.com").build());
        users.add(new User.Builder().addName("Boris").addEmail("brs@gmail.com").build());
        users.add(new User.Builder().addName("Vladislav").addEmail("vlad@gmail.com").build());
        users.add(new User.Builder().addName("Igor").addEmail("eeeegor@gmail.com").build());
        users.add(new User.Builder().addName("Boris").addEmail("brs@gmail.com").build());
        users.add(new User.Builder().addName("Alisa").addEmail("liza@gmail.com").build());

        MergeSort.sort(users, new UserNameComparator());
        assertEquals("Alisa", users.get(0).getName());
        assertEquals("Yana", users.get(5).getName());
    }

}
