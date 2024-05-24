package common.utility;

import java.util.List;
import java.util.stream.Stream;

/**
 * *Developer: ALireza Abolhasani
 * 2/25/2024
 * 8:59 AM
 **/


public abstract class AlirezaUtils {
    /**
     * @param list
     * @param <T>
     * @return stream list
     * Generic function to convert a list to stream
     * Convert List to stream
     * Stream<String> stream = AlirezaUtils.convertListToStream(list);
     */
    public  static <T> Stream<T> convertListToStream(List<T> list)
    {
        return list.stream();
    }

}
