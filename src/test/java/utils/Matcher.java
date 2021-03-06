package utils;

import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.readListFromJsonMvcResult;
import static utils.TestUtil.readMapFromJsonMvcResult;

public class Matcher<T> {
    private final Class<T> clazz;
    private final String[] fieldsToIgnore;
    private final boolean usingEquals;

    private Matcher(Class<T> clazz, boolean usingEquals, String... fieldsToIgnore) {
        this.clazz = clazz;
        this.fieldsToIgnore = fieldsToIgnore;
        this.usingEquals = usingEquals;
    }

    public static <T> Matcher<T> usingEquals(Class<T> clazz) {
        return new Matcher<>(clazz, true);
    }

    public static <T> Matcher<T> usingFieldsComparator(Class<T> clazz, String... fieldsToIgnore) {
        return new Matcher<>(clazz, false, fieldsToIgnore);
    }

    public void assertMatch(T actual, T expected) {
        if (usingEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).usingRecursiveComparison().ignoringFields(fieldsToIgnore).isEqualTo(expected);
        }
    }

    public void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        if (usingEquals) {
            assertThat(actual).isEqualTo(expected);
        } else {
            assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
        }
    }

    public ResultMatcher contentJson(T expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, clazz), expected);
    }

    public ResultMatcher contentJson(Iterable<T> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, clazz), expected);
    }

    public ResultMatcher contentJson(Map<String, Integer> expected) {
        return result -> assertMatch((Iterable) readMapFromJsonMvcResult(result).entrySet(),
                (Iterable) expected.entrySet());
    }
}

