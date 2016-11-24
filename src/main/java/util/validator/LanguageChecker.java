package util.validator;


import com.google.common.base.Optional;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public class LanguageChecker {

    private static final Logger LOG = Logger.getLogger(LanguageChecker.class);

    public static Optional<LdLocale> getRecognisedLanguage(String text) throws IOException {
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();

        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .build();

        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();

        TextObject textObject = textObjectFactory.forText(text);

        return languageDetector.detect(textObject);
    }

    public static Optional<LdLocale> getRecognisedLanguage(WebDriver driver) throws IOException {
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();

        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .build();

        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();

        TextObject textObject = textObjectFactory.forText(getTextFromPage(driver));

        return languageDetector.detect(textObject);
    }

    public static boolean isCorrectLanguageOnThePage(WebDriver driver, String lang) throws IOException {
        boolean isCorrectLang = true;
        String bodyText = getTextFromPage(driver);

        int textBlockLength = 300;
        int bodyTextLength = bodyText.length();

        if (bodyTextLength == 0) {
            LOG.info("\n!!! - Text on the page is absent\n");
            isCorrectLang = false;
        } else {
            for (int i = 0; i < bodyTextLength; i += textBlockLength) {
                String tempString;
                if (bodyTextLength >= (i + textBlockLength)) {
                    tempString = bodyText.substring(i, i + textBlockLength);
                    try {
                        String detectedLanguage = getRecognisedLanguage(tempString).get().getLanguage();

                        if (!detectedLanguage.toLowerCase().equals(lang.toLowerCase())) {
                            LOG.info("\n!!! - Piece of text without translation: \n" + tempString + "\nExpected language is \"" + lang + "\"\n");
                            LOG.info("\n!!! Current URL is " + driver.getCurrentUrl() + "\n-  !!!");
                            LOG.info(String.format("\n!!! Characters are between %s and %s from %s full amount of characters \n", i, i + textBlockLength, bodyTextLength));
                            isCorrectLang = false;
                            break;
                        }
                    } catch (Exception e) {
                        LOG.info("\n!!! - Impossible to recognise the language of this piece of text: \n" + tempString + "\nExpected language is \"" + lang + "\"\n");
                        LOG.info("\n!!! Current URL is " + driver.getCurrentUrl() + "\n-  !!!");
                        LOG.info(String.format("\n!!! Characters are between %s and %s from %s full amount of characters \n", i, i + textBlockLength, bodyTextLength));
                    }
                }
            }
        }
        return isCorrectLang;
    }

    private static String getTextFromPage(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String bodyText = jse.executeScript("return document.body.innerHTML", "").toString();
        bodyText = bodyText.replaceAll("<script\\b[^<]*(?:(?!</script>)<[^<]*)*</script>", " ")
                .replaceAll("<noscript\\b[^<]*(?:(?!</noscript>)<[^<]*)*</noscript>", " ")
                .replaceAll("<style\\b[^<]*(?:(?!</style>)<[^<]*)*</style>", " ")
                .replaceAll("<pre\\b[^<]*(?:(?!</pre>)<[^<]*)*</pre>", " ")
                .replaceAll("<[^>]*>", " ").toLowerCase()
                .replaceAll("[\\t|\\n|\\r|\\s]+", " ").replaceAll("[\\s]+", " ");

        return bodyText;
    }
}
