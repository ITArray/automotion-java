package util.validator.properties;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.web.BaseWebMobileElement}
 */
@Deprecated
public class Padding extends net.itarray.automotion.validation.properties.Padding {

    public Padding(int padding) {
        super(padding);
    }

    public Padding(int top, int right, int bottom, int left) {
        super(top, right, bottom, left);
    }
}
