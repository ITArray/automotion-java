package http.helpers;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

public class EnvironmentHelper {

    public void setEnv(Map<String, String> newenv) {
        Class[] classes = Collections.class.getDeclaredClasses();
        Map<String, String> env = System.getenv();
        for(Class cl : classes) {
            if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                Field field = null;
                try {
                    field = cl.getDeclaredField("m");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                assert field != null;
                field.setAccessible(true);
                Object obj = null;
                try {
                    obj = field.get(env);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Map<String, String> map = (Map<String, String>) obj;
                assert map != null;
                map.clear();
                map.putAll(newenv);
            }
        }
    }
}
