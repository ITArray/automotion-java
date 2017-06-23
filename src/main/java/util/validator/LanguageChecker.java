package util.validator;


import com.google.common.base.Optional;
import com.optimaize.langdetect.i18n.LdLocale;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.helpers.LanguageChecker}
 */
@Deprecated
public class LanguageChecker {

    /**
     * Return recognized language on the page. E.g: 'en', 'es', ...
     *
     * @param text
     * @return
     * @throws IOException
     */
    public static Optional<LdLocale> getRecognisedLanguage(String text) throws IOException {
        return net.itarray.automotion.tools.helpers.LanguageChecker.getRecognisedLanguage(text);
    }

    /**
     * Return recognized language on the page. E.g: 'en', 'es', ...
     *
     * @param driver
     * @return
     * @throws IOException
     */
    public static Optional<LdLocale> getRecognisedLanguage(WebDriver driver) throws IOException {
        return net.itarray.automotion.tools.helpers.LanguageChecker.getRecognisedLanguage(driver);
    }

    /**
     * Verify if correct language on the web/mobile page
     *
     * @param driver
     * @param lang
     * @return
     * @throws IOException
     */
    public static boolean isCorrectLanguageOnThePage(WebDriver driver, String lang) throws IOException {
        return net.itarray.automotion.tools.helpers.LanguageChecker.isCorrectLanguageOnThePage(driver, lang);
    }

}
