package http.helpers;

import java.util.Map;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.helpers.EnvironmentHelper}
 */
@Deprecated()
public class EnvironmentHelper {

    public static void setEnv(Map<String, String> newenv) {
        net.itarray.automotion.tools.helpers.EnvironmentHelper.setEnv(newenv);
    }

}
