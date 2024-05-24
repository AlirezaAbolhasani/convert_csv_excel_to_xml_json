package common.enumerable;

import java.util.HashMap;
import java.util.Map;

/**
 * @auteur ALireza Abolhasani
 * 2/24/2024
 * 3:39 PM
 **/


public enum AccountType {

    LOAN(1),
    CHEQUE(2),
    SHORT(3),
    LONG(4);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getIntValue(){
      return this.value;
    }
    private static final Map<Integer,AccountType> map;

    static {
        map = new HashMap<Integer,AccountType>();
        for (AccountType v : AccountType.values()) {
            map.put(v.value, v);
        }

    }

    public static AccountType findByKey(int i) {
        return map.get(i);
    }

}
