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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public class LanguageChecker {

    public static Optional<LdLocale> getRecognisedLanguage(String text) throws IOException {
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();

        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .build();

        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();

        TextObject textObject = textObjectFactory.forText(text);

        return languageDetector.detect(textObject);
    }

    public static boolean isCorrectLanguageOnThePage(WebDriver driver, String lang) throws IOException {
        boolean isCorrectLang = true;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String bodyText = jse.executeScript("return document.body.innerText", "").toString();
        int textBlockLength = 300;
        int bodyTextLength = bodyText.length();

        if (bodyTextLength == 0) {
            System.out.println("\n!!! - Text on the page is absent\n");
            isCorrectLang = false;
        } else {
            for (int i = 0; i < bodyTextLength; i += textBlockLength) {
                String tempString;
                if (bodyTextLength >= (i + textBlockLength) ) {
                    tempString = bodyText.substring(i, i + textBlockLength);

                    try {
                        String detectedLanguage = getRecognisedLanguage(tempString.toLowerCase().replaceAll("[\\t|\\n|\\r|\\s]+", " ").replaceAll("[\\s]+", " ")).get().getLanguage();

                        if (!detectedLanguage.toLowerCase().equals(lang.toLowerCase())) {
                            System.out.println("\n!!! - Piece of text without translation: \n" + tempString + "\nExpected language is \"" + lang + "\"\n");
                            System.out.println("\n!!! Current URL is " + driver.getCurrentUrl() + "\n-  !!!");
                            System.out.println(String.format("\n!!! Characters are between %s and %s from %s full amount of characters \n", i, i + textBlockLength, bodyTextLength));
                            isCorrectLang = false;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("\n!!! - Impossible to recognise the language of this piece of text: \n" + tempString + "\nExpected language is \"" + lang + "\"\n");
                        System.out.println("\n!!! Current URL is " + driver.getCurrentUrl() + "\n-  !!!");
                        System.out.println(String.format("\n!!! Characters are between %s and %s from %s full amount of characters \n", i, i + textBlockLength, bodyTextLength));
                    }
                }
            }
        }
        return isCorrectLang;
    }
}
