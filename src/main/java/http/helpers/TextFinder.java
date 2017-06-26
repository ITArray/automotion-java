package http.helpers;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.helpers.TextFinder}
 */
@Deprecated
public class TextFinder {

    /**
     * Smart Text finder that allows to fins piece of corrupted text
     *
     * @param pattern
     * @param text
     * @return
     */
    public static boolean textIsFound(String pattern, String text) {
        return net.itarray.automotion.tools.helpers.TextFinder.textIsFound(pattern, text);
    }

    /**
     * Set derivation for text searching
     *
     * @param derivation
     */
    public static void setDerivation(int derivation) {
        net.itarray.automotion.tools.helpers.TextFinder.setDerivation(derivation);
    }
}