package commandR.implementations;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class Guard {

    public static void AgainstNull(Object argument)
    {
        if (argument == null)
        {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED);
        }
    }

    public static void AgainstNullOrEmpty(Iterable<?> collection) {
        if (collection == null) {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED);
        }

        if (!collection.iterator().hasNext()) {
            throw new IllegalArgumentException("An empty collection is not valid.");
        }
    }
}
