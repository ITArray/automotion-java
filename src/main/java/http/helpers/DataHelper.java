package http.helpers;

import java.util.HashMap;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.helpers.DataHelper}
 */
@Deprecated
public class DataHelper {

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    public DataHelper(HashMap<String, Object> map) {
        this.map = map;
    }

    /**
     * If the value is equal to {skip} then it will be skipped from adding
     */
    public static HashMap<String, Object> putIntoMap(String key, Object value) {
        if (!value.equals("{skip}")) {
            map.put(key, value);
        }

        return map;
    }
}
