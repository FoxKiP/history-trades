package util;

import model.AbstractBaseEntity;
import org.springframework.util.Assert;

import javax.xml.bind.ValidationException;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String NAME_VALID_REGEX = "[\u0430-\u044F\u0410-\u042F\\d\\p{Space}]+?";

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        Assert.isTrue(found, "not found entity with id = " + id);
    }

    public static void checkForNull(Object object) {
        Assert.notNull(object, "object must not be null");
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if(!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new");
        }
    }

    public static void nameValidation(String name) throws ValidationException{
        if(!Pattern.matches(NAME_VALID_REGEX, name)) {
            throw new ValidationException("the name can only consist of Cyrillic, digit and spaces");
        }
    }
}
