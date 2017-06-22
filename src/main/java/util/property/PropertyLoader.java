package util.property;

/**
 * @deprecated As of release 2.0, replaced by{@link net.itarray.automotion.tools.util.property.PropertyLoader}
 */
public class PropertyLoader {

    private final net.itarray.automotion.tools.util.property.PropertyLoader delegatee;

    public PropertyLoader(String propertyPath) {
        delegatee = new net.itarray.automotion.tools.util.property.PropertyLoader(propertyPath);
    }

    public String loadProperty(String name) {
        return delegatee.loadProperty(name);
    }
}