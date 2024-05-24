package common.enumerable;

import java.util.HashMap;
import java.util.Map;

/**
 * *Developer: ALireza Abolhasani
 * 2/24/2024
 * 8:58 PM
 **/


public enum CustomerType {
    REAL(1),
    LEGAL(2),
    SHARE(3);

    private final int value;

    CustomerType(int value){
        this.value = value;
    }

    public int getValue(int value){
        return value;
    }
    private static final Map<Integer,CustomerType> map;

    static {
        map = new HashMap<Integer,CustomerType>();
        for (CustomerType v : CustomerType.values()) {
            map.put(v.value, v);
        }

    }
    public static CustomerType findByKey(int i) {
        return map.get(i);
    }

}
